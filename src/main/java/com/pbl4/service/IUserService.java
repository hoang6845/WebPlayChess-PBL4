package com.pbl4.service;

import java.util.ArrayList;

import com.pbl4.model.bean.UserModel;
import com.pbl4.paging.PageRequest;

public interface IUserService extends IService {
	UserModel findByUserNameAndPasswordAndStatus(String UserName, String password);

	ArrayList<UserModel> getListUser();

	ArrayList<UserModel> findUserByName(String mname);
	
	String findFullnameById(long id);
	
	UserModel FindUserById(long id);
	
	ArrayList<UserModel> getTop10UserRanks();
	boolean updatePassword(long userId,String oldPass , String newPassword);
	
	boolean updateName(long userId, String newName);
	
	 boolean update(long id, String fullname, String username, String password);
	
	String hashPassword(String password);
	
	boolean insert(String username, String password, String email);
	
	boolean insertBy(String username, String password, String email, String createby);
	
	ArrayList<UserModel> findAll(PageRequest pageRequest);
	
	int totalItems();

}
