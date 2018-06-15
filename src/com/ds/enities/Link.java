package com.ds.enities;

public class Link implements Entity{
	
	String linkName; 
	Table sourceTable;
	Table targetTable;
	
	
	public Link(String linkName, Table sourceTable, Table targetTable) {
		this.linkName=linkName;
		this.sourceTable=sourceTable;
		this.targetTable=targetTable;
	}
	
	public String getLinkName() {
		return this.linkName;
	}

	public Table getSourceTable() {
		return sourceTable;
	}

	public Table getTargetTable() {
		return targetTable;
	}
	
	

}
