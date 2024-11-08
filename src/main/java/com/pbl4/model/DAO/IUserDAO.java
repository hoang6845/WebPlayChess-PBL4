package com.pbl4.model.DAO;

import java.util.ArrayList;

import com.pbl4.model.bean.UserModel;

public interface IUserDAO extends DAOInterface<UserModel> {

	ArrayList<UserModel> getListUser();
	
	UserModel FindUserById(long id);
	
	ArrayList<UserModel> findUserByName(String mname);
	
	String findFullnameById(long id);
	
	boolean updateUser(UserModel user);
	
	UserModel findByUserName(String userName);
	
	void insert(String username, String password);
}
