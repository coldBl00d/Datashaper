package com.ds.enities;

public class Table implements Entity {

	public static final String DIRECTION_TO="to";
	public static final String DIRECTION_FROM="from";
	
	String tableName; 
	String direction;
	Link link; 
	String pk;
	
	public Table(String tableName, String direction) {
		this.tableName=tableName;
		this.direction=direction;
	}
	
	public Table(String tableName, String direction, String pk) {
		this.tableName=tableName;
		this.direction=direction;
		this.pk=pk;
	}
	
	public String getPk() {
		if(this.pk!=null) {
			return this.pk;
		}else {
			return new String("NO_PK");
		}
	}
	
	public void setPk(String pk) {
		if(pk==null) this.pk = "NO_PK";
		else this.pk=pk;
	}
	
	public void setLink(Link link) {
		this.link=link;
	}
	
	public Link getLink() {
		if (link!=null)
			return this.link;
		return new Link("NO_LINK",null,null);
	}
	
	public String getTable() {
		return this.tableName;	
	}

	@Override
	public String toString() {
		return "Table Name: "+ this.tableName + 
				"\n Table Direction "+ this.direction+
				"\n Table Link Name "+ this.getLink().getLinkName();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Table) {
			return this.getTable().equals(((Table)obj).getTable());
		}
		return false;
		
	}
	
	
}
