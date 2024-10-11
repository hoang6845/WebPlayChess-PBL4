package com.pbl4.controller.web;

import java.io.IOException;

import com.pbl4.model.bean.UserModel;
import com.pbl4.utils.SessionUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/PvP"})
public class PlayChessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL"); 
		if (model !=null) {
			System.out.print("model!=null"+model.getId());
			RequestDispatcher rd= req.getRequestDispatcher("/WEB-INF/views/web/PvP.jsp");
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

