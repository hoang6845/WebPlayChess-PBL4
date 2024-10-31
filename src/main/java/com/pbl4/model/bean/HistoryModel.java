package com.pbl4.model.bean;

import java.sql.Date;

public class HistoryModel extends AbstractModel<HistoryModel> {
    private long id;               
    private long whiteId;         
    private long blackId;      
    private String result;         
    private int eloChange;
    private String opponentName;

    public long getWhiteId() {
		return whiteId;
	}

	public void setWhiteId(long whiteId) {
		this.whiteId = whiteId;
	}

	public long getBlackId() {
		return blackId;
	}

	public void setBlackId(long blackId) {
		this.blackId = blackId;
	}

	public String getOpponentName() {
		return opponentName;
	}

	public void setOpponentName(String opponentName) {
		this.opponentName = opponentName;
	}

	public HistoryModel() {
    }

    public HistoryModel(long id, long whiteId, long blackId, String result, Date createDate,
                        String createBy, Date modifiedDate, String modifiedBy, int eloChange) {
    	super(id, createDate, createBy, modifiedDate, modifiedBy);
        this.whiteId = whiteId;
        this.blackId = blackId;
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
    public int calculateEloChange(long Id) {
        if (this.whiteId == Id) {
            return eloChange; 
        } else if (this.blackId == Id) {
            return -eloChange; 
        }
        return 0; 
    }
    public long getOpId(long Id) {
        if (this.whiteId == Id) {
            return  this.blackId; 
        } else if (this.blackId == Id) {
            return  this.whiteId; 
        }
        return 0; 
    }
    public String getResult(long Id) {
        if (this.whiteId != Id) {
            if ("win".equals(this.result)) {
                return "lose";
            } else if ("lose".equals(this.result)) {
                return "win";
            }
        }
        return this.result;
    }

    
}
