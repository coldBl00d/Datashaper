package com.ds.tests;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

import com.ds.analyser.Analyser;
import com.ds.enities.Link;
import com.ds.enities.Shape;
import com.ds.enities.Table;

public class AnalyzerTest {
	
	public Table generateTestTable(String tableName, String direction, String pk) {
		return new Table(tableName, direction, pk);
	}
	
	public Link generateTestLink(Table source, Table target, String linkName, String sourceTableField) {
		return new  Link(linkName, source, target, sourceTableField);
	}
	
	public Shape generateTestShape() {
		Table empTable= this.generateTestTable("Employees", "to", "EMPLOYEE_ID");
		Table jobHistoryTable = this.generateTestTable("Job_history", "from", "NO_PK");
		Link jobHistoryToEmpLink = this.generateTestLink(empTable, jobHistoryTable, "Employee_id", empTable.getPk());
		jobHistoryTable.setLink(jobHistoryToEmpLink);
		System.out.println(empTable);
		System.out.println(jobHistoryTable);
		Shape shape = new Shape(1, jobHistoryTable, empTable);
		return shape; 
	}
	
	@Test
	public void testMakeQuery() {
		Analyser analyser = new Analyser();
		String query=analyser.makeQuery(this.generateTestShape().getFromTable(), this.generateTestShape().getToTable());
		System.out.println(query);
		assertEquals(query,"SELECT p.rowid, c.rowid from Employees p, Job_history c WHERE p.EMPLOYEE_ID=c.Employee_id" );
		
	}
	
	@Test 
	public void testProcess() {
		Analyser analyser = new Analyser();
		analyser.process(this.generateTestShape());
	}

}
