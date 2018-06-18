package com.ds.enities;

public class Result implements Entity {
	
	Table table; 
	String identifier; 
	int childCount; 
	int sharedCount; 
	int uniqueCount; 
	
	public Result() {
		this.childCount=0;
		this.sharedCount=0;
		this.identifier="DEMODEMO123";
		this.table=null;
	}
	
	public Result(Table table, int childCount, String identifier) {
		this.childCount=childCount; 
		this.table=table;
		this.identifier=identifier; 
	}
	
	//fluency for test 
	
	public static Result getInstance() {
		return new Result(); 
	}
	
	public Result setChildCount(int count) {
		this.childCount=count; 
		return this; 
	}
	
	public Result identifiesAs(String identifier) {
		this.identifier=identifier;
		return this;
	}
	
	public Result shares(int count) {
		this.sharedCount=count;
		return this;
	}
	
	public Result ofTable(Table table) {
		this.table=table;
		return this;
	}
	
	public Result foundShared() throws Exception {
		if(this.sharedCount+this.uniqueCount == this.childCount) {
			this.sharedCount++;
			this.uniqueCount--;
			return this;
		}else {
			throw new Exception("Inconsistency in result");
		}
	}
	
	@Override 
	public String toString() {
		return "Result Object identified as "+this.identifier +" from table "+ this.table
				+" with " + this.sharedCount + " shared childs and "
				+ this.uniqueCount + " unique childs.";
	}

}
