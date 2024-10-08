package com.pbl4.serviceImpl;

import java.util.ArrayList;

import com.pbl4.model.DAOImpl.UserDAO;
import com.pbl4.model.bean.UserModel;
import com.pbl4.service.IUserService;

public class UserService implements IUserService {

	public static UserService getInstance() {
		return new UserService();
	}

	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String UserName, String password) {
		// TODO Auto-generated method stub
		return UserDAO.getInstance().findByUserNameAndPasswordAndStatus(UserName, password);
	}

	@Override
	public ArrayList<UserModel> getListUser() {
		// TODO Auto-generated method stub
		return UserDAO.getInstance().getListUser();
	}

	@Override
	public ArrayList<UserModel> findUserByName(String mname) {
		// TODO Auto-generated method stub
		return UserDAO.getInstance().findUserByName(mname);
	}

}
