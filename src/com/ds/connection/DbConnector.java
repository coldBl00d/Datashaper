package com.ds.connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
	
	public static final int CONNECTION_SUCCESS = 1; 
	public static final int CONNECTION_FAILURE = -1; 
	public static final int DRIVER_LOAD_FAILURE = -2; 
	private Connection mConnection = null; 
	
	public int init(String dbString) {
		try {
			Driver driver= new oracle.jdbc.driver.OracleDriver();
			DriverManager.registerDriver(driver);
			this.mConnection=DriverManager.getConnection(dbString,"hr","hr");
		}catch(SQLException e) {
			System.err.println("ERROR: DB Connection failed");
			e.printStackTrace();
			return CONNECTION_FAILURE;
			
		}
		
		return CONNECTION_SUCCESS;
	}
	
	
	public Connection getConnection() throws SQLException {
		if(this.mConnection!=null)
			return this.mConnection;
		throw new SQLException("Connection not initialized");
	}
	
	
	
}
