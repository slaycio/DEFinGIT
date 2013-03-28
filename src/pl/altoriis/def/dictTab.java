package pl.altoriis.def;

import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;


public class dictTab {

	
	private TabItem localTabItem;
	public 	static staticData st = new staticData();
	private Button dictBtnSave;
	private Button dictBtnDiscard;
	private Button dictBtnAdd;
	private Button dictBtnDel;
	private Combo dictCmbSelector;
	private Composite dictDataComp;
	public 	defTable dictTable;
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
		//dictDataComp.setSize(pTools.a2p(dictDataComp.getShell().getClientArea(),0,0));
		drawDictTab();
		
	}
	
	
	
	public  void dictUpdateOn() {
		
		dictBtnSave.setEnabled(true);
		dictBtnDiscard.setEnabled(true);
		dictBtnAdd.setEnabled(true);
		dictBtnDel.setEnabled(true);
		
	};

	public  void dictUpdateOff() {
		dictBtnSave.setEnabled(false);
		dictBtnDiscard.setEnabled(false);
		dictBtnAdd.setEnabled(true);
		dictBtnDel.setEnabled(true);

	};

	public  void dictBtnsOff() {
		dictBtnSave.setEnabled(false);
		dictBtnDiscard.setEnabled(false);
		dictBtnAdd.setEnabled(true);
		dictBtnDel.setEnabled(false);
		
		

	};
	
	
private void drawDictTab() {
	

		dictCmbComp = new Composite(dictDataComp, SWT.NONE);
		dictCmbComp.setLayout(new RowLayout(SWT.HORIZONTAL));
		dictCmbSelector = new Combo(dictCmbComp, SWT.DROP_DOWN);
		

		for (int r = 0; r < st.get().size(); r++) {
			dictCmbSelector.add(st.get().get(r).get(0));
		}

		dictBtnSave = new Button(dictCmbComp, SWT.NONE);
		dictBtnSave.setSize(60, 25);
		dictBtnSave.setText("Save");

		dictBtnDiscard = new Button(dictCmbComp, SWT.NONE);
		dictBtnDiscard.setSize(60, 25);
		dictBtnDiscard.setText("Discard");

		dictBtnAdd = new Button(dictCmbComp, SWT.NONE);
		dictBtnAdd.setSize(60, 25);
		dictBtnAdd.setText("New Line");
		
		dictBtnDel = new Button(dictCmbComp, SWT.NONE);
		dictBtnDel.setSize(60, 25);
		dictBtnDel.setText("Delete");
		
		
		
		dictTblComp = new Composite(dictDataComp, SWT.NONE);
		dictTable = new defTable(dictTblComp, SWT.BORDER | SWT.FULL_SELECTION | SWT.NO_SCROLL, 170,150);
						
		dictBtnsOff();
		dictBtnAdd.setEnabled(false);

		/*MouseListener tableCheck = new MouseListener(){

			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				
			}

			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
						
		};
		
		dictDataComp.addMouseListener(tableCheck);*/
		
		
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
