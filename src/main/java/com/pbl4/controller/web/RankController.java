package com.pbl4.controller.web;

import java.io.IOException;
import java.util.ArrayList;

import com.pbl4.model.bean.RankModel;
import com.pbl4.model.bean.UserModel;
import com.pbl4.serviceImpl.RankService;
import com.pbl4.serviceImpl.UserService;
import com.pbl4.utils.SessionUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/rank"})
public class RankController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub			
			UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL"); 
			String page = req.getParameter("page");
			if (model !=null) {
				System.out.print("model!=null"+model.getId());
				RankModel MyRankModel = RankService.getInstance().findByUserId(model.getId());
				MyRankModel.setRankPosition(RankService.getInstance().findRankPosition(model.getId()));
				System.out.println(MyRankModel.getRankPosition());
				MyRankModel.setRankPositionPercentage(model.getId());
				req.setAttribute("RANKMODEL",MyRankModel);
				
				ArrayList<UserModel> PlayerRanking = UserService.getInstance().getTop10UserRanks();
				req.setAttribute("PLAYERRANKING", PlayerRanking);
				req.setAttribute("page", page);
				RequestDispatcher rd= req.getRequestDispatcher("/WEB-INF/views/web/rank.jsp");
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
		
	}

}
