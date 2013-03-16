package pl.altoriis.def;

import java.util.ArrayList;


public class staticData {

	private ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
	
	
	public ArrayList<ArrayList<String>> lovData(String tName){
		
		
		String lovPKeyQuery = "acc_type_id" ;
			/*	"select c.column_name " +
				"from information_schema.constraint_column_usage c, " +
				"information_schema.table_constraints t " +
				"where c.table_name = t.table_name " +
				"and c.constraint_name = t.constraint_name " +
				"and t.constraint_type = 'PRIMARY KEY' " +
				"and t.table_name = '" +tName+ "'";*/
		
		
		
		
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
	
	public ArrayList<String> retAsArray(String inString) {
		ArrayList<String> outArray =  new ArrayList<String>();
		
		String[] tempString = inString.split(":");
		
		for (int e = 0; e < tempString.length ;e++){
			outArray.add(tempString[e]);
		}
		
		return outArray;
		
	}
	
	
	public staticData() {
	
	// 0 name of dictionary
	// 1 database query
	// 2 column description
	// 3 column name
	// 4 column type
	// 5 column size

	ArrayList<String> newRecord;
	
	newRecord = new ArrayList<String>();
    newRecord.add("Typy kont");
    newRecord.add("select 'account_types', acc_type_id, name, description from account_types order by 2");
    newRecord.add("account_types:acc_type_id:Nazwa:Opis");
    newRecord.add("account_types:acc_type_id:name:description");
    newRecord.add("account_types:acc_type_id:data:data");
    newRecord.add("240:10:240:240");
    newInstance(newRecord);
  
    
    newRecord = new ArrayList<String>();
    newRecord.add("Konta");
    newRecord.add("select 'accounts' , acc_id," +
    		"organizations.name , " +
    		"accounts.symbol , " +
    		"accounts.name , " +
    		"accounts.description , " +
    		"currencies.name , " +
    		"account_types.name  " +
    		"from accounts , account_types , currencies , organizations " +
    		"where accounts.acc_type_id = account_types.acc_type_id " +
    		"and accounts.currency_id = currencies.currency_id " +
    		"and accounts.org_id =organizations.org_id order by 2" 
    		);
    newRecord.add("accounts:acc_id:Nazwa organizacji:Symbol konta:Nazwa konta:Opis konta:Waluta:Typ konta");
    newRecord.add("accounts:acc_id:org_id:symbol:name:description:currency_id:acc_type_id");
    newRecord.add("accounts:acc_id:lov_organizations:data:data:data:lov_currencies:lov_account_types");
    newRecord.add("240:10:10:5:100:100:10:10 ");
    newInstance(newRecord);
    
    
    newRecord = new ArrayList<String>();
    newRecord.add("Organizacje");
    newRecord.add("select 'organizations', org_id, name, description from organizations order by 2");
    newRecord.add("organizations:org_id:Nazwa:Opis");
    newRecord.add("organizations:org_id:name:description");
    newRecord.add("organizations:org_id:data:data");
    newRecord.add("240:10:240:240");
    newInstance(newRecord);	
   
	}
	
	public ArrayList<ArrayList<String>> get() {
		return output;
	}

	public void set(ArrayList<ArrayList<String>> output) {
		this.output = output;
	}
	
	
}
