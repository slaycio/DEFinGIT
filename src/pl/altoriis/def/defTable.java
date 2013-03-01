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
	private Boolean isEdited = false;
	
	public defTable(Composite parent, int style) {
				
		set(new Table(parent, style));	
		// TODO Auto-generated constructor stub
	}

	
	
	public Integer gSelIndex(){
	return localTable.getSelectionIndex();
	}
	
		
	public void addEditor(){
		
		
		Integer s = localTable.getSelectionIndex();
				
		if (s != null){
		// edytor
		arCombo = new ArrayList<Combo>();
		arEditor = new ArrayList<TableEditor>();

		// to przygotwuje odpowiednia ilosc combo z danymi i edytorów  do nich
		for (int z = 0; z < localTable.getColumnCount(); z++) {
			arCombo.add(new Combo(localTable, SWT.NONE));
			arCombo.get(z).add("item " + z);
			arEditor.add(new TableEditor(localTable));
		}

		
		TableItem item = localTable.getItem(s);

		for (int w = 0; w < localTable.getColumnCount(); w++) {
			arCombo.get(w).setText(item.getText(w));
			arEditor.get(w).grabHorizontal = true;
			arEditor.get(w).grabVertical = true;
			arEditor.get(w).setEditor(arCombo.get(w), item, w);

		}

		isEdited = true;
		// koniec edytora
		}
		
		
	}
	
	public void clearEditor() {

		if (isEdited) {
				for (int u = 0; u < arCombo.size()
						; u++) {
					arCombo.get(u).dispose();
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
