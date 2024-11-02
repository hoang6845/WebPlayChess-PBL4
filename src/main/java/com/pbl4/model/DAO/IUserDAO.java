package com.pbl4.model.DAO;

import java.util.ArrayList;

import com.pbl4.model.bean.UserModel;

public interface IUserDAO extends DAOInterface<UserModel> {

	ArrayList<UserModel> getListUser();
	
	UserModel FindUserById(long id);
	
	ArrayList<UserModel> findUserByName(String mname);
	
	String findUserNameById(long id);
	
	boolean updateUser(UserModel user);
	
	ArrayList<UserModel> findByUserName(String userName);
}
