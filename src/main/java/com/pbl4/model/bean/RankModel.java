package com.pbl4.model.bean;

import java.sql.Date;

public class RankModel extends AbstractModel<RankModel> {
	private long userId; 
	private int elo;
	private int totalMatches;
    private int win;   
    private int draws;  
    private int lose;  
    private int RankPosition;
    private double RankPositionPercentage;
    
    public int getRankPosition() {
		return RankPosition;
	}

	public void setRankPosition(int rankPosition) {
		RankPosition = rankPosition;
	}

	public double getRankPositionPercentage() {
		return RankPositionPercentage;
	}

	public void setRankPositionPercentage(double rankPositionPercentage) {
		RankPositionPercentage = rankPositionPercentage;
	}

	public RankModel(long id, Date createDate, String createBy, Date modifiedDate, String modifiedBy, long userId,
			int totalMatches, int win, int draws, int lose,int elo) {
		super(id, createDate, createBy, modifiedDate, modifiedBy);
		this.userId = userId;
		this.totalMatches = totalMatches;
		this.win = win;
		this.draws = draws;
		this.lose = lose;
		this.elo = elo;
	}
    
    public int getElo() {
		return elo;
	}

	public void setElo(int elo) {
		this.elo = elo;
	}

	public RankModel() {
    	
    }

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getTotalMatches() {
		return totalMatches;
	}

	public void setTotalMatches(int totalMatches) {
		this.totalMatches = totalMatches;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getDraws() {
		return draws;
	}

	public void setDraws(int draws) {
		this.draws = draws;
	}

	public int getLose() {
		return lose;
	}

	public void setLose(int lose) {
		this.lose = lose;
	}

	public double getWinRate() {
        return (double) (this.win) / totalMatches * 100; 
    }

}