package com.ds.enities;

public class Link implements Entity{
	
	String linkName; //targetTableField
	Table sourceTable;
	Table targetTable;
	String sourceTableField;
	
	
	public Link(String linkName, Table sourceTable, Table targetTable) {
		this.linkName=linkName;
		this.sourceTable=sourceTable;
		this.targetTable=targetTable;
	}
	
	
	public Link(String linkName, Table sourceTable, Table targetTable, String sourceTableField) {
		this.linkName=linkName;
		this.sourceTable=sourceTable;
		this.targetTable=targetTable;
		this.sourceTableField=sourceTableField;
	}
	
	public String getSourceTableField() {
		return sourceTableField;
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
