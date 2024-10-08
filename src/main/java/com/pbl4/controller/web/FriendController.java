package com.pbl4.controller.web;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pbl4.model.bean.FriendModel;
import com.pbl4.model.bean.UserModel;
import com.pbl4.model.bean.dataString;
import com.pbl4.serviceImpl.FriendService;
import com.pbl4.utils.HttpUtil;
import com.pbl4.utils.SessionUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/friend" })
public class FriendController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		String page = req.getParameter("page");
		if (model != null) {
			System.out.print("model!=null" + model.getId());
			model.setFriendList(FriendService.getInstance().getListFriend(model.getId()));
			SessionUtil.getInstance().putValue(req, "USERMODEL", model);
			if (page != null) {
				req.setAttribute("page", page);
				System.out.print(page);
			}

			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/web/friend.jsp");
			rd.forward(req, resp);
		} else {
			System.out.print("model null");
			resp.sendRedirect(req.getContextPath() + "/dang-nhap?action=login&message=not_login&alert=danger");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		System.out.print("da vao post friend");
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (model != null) {
			dataString friendName = new dataString();
			friendName = HttpUtil.of(req.getReader()).ToModel(dataString.class);
			ArrayList<FriendModel> ListSearchFriend = new ArrayList<FriendModel>();
			ListSearchFriend = FriendService.getInstance().getListSearchNewFr(model.getId(),friendName.getDataStr());
			req.setAttribute("ListSearchFriend", ListSearchFriend);
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(resp.getOutputStream(), ListSearchFriend);

		} else {
			System.out.print("model null");
			resp.sendRedirect(req.getContextPath() + "/dang-nhap?action=login&message=not_login&alert=danger");
		}
		
		
	}

}
