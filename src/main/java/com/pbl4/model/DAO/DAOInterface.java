package com.pbl4.model.DAO;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import com.pbl4.mapper.RowMapper;

public interface DAOInterface<T> {
	int insert(String sql, Object...parameters);

	int update(String sql, Object...parameters);

	int delete(String sql, Object...parameters);

	ArrayList<T> selectAll();

	T selectByID(String ID);

	ArrayList<T> selectByCondition(String Condition);
	
	<T> ArrayList<T> query(String sql, RowMapper<T> rowMapper, Object...parameters);

	void setParameter(PreparedStatement pstmt, Object...parameters);
	
	int countItems(String sql, Object...parameters);
}
