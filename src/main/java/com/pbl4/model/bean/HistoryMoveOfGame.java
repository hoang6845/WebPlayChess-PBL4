package com.pbl4.model.bean;

import java.util.Stack;

public class HistoryMoveOfGame {
	private String id;
	private String whiteName;
	private String blackName;
	private String result;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	private Stack<Undo> u = new Stack<Undo>();
	public HistoryMoveOfGame(String id, Stack<Undo> u, String whiteName, String blackName) {
		super();
		this.id = id;
		this.u = u;
		this.whiteName = whiteName;
		this.blackName = blackName;
	}
	public String getWhiteName() {
		return whiteName;
	}
	public void setWhiteName(String whiteName) {
		this.whiteName = whiteName;
	}
	public String getBlackName() {
		return blackName;
	}
	public void setBlackName(String blackName) {
		this.blackName = blackName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Stack<Undo> getU() {
		return u;
	}
	public void setU(Stack<Undo> u) {
		this.u = u;
	}
	
}
