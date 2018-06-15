package com.ds.enities;

public class Shape {
	
	Table fromTable;
	Table toTable;
	int id; 
	
	public Shape(int id, Table ft, Table tT) {
		this.id = id; 
		this.fromTable=ft;
		this.toTable=tT; 
	}

	public Table getFromTable() {
		return fromTable;
	}

	public void setFromTable(Table fromTable) {
		this.fromTable = fromTable;
	}

	public Table getToTable() {
		return toTable;
	}

	public void setToTable(Table toTable) {
		this.toTable = toTable;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	

}
