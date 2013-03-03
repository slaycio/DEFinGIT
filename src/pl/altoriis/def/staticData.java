package pl.altoriis.def;

import java.util.ArrayList;


public class staticData {

	private ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
	
	
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
    newRecord.add(	"select 'account_types' as \"table_name\", acc_type_id," +
    				"name as \"Nazwa\", " +
					"description as \"Opis\" " +
					"from account_types");
  
    newInstance(newRecord);
  
    
    newRecord = new ArrayList<String>();
    newRecord.add("Konta");
    newRecord.add("select 'accounts' as \"table_name\", acc_id," +
    		"organizations.name as \"Nazwa organizacji\", " +
    		"accounts.symbol as \"Symbol konta\", " +
    		"accounts.name as \"Nazwa konta\", " +
    		"accounts.description as \"Opis konta\", " +
    		"currencies.name as \"Waluta\", " +
    		"account_types.name as \"Typ konta\" " +
    		"from accounts , account_types , currencies , organizations " +
    		"where accounts.acc_type_id = account_types.acc_type_id " +
    		"and accounts.currency_id = currencies.currency_id " +
    		"and accounts.org_id =organizations.org_id ");
    
    newInstance(newRecord);
    
    
    newRecord = new ArrayList<String>();
    newRecord.add("Organizacje");
    newRecord.add("select 'organizations' as \"table_name\", org_id," +
    			"name as \"Nazwa\", " +
    			"description as \"Opis\" " +
    			"from organizations");
    
    newInstance(newRecord);
    
 	
   
	}
	
	public ArrayList<ArrayList<String>> get() {
		return output;
	}

	public void set(ArrayList<ArrayList<String>> output) {
		this.output = output;
	}
	
	
}
