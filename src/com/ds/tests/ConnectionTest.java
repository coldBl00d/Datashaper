package com.ds.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.ds.connection.DbConnector;

class ConnectionTest {

	private final String dbString="jdbc:oracle:thin:@localhost:1521/xe";
	
	@Test
	void testInit() {
		//fail("Not yet implemented");
		DbConnector connector = new DbConnector(); 
		int connectionStatus = connector.init(dbString);
		assertEquals(connectionStatus, DbConnector.CONNECTION_SUCCESS);
	}
	
	@Test
	void testConnection(){
		DbConnector connector = new DbConnector(); 
		int connectionStatus = connector.init(dbString);
		try {
			assertNotNull(connector.getConnection());
		} catch (SQLException e) {
			fail("connection null");
			e.printStackTrace();
		}
	}
}
