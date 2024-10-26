package com.pbl4.model.DAO;

import java.util.ArrayList;

import com.pbl4.model.bean.UserModel;

public interface IUserDAO extends DAOInterface<UserModel> {
	UserModel findByUserNameAndPasswordAndStatus(String UserName, String password);
	
	ArrayList<UserModel> getListUser();
	
	UserModel FindUserById(long id);
	
	ArrayList<UserModel> findUserByName(String mname);
	
	String findUserNameById(long id);
}
