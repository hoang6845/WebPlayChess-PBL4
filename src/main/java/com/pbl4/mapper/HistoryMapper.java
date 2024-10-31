package com.pbl4.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pbl4.model.bean.HistoryModel;

public class HistoryMapper implements RowMapper<HistoryModel> {

    @Override
    public HistoryModel mapRow(ResultSet rs) {
        HistoryModel history = null;
        try {
            long id = rs.getLong("id");
            long whiteId = rs.getLong("whiteId");
            long blackId = rs.getLong("blackId");
            String result = rs.getString("result");
            Date createDate = rs.getDate("createdate");
            Date modifiedDate = rs.getDate("modifieddate");
            String createBy = rs.getString("createby");
            String modifiedBy = rs.getString("modifiedby");
            int eloChange = rs.getInt("eloChange"); 
            history = new HistoryModel(id, whiteId, blackId, result, createDate, createBy, modifiedDate, modifiedBy, eloChange); // Thêm eloChange vào constructor
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }
}
