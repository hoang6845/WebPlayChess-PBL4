package com.pbl4.controller.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pbl4.SystemConstant.SystemConstant;
import com.pbl4.model.bean.UserModel;
import com.pbl4.serviceImpl.ProfileService;
import com.pbl4.serviceImpl.RankService;
import com.pbl4.serviceImpl.UserService;
import com.pbl4.utils.FormUtil;
import com.pbl4.utils.SessionUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeController
 */
@WebServlet(urlPatterns = {"/trang-chu","/dang-nhap","/dang-ky"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	ResourceBundle rsbundle = ResourceBundle.getBundle("message");
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("GetHomeControllerWeb");
		System.out.println(req.getSession());
		String action = req.getParameter("action");
		String page = req.getParameter("page");
		UserModel modal= (UserModel)SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (modal!=null) {
			if (modal.getRole().getCodeRole().equals(SystemConstant.PLAYER)) {
				modal.setElo(RankService.getInstance().findByUserId(modal.getId()).getElo());
				SessionUtil.getInstance().putValue(req, "USERMODEL", modal);			
			}
		}
		if (action != null && action.equals("login")) {
			String message = req.getParameter("message");
			String alert = req.getParameter("alert");
		
		
			if (message != null && alert!=null) {
				req.setAttribute("message", rsbundle.getString(message));
				req.setAttribute("alert", alert);
				System.out.print(message+alert);
			}
			RequestDispatcher rd= req.getRequestDispatcher("/WEB-INF/views/login.jsp");
			rd.forward(req, resp);
		}else if (action!=null && action.equals("logout")) {
			System.out.println("out");
			SessionUtil.getInstance().removeValue(req, "USERMODEL");
			SessionUtil.getInstance().removeValue(req, "listMove");
			System.out.println("đã log out");
			resp.sendRedirect(req.getContextPath()+"/trang-chu");
		}else {
			System.out.print("vao roi");
			if (page != null) {
				req.setAttribute("page", page);
				System.out.print(page);
			}
			RequestDispatcher rd= req.getRequestDispatcher("/WEB-INF/views/web/home.jsp");
			rd.forward(req, resp);	
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.print("dang nhap thanh cong");
		System.out.println(req.getSession());
		String action = req.getParameter("action");
		System.out.println(action);
		Map<String, String[]> parameterMap = req.getParameterMap();
		for (String key : parameterMap.keySet()) {
		    System.out.println("Key: " + key + ", Value: " + Arrays.toString(parameterMap.get(key)));
		}

		if (action!= null && action.equals("login")) {
			UserModel model = FormUtil.ToModel(UserModel.class, req);
			System.out.print(model.getUsername()+model.getPassword());
			model = UserService.getInstance().findByUserNameAndPasswordAndStatus(model.getUsername(), model.getPassword());
			if (model!=null)System.out.print("toi la "+model.getRole().getCodeRole());
			if (model!=null) {
				if (model.getRole().getCodeRole().equals(SystemConstant.PLAYER))
					model.setElo(RankService.getInstance().findByUserId(model.getId()).getElo());
				model.setAvatar(ProfileService.getInstance().findByUserId(model.getId()).getImageOfUser());
				SessionUtil.getInstance().putValue(req, "USERMODEL", model);
				if (model.getRole().getCodeRole().equals(SystemConstant.PLAYER)) {
					resp.sendRedirect(req.getContextPath()+"/trang-chu?page=home");
				}else if (model.getRole().getCodeRole().equals(SystemConstant.ADMIN)) {
					System.out.print("vao admin");
					resp.sendRedirect(req.getContextPath()+"/admin-home");
				}
			}else {
				resp.sendRedirect(req.getContextPath()+"/dang-nhap?action=login&message=username_password_invalid&alert=danger");
			}
		}else {
//		Cách 1:
//			String email = req.getParameter("email");
//			System.out.println(email);
//		Cách 2:	
//			Map<String, String[]> data = req.getParameterMap();
//			System.out.println((data.get("username"))[0] );
//			String username = data.get("username")[0];
//			String password = data.get("password")[0];
//			String email = data.get("email")[0];
		// 2 cách trên sử dụng cho form post thông thường
			req.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
			BufferedReader reader = req.getReader();
			StringBuilder sb = new StringBuilder();
			String line= new String();
			while ((line=reader.readLine())!=null) {
				sb.append(line);
			}
			ObjectMapper obj = new ObjectMapper();
			Map<String, String> data = obj.readValue(sb.toString(), new TypeReference<Map<String, String>>(){});
			String type = data.get("type");
			if (type.equals("register")) {
				String username = data.get("username");
				String password = data.get("password");
				String email = data.get("email");
				Map<String, String> dataResp= new HashMap<String, String>();
				dataResp.put("type", "register");
				if (UserService.getInstance().insert(username, password, email)) {
					dataResp.put("result", "success");
				}else {
					dataResp.put("result", "fail");
				}
				obj.writeValue(resp.getOutputStream(), dataResp);
			}
		}
	}

}
