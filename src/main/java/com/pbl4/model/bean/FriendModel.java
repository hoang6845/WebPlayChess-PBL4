package com.pbl4.model.bean;

import java.sql.Date;

public class FriendModel extends AbstractModel<FriendModel>{
	private long idUser;
	private long idFriend;
	private String status;
	private String nameFriend;
	public String getNameFriend() {
		return nameFriend;
	}
	public void setNameFriend(String nameFriend) {
		this.nameFriend = nameFriend;
	}
	public FriendModel(long id, Date createDate, String createBy, Date modifiedDate, String modifiedBy,long idUser, long idFriend,
			String status, String nameFriend) {
		super(id, createDate, createBy, modifiedDate, modifiedBy);
		this.idFriend = idFriend;
		this.status = status;
		this.nameFriend = nameFriend;
		this.idUser=idUser;
	}
	public FriendModel() {
		super();
	}
	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	public long getIdFriend() {
		return idFriend;
	}
	public void setIdFriend(long idFriend) {
		this.idFriend = idFriend;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
