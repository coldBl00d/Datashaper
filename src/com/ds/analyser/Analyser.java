package com.ds.analyser;

import java.util.ArrayList;

import com.ds.enities.Shape;
import com.ds.enities.Table;

public class Analyser {
	
	private final String CHILD_COUNT="SELECT count(1), :fktoparent: FROM :childTable group by :fktoparent:";
	
	public void process(ArrayList<Shape> shapes) {
		for(Shape cShape : shapes) {
			process(cShape);
		}
	}
	
	public void process(Shape shape) {
		Table fromTable = shape.getFromTable();
		Table toTable = shape.getToTable();
		assert fromTable!=null && toTable!=null;
		String query = new String(CHILD_COUNT); 
		query.replaceAll(":fktoparent:", fromTable.getLink().getLinkName());
		query.replaceAll(":childTable:", fromTable.getTable());
		System.out.println(query);
	}
	
	
}
