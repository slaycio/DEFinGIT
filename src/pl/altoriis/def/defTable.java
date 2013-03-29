package pl.altoriis.def;
//System.out.println(data);

import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.*;


/**
 *
 * TODO New line without button. ????
 * TODO data refresh is visible - fix it.
 * TODO Change table to be more universal with inserts deletes and updates - some attributes of entities used with tables
 * 
 */

class defTable {
    
	private Table localTable;
	private Boolean isEdited = false;
	private Integer isEditedNo;
	private ArrayList<Control> arControl;
	private ArrayList<TableEditor> arEditor;
	private ArrayList<String> arMap;
	private ArrayList<String> arColNames;
	private ArrayList<String> arColDesc;
	private ArrayList<String> arLn;
	private ArrayList<TableColumn> arTc;
	private ArrayList<ArrayList<ArrayList<String>>> arLovs;
	private String tableName;
	private String pKeyName;
	private Integer shellMarginX;
	private Integer shellMarginY;
	public String tableState = "NEW";   // {NEW ON ED}
	
	
	

public defTable(Composite parent, int style, int inShellMarginX, int inShellMarginY) {
		shellMarginX = inShellMarginX;
		shellMarginY = inShellMarginY;
		
		localTable = new Table(parent, style);
		localTable.setHeaderVisible(true);
		localTable.setLinesVisible(true);
		defResize();
		
		
		
		localTable.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				clearEditor();
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (isEdited){
					saveEditor();
				}
				clearEditor();
				addEditor();
			}
		}); 
		
				
}
	
public void defResize(){
	
		localTable.setSize(pTools.a2p(localTable.getShell().getClientArea(),shellMarginX,shellMarginY));
		//System.out.println("rrrr");		
		if (arTc != null){
			arTc.get(0).setWidth(0);
			for (int e=1;e < arTc.size(); e++) {
				arTc.get(e).setWidth((localTable.getParent().getParent().getClientArea().width-50)/(arTc.size()-1));
			}
		}
}
	
public void addMetaData(String inTableName,String inpKeyName,ArrayList<String> inMap ,ArrayList<String> inColNames,ArrayList<String> inColDesc, ArrayList<String> inLn) {
		
		clearTable();
		tableName = inTableName;
		pKeyName = inpKeyName;
		arMap = inMap;
		arColNames = inColNames;
		arColDesc = inColDesc;
		arLn = inLn;
}

public void clearTable() {

		if (isEdited){
			saveEditor();
		}
		
		localTable.setRedraw(false);
		clearEditor();
		localTable.removeAll();
		while (localTable.getColumnCount() > 0) {
			localTable.getColumns()[0].dispose();
		}
		if (arMap != null && arColNames != null && arLn != null && arColDesc != null){
		
		for (int u = 0; u < arMap.size(); u++) {
			arMap.removeAll(arMap);
		}
		for (int u = 0; u < arColNames.size(); u++) {
			arColNames.removeAll(arColNames);
		}
		for (int u = 0; u < arLn.size(); u++) {
			arLn.removeAll(arLn);
		}
		for (int u = 0; u < arColDesc.size(); u++) {
			arColDesc.removeAll(arColDesc);
		}
		}
		tableName = null;
		pKeyName = null;
		
		localTable.setRedraw(true);
		tableState ="NEW";
}

public void populateTable(ArrayList<ArrayList<String>> input) {
		
		arTc = new ArrayList<TableColumn>();
		
		for (int a = 0; a < arColDesc.size(); a++) {
			arTc.add(new TableColumn(localTable, SWT.NONE));
			arTc.get(a).setText(arColDesc.get(a));
		}
		for (int w = 0; w < input.size(); w++) {
			TableItem item = new TableItem(localTable, SWT.NONE);
			for (int g = 0; g < input.get(w).size(); g++) {
				item.setText(g, input.get(w).get(g));
			}
		}

	
		localTable.update();
		localTable.setRedraw(true);
		localTable.getParent().layout();
		defResize();
		tableState ="ON";
		
}

public Boolean addEditor() {

		Integer s = localTable.getSelectionIndex();

		if (s != null) {

			arEditor = new ArrayList<TableEditor>();
			arControl = new ArrayList<Control>();
			arLovs = new ArrayList<ArrayList<ArrayList<String>>>();

					
			for (int z = 0; z < localTable.getColumnCount(); z++) {

				 				
				if (arMap.get(z) != null) {

					if (arMap.get(z).toString().startsWith("lov")) {

						arControl.add(new Combo(localTable, SWT.NONE));

						arLovs.add(dictTab.st.lovData(arMap.get(z).toString().substring(4)));

						for (int e = 0; e < arLovs.get(arLovs.size() - 1).size(); e++) {
							((Combo) arControl.get(z)).add(arLovs.get(arLovs.size() - 1).get(e).get(1));
						}
						((Combo) arControl.get(z)).setText(localTable.getItem(s).getText(z));

						((Combo) arControl.get(z)).setBackground(new Color(mainWindow.get().display, 240, 240, 240));

					} else {
						arLovs.add(new ArrayList<ArrayList<String>>());
						arControl.add(new Text(localTable, SWT.NONE));
						((Text) arControl.get(z)).setText(localTable.getItem(s).getText(z));
						((Text) arControl.get(z)).setTextLimit(Integer.valueOf(arLn.get(z)));
						((Text) arControl.get(z)).setBackground(new Color(mainWindow.get().display, 240, 240, 240));

					}

					arEditor.add(new TableEditor(localTable));

				}

			}

			TableItem item = localTable.getItem(s);

			for (int w = 0; w < localTable.getColumnCount(); w++) {

				arEditor.get(w).grabHorizontal = true;
				arEditor.get(w).grabVertical = true;
				arEditor.get(w).setEditor(arControl.get(w), item, w);
								
				Listener changedLst = new Listener() {
					@Override
					public void handleEvent(Event event) {
						isEdited = true;
						isEditedNo = localTable.getSelectionIndex();
					}
				};
								
				arControl.get(w).addListener(SWT.Modify, changedLst);
				
			}

			tableState = "ED";
			return true;
			
		} else {

			return false;
			}

}

public void saveEditor() {

		String updateData = new String();
		Integer nullCounter = 0;
	
		if (isEditedNo!=null){
		if (pTools.yesNo("Zapisaæ zmiany ?", "Zapisaæ zmiany ?"))
			{
		
		
		for (int i = 1; i < localTable.getColumnCount(); i++) {
			
			String curText = new String();

			if (arMap.get(i).toString().startsWith("lov")) {
				curText = ((Combo) arControl.get(i)).getText();
				if (curText.length() == 0)
					nullCounter++;

			} else {
				curText = ((Text) arControl.get(i)).getText();

			}

			localTable.getItem(isEditedNo).setText(i, curText);

		}

		int l = isEditedNo;

		if (nullCounter == 0) {

			if (!(localTable.getItem(l).getText(0).length() == 0)) {

				updateData = "update " + tableName + " set ";

				for (int e = 1; e < localTable.getColumnCount(); e++) {

					if (arMap.get(e).toString().startsWith("lov")) {

						ArrayList<ArrayList<String>> temp = arLovs.get(e);
						String out = "";

						for (int k = 0; k < temp.size(); k++) {

							if (temp.get(k).get(1).equalsIgnoreCase(localTable.getItem(l).getText(e))) {

								out = temp.get(k).get(0);
							}
						}

						if (e < localTable.getColumnCount() - 1) {
							updateData = updateData + arColNames.get(e).toString() + " = " + out + ", ";

						} else {
							updateData = updateData + arColNames.get(e).toString() + " = " + out + "";
						}

					} else {

						if (e < localTable.getColumnCount() - 1) {
							updateData = updateData + arColNames.get(e).toString() + " = '" + localTable.getItem(l).getText(e) + "', ";

						} else {
							updateData = updateData + arColNames.get(e).toString() + " = '" + localTable.getItem(l).getText(e) + "' ";
						}

					}

				}
				updateData = updateData + " where " + pKeyName + " = " + localTable.getItem(l).getText(0);

			} else {
				updateData = "insert into " + tableName + " (";

				for (int e = 1; e < localTable.getColumnCount(); e++) {

					if (arMap.get(e).toString().startsWith("lov")) {

						if (e < localTable.getColumnCount() - 1) {
							updateData = updateData + arColNames.get(e).toString() + ", ";

						} else {
							updateData = updateData + arColNames.get(e).toString() + "";
						}

					} else {

						if (e < localTable.getColumnCount() - 1) {
							updateData = updateData + arColNames.get(e).toString() + ", ";

						} else {
							updateData = updateData + arColNames.get(e).toString() + "";
						}

					}
				}
				updateData = updateData + ") values (";

				for (int e = 1; e < localTable.getColumnCount(); e++) {

					if (arMap.get(e).toString().startsWith("lov")) {

						ArrayList<ArrayList<String>> temp = arLovs.get(e);
						String out = "";

						for (int k = 0; k < temp.size(); k++) {

							if (temp.get(k).get(1).equalsIgnoreCase(localTable.getItem(l).getText(e))) {

								out = temp.get(k).get(0);
							}
						}

						if (e < localTable.getColumnCount() - 1) {
							updateData = updateData + out + ", ";

						} else {
							updateData = updateData + out + "";
						}

					} else {

						if (e < localTable.getColumnCount() - 1) {
							updateData = updateData + " '" + localTable.getItem(l).getText(e) + "', ";

						} else {
							updateData = updateData + " '" + localTable.getItem(l).getText(e) + "' ";
						}

					}

				}

				updateData = updateData + ")";

			}

			db dbCon = new db();
			dbCon.updateData(updateData);
			dbCon.finalize();
				} else {
						pTools.msg("Uwaga!!","Wprowadzono niepoprawne dane. Nale¿y wskazaæ wartoœci we wszystkich listach wyboru");
						}
			}
		}
		clearEditor();

}

public void discardNewline() {
		for (int t = 0; t< localTable.getItemCount();t++){
			if ((localTable.getItem(t).getText(0).length() == 0)) {
				localTable.getItem(t).dispose();
			}
		}
		
}

public Boolean addLine() {

		clearEditor();
		new TableItem(localTable, SWT.NONE);
		localTable.setSelection(localTable.getItemCount() - 1);
		return addEditor();
}
	
public boolean deleteLine(){
		
		String deleteData = new String();
		
		Integer s = localTable.getSelectionIndex();

		if (s != null) {
						
				if (localTable.getItem(localTable.getSelectionIndex()).getText(0).length() > 0){	
				if (pTools.yesNo("Skasowaæ rekord ?","Skasowaæ rekord ?")){
				deleteData = "delete from " + tableName + " where " + pKeyName + " = " + localTable.getItem(localTable.getSelectionIndex()).getText(0);
				
				db dbCon = new db();
				dbCon.updateData(deleteData);
				dbCon.finalize();
				
				localTable.remove(s);
				}
				clearEditor();
				return true;
				
				}else {
					clearEditor();
					return false;
				}
		} else{
			return false;
		}
}

public void clearEditor() {

		isEdited = false;
		isEditedNo = null;
		tableState ="ON";	
			
		
			discardNewline();
			
			if (arControl != null){
				
			
			
			for (int u = 0; u < arControl.size(); u++) {
				((Control) arControl.get(u)).dispose();
			}
		
			
			for (int y = 0; y < arEditor.size(); y++) {
				arEditor.get(y).dispose();
			}
					
			
		}
	
}

public Table get() {
		return localTable;
}

public void set(Table localTable) {
		this.localTable = localTable;
}

}
