package com.pbl4.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pbl4.model.bean.RankModel;

public class RankMapper implements RowMapper<RankModel> {

    @Override
    public RankModel mapRow(ResultSet rs) {
    	RankModel newRankModel = new RankModel();
        try {
        	long id = rs.getLong("id");
            long userId = rs.getLong("userId");
            int win = rs.getInt("win");
            int lose = rs.getInt("lose");
            int draws = rs.getInt("draws"); 
            int elo = rs.getInt("elo");
            int totalMatches = rs.getInt("totalMatches");
        	Date createDate = rs.getDate("createdate");
			Date modifiedDate = rs.getDate("modifieddate");
			String createBy = rs.getString("createby");
			String modifiedBy = rs.getString("modifiedby");
			newRankModel = new RankModel(id, createDate, createBy, modifiedDate, modifiedBy, userId, totalMatches, win, draws, lose,elo);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newRankModel;
    }
}