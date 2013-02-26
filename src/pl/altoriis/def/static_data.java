package pl.altoriis.def;

import java.util.ArrayList;


public class static_data {

	public ArrayList<ArrayList<ArrayList<String>>> slownik = new ArrayList<ArrayList<ArrayList<String>>>();
	
	
	private void newInstance(ArrayList<ArrayList<String>> newRecord) {
	
		
		slownik.add(newRecord);
	}
	
/*	private ArrayList<ArrayList<String>> newSet(ArrayList<String> nazwa, ArrayList<String> kolumny) {
		
		ArrayList<String> nazwaX = new ArrayList<String>();
	    nazwaX.add("Typy kont");
		
	    ArrayList<String> kolumnyX = new ArrayList<String>();
	    kolumnyX.add("Nazwa");
	    kolumnyX.add("Opis");
	    
	    ArrayList<String> zapytanieX = new ArrayList<String>();
	    zapytanieX.add("select name, description from account_types");
	    
	    ArrayList<ArrayList<String>> out = new ArrayList<ArrayList<String>>();
	    
	    out.add(nazwaX);
	    out.add(kolumnyX);
	    out.add(zapytanieX);
		
	    return zero;
		
	}*/
	
	
	public static_data() {
	
	// 0 nazwa s³ownika
	// 1 ilosc kolumn + nazwy
	// 2 zapytanie do bazy
	// 3 update bazy
	// 4 insert bazy 

	
    ArrayList<String> nazwa0 = new ArrayList<String>();
    nazwa0.add("Typy kont");
	
    ArrayList<String> kolumny0 = new ArrayList<String>();
    kolumny0.add("Nazwa");
    kolumny0.add("Opis");
    
    ArrayList<String> zapytanie0 = new ArrayList<String>();
    zapytanie0.add("select name, description from account_types");
    
    ArrayList<ArrayList<String>> zero = new ArrayList<ArrayList<String>>();
    
    zero.add(nazwa0);
    zero.add(kolumny0);
    zero.add(zapytanie0);
    
    //newInstance(newSet()); // tutaj ma byc zmiana
    newInstance(zero);
  	
  	//nazwa0.clear();
    
 
    ArrayList<String> nazwa1 = new ArrayList<String>();
    nazwa1.add("Konta");
	
    ArrayList<String> kolumny1 = new ArrayList<String>();
    kolumny1.add("Organizacja");
    kolumny1.add("Symbol");
    kolumny1.add("Nazwa");
    kolumny1.add("Opis");
    kolumny1.add("Nazwa");
    kolumny1.add("Typ konta");
    
    ArrayList<String> zapytanie1 = new ArrayList<String>();
    zapytanie1.add("select organizations.name, accounts.symbol, accounts.name, accounts.description, currencies.name, account_types.name from accounts , account_types , currencies , organizations where accounts.acc_type_id = account_types.acc_type_id and accounts.currency_id = currencies.currency_id and accounts.org_id =organizations.org_id ");
    
    ArrayList<ArrayList<String>> jeden = new ArrayList<ArrayList<String>>();
    jeden.add(nazwa1);
    jeden.add(kolumny1);
    jeden.add(zapytanie1);
    newInstance(jeden);
    

  	ArrayList<String> nazwa2 = new ArrayList<String>();
    nazwa2.add("Organizacje");
	
    ArrayList<String> kolumny2 = new ArrayList<String>();
    kolumny2.add("Nazwa");
    kolumny2.add("Opis");
      
    ArrayList<String> zapytanie2 = new ArrayList<String>();
    zapytanie2.add("select name, description from organizations");
    
    ArrayList<ArrayList<String>> dwa = new ArrayList<ArrayList<String>>();
    dwa.add(nazwa2);
    dwa.add(kolumny2);
    dwa.add(zapytanie2);
    newInstance(dwa);
  	      
   
	}
	
}
