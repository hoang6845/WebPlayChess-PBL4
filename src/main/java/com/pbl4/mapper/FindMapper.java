package com.pbl4.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.pbl4.model.bean.FriendModel;

public class FindMapper implements RowMapper<FriendModel> {

	@Override
	public FriendModel mapRow(ResultSet rs) {
		// TODO Auto-generated method stub
		FriendModel t = new FriendModel();
		try {
				Long idFriend = rs.getLong("id");
				String namefriend = rs.getString("fullname");
				String status = rs.getString("status");
				t = new FriendModel(0, null, null, null, null,0, idFriend, status, namefriend);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

}
