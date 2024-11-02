package com.pbl4.model.DAOImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.pbl4.database.connectDatabase;
import com.pbl4.mapper.RowMapper;
import com.pbl4.model.DAO.DAOInterface;



public class DAOimple<T> implements DAOInterface<T> {

	@Override
	public int insert(String sql, Object...parameters) {
		// TODO Auto-generated method stub
		try {
			Connection con = connectDatabase.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			setParameter(pstmt, parameters);
			int kq = pstmt.executeUpdate();
			System.out.println("Thuc thi: " + sql);
			System.out.println("Co" + kq + "Ket qua thay doi");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(String sql, Object...parameters) {
		int kq=0;
		try {
			Connection con = connectDatabase.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			setParameter(pstmt, parameters);
			kq = pstmt.executeUpdate();
			System.out.println("Thuc thi: " + sql);
			System.out.println("Co" + kq + "Ket qua thay doi");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public int delete(String sql, Object...parameters) {
		try {
			Connection con = connectDatabase.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			setParameter(pstmt, parameters);
			int kq = pstmt.executeUpdate();
			System.out.println("Thuc thi: " + sql);
			System.out.println("Co" + kq + "Ket qua thay doi");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<T> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T selectByID(String ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<T> selectByCondition(String Condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> ArrayList<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
		ArrayList<T> results = new ArrayList<T>();
		try {
			Connection con = connectDatabase.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			// set parameters
			setParameter(pstmt, parameters);
			//
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				results.add(rowMapper.mapRow(rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public void setParameter(PreparedStatement pstmt, Object... parameters) {
		try {
			for (int i = 0; i < parameters.length; i++) {
				Object para = parameters[i];
				if (para instanceof Long) {
					pstmt.setLong(i + 1, (Long) para);
				}
				else if (para instanceof String) {
					pstmt.setString(i+1,(String)para);
				}
				else if (para instanceof Date) {
					pstmt.setDate(i+1, (Date)para);
				}
				else if (para instanceof Integer) {
					pstmt.setInt(i+1, (Integer)para);
				}
				else if (para == null ) {
					 pstmt.setNull(i + 1, Types.DATE);
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public int countItems(String sql, Object... parameters) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			Connection con = connectDatabase.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			// set parameters
			setParameter(pstmt, parameters);
			//
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}



}
