Datashaper Flow 

1) Analyse 
2) Replicate 

1) Analyse: 

Requirements: 

1) Performance issue is always for a singular query, thus the datashape related to those tables should be replicated without retreving data. 
2) Upper limit on the number of tables = 10 
3) Table skew on defined fk should be replicated 
4) Intersecting child record shape should be replicated 


Example scenarios 



Person to Project: 
A person can belong to many project 
A Project can have many person working in it 
Thus m-n relationship 

Input:

<shape> 
	<driver id="1">PERSON</driver>
		<child>
			<table>DEPARTMENT</table>
			<source>department_id</source> 
			<target>department_id_fk</target> <!-- this is the fk in the driver --> 
		</child> 
	</driver> 
</shape> 



output: 

<results> 
	<result id="1">
		<record table="PERSON" rowid="1">
			<replicant>1</replicant>
			<unique>3</unique> <!-- 1-1 for this record --> 
			<intersecting>2</intersecting> 
		</record>
	.
	.
	.
	.
	.
	</result>
	<result id="2">

	</result>
</results>


algorithm : 

find total number of parent, write to xml 
find total number of child, write to xml 
for record in the parent table: 
    find its child records 
    get number of child for a parent record from the result set.
    Make an XML entity object with parent record id, count of child
    make a map of parent pk --> meta object   
    make a map of child --> parent 

for every child in the map:
	if(number_of_parents(child)> 1 ) 
    	for every parent that it is associated to child 
        	parent.unique - 1 

for every parent metaobject 
	write metaboject



2) Replication: 

populate all the table with count records 
for every result: 
    for every record: 
        update unique in random 
        balck list uniques 
        update intersecting in random


Types of fk 

Table to same table 
table to intersection table
   table to table x 2  
table to child table 


features: 
   column density of fk columns 
   average child number, show oddities
   
   
```xml 

Now generates some kind of analytic information on a fk

AAAEAbAAEAAAADNAAB--> |AAAEAfAAEAAAADlAAB|AAAEAfAAEAAAADlAAC
AAAEAbAAEAAAADOAAD--> |AAAEAfAAEAAAADlAAD
AAAEAbAAEAAAADNAAC--> |AAAEAfAAEAAAADlAAA
AAAEAbAAEAAAADOAAC--> |AAAEAfAAEAAAADlAAG|AAAEAfAAEAAAADlAAJ
AAAEAbAAEAAAADNAAW--> |AAAEAfAAEAAAADlAAF
AAAEAbAAEAAAADNABM--> |AAAEAfAAEAAAADlAAH|AAAEAfAAEAAAADlAAI
AAAEAbAAEAAAADNAAO--> |AAAEAfAAEAAAADlAAE

-->-->-->-->-->-->--
Child --> Parent
AAAEAfAAEAAAADlAAF--> |AAAEAbAAEAAAADNAAW
AAAEAfAAEAAAADlAAG--> |AAAEAbAAEAAAADOAAC
AAAEAfAAEAAAADlAAD--> |AAAEAbAAEAAAADOAAD
AAAEAfAAEAAAADlAAE--> |AAAEAbAAEAAAADNAAO
AAAEAfAAEAAAADlAAJ--> |AAAEAbAAEAAAADOAAC
AAAEAfAAEAAAADlAAH--> |AAAEAbAAEAAAADNABM
AAAEAfAAEAAAADlAAI--> |AAAEAbAAEAAAADNABM
AAAEAfAAEAAAADlAAB--> |AAAEAbAAEAAAADNAAB
AAAEAfAAEAAAADlAAC--> |AAAEAbAAEAAAADNAAB
AAAEAfAAEAAAADlAAA--> |AAAEAbAAEAAAADNAAC


<Results>
	<Result id=" AAAEAbAAEAAAADNAAB" table="Employees" >
		<count>2</count>
		<unique>2</unqiue>
		<shared>0</shared>
	</Result>
	<Result id=" AAAEAbAAEAAAADOAAD" table="Employees" >
		<count>1</count>
		<unique>1</unqiue>
		<shared>0</shared>
	</Result>
	<Result id=" AAAEAbAAEAAAADNAAC" table="Employees" >
		<count>1</count>
		<unique>1</unqiue>
		<shared>0</shared>
	</Result>
	<Result id=" AAAEAbAAEAAAADOAAC" table="Employees" >
		<count>2</count>
		<unique>2</unqiue>
		<shared>0</shared>
	</Result>
	<Result id=" AAAEAbAAEAAAADNAAW" table="Employees" >
		<count>1</count>
		<unique>1</unqiue>
		<shared>0</shared>
	</Result>
	<Result id=" AAAEAbAAEAAAADNABM" table="Employees" >
		<count>2</count>
		<unique>2</unqiue>
		<shared>0</shared>
	</Result>
	<Result id=" AAAEAbAAEAAAADNAAO" table="Employees" >
		<count>1</count>
		<unique>1</unqiue>
		<shared>0</shared>
	</Result>
</Results>

```
