package pl.altoriis.def;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

class defTable  {
	
	private Table localTable;
	private static ArrayList<Combo> arCombo;
	private static ArrayList<TableEditor> arEditor;
	private static Integer s;
	
	public defTable(Composite parent, int style) {
				
		setLocalTable(new Table(parent, style));	
		// TODO Auto-generated constructor stub
	}

	
	public TableItem dajItem (int a){
		
		
		return localTable.getItem(a);
		
	}
	
	public void addEditor(){
		
		// edytor
		arCombo = new ArrayList<Combo>();
		arEditor = new ArrayList<TableEditor>();

		// to przygotwuje odpowiednia ilosc combo z danymi i edytorw
		// do nich
		for (int z = 0; z < localTable).getColumnCount(); z++) {
			arCombo.add(new Combo(localTable, SWT.NONE));
			arCombo.get(z).add("item " + z);
			arEditor.add(new TableEditor(localTable));
		}

		s = localTable.getSelectionIndex();
		///TableItem item = dict_data.getItem(s);
		TableItem item = localTable.getItem(s);

		for (int w = 0; w < localTable.getColumnCount(); w++) {
			arCombo.get(w).setText(item.getText(w));
			arEditor.get(w).grabHorizontal = true;
			arEditor.get(w).grabVertical = true;
			arEditor.get(w).setEditor(arCombo.get(w), item, w);

		}

		// koniec edytora

		
		
	}
	
	public void clearEditor() {

		for (int d = 0; d < arCombo.size(); d++) {
			arCombo.get(d).dispose();
		}
		for (int d = 0; d < arEditor.size(); d++) {
			arEditor.get(d).dispose();
		}
	}
	
	
	
	
	public Table getLocalTable() {
		return localTable;
	}

	public void setLocalTable(Table localTable) {
		this.localTable = localTable;
	}

	
	
}
