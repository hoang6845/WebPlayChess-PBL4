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
}
