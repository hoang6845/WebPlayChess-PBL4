package com.pbl4.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pbl4.model.bean.FriendModel;
import com.pbl4.model.bean.RoleModel;
import com.pbl4.model.bean.UserModel;

public class FriendMapper implements RowMapper<FriendModel> {

	@Override
	public FriendModel mapRow(ResultSet rs) {
		FriendModel t = new FriendModel();
		try {
				long id = rs.getLong("id");
				long idUser = rs.getLong("idUser"); 
				long idFriend = rs.getLong("idFriend");
				String status = rs.getString("status");
				Date createDate = rs.getDate("createdate");
				Date modifiedDate = rs.getDate("modifieddate");
				String createBy = rs.getString("createby");
				String modifiedBy = rs.getString("modifiedby");
				String namefriend= rs.getString("fullname");
				String avatar = rs.getString("ImageOfUser");
				t = new FriendModel(id, createDate, createBy, modifiedDate, modifiedBy,idUser, idFriend, status, namefriend);
				t.setAvatarFriend(avatar);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

}
