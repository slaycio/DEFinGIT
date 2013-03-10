package pl.altoriis.def;
//System.out.println(data);


import java.util.ArrayList;
import org.eclipse.swt.SWT;
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
	private static ArrayList<String> arMap;
	private static ArrayList<String> arColNames;
	private static ArrayList<ArrayList<ArrayList<String>>>  arLovs;

	
	public defTable(Composite parent, int style) {
				
		//set(new Table(parent, style));	
		localTable = new Table(parent, style);
		arMap = new ArrayList<String>();
		arColNames = new ArrayList<String>();
	
		// TODO Auto-generated constructor stub
	}

	
	
			
	public void clearTable(){
	
		localTable.setRedraw(false);
		localTable.removeAll();
		while (localTable.getColumnCount() > 0) {
			localTable.getColumns()[0].dispose();
		}
		
		for (int u = 0; u < arMap.size(); u++) {
			arMap.removeAll(arMap);
		}
		for (int u = 0; u < arColNames.size(); u++) {
			arColNames.removeAll(arColNames);
		}
		
		
		localTable.setRedraw(true);
	}
	
	
	public void populateTable(ArrayList<ArrayList<String>> input){
		
		localTable.setRedraw(false);
		clearTable();
		
		for (int a = 0; a < input.get(0).size();a++){
			TableColumn tNColumn = new TableColumn(localTable, SWT.NONE);
	
			arMap.add(input.get(2).get(a));
			arColNames.add(input.get(1).get(a));
			//System.out.println(input.get(1).get(a));
			if (a <2){
			tNColumn.setWidth(0);
			}else {
			tNColumn.setWidth((localTable.getSize().x / (input.get(0).size()-2) ) - 2);
			}
			tNColumn.setText(input.get(0).get(a));
				
			}
		
		//localTable.setSortColumn(localTable.getColumn(2));
		//localTable.setSortDirection(SWT.DOWN);
		// by³o 3
		for (int w = 3; w < input.size(); w++) {
			TableItem item = new TableItem(localTable, SWT.NONE);
			for(int g = 0;g < input.get(w).size();g++){
				item.setText(g,input.get(w).get(g));
				}	
			}
		
		
		localTable.update();
		
		localTable.setRedraw(true);
	}
	
	
	
		
	public Boolean addEditor(){
		
		
		Integer s = localTable.getSelectionIndex();
				
		if (s != null){
		// edytor
		
		arEditor = new ArrayList<TableEditor>();
		arControl = new ArrayList<Control>();
		arLovs = new ArrayList<ArrayList<ArrayList<String>>>();
		//arLovMap =  new ArrayList<Integer>();
			
		// to przygotowuje odpowiednia ilosc combo z danymi i edytorów  do nich
		
		
		for (int z = 0; z < localTable.getColumnCount(); z++) {
						
			if (arMap.get(z) != null ) {
			
				//System.out.println(arMap.get(z).toString());
				//System.out.println(localTable.getColumn(z).getToolTipText().getClass());
				if (arMap.get(z).toString().startsWith("lov") ) {
			
					arControl.add(new Combo(localTable, SWT.NONE));
					
					arLovs.add( mainWindow.st.lovData(arMap.get(z).toString().substring(4)));
					//arLovMap.add(z);
					//ArrayList<ArrayList<String>> newLov= mainWindow.st.lovData(	arMap.get(z).toString().substring(4));
					
					for (int e = 0; e < arLovs.get(arLovs.size()-1).size(); e++)	{
					((Combo) arControl.get(z)).add(arLovs.get(arLovs.size()-1).get(e).get(1));
					}
					((Combo) arControl.get(z)).setText(localTable.getItem(s).getText(z));
					
				} else {
					arLovs.add( new ArrayList<ArrayList<String>>());
					arControl.add(new Text(localTable, SWT.NONE));
					((Text) arControl.get(z)).setText(localTable.getItem(s).getText(z));
						
				} 
				
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
	
	
	public void saveEditor() {
		
		String updateData = new String();
		
		
		for (int i=0;i<localTable.getColumnCount();i++){
			
			String curText = new String();
			
			if (arMap.get(i).toString().startsWith("lov") ) {
				 curText =  ((Combo) arControl.get(i)).getText();
			} else {
				 curText =  ((Text) arControl.get(i)).getText();
				
			}
		
		localTable.getItem(localTable.getSelectionIndex()).setText(i,curText);
		
		}
		clearEditor();
		
		
		
		int l = localTable.getSelectionIndex();
		
		updateData = "update " + localTable.getItem(l).getText(0) + " set ";
		
		for (int e = 2; e < localTable.getColumnCount(); e++) {
		
			if (arMap.get(e).toString().startsWith("lov") ) {
			
				ArrayList<ArrayList<String>> temp = arLovs.get(e);
				String out="";
				
				for (int k=0; k< temp.size(); k++){
					
					if (temp.get(k).get(1).equalsIgnoreCase(localTable.getItem(l).getText(e))){
														
						out = temp.get(k).get(0);
					}
				}
				
				if (e<localTable.getColumnCount()-1){
				updateData = updateData + arColNames.get(e).toString()+ " = " + out +", " ;
				
				}else 
					{updateData = updateData + arColNames.get(e).toString()+ " = " + out +" " ;}
				
						
			
			} else 
			{
			
				if (e<localTable.getColumnCount()-1){
				updateData =  updateData + arColNames.get(e).toString()+ " = '" + localTable.getItem(l).getText(e) + "', ";
				
				} else {
					updateData =  updateData + arColNames.get(e).toString()+ " = '" + localTable.getItem(l).getText(e) + "' ";
				}
				
			}
			
				
			}
		updateData =  updateData + " where "+ arColNames.get(1).toString() + " = " + localTable.getItem(l).getText(1);
		System.out.println(updateData);
		
		db dbCon = new db();
		dbCon.updateData(updateData);
		dbCon.finalize();
	
		
	
		
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
