package com.pbl4.model.DAOImpl;

import java.util.ArrayList;

import com.pbl4.SystemConstant.SystemConstant;
import com.pbl4.mapper.UserMapper;
import com.pbl4.model.DAO.IUserDAO;
import com.pbl4.model.bean.UserModel;



public class UserDAO extends DAOimple<UserModel> implements IUserDAO {
	public static UserDAO getInstance() {
		return new UserDAO();
	}

	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String username, String password) {
		StringBuilder sql = new StringBuilder("Select * from Userr U ");
		sql.append(" inner join Rolee r on r.id=U.idRole");
		sql.append(" where username=? and password = ? ");
		ArrayList<UserModel> ar= query(sql.toString(),new UserMapper(), username, password);
		return ar.isEmpty()?null:ar.get(0);
	}

	@Override
	public ArrayList<UserModel> getListUser() {
		String sql = "Select * from Userr where idRole = 1	";
		return query(sql,new UserMapper());
	}

	@Override
	public ArrayList<UserModel> findUserByName(String mname) {
		StringBuilder sql =  new StringBuilder("Select * from Userr");
		sql.append(" Where fullname like ? and idRole=?");
		ArrayList<UserModel> ar = query(sql.toString(), new UserMapper(),"%"+ mname+"%",2);
		return ar.isEmpty()?null:ar;
	}

	@Override
	public String findUserNameById(long id) {
		StringBuilder sql =  new StringBuilder("Select * from Userr U");
		sql.append(" inner join Rolee r on r.id=U.idRole");
		sql.append(" Where U.id = ? and codeRole=?");
		ArrayList<UserModel> ar = query(sql.toString(), new UserMapper(),id,SystemConstant.PLAYER);
		String fullname= ar.isEmpty()?null:ar.get(0).getFullname();
		return fullname;
	}

	@Override
	public UserModel FindUserById(long id) {
		StringBuilder sql =  new StringBuilder("Select * from Userr U");
		sql.append(" inner join Rolee r on r.id=U.idRole");
		sql.append(" Where U.id = ? and codeRole=?");
		ArrayList<UserModel> ar = query(sql.toString(), new UserMapper(),id,SystemConstant.PLAYER);
		return ar.isEmpty()?null:ar.get(0);
	}

	


	

	

}