package com.ds.analyser;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ds.connection.DbConnector;
import com.ds.enities.Shape;
import com.ds.enities.Table;

public class Analyser {
	
	private final String CHILD_COUNT="SELECT count(1), :fktoparent: FROM :childTable: group by :fktoparent:";
	private final String PARENT_CHILD="SELECT p.rowid AS prid, c.rowid AS crid from :parentTable: p, :childTable: c WHERE p.:parentPrimaryKey:=c.:childForeignKey:";
	
	public void process(ArrayList<Shape> shapes) {
		for(Shape cShape : shapes) {
			process(cShape);
		}
	}
	
	public void process(Shape shape) {
		
		Table fromTable = shape.getFromTable();
		Table toTable = shape.getToTable();
		assert fromTable!=null && toTable!=null;
		String query = makeQuery(fromTable, toTable);
		ResultSet childParentRs = this.fireQuery(query);
		printResultSet(childParentRs);
	}
	
	private void printResultSet(ResultSet childParentRs) {
		if(childParentRs != null) {
			try {
				while(childParentRs.next()) {
					
						System.out.print(childParentRs.getString("prid"));
						System.out.print("-->");
						System.out.println(childParentRs.getString("crid"));
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String makeQuery(Table fromTable, Table toTable) {
		String query = new String(PARENT_CHILD); 
		query=query.replace(":parentTable:", toTable.getTable());
		query=query.replace(":childTable:", fromTable.getTable());
		query=query.replace(":parentPrimaryKey:", fromTable.getLink().getSourceTableField());
		query=query.replaceAll(":childForeignKey:", fromTable.getLink().getLinkName());
		return query;
	}
	
	
	public ResultSet fireQuery(String query) {
		DbConnector connector = new DbConnector();
		connector.init(null); //default connection 
		try {
			Connection c = connector.getConnection();
			Statement statement =c.createStatement();
			return statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
		
	}
	
}
