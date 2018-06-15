package com.ds.enities;

public class Table implements Entity {

	public static final String DIRECTION_TO="to";
	public static final String DIRECTION_FROM="from";
	
	String tableName; 
	String direction;
	Link link; 
	
	public Table(String tableName, String direction) {
		this.tableName=tableName;
		this.direction=direction;
	}
	
	public void setLink(Link link) {
		this.link=link;
	}
}
