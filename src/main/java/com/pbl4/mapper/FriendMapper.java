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
				long idUser = rs.getLong("idUser"); //đang không dùng tới idUser sau này cần thì thêm vào
				long idFriend = rs.getLong("idFriend");
				String status = rs.getString("status");
				Date createDate = rs.getDate("createdate");
				Date modifiedDate = rs.getDate("modifieddate");
				String createBy = rs.getString("createby");
				String modifiedBy = rs.getString("modifiedby");
				String namefriend= rs.getString("fullname");
				t = new FriendModel(id, createDate, createBy, modifiedDate, modifiedBy, idFriend, status, namefriend);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

}
