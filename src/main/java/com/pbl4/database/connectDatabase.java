package com.pbl4.database;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class connectDatabase {
	
	public static Connection getConnection(){
		String Server = "LAPTOP-97M6INJM";
		// cua dat LAPTOP-LSOV3LBJ
		// hoang LAPTOP-97M6INJM
		String databaseName = "ChessGame";
		String password = "110204";
		String connectionUrl = "jdbc:sqlserver://" + Server + ":1433;databaseName=" + databaseName + ";user=sa;password=" + password + ";encrypt=true;trustServerCertificate=true";
		try {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	Connection con = DriverManager.getConnection(connectionUrl); 
        	Statement stmt = con.createStatement();
        	System.out.println("Ket noi database thanh cong");
        	return con;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}
	
	public static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}

