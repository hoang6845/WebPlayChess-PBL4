package com.pbl4.controller.web;

import java.io.IOException;
import java.util.Stack;

import com.google.gson.Gson;
import com.pbl4.model.bean.HistoryMoveOfGame;
import com.pbl4.model.bean.Undo;
import com.pbl4.model.bean.UserModel;
import com.pbl4.utils.SessionUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/historyMove"})
public class HistoryMoveController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub			
			UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL"); 
			if (model !=null) {
				HistoryMoveOfGame hmg = (HistoryMoveOfGame) SessionUtil.getInstance().getValue(req, "HistoryMoveOfGame");
				if (hmg!=null) {					
					System.out.print("hmg!=null"+model.getId());
					Stack<Undo> u = hmg.getU();
					Gson gson = new Gson();
			        String jsonStack = gson.toJson(u);
					req.setAttribute("StackMove", jsonStack);
					RequestDispatcher rd= req.getRequestDispatcher("/WEB-INF/views/web/history.jsp");
					rd.forward(req, resp);	
				}else {
					System.out.print("hmg null"+model.getId());
					resp.sendRedirect(req.getContextPath()+"/trang-chu?page=home");
				}
			}else {
				System.out.print("model null");
				resp.sendRedirect(req.getContextPath()+"/dang-nhap?action=login&message=not_login&alert=danger");
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
