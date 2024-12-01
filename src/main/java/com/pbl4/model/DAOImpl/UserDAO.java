package com.pbl4.model.DAOImpl;

import java.util.ArrayList;

import com.pbl4.SystemConstant.SystemConstant;
import com.pbl4.mapper.UserMapper;
import com.pbl4.model.DAO.IUserDAO;
import com.pbl4.model.bean.UserModel;
import com.pbl4.paging.PageRequest;



public class UserDAO extends DAOimple<UserModel> implements IUserDAO {
	public static UserDAO getInstance() {
		return new UserDAO();
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
	public String findFullnameById(long id) {
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
	@Override
	public boolean updateUser(UserModel user) {
	    String sql = "UPDATE Userr SET username = ?, password = ?, fullname = ?, createdate = ?, modifieddate = ?, createby = ?, modifiedby = ? " +
	                 "WHERE id = ?";
	    return update(sql, user.getUsername(), user.getPassword(), user.getFullname(), 
	                   user.getCreateDate(), user.getModifiedDate(), user.getCreateBy(),
	                  user.getModifiedBy(), user.getId()) > 0;
	}
	//de check mat khau nen username=?
	@Override
	public UserModel findByUserName(String userName) {
	    StringBuilder sql = new StringBuilder("SELECT * FROM Userr U ");
	    sql.append("INNER JOIN Rolee r ON r.id = U.idRole ");
	    sql.append("WHERE username = ?");
	    
	    ArrayList<UserModel> userList = query(sql.toString(), new UserMapper(), userName);
	    return userList.isEmpty() ? null : userList.get(0); 
	}
	@Override
	public void insert(String username, String password) {
	    String sql = "INSERT INTO Userr (username, password, fullname, createdate, modifieddate, createby, modifiedby, idRole) " +
	                 "VALUES (?, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?)";
	    insert(sql, username, password, username, null ,null, null, 2);
	}
	
	@Override
	public void insertBy(String username, String password, String createby) {
	    String sql = "INSERT INTO Userr (username, password, fullname, createdate, modifieddate, createby, modifiedby, idRole) " +
	                 "VALUES (?, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?)";
	    insert(sql, username, password, username, null ,createby, null, 2);
	}


	@Override
	public ArrayList<UserModel> findAll(PageRequest pageRequest) {
		StringBuilder sql = new StringBuilder("select * from userr u INNER JOIN Rolee r ON r.id = U.idRole ");
		sql.append( "where codeRole=?");
		if (pageRequest.getSorter().getSortName() == null) {
			pageRequest.getSorter().setSortName("id");
		}
		sql.append(" ORDER BY u."+pageRequest.getSorter().getSortName());
		if (pageRequest.getSorter().getSortBy() !=null) {
			sql.append(" "+pageRequest.getSorter().getSortBy());
		}
		
		if (pageRequest.getOffset() != null && pageRequest.getLimit() !=null) {
			sql.append(" OFFSET ? ROWS"
					+ " FETCH NEXT ? ROWS ONLY;");
			System.out.println(sql);
			return query(sql.toString(), new UserMapper(),SystemConstant.PLAYER , pageRequest.getOffset(),  pageRequest.getLimit());
		}
		else return query(sql.toString(), new UserMapper(), SystemConstant.PLAYER);
	}


	@Override
	public int countItems() {
		String sql = "select count(*) from userr";
		return countItems(sql);
	}


	@Override
	public void delete(long id) {
		String sql = " delete from userr where id=?";
		delete(sql, id);
		
	}


	@Override
	public boolean update(long id, String fullname, String username, String password) {
		String sql = "UPDATE Userr SET username = ?, password = ?, fullname = ? " +
                "WHERE id = ?";
		return update(sql, username, password, fullname, id)>0;
	}
	
}

	


	

	

