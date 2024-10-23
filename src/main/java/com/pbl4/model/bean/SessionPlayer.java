package com.pbl4.model.bean;

import jakarta.websocket.Session;

public class SessionPlayer {
	Session session;
	String nameSession;
	
	
	public SessionPlayer(Session session, String nameSession) {
		super();
		this.session = session;
		this.nameSession = nameSession;
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
