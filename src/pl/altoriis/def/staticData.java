package pl.altoriis.def;

import java.util.ArrayList;


public class staticData {

	private ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
	
	
	public ArrayList<ArrayList<String>> lovData(String tName){
		
		
		String lovPKeyQuery = 
				"select c.column_name " +
				"from information_schema.constraint_column_usage c, " +
				"information_schema.table_constraints t " +
				"where c.table_name = t.table_name " +
				"and c.constraint_name = t.constraint_name " +
				"and t.constraint_type = 'PRIMARY KEY' " +
				"and t.table_name = '" +tName+ "'";
		
		
		
		db dbCon = new db();
		
		ArrayList<ArrayList<String>> lovPKey = dbCon.getData(lovPKeyQuery);
		
		String lovQuery = 
				"select "+ lovPKey.get(0).get(0).toString() + ", name from " +tName ;
		
		ArrayList<ArrayList<String>> lovDataOutput = dbCon.getData(lovQuery);
		
		
		
		
		dbCon.finalize();
		return lovDataOutput;
		
		
			
	}
	
	
	private void newInstance (ArrayList<String> newRecord) {
	
		output.add(newRecord);
	}
		
	
	public staticData() {
	
	// 0 name of dictionary
	// 1 database query
	// 2 database update 
	// 3 database insert  

	ArrayList<String> newRecord;
	
	newRecord = new ArrayList<String>();
    newRecord.add("Typy kont");
    newRecord.add(	
    		"select 'account_types' ,'acc_type_id' ,'Nazwa','Opis' UNION ALL " +
    		"select 'account_types' ,'acc_type_id' ,'name' ,'description' UNION ALL " +
    		"select 'account_types' ,'acc_type_id' ,'data' ,'data' UNION ALL " +
    		"select '240' ,'10' ,'240' ,'240' UNION ALL " +
    		"(select 'account_types', acc_type_id::text, name, description from account_types order by 2)"
    				);
    newRecord.add("update account_types set name = &u1, description = &u2");
    newRecord.add("insert into account_types (name, description) values ");
  
    newInstance(newRecord);
  
    
    newRecord = new ArrayList<String>();
    newRecord.add("Konta");
    newRecord.add(
    		"select 'accounts' ,'acc_id' ,'Nazwa organizacji','Symbol konta','Nazwa konta','Opis konta','Waluta','Typ konta' UNION ALL " +
    	    "select 'accounts' ,'acc_id' ,'org_id' ,'symbol','name','description','currency_id','acc_type_id' UNION ALL " +
    	    "select 'accounts' ,'acc_id' ,'lov_organizations' ,'data','data','data','lov_currencies','lov_account_types' UNION ALL " +
    	    "select '240' ,'10' ,'10' ,'5','100','100','10','10' UNION ALL " +
    	    "(select 'accounts' , acc_id::text," +
    		"organizations.name , " +
    		"accounts.symbol , " +
    		"accounts.name , " +
    		"accounts.description , " +
    		"currencies.name , " +
    		"account_types.name  " +
    		"from accounts , account_types , currencies , organizations " +
    		"where accounts.acc_type_id = account_types.acc_type_id " +
    		"and accounts.currency_id = currencies.currency_id " +
    		"and accounts.org_id =organizations.org_id order by 2)" 
    		);
    newRecord.add(" ");
    newRecord.add(" ");
    
    
    newInstance(newRecord);
    
    
    newRecord = new ArrayList<String>();
    newRecord.add("Organizacje");
    newRecord.add(
    		"select 'organizations','org_id' ,'Nazwa','Opis' UNION ALL " +
    		"select 'organizations','org_id' ,'name','description' UNION ALL "+
    		"select 'organizations','org_id' ,'data','data' UNION ALL "+
    		"select '240' ,'10' ,'240' ,'240' UNION ALL " +
    		"(select 'organizations' , org_id::text, name, description from organizations order by 2) " 
    		);
    
    newInstance(newRecord);
    newRecord.add(" ");
 	
   
	}
	
	public ArrayList<ArrayList<String>> get() {
		return output;
	}

	public void set(ArrayList<ArrayList<String>> output) {
		this.output = output;
	}
	
	
}
