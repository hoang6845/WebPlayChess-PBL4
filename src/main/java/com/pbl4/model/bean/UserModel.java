package com.pbl4.model.bean;

import java.sql.Date;
import java.util.ArrayList;

public class UserModel extends AbstractModel<UserModel> {
	private String fullname;
	private String username;
	private String password;
	private RoleModel Role;
	private ArrayList<FriendModel> FriendList;
	
	public ArrayList<FriendModel> getFriendList() {
		return FriendList;
	}
	public void setFriendList(ArrayList<FriendModel> friendList) {
		FriendList = friendList;
	}
	public RoleModel getRole() {
		return Role;
	}
	public void setRole(RoleModel role) {
		Role = role;
	}
	public UserModel(long id, Date createDate, String createBy, Date modifiedDate, String modifiedBy, String fullname,
			String username, String password, RoleModel Role) {
		super(id, createDate, createBy, modifiedDate, modifiedBy);
		this.fullname = fullname;
		this.username = username;
		this.password = password;
		this.Role = Role;
	}
	public UserModel() {
		super();
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	
}
