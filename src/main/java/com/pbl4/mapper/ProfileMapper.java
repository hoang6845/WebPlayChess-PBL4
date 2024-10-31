package com.pbl4.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pbl4.model.bean.ProfileModel;

public class ProfileMapper implements RowMapper<ProfileModel> {

    @Override
    public ProfileModel mapRow(ResultSet rs) {
        ProfileModel profile = new ProfileModel();
        try {
            long id = rs.getLong("id");
            long userId = rs.getLong("userId"); 
            String imageOfUser = rs.getString("imageOfUser");
            String description = rs.getString("description");
            String email = rs.getString("email");
            Date createDate = rs.getDate("createdate");
            Date modifiedDate = rs.getDate("modifieddate");
            String createBy = rs.getString("createby");
            String modifiedBy = rs.getString("modifiedby");
            profile = new ProfileModel(id, createDate, createBy, modifiedDate, modifiedBy, userId, imageOfUser, description, email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profile;
    }
}
