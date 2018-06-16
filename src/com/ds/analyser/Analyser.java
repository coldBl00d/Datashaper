package com.ds.analyser;

import java.util.ArrayList;

import com.ds.enities.Shape;
import com.ds.enities.Table;

public class Analyser {
	
	private final String CHILD_COUNT="SELECT count(1), :fktoparent: FROM :childTable: group by :fktoparent:";
	
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
		String query = new String(CHILD_COUNT); 
		query=query.replace(":fktoparent:", fromTable.getLink().getLinkName());
		System.out.println(query);
		query=query.replace(":childTable:", fromTable.getTable());
		System.out.println(query);
		return query;
	}
	
	
}
