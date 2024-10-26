package com.pbl4.model.bean;

import jakarta.websocket.Session;

public class SessionPlayer {
	Session session;
	String nameSession;
	long userId;
	
	public SessionPlayer(Session session, String nameSession, long userId) {
		super();
		this.session = session;
		this.nameSession = nameSession;
		this.userId = userId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public String getNameSession() {
		return nameSession;
	}
	public void setNameSession(String nameSession) {
		this.nameSession = nameSession;
	}
	public String getId() {
		// TODO Auto-generated method stub
		return session.getId();
	}
	
}
