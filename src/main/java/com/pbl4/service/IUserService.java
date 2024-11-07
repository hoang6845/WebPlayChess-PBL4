package com.pbl4.service;

import java.util.ArrayList;

import com.pbl4.model.bean.UserModel;

public interface IUserService extends IService {
	UserModel findByUserNameAndPasswordAndStatus(String UserName, String password);

	ArrayList<UserModel> getListUser();

	ArrayList<UserModel> findUserByName(String mname);
	
	String findUserNameById(long id);
	
	UserModel FindUserById(long id);
	
	ArrayList<UserModel> getTop10UserRanks();
	boolean updatePassword(long userId, String newPassword);
	
	boolean updateName(long userId, String newName);
	
	String hashPassword(String password);
	
	boolean insert(String username, String password, String email);

}
