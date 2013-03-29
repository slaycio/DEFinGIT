package pl.altoriis.def;

import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;



public class dictTab {

	
	private TabItem localTabItem;
	private Button dictBtnSave;
	private Button dictBtnDiscard;
	private Button dictBtnAdd;
	private Button dictBtnDel;
	private Combo dictCmbSelector;
	private Composite dictDataComp;
	private	defTable dictTable;
	private Composite dictTblComp;
	private Composite dictCmbComp;
	
	
	
	
public TabItem get() {
		return localTabItem;
}

public void set(TabItem localTabItem) {
		this.localTabItem = localTabItem;
}
	
	
public dictTab(TabFolder parent, int style) {
		localTabItem = new TabItem(parent, style);
		
		localTabItem.setText("S³owniki");
		dictDataComp = new Composite(parent, SWT.NONE);
		localTabItem.setControl(dictDataComp);
		dictDataComp.setLayout(new RowLayout(SWT.VERTICAL));
		drawDictTab();
		
				
}
	
	
	
private void dictUpdateOn() {
		
		dictBtnSave.setEnabled(true);
		dictBtnDiscard.setEnabled(true);
		dictBtnAdd.setEnabled(true);
		dictBtnDel.setEnabled(true);
};

private void dictUpdateOff() {
		dictBtnSave.setEnabled(false);
		dictBtnDiscard.setEnabled(false);
		dictBtnAdd.setEnabled(true);
		dictBtnDel.setEnabled(true);
};

private void dictBtnsOff() {
		dictBtnSave.setEnabled(false);
		dictBtnDiscard.setEnabled(false);
		dictBtnAdd.setEnabled(true);
		dictBtnDel.setEnabled(false);
};
	
	
private void drawDictTab() {
	
		/** -------COMBO------- */
		dictCmbComp = new Composite(dictDataComp, SWT.NONE);
		dictCmbComp.setLayout(new RowLayout(SWT.HORIZONTAL));
		dictCmbSelector = new Combo(dictCmbComp, SWT.DROP_DOWN);
		dictCmbSelector.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dictTable.clearTable();
				
				dictBtnsOff();
				Integer tempIndex = dictCmbSelector.getSelectionIndex();
				dictTable.addMetaData(sD.retAsMD("dict",tempIndex));
				dictTable.populateTable(getDictTableData(tempIndex));
								
			}
		}); 
		
		for (int r = 0; r < sD.dict().size(); r++) {
			dictCmbSelector.add(sD.dict().get(r).get(0));
		}

		
		/** -------BUTTONS------- */
		dictBtnSave = new Button(dictCmbComp, SWT.NONE);
		dictBtnSave.setSize(60, 25);
		dictBtnSave.setText("Save");
		Listener dictSaveBtnListener = new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (event.widget == dictBtnSave) {
					dictTable.saveEditor();
					dictTable.addMetaData(sD.retAsMD("dict",dictCmbSelector.getSelectionIndex()));
					dictTable.populateTable(getDictTableData(dictCmbSelector.getSelectionIndex()));
					dictBtnsOff();
				}
			}
		};
		dictBtnSave.addListener(SWT.Selection, dictSaveBtnListener);
		

		dictBtnDiscard = new Button(dictCmbComp, SWT.NONE);
		dictBtnDiscard.setSize(60, 25);
		dictBtnDiscard.setText("Discard");
		Listener dictDiscardBtnListener = new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (event.widget == dictBtnDiscard) {

					dictTable.clearEditor();
					dictTable.discardNewline();
					
					if (dictTable.get().getSelectionCount() == 0){
						dictBtnsOff();
					}else { 
						dictUpdateOff();
					}
				}
			}
		};
		dictBtnDiscard.addListener(SWT.Selection, dictDiscardBtnListener);
		

		dictBtnAdd = new Button(dictCmbComp, SWT.NONE);
		dictBtnAdd.setSize(60, 25);
		dictBtnAdd.setText("New Line");
		Listener dictAddBtnListener = new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (event.widget == dictBtnAdd) {

					if (dictTable.addLine()) {

						dictUpdateOn();
					}

					dictUpdateOn();
				}
			}
		};
		dictBtnAdd.addListener(SWT.Selection, dictAddBtnListener);
		
		
		dictBtnDel = new Button(dictCmbComp, SWT.NONE);
		dictBtnDel.setSize(60, 25);
		dictBtnDel.setText("Delete");
		Listener dictDelBtnListener = new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (event.widget == dictBtnDel) {
						if (dictTable.deleteLine()) {
							dictBtnsOff();
						}
				}
			}
		};
		dictBtnDel.addListener(SWT.Selection, dictDelBtnListener);
		
		
		/** -------TABLE------- */
		dictTblComp = new Composite(dictDataComp, SWT.NONE);
		dictTable = new defTable(dictTblComp, SWT.BORDER | SWT.FULL_SELECTION | SWT.NO_SCROLL, 170,150);
		dictTable.get().addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				dictUpdateOff();  
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				dictUpdateOn(); 
			}
		}); 
		
		dictDataComp.getShell().addListener(SWT.Resize, new Listener() {
			@Override
			public void handleEvent(Event e) {
				
				dictTable.defResize();
				System.out.println("controlResized"); // TODO Trzeba dodaæ jakieœ dodatkowe sprawdzanie po zakoñczonym resize i redraw zrobiæ. ni jest za szybki
				}
			});
				
			
		
		
		/** -------INITIAL STATE------- */
		dictBtnsOff();
		dictBtnAdd.setEnabled(false);
		
}
	
private ArrayList<ArrayList<String>> getDictTableData(Integer indexOf){
		
		db dbCon = new db();
		ArrayList<ArrayList<String>> dictTableDataTemp = dbCon.getData(sD.dict().get(indexOf).get(1));
		dbCon.finalize();
		return dictTableDataTemp;
}
	

}
