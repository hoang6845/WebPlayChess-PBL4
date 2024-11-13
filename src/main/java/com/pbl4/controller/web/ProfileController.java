package com.pbl4.controller.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pbl4.model.bean.HistoryModel;
import com.pbl4.model.bean.ProfileModel;
import com.pbl4.model.bean.UserModel;
import com.pbl4.serviceImpl.HistoryService;
import com.pbl4.serviceImpl.ProfileService;
import com.pbl4.serviceImpl.RankService;
import com.pbl4.serviceImpl.UserService;
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
			ProfileModel Myprf = ProfileService.getInstance().findByUserId(model.getId());
			req.setAttribute("MYPRF", Myprf);
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
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line = new String();
		while ((line=reader.readLine())!=null) {
			System.out.println(line);
			sb.append(line);
		}
		System.out.println(sb);
		ObjectMapper objectmapper = new ObjectMapper();
		Map<String, String> data = objectmapper.readValue(sb.toString(), new TypeReference<Map<String,String>>(){});
		Map<String, String> respData = new HashMap<String, String>();
		String type = data.get("type");
		if (type.equals("ChangePassword")) {
			String oldPassword = data.get("oldPassword");
			String newPassword = data.get("newPassword");
			respData.put("type", "ChangePassword");
			
			if (UserService.getInstance().updatePassword(model.getId(), oldPassword, newPassword)) {
				respData.put("result", "success");
				objectmapper.writeValue(resp.getOutputStream(), respData);
			}else {
				respData.put("result", "fail");
				objectmapper.writeValue(resp.getOutputStream(), respData);
			}			
		}else if (type.equals("username")) {
			String newName = data.get("newValue");
			respData.put("type", "username");
			if (UserService.getInstance().updateName(model.getId(), newName)) {
				respData.put("result", "success");				
			}else {
				respData.put("result", "fail");	
			}
			objectmapper.writeValue(resp.getOutputStream(), respData);
			model.setFullname(newName);
			SessionUtil.getInstance().putValue(req, "USERMODEL", model);
		}else if (type.equals("description")) {
			String newDes = data.get("newValue");
			respData.put("type", "description");
			if (ProfileService.getInstance().updateDescription(model.getId(), newDes)) {
				respData.put("result", "success");				
			}else {
				respData.put("result", "fail");	
			}
			objectmapper.writeValue(resp.getOutputStream(), respData);

		}
		
		
	}

}
