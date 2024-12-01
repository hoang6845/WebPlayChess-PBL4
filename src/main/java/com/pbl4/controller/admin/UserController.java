package com.pbl4.controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import com.pbl4.SystemConstant.SystemConstant;
import com.pbl4.model.bean.ProfileModel;
import com.pbl4.model.bean.RoleModel;
import com.pbl4.model.bean.UserModel;
import com.pbl4.paging.PageRequest;
import com.pbl4.serviceImpl.ProfileService;
import com.pbl4.serviceImpl.UserService;
import com.pbl4.sorter.Sorter;
import com.pbl4.utils.FormUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/admin-user" })
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.print("da vao admin-user");
		// type=list&page=1&itemsInPage=4&sortName=id&sortBy=desc
		UserModel u = FormUtil.ToModel(UserModel.class, req);
		if (u.getType().equals(SystemConstant.LIST)) {
			PageRequest pageRequest = new PageRequest(u.getPage(), u.getItemsInPage(),
					new Sorter(u.getSortName(), u.getSortBy()));
			ArrayList<UserModel> listUser = UserService.getInstance().findAll(pageRequest);
			u.setTotalItems(UserService.getInstance().totalItems());
			u.setTotalPage((int) Math.ceil(u.getTotalItems() / u.getItemsInPage()));
			u.setListModel(listUser);
			req.setAttribute(SystemConstant.MODEL, u);
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/admin/new/list.jsp");
			rd.forward(req, resp);

		} else if (u.getType().equals(SystemConstant.EDIT)) {
			u = UserService.getInstance().FindUserById(u.getId());
			ProfileModel f = null ;
			if (u != null) {
				System.out.println("Đang edit: "+ u.getId());
				f = ProfileService.getInstance().findByUserId(u.getId());
				req.setAttribute("profile", f);
			} else {
				System.out.println("Đang insert: ");
			}
			ArrayList<RoleModel> Role = new ArrayList<RoleModel>();
			Role.add(new RoleModel("ADMIN", "Quản trị viên"));
			Role.add(new RoleModel("PLAYER", "Người chơi"));
			req.setAttribute("ROLE", Role);
			req.setAttribute(SystemConstant.MODEL, u);
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/admin/new/edit.jsp");
			rd.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}
}
