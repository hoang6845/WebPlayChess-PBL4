package com.pbl4.model.DAO;

import java.util.ArrayList;

import com.pbl4.model.bean.UserModel;
import com.pbl4.paging.PageRequest;

public interface IUserDAO extends DAOInterface<UserModel> {

	ArrayList<UserModel> getListUser();
	
	UserModel FindUserById(long id);
	
	ArrayList<UserModel> findUserByName(String mname);
	
	String findFullnameById(long id);
	
	boolean updateUser(UserModel user);
	
	boolean update(long id, String fullname, String username, String password);
	
	UserModel findByUserName(String userName);
	
	void insert(String username, String password);
	
	public void insertBy(String username, String password, String createby);
	
	ArrayList<UserModel> findAll(PageRequest pageRequest);
	
	int countItems();
	void delete(long id);
}
