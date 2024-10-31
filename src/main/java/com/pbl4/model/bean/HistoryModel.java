package com.pbl4.model.bean;

import java.sql.Date;

public class HistoryModel extends AbstractModel<HistoryModel> {
    private long id;               
    private long playerId;         
    private long opponentId;      
    private String result;         
    private int eloChange;         

    public HistoryModel() {
    }

    public HistoryModel(long id, long playerId, long opponentId, String result, Date createDate,
                        String createBy, Date modifiedDate, String modifiedBy, int eloChange) {
    	super(id, createDate, createBy, modifiedDate, modifiedBy);
        this.playerId = playerId;
        this.opponentId = opponentId;
        this.result = result;
        this.eloChange = eloChange; 
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public long getOpponentId() {
        return opponentId;
    }

    public void setOpponentId(long opponentId) {
        this.opponentId = opponentId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getEloChange() {
        return eloChange;
    }

    public void setEloChange(int eloChange) {
        this.eloChange = eloChange; 
    }
    public int calculateEloChange(long playerId) {
        if (this.playerId == playerId) {
            return eloChange; 
        } else if (this.opponentId == playerId) {
            return -eloChange; 
        }
        return 0; 
    }
    public long getOpId(long playerId) {
        if (this.playerId == playerId) {
            return  this.opponentId; 
        } else if (this.opponentId == playerId) {
            return  this.playerId; 
        }
        return 0; 
    }
    public String getResultt(long playerId) {
        if (this.playerId != playerId) {
            if ("win".equals(this.result)) {
                return "lost";
            } else if ("lost".equals(this.result)) {
                return "win";
            }
        }
        return this.result;
    }

    
}
