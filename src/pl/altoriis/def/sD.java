package pl.altoriis.def;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.MessageBox;

class sD {
	
	public static ArrayList<ArrayList<String>> lovData(String tName){
		
		db dbCon = new db();
		ArrayList<ArrayList<String>> lovDataOutput = dbCon.getData("select id, name from "+tName);
		dbCon.finalize();
		return lovDataOutput;
	}

	public static ArrayList<String> retAsArray(String inString) {
		ArrayList<String> outArray =  new ArrayList<String>();
		String[] tempString = inString.split(":");
		for (int e = 0; e < tempString.length ;e++){
			outArray.add(tempString[e]);
		}
		return outArray;
	}
	
	public static defMetaData retAsMD(String type, Integer tempIndex){
				
		defMetaData outX = new defMetaData();
		
		if (type == "dict"){
			
		outX.inTableName = dict().get(tempIndex).get(6);
		outX.inpKeyName = dict().get(tempIndex).get(7);
		outX.inMap = retAsArray(dict().get(tempIndex).get(4));
		outX.inColNames = retAsArray(dict().get(tempIndex).get(3));
		outX.inColDesc = retAsArray(dict().get(tempIndex).get(2));
		outX.inLn = retAsArray(dict().get(tempIndex).get(5));
		}
		
		return outX;		
	}
	
	public static ArrayList<ArrayList<String>> dict(){
	
		// 0 name of dictionary
		// 1 database query
		// 2 column description
		// 3 column name
		// 4 column type
		// 5 column size
		// 6 table name
		// 7 primary key name


		final ArrayList<ArrayList<String>> out = new ArrayList<ArrayList<String>>();
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
	    out.add(newRecord);
	  
	    
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
			    	  "and accounts.org_id =organizations.id order by 1" 
			    	  );
	    newRecord.add("id:Nazwa organizacji:Symbol konta:Nazwa konta:Opis konta:Waluta:Typ konta");
	    newRecord.add("id:org_id:symbol:name:description:currency_id:acc_type_id");
	    newRecord.add("id:lov_organizations:data:data:data:lov_currencies:lov_account_types");
	    newRecord.add("10:10:5:100:100:10:10");
	    newRecord.add("accounts");
	    newRecord.add("id");
	    out.add(newRecord);
	    
	    
	    newRecord = new ArrayList<String>();
	    newRecord.add("Organizacje");
	    newRecord.add("select id, name, description from organizations order by 1");
	    newRecord.add("id:Nazwa:Opis");
	    newRecord.add("id:name:description");
	    newRecord.add("id:data:data");
	    newRecord.add("10:240:240");
	    newRecord.add("organizations");
	    newRecord.add("id");
	    out.add(newRecord);	
	    
	    
	    newRecord = new ArrayList<String>();
	    newRecord.add("Waluty");
	    newRecord.add("select id, name,symbol, description from currencies order by 1");
	    newRecord.add("id:Nazwa:Symbol:Opis");
	    newRecord.add("id:name:symbol:description");
	    newRecord.add("id:data:data:data");
	    newRecord.add("10:240:240:240");
	    newRecord.add("currencies");
	    newRecord.add("id");
	    out.add(newRecord);	
	    
	    newRecord = new ArrayList<String>();
	    newRecord.add("Typy organizacji");
	    newRecord.add("select id, name,description from organization_types order by 1");
	    newRecord.add("id:Nazwa:Opis");
	    newRecord.add("id:name:description");
	    newRecord.add("id:data:data");
	    newRecord.add("10:240:240");
	    newRecord.add("organization_types");
	    newRecord.add("id");
	    out.add(newRecord);	
	   
	    
	    newRecord = new ArrayList<String>();
	    newRecord.add("Przydzia� typ�w organizacji");
	    newRecord.add("select org_type_asg.id, organizations.name ,organization_types.name " +
			    	  "from org_type_asg,organization_types ,organizations " +
			    	  "where org_type_asg.org_type_id = organization_types.id " +
			    	  "and org_type_asg.org_id = organizations.id " +
			    	  "order by 2");
	    newRecord.add("id:Organizacja:Typ organizacji");
	    newRecord.add("id:org_id:org_type_id");
	    newRecord.add("id:lov_organizations:lov_organization_types");
	    newRecord.add("10:240:240");
	    newRecord.add("org_type_asg");
	    newRecord.add("id");
	    out.add(newRecord);	

		
		    
	    newRecord = new ArrayList<String>();
	    newRecord.add("Typy terminarzy");
	    newRecord.add("select id, name,description from schedule_types order by 1");
	    newRecord.add("id:Nazwa:Opis");
	    newRecord.add("id:name:description");
	    newRecord.add("id:data:data");
	    newRecord.add("10:240:240");
	    newRecord.add("schedule_types");
	    newRecord.add("id");
	    out.add(newRecord);	
		
		
	    newRecord = new ArrayList<String>();
	    newRecord.add("Typy linii");
	    newRecord.add("select id, name,description from trx_types order by 1");
	    newRecord.add("id:Nazwa:Opis");
	    newRecord.add("id:name:description");
	    newRecord.add("id:data:data");
	    newRecord.add("10:240:240");
	    newRecord.add("trx_types");
	    newRecord.add("id");
	    out.add(newRecord);	
		
		
		
		
	return out;	
	}
	
	
	public static Point a2p(Rectangle inArea, Integer xMinus, Integer yMinus){
		
		return 	new Point(inArea.width-xMinus,inArea.height-yMinus);
	
}
	
	
public static Boolean yesNo(String title, String question){
		
		int style = SWT.ICON_QUESTION | SWT.YES | SWT.NO;

		MessageBox dialogTemp = new MessageBox(mainWindow.get().shell, style);

		dialogTemp.setText(title);

		dialogTemp.setMessage(question);

		if (dialogTemp.open() == SWT.YES) {
			return true;	
		} else {
			return false;
		}
	}
	
public static void msg(String title, String question){
		
		int style = SWT.ICON_ERROR | SWT.OK ;

		MessageBox dialogTemp = new MessageBox(mainWindow.get().shell, style);

		dialogTemp.setText(title);

		dialogTemp.setMessage(question);
		
		if (dialogTemp.open() == SWT.OK) {
			
		}
	}
	
	
	
	
}
