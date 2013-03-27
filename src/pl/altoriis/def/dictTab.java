package pl.altoriis.def;

import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class dictTab {

	
	private TabItem localTabItem;
	public static staticData st = new staticData();
	//private  Button dictBtnUpdate;
	private  Button dictBtnSave;
	private  Button dictBtnDiscard;
	private  Button dictBtnAdd;
	private  Button dictBtnDel;
	private  Combo dictCmbSelector;
	public  Composite dictDataComp;
	public defTable dictTable;
	
	
	
	public TabItem get() {
		return localTabItem;
	}

	public void set(TabItem localTabItem) {
		this.localTabItem = localTabItem;
	}
	
	
	public dictTab(TabFolder parent, int style , Point setSize) {
		localTabItem = new TabItem(parent, style);
		
		localTabItem.setText("S�owniki");
		dictDataComp = new Composite(parent, SWT.NONE);
		localTabItem.setControl(dictDataComp);
		RowLayout rlComposite = new RowLayout(SWT.VERTICAL);
		dictDataComp.setLayout(rlComposite);
		dictDataComp.setSize(setSize.x - 200, setSize.y);
		//dictDataComp.setBounds(mainWindow.shell.getClientArea());
		drawDictTab();
		
	}
	
	
	
	public  void dictUpdateOn() {
		//dictBtnUpdate.setEnabled(false);
		dictBtnSave.setEnabled(true);
		dictBtnDiscard.setEnabled(true);
		dictBtnAdd.setEnabled(true);
		//dictCmbSelector.setEnabled(false);
		//dictCmbSelector.setEnabled(true);
		dictBtnDel.setEnabled(true);
		
	};

	public  void dictUpdateOff() {
		//dictBtnUpdate.setEnabled(true);
		dictBtnSave.setEnabled(false);
		dictBtnDiscard.setEnabled(false);
		dictBtnAdd.setEnabled(true);
		//dictCmbSelector.setEnabled(true);

		dictBtnDel.setEnabled(true);

	};

	public  void dictBtnsOff() {
		//dictBtnUpdate.setEnabled(false);
		dictBtnSave.setEnabled(false);
		dictBtnDiscard.setEnabled(false);
		dictBtnAdd.setEnabled(true);
		//dictCmbSelector.setEnabled(true);
		dictBtnDel.setEnabled(false);
		
		

	};
	
	
private void drawDictTab() {
	

		Composite combo_holder = new Composite(dictDataComp, SWT.NONE);

		dictCmbSelector = new Combo(combo_holder, SWT.DROP_DOWN);
		dictCmbSelector.setSize(new Point(dictDataComp.getSize().x - 600, 23));

		for (int r = 0; r < st.get().size(); r++) {
			dictCmbSelector.add(st.get().get(r).get(0));
		}

		dictTable = new defTable(dictDataComp, SWT.BORDER | SWT.FULL_SELECTION);
		//dictTable.get().setLayoutData(new RowData(dictDataComp.getSize().x, dictDataComp.getSize().y - 230));
		

		Composite buttons = new Composite(dictDataComp, SWT.NONE);
		buttons.setLayout(new FillLayout(SWT.HORIZONTAL));

		
		dictBtnSave = new Button(buttons, SWT.NONE);
		dictBtnSave.setSize(60, 25);
		dictBtnSave.setText("Save");

		dictBtnDiscard = new Button(buttons, SWT.NONE);
		dictBtnDiscard.setSize(60, 25);
		dictBtnDiscard.setText("Discard");

		dictBtnAdd = new Button(buttons, SWT.NONE);
		dictBtnAdd.setSize(60, 25);
		dictBtnAdd.setText("New Line");
		
		dictBtnDel = new Button(buttons, SWT.NONE);
		dictBtnDel.setSize(60, 25);
		dictBtnDel.setText("Delete");
				
		dictBtnsOff();
		dictBtnAdd.setEnabled(false);

		
		Listener dictSaveBtnListener = new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (event.widget == dictBtnSave) {
					dictTable.saveEditor();
					dictTable.addMetaData(
							st.get().get(dictCmbSelector.getSelectionIndex()).get(6),
							st.get().get(dictCmbSelector.getSelectionIndex()).get(7),
							st.retAsArray(st.get().get(dictCmbSelector.getSelectionIndex()).get(4)),
							st.retAsArray(st.get().get(dictCmbSelector.getSelectionIndex()).get(3)),
							st.retAsArray(st.get().get(dictCmbSelector.getSelectionIndex()).get(2)),
							st.retAsArray(st.get().get(dictCmbSelector.getSelectionIndex()).get(5)));
					dictTable.populateTable(getDictTableData(dictCmbSelector.getSelectionIndex()));
					dictBtnsOff();
				}
			}
		};
		dictBtnSave.addListener(SWT.Selection, dictSaveBtnListener);

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
		
		
		
		dictCmbSelector.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dictTable.clearTable();
				
				dictBtnsOff();
				Integer tempIndex = dictCmbSelector.getSelectionIndex();
				dictTable.addMetaData(
						st.get().get(tempIndex).get(6),
						st.get().get(tempIndex).get(7),
						st.retAsArray(st.get().get(tempIndex).get(4)),
						st.retAsArray(st.get().get(tempIndex).get(3)),
						st.retAsArray(st.get().get(tempIndex).get(2)),
						st.retAsArray(st.get().get(tempIndex).get(5)));
				dictTable.populateTable(getDictTableData(tempIndex));
				
							
			}
		}); // koniec listenera comboboxa

	}
	
	private ArrayList<ArrayList<String>> getDictTableData(Integer indexOf){
		
		db dbCon = new db();
		
		ArrayList<ArrayList<String>> dictTableDataTemp = dbCon.getData(st.get().get(indexOf).get(1));
						
		dbCon.finalize();
		
		return dictTableDataTemp;
	}
	

}
