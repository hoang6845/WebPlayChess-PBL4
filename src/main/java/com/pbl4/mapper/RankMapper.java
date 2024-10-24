package com.pbl4.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import com.pbl4.model.bean.RankModel;

public class RankMapper implements RowMapper<RankModel> {

    @Override
    public RankModel mapRow(ResultSet rs) {
        RankModel rankModel = new RankModel();
        try {
            long id = rs.getLong("id");  
            long elo = rs.getLong("elo");
            long win = rs.getLong("win");
            long lose = rs.getLong("lose");
            long matches = rs.getLong("matches"); 

            Date createDate = rs.getDate("createdate");
            Date modifiedDate = rs.getDate("modifieddate");
            String createBy = rs.getString("createby");
            String modifiedBy = rs.getString("modifiedby");

            rankModel = new RankModel();
            rankModel.setId(id); 
            rankModel.setElo(elo);
            rankModel.setWin(win);
            rankModel.setLose(lose);
            rankModel.setMatches(matches);
            rankModel.setCreateDate(createDate);
            rankModel.setCreateBy(createBy);
            rankModel.setModifiedDate(modifiedDate);
            rankModel.setModifiedBy(modifiedBy);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rankModel;
    }
}
