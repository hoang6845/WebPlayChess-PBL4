package com.pbl4.controller.web;

import java.io.IOException;
import java.util.ArrayList;

import com.pbl4.model.bean.HistoryModel;
import com.pbl4.model.bean.UserModel;
import com.pbl4.serviceImpl.HistoryService;
import com.pbl4.serviceImpl.RankService;
import com.pbl4.utils.SessionUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/profile" })
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getSession());
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		String page = req.getParameter("page");
		if (model != null) {
			System.out.print("model!=null" + model.getId());
			ArrayList<HistoryModel> listHistory = HistoryService.getInstance().findAllByPlayerId(model.getId());
			req.setAttribute("listHistory", listHistory);
			if (page != null) {
				req.setAttribute("page", page);
				System.out.print(page);
			}

			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/web/profile.jsp");
			rd.forward(req, resp);
		} else {
			System.out.print("model null");
			resp.sendRedirect(req.getContextPath() + "/dang-nhap?action=login&message=not_login&alert=danger");
		}
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
	}

}
