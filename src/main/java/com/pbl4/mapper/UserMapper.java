package com.pbl4.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pbl4.model.bean.RoleModel;
import com.pbl4.model.bean.UserModel;

public class UserMapper implements RowMapper<UserModel> {

	@Override
	public UserModel mapRow(ResultSet rs) {
		UserModel t = new UserModel();
		try {
				String userName = rs.getString("username");
				String fullName = rs.getString("fullname");
				String password = rs.getString("password");
				long id = rs.getLong("id");
				Date createDate = rs.getDate("createdate");
				Date modifiedDate = rs.getDate("modifieddate");
				String createBy = rs.getString("createby");
				String modifiedBy = rs.getString("modifiedby");
				RoleModel role= new RoleModel();
				try {
					role.setCodeRole(rs.getString("codeRole"));
					role.setNameRole(rs.getString("nameRole"));					
				}catch (Exception e) {
					System.out.print(e.getMessage());
				}
				t = new UserModel( id,  createDate,  createBy,  modifiedDate,  modifiedBy,  fullName,
						userName,  password,  role);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

}
