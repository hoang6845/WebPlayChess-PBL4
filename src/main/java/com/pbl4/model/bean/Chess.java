package com.pbl4.model.bean;

import java.util.Stack;

public class Chess {
	public int value; // gia tri cua quan co 10,35,30,50,90,1000
	int index; // 1 la ben may,-1 la ben minh;
	public int isQueen = 0;

	Chess() {
	}

	public Chess(Chess other) {
		this.value = other.value;
		this.index = other.index;
		this.isQueen = other.isQueen;
		this.P = new Location(other.P.x, other.P.y);
		this.Pnew = (Stack<Location>) other.Pnew.clone();
	}

	Location P = new Location(); // vi tri hien tai
	Stack<Location> Pnew = new Stack<Location>(); // vi tri di chuyen toi duoc

	public void die() {
		this.value = -1;
	}

	public void setValue() {

	}

	public void alive() {
		setValue();
	}

	public int getValue() {
		return value * index;
	}

	public int getIndex() {
		return index;
	}

	public void BecomeQueen() {

	}
	
	public void ReverseBecomeQueen(){
		
	}

	public void setIndex(int i) {
		this.index = i;
	}

	public void setP(int m, int n) {
		this.P.x = m;
		this.P.y = n;
	}

	public Location getP() {
		return P;
	}

	public void updatePnew(int x1, int y1) {
		Location New = new Location();
		New.x = x1;
		New.y = y1;
		Pnew.push(New);
	}

	public void resetPnew() {
		while (!Pnew.empty()) {
			Pnew.pop();
		}
	}

	void PositiveMove(int[][] Board) {

	}

	public Stack<Location> ValidMove(int[][] Board) {
		resetPnew();
		PositiveMove(Board);
		return Pnew;
	}

	public int getIsQueen() {
		return isQueen;
	}

	public void setIsQueen(int isQueen) {
		this.isQueen = isQueen;
	}
	
}




