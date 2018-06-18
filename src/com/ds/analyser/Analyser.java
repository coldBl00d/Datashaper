package com.ds.analyser;

import java.util.ArrayList;

import com.ds.enities.Shape;
import com.ds.enities.Table;

public class Analyser {
	
	private final String CHILD_COUNT="SELECT count(1), :fktoparent: FROM :childTable: group by :fktoparent:";
	private final String PARENT_CHILD="SELECT p.rowid, c.rowid from :parentTable: p, :childTable: c WHERE p.:parentPrimaryKey:=c.:childForeignKey:";
	
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
	}
	
	public String makeQuery(Table fromTable, Table toTable) {
		String query = new String(PARENT_CHILD); 
		query=query.replace(":parentTable:", toTable.getTable());
		query=query.replace(":childTable:", fromTable.getTable());
		query=query.replace(":parentPrimaryKey:", fromTable.getLink().getSourceTableField());
		query=query.replaceAll(":childForeignKey:", fromTable.getLink().getLinkName());
		return query;
	}
	
	
}
