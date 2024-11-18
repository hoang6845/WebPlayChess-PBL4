package com.pbl4.model.bean;

import java.util.ArrayList;

public class Message {
	private String room;
	private String type;
	private String sender;
	private String content;
	private UserModel whiteModel;
	private UserModel blackModel;
	private ArrayList<ChessInfo> pieceArr;
	
	
	//friend
	private long userId;
	private long friendId;
	private String friendName;
	private String userName;
	
	private String img;
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Message(String room, String type, String sender, String content) {
		super();
		this.room = room;
		this.type = type;
		this.sender = sender;
		this.content = content;
	}
	
	public Message(String room, String type, String sender, String content, UserModel whiteModel, UserModel blackModel) {
		super();
		this.room = room;
		this.type = type;
		this.sender = sender;
		this.content = content;
		this.whiteModel = whiteModel;
		this.blackModel = blackModel;
	}
	
	public Message(String room, String type, String sender, String content, UserModel whiteModel, UserModel blackModel, ArrayList<ChessInfo> pieceArr) {
		super();
		this.room = room;
		this.type = type;
		this.sender = sender;
		this.content = content;
		this.whiteModel = whiteModel;
		this.blackModel = blackModel;
		this.pieceArr = pieceArr;
	}

	public Message(String content, long userId, long friendId) {
		super();
		this.content = content;
		this.userId = userId;
		this.friendId = friendId;
	}
	
	public Message(String content, long userId, long friendId, String img) {
		super();
		this.content = content;
		this.userId = userId;
		this.friendId = friendId;
		this.img = img;
	}

	
	
	public Message(String content, long userId, long friendId,  String userName,String friendName) {
		super();
		this.content = content;
		this.userId = userId;
		this.friendId = friendId;
		this.friendName = friendName;
		this.userName = userName;
	}
	
	public Message(String content, long userId, long friendId,  String userName,String friendName, String img) {
		super();
		this.content = content;
		this.userId = userId;
		this.friendId = friendId;
		this.friendName = friendName;
		this.userName = userName;
		this.img = img;
	}

	public Message() {
		
	}
	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getFriendId() {
		return friendId;
	}

	public void setFriendId(long friendId) {
		this.friendId = friendId;
	}
	
	
	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
