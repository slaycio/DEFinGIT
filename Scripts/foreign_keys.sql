-- lista kluczy obcych na tabelkach
select fk.table_name, col.*
from 
information_schema.referential_constraints ref, 
information_schema.table_constraints fk,
information_schema.constraint_column_usage col, 
information_schema.columns c
where 1=1 
and c.table_name = col.table_name
and ref.constraint_name = fk.constraint_name
and col.constraint_name = fk.constraint_name
and fk.table_name = 'accounts'
;


select *
from information_schema.columns c
where c.table_name = 'accounts';


select * from information_schema.columns; 



select 'account_types' as table_name, acc_type_id, 
    				name as Nazwa, 
					description as Opis
					from account_types 
					UNION all
					select 'account_types' ,0 ,'data' ,'data' ;
					

					
select t.table_name, c.column_name 
from information_schema.constraint_column_usage c, information_schema.table_constraints t
where c.table_name = t.table_name 
and c.constraint_name = t.constraint_name
and t.constraint_type = 'PRIMARY KEY';
		


select (select c.column_name 
from information_schema.constraint_column_usage c, information_schema.table_constraints t
where c.table_name = t.table_name 
and c.constraint_name = t.constraint_name
and t.constraint_type = 'PRIMARY KEY'
and t.table_name = tName),
name
from tName

					

select * from information_schema.table_constraints
where constraint_type = 'PRIMARY KEY';

select * from information_schema.referential_constraints;

select b.* from information_schema.constraint_column_usage b;
where b.constraint_name in (select a.constraint_name from information_schema.table_constraints a
where a.constraint_type = a.constraint_type --'FOREIGN KEY'
);

select fk.table_name as "na tej tablicy jest klucz", pk.table_name as "ta tablice wola", col.column_name as "ta kolumna ma klucz" from 
information_schema.referential_constraints ref, 
information_schema.table_constraints fk,
information_schema.table_constraints pk, 
information_schema.constraint_column_usage col
where 1=1 
and ref.constraint_name = fk.constraint_name
and ref.unique_constraint_name = pk.constraint_name
and fk.table_name != pk.table_name
and col.constraint_name = fk.constraint_name
;



select (select c.column_name 
				from information_schema.constraint_column_usage c, information_schema.table_constraints t
				where c.table_name = t.table_name 
				and c.constraint_name = t.constraint_name 
				and t.constraint_type = 'PRIMARY KEY' 
				and t.table_name = 'accounts' ), name from  accounts ;
				
				
				
select 'table_name',0 ,'Nazwa','Opis' UNION ALL 
    		select 'table_name',0 ,'data','data' UNION ALL 
    		select 'organizations' , org_id, name, description from organizations ;
