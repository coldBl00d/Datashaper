package com.ds.enities;

public class Result implements Entity {
	
	private Table table; 
	private String identifier; 
	private int childCount; 
	private int sharedCount; 
	private int uniqueCount; 
	
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
		this.uniqueCount=this.childCount-this.sharedCount;
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

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Result) {
			Result result = (Result)obj;
			if(this.identifier.equals(result.identifier) && this.table.equals(result.getTable())) {
				return true;
			}
		}
		return false;
		
	}

	@Override
	public int hashCode() {
		long code = 17;
		code = 37*code+this.getTable().getTable().hashCode();
		code = 37*code+this.getIdentifier().hashCode();
		return  (int) code;
	}

	public Table getTable() {
		return table;
	}

	public String getIdentifier() {
		return identifier;
	}

	public int getChildCount() {
		return childCount;
	}

	public int getSharedCount() {
		return sharedCount;
	}

	public int getUniqueCount() {
		return uniqueCount;
	}
	
	
		

}
