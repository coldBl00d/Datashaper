package com.ds.analyser;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		//printResultSet(childParentRs);
		//Map <String, List<String>> pcMap = makeMap(childParentRs, "prid","crid");
		//printMap(pcMap);
		//Map <String, List<String>> cpMap = makeMap(childParentRs, "crid", "prid");
		//System.out.println();
		//printMap(cpMap);
		List<Map<String, List<String>>> mapList = makeMaps(childParentRs);
		printMap(mapList.get(0));//parent to child
		System.out.println("-->-->-->-->-->-->--");
		printMap(mapList.get(1));//child to parent
		try {
			childParentRs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//makeResults(map, map); 
		//findCounts()
		//closeConnection()
		//writeResult()
		//done
	}
	
	
	private List<Map<String, List<String>>> makeMaps(ResultSet rs){
		Map <String, List<String>> cpMap = new HashMap<String, List<String>> ();
		Map <String, List<String>> pcMap = new HashMap<String, List<String>> ();
		assert rs!=null;
		List<String> cTempList; //parents
		List<String> pTempList;
		
		try {
			while(rs.next()) {
				List<String> parentValueList = cpMap.get(rs.getString("crid"));
				List<String> childValueList = pcMap.get(rs.getString("prid"));
				if(parentValueList == null) {
					pTempList = new ArrayList<String>();
					pTempList.add(rs.getString("prid"));
				    cpMap.put(rs.getString("crid"), pTempList);
				}else {
					parentValueList.add(rs.getString("prid"));
				}
				
				if(childValueList == null) {
					cTempList = new ArrayList<String>();
					cTempList.add(rs.getString("crid"));
				    pcMap.put(rs.getString("prid"), cTempList);
				}else {
					childValueList.add(rs.getString("crid"));
				}
			}
			
			List<Map<String, List<String>>> rList = new ArrayList<Map<String, List<String>>>();
			rList.add(pcMap);
			rList.add(cpMap);
			return rList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
	private void printMap(Map<String, List<String>> map) {
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			System.out.print(entry.getKey());
			System.out.print("--> ");
			printList(entry.getValue());
			System.out.println();
		}
	}
	
	private void printList(List<String> list) {
		for(String s : list) {
			System.out.print("|"+s);
		}
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
