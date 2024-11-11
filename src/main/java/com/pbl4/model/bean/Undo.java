package com.pbl4.model.bean;


public class Undo {
	int i;
	int PorC;
	Location begin = new Location();
	Location end = new Location();
	boolean checkQueen = false;
	public Undo(int i, int porC, Location begin, Location end, boolean checkQueen) {
		super();
		this.i = i;
		PorC = porC;
		this.begin = begin;
		this.end = end;
		this.checkQueen = checkQueen;
	}
	public Undo() {
		
	}
	public Undo(Undo u) {
		this.i = u.getI();
		PorC = u.getPorC();
		this.begin = u.getBegin();
		this.end = u.getEnd();
		this.checkQueen = u.checkQueen;
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public int getPorC() {
		return PorC;
	}
	public void setPorC(int porC) {
		PorC = porC;
	}
	public Location getBegin() {
		return begin;
	}
	public void setBegin(Location begin) {
		this.begin = begin;
	}
	public Location getEnd() {
		return end;
	}
	public void setEnd(Location end) {
		this.end = end;
	}
	public boolean isCheckQueen() {
		return checkQueen;
	}
	public void setCheckQueen(boolean checkQueen) {
		this.checkQueen = checkQueen;
	}
	
}
