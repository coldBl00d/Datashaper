package com.ds.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.ds.analyser.Analyser;
import com.ds.enities.Link;
import com.ds.enities.Shape;
import com.ds.enities.Table;

public class AnalyzerTest {
	
	public Table generateTestTable(String tableName, String direction) {
		return new Table(tableName, direction);
	}
	
	public Link generateTestLink(Table source, Table target, String linkName) {
		return new  Link(linkName, source, target);
	}
	
	public Shape generateTestShape() {
		Table empTable= this.generateTestTable("Employees", "to");
		Table jobHistoryTable = this.generateTestTable("Job_history", "from");
		Link jobHistoryToEmpLink = this.generateTestLink(empTable, jobHistoryTable, "Employee_id");
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
		assertEquals(query,"SELECT count(1), Employee_id FROM Job_history group by Employee_id" );
		
	}

}
