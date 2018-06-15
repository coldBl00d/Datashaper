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

for record in the drving table: 
    find number of child record in child table 
    write the child rowid into xml 
    make a map of child --> parent 

for every child in the map:
	if(number_of_parents(child)> 1 ) 
    	for every parent that it is associated to child 
        	parent.count - 1 

for every parent 
	write the unique count 



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
