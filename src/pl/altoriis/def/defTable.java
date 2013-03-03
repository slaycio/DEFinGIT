package pl.altoriis.def;
//System.out.println(data);


import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

class defTable  {
	
	private Table localTable;
	private static ArrayList<Control> arControl;
	private static ArrayList<TableEditor> arEditor;
	private Boolean isEdited = false;
	
	public defTable(Composite parent, int style) {
				
		set(new Table(parent, style));	
		// TODO Auto-generated constructor stub
	}

		
	public void clearTable(){
	
		localTable.setRedraw(false);
		localTable.removeAll();
		while (localTable.getColumnCount() > 0) {
			localTable.getColumns()[0].dispose();
		}
		localTable.setRedraw(true);
	}
	
	
	public void populateTable(ArrayList<ArrayList<String>> input){
		
		localTable.setRedraw(false);
		clearTable();
		
		for (int a = 0; a < input.get(0).size();a++){
			TableColumn tNColumn = new TableColumn(localTable, SWT.NONE);
			
			if (a <2){
			tNColumn.setWidth(0);
			}else {
			tNColumn.setWidth((localTable.getSize().x / (input.get(0).size()-2) ) - 2);
			}
			tNColumn.setText(input.get(0).get(a));
			}
		for (int w = 1; w < input.size(); w++) {
			TableItem item = new TableItem(localTable, SWT.NONE);
			for(int g = 0;g < input.get(w).size();g++){
				item.setText(g,input.get(w).get(g));
				}	
			}
		
			
		
		localTable.setRedraw(true);
	}
	
	
		
	public Boolean addEditor(){
		
		
		Integer s = localTable.getSelectionIndex();
				
		if (s != null){
		// edytor
		
		arEditor = new ArrayList<TableEditor>();
		arControl = new ArrayList<Control>();
// TU TRZBA TE MAPY WYKMINIC I ZROBIC £ADNIE
		ArrayList<String> mapa =  new ArrayList<String>();
		
		mapa.add("Ukryta");
		mapa.add("Ukryta");
		mapa.add("Text");
		mapa.add("Combo");
		mapa.add("Text");
		mapa.add("Combo");
		mapa.add("Text");
		mapa.add("Text");
		mapa.add("Combo");
		
		db fk = new db();
		
		// TODO trzeba zrobiæ by mozna podac do query liste paramatrów
		// przeladujemy wywolanie tej metody.
		//Query query = em.createQuery("SELECT p FROM Pracownik p WHERE p.imie = :imie");
	   // query.setParameter("imie", "Jacek");
		
		ArrayList<ArrayList<String>> mapaX = fk.getData("select fk.table_name, col.column_name " +
		"from information_schema.referential_constraints ref, "+
		"information_schema.table_constraints fk, "+
		"information_schema.constraint_column_usage col "+
		"where ref.constraint_name = fk.constraint_name "+
		"and col.constraint_name = fk.constraint_name ");
		
		
		
	
		
		// to przygotowuje odpowiednia ilosc combo z danymi i edytorów  do nich
	
		
		
		for (int z = 0; z < localTable.getColumnCount(); z++) {
			
			if (mapa.get(z) != null ) {
				if (mapa.get(z).toString() =="Text") {
				
			
					arControl.add(new Text(localTable, SWT.NONE));
				((Text) arControl.get(z)).setText("item " + z);
				} else {
					arControl.add(new Combo(localTable, SWT.NONE));
				((Combo) arControl.get(z)).add("item " + z);
				((Combo) arControl.get(z)).add("item " + (z+1));}
				arEditor.add(new TableEditor(localTable));
			}
						
			
			
		}
	
		
		TableItem item = localTable.getItem(s);

		
		for (int w = 0; w < localTable.getColumnCount()
				; w++) {
			
			arEditor.get(w).grabHorizontal = true;
			arEditor.get(w).grabVertical = true;
			arEditor.get(w).setEditor(arControl.get(w), item, w);

		}
		
		
		
		isEdited = true;

		return true;
		
		// koniec edytora
		}
		else {
	
			return false;
		
			
		}
		
	}
	
	public void clearEditor() {

		
		if (isEdited) {
							
				for (int u = 0; u < arControl.size()
						; u++) {
					((Control) arControl.get(u)).dispose();
				}
				for (int y = 0; y < arEditor.size()
						; y++) {
					arEditor.get(y).dispose();
				}
				isEdited = false;
		}
		
	}
	
	
	
	
	public Table get() {
		return localTable;
	}

	public void set(Table localTable) {
		this.localTable = localTable;
	}

	
	
}
