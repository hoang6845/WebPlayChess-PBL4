package com.pbl4.controller.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pbl4.GamePlay.GameModeAI;
import com.pbl4.model.bean.Chess;
import com.pbl4.model.bean.HistoryMoveOfGame;
import com.pbl4.model.bean.UserModel;
import com.pbl4.utils.SessionUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/PvC"})
public class PvCController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL"); 
		String level = req.getParameter("type");
		req.setAttribute("level", level);
		System.out.println("Độ khó là :"+level);
		SessionUtil.getInstance().removeValue(req, "AIGame");
		if (model !=null) {
			
			System.out.print("model!=null"+model.getId());
			RequestDispatcher rd= req.getRequestDispatcher("/WEB-INF/views/web/PvC.jsp");
			rd.forward(req, resp);	
		}else {
			System.out.print("model null");
			resp.sendRedirect(req.getContextPath()+"/dang-nhap?action=login&message=not_login&alert=danger");
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line = new String();
		while ((line=reader.readLine())!=null) {
			sb.append(line);
		}
		System.out.println(sb);
		ObjectMapper obj = new ObjectMapper();
		Map<String, String> data = obj.readValue(sb.toString(), new TypeReference<Map<String, String>>(){});
		String type = data.get("type");
		GameModeAI aiGame = new GameModeAI();
		if (req.getSession().getAttribute("AIGame")==null) {
			aiGame.begin();
		}else {
			 aiGame =  (GameModeAI) SessionUtil.getInstance().getValue(req, "AIGame");
		}
		if (type.equals("move")) {
			String level = data.get("level");
			int depth = 0;
			if (level.equals("easy")) {
				depth=2;
			}else if (level.equals("medium")) {
				depth=3;
			}else if (level.equals("hard")) {
				depth=4;
			}
			String pieceId = data.get("pieceId");
			String destinationSquareId = data.get("destinationSquareId");
			System.out.println(pieceId+destinationSquareId);
			ArrayList<Integer> dt = ConvertChessBoardToBoard(pieceId, destinationSquareId);
			aiGame.MoveB(dt.get(0), dt.get(1), dt.get(2));
			System.out.println("nuoc di la:"+dt.get(0)+"->"+ dt.get(1)+" "+ dt.get(2));
			aiGame.moveOder(aiGame.Board, -10000, 10000, depth, depth, true, aiGame.mgr.Computer, aiGame.mgr.Player);
			System.out.println(aiGame.mgr.MoveOderCurent+" "+ aiGame.mgr.MoveOderNext.getX()+aiGame.mgr.MoveOderNext.getY());
			Map<String , Integer> dataResp = new HashMap<String, Integer>();
			dataResp.put("Chess", aiGame.mgr.MoveOderCurent);
			dataResp.put("toX", aiGame.mgr.MoveOderNext.getX());
			dataResp.put("toY", aiGame.mgr.MoveOderNext.getY());
			dataResp.put("type", 100);
			obj.writeValue(resp.getOutputStream(), dataResp);
//			aiGame.banco();
//			for(int i=0;i<16;i++) {
//				Chess Computer2[] = new Chess[16];
//				aiGame.CopyChess(aiGame.mgr.Computer, Computer2);
//				System.out.println(Computer2[i].getValue());
//			}
			aiGame.MoveA();
//			aiGame.banco();
			SessionUtil.getInstance().putValue(req, "AIGame", aiGame);
		}else if (type.equals("acceptLose")) {
			HistoryMoveOfGame hmg = new HistoryMoveOfGame("0", aiGame.getU(), "acceptLose");
			SessionUtil.getInstance().putValue(req, "HistoryMoveOfGame", hmg);
			SessionUtil.getInstance().removeValue(req, "AIGame");
			Map<String, String> dataResp = new HashMap<String, String>();
			dataResp.put("type", "acceptLose");
			obj.writeValue(resp.getOutputStream(), dataResp);
		}else if (type.equals("undo")) {
			obj.writeValue(resp.getOutputStream(), aiGame.undo());
			SessionUtil.getInstance().putValue(req, "AIGame", aiGame);
		}else if (type.equals("win")||type.equals("lose")) {
			HistoryMoveOfGame hmg = new HistoryMoveOfGame("0", aiGame.getU(), type);
			SessionUtil.getInstance().putValue(req, "HistoryMoveOfGame", hmg);
			SessionUtil.getInstance().removeValue(req, "AIGame");
			Map<String, String> dataResp = new HashMap<String, String>();
			dataResp.put("type", type);
		}
	}
	
	public ArrayList<Integer> ConvertChessBoardToBoard(String chessMove, String moveTo) {
		ArrayList<Integer> data = new ArrayList<Integer>();
		int chessMoveX = chessMove.charAt(chessMove.length() - 2) - 'a';
		int chessMoveY = chessMove.charAt(chessMove.length() - 1) - '0';
		chessMoveY = 8 - (chessMoveY);
		if (chessMoveY == 0 || chessMoveY == 1) {
			System.out.println("-----------Quân đen");
		} else if (chessMoveY == 6 || chessMoveY == 7) {
			System.out.println("-----------Quân trắng");
		} else {
			System.out.println("----------------Lỗi");
		}
		int moveToX = moveTo.charAt(moveTo.length() - 2) - 'a';
		int moveToY = moveTo.charAt(moveTo.length() - 1) - '0';
		moveToY = 8 - moveToY;
		switch (chessMoveY) {
		case 1:
		case 6:
			data.add(chessMoveX);
			break;
		case 0:
		case 7:
			switch (chessMoveX) {
			case 0:
				data.add(8);
				break;
			case 7:
				data.add(9);
				break;
			case 1:
				data.add(10);
				break;
			case 6:
				data.add(11);
				break;
			case 2:
				data.add(12);
				break;
			case 5:
				data.add(13);
				break;
			case 3:
				data.add(14);
				break;
			case 4:
				data.add(15);
				break;
			}
		}
		data.add(moveToX);
		data.add(moveToY);
		System.out.println("_______" + data.size() + ": " + "quân thứ " + data.get(0) + " đi tới: " + data.get(1) + " "
				+ data.get(2));
		return data;
	}
}

