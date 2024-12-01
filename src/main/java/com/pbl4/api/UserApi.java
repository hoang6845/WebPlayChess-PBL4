package com.pbl4.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pbl4.model.DAOImpl.UserDAO;
import com.pbl4.model.bean.ProfileModel;
import com.pbl4.model.bean.UserModel;
import com.pbl4.serviceImpl.ProfileService;
import com.pbl4.serviceImpl.UserService;
import com.pbl4.utils.HttpUtil;
import com.pbl4.utils.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/api-admin-user" })
public class UserApi extends HttpServlet {

	private static final long serialVersionUID = 1L;

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
		UserModel newUser = new UserModel();
		newUser.setFullname(data.get("fullname"));
		newUser.setUsername(data.get("username"));
		newUser.setPassword(data.get("password"));
		ProfileModel newProfile = new ProfileModel();
		newProfile.setEmail(data.get("email"));
		newProfile.setDescription(data.get("description"));
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		boolean check= UserService.getInstance().insertBy(newUser.getUsername(), newUser.getPassword(), newProfile.getEmail(), model.getFullname());
		Map<String , String> dataResp = new HashMap<String, String>();
		if (check==false) {
			dataResp.put("type", "fail");
			obj.writeValue(resp.getOutputStream(), dataResp);
			System.out.println("fail");
			return;
		}
		Long idthis=UserDAO.getInstance().findByUserName(newUser.getUsername()).getId();
		UserService.getInstance().updateName(idthis, newUser.getFullname());
		if (!newProfile.getDescription().equals("")) {
			ProfileService.getInstance().updateDescription(idthis, newProfile.getDescription());
		}
		dataResp.put("type", "success");
		obj.writeValue(resp.getOutputStream(), dataResp);
		System.out.print("chay xong api");	
	}

	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line = new String();
		while ((line=reader.readLine())!=null) {
			sb.append(line);
		}
		System.out.println(sb);
		ObjectMapper obj = new ObjectMapper();
		Map<String, String> data = obj.readValue(sb.toString(), new TypeReference<Map<String, String>>(){});
		UserModel newUser = new UserModel();
		newUser.setId(Long.parseLong(data.get("id")));
		newUser.setFullname(data.get("fullname"));
		newUser.setUsername(data.get("username"));
		if (!data.get("password").equals("")) newUser.setPassword(data.get("password"));
		boolean t=UserService.getInstance().update(newUser.getId(), newUser.getFullname(), newUser.getUsername(), newUser.getPassword());
		Map<String , String> dataResp = new HashMap<String, String>();
		if (!t) {
			dataResp.put("type", "fail");
			obj.writeValue(resp.getOutputStream(), dataResp);
			System.out.println("fail");
			return;
		}
		ProfileModel newProfile = ProfileService.getInstance().findByUserId(newUser.getId());
		newProfile.setEmail(data.get("email"));
		newProfile.setDescription(data.get("description"));
		newProfile.setModifiedBy(model.getFullname());
		ProfileService.getInstance().updateProfile(newProfile);
		dataResp.put("type", "success");
		obj.writeValue(resp.getOutputStream(), dataResp);
	}

	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
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
		Map<String, Long[]> data = obj.readValue(sb.toString(), new TypeReference<Map<String, Long[]>>(){});
		UserService.getInstance().delete(data.get("ids"));
		Map<String , String> dataResp = new HashMap<String, String>();
		dataResp.put("type", "done");
		obj.writeValue(resp.getOutputStream(), dataResp);
		System.out.print("chay xong api");	
	}

}
