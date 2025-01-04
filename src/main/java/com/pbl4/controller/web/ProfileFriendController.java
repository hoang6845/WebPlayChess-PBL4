package com.pbl4.controller.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import com.pbl4.model.bean.HistoryModel;
import com.pbl4.model.bean.ProfileModel;
import com.pbl4.model.bean.UserModel;
import com.pbl4.serviceImpl.HistoryService;
import com.pbl4.serviceImpl.ProfileService;
import com.pbl4.serviceImpl.UserService;
import com.pbl4.utils.SessionUtil;


@WebServlet("/profilefriend")
public class ProfileFriendController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getSession());
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		long id = Long.parseLong(req.getParameter("id"));
		UserModel friendModel = UserService.getInstance().FindUserById(id);
		friendModel.setAvatar(ProfileService.getInstance().findByUserId(id).getImageOfUser());
		if (friendModel!=null) req.setAttribute("FRIENDMODEL", friendModel);
		if (model != null) {
			System.out.print("model!=null" + model.getId());
			ArrayList<HistoryModel> listHistory = HistoryService.getInstance().findAllByPlayerId(id);
			req.setAttribute("listHistory", listHistory);
			ProfileModel Myprf = ProfileService.getInstance().findByUserId(id);
			req.setAttribute("MYPRF", Myprf);
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/web/friend_profile.jsp");
			rd.forward(req, resp);
		} else {
			System.out.print("model null");
			resp.sendRedirect(req.getContextPath() + "/dang-nhap?action=login&message=not_login&alert=danger");
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
