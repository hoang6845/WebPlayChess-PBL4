package com.pbl4.model.bean;

public class Message {
	private String room;
	private String type;
	private String sender;
	private String content;
	private UserModel whiteModel;
	private UserModel blackModel;
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



	public Message() {
		
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
