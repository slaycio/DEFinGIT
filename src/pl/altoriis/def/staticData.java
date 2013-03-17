package pl.altoriis.def;

import java.util.ArrayList;


public class staticData {

	private ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
	
	
	public ArrayList<ArrayList<String>> lovData(String tName){
						
		db dbCon = new db();
		
		//ArrayList<ArrayList<String>> lovPKey = dbCon.getPragma(tName);
		
		//String lovQuery = "select "+ lovPKey.get(0).get(1).toString() + ", name from " +tName ;
		
		ArrayList<ArrayList<String>> lovDataOutput = dbCon.getData("select id, name from "+tName);
			
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
	// 6 table name
	// 7 primary key name

	ArrayList<String> newRecord;
	
	    
    newRecord = new ArrayList<String>();
    newRecord.add("Typy kont");
    newRecord.add("select id, name, description from account_types order by 2");
    newRecord.add("id:Nazwa:Opis");
    newRecord.add("id:name:description");
    newRecord.add("id:data:data");
    newRecord.add("10:240:240");
    newRecord.add("account_types");
    newRecord.add("id");
    newInstance(newRecord);
  
    
    newRecord = new ArrayList<String>();
    newRecord.add("Konta");
    newRecord.add("select accounts.id," +
    		"organizations.name , " +
    		"accounts.symbol , " +
    		"accounts.name , " +
    		"accounts.description , " +
    		"currencies.name , " +
    		"account_types.name  " +
    		"from accounts , account_types , currencies , organizations " +
    		"where accounts.acc_type_id = account_types.id " +
    		"and accounts.currency_id = currencies.id " +
    		"and accounts.org_id =organizations.id order by 2" 
    		);
    newRecord.add("id:Nazwa organizacji:Symbol konta:Nazwa konta:Opis konta:Waluta:Typ konta");
    newRecord.add("id:org_id:symbol:name:description:currency_id:acc_type_id");
    newRecord.add("id:lov_organizations:data:data:data:lov_currencies:lov_account_types");
    newRecord.add("10:10:5:100:100:10:10");
    newRecord.add("accounts");
    newRecord.add("id");
    newInstance(newRecord);
    
    
    newRecord = new ArrayList<String>();
    newRecord.add("Organizacje");
    newRecord.add("select id, name, description from organizations order by 2");
    newRecord.add("id:Nazwa:Opis");
    newRecord.add("id:name:description");
    newRecord.add("id:data:data");
    newRecord.add("10:240:240");
    newRecord.add("organizations");
    newRecord.add("id");
    newInstance(newRecord);	
   
	}
	
	public ArrayList<ArrayList<String>> get() {
		return output;
	}

	public void set(ArrayList<ArrayList<String>> output) {
		this.output = output;
	}
	
	
}
