package pl.altoriis.def;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;


public class trxTab {

	private TabItem localTabItem;
	private static Composite trxDataComp;
	private static Combo trxCmbSelector;
	private Composite trxTblComp;
	private Composite trxCmbComp;
	public 	defTable trxTable;
		
	
	
	public TabItem get() {
		return localTabItem;
	}

	public void set(TabItem localTabItem) {
		this.localTabItem = localTabItem;
	}
	
	public trxTab(TabFolder parent, int style) {
		localTabItem = new TabItem(parent, style);
		
		localTabItem.setText("Transakcje");
		trxDataComp = new Composite(parent, SWT.NONE);
		localTabItem.setControl(trxDataComp);
		trxDataComp.setLayout(new RowLayout(SWT.VERTICAL));
		drawTrxTab();
		
}


	private void drawTrxTab() {
		
		/** -------COMBO------- */
		trxCmbComp = new Composite(trxDataComp, SWT.NONE);
		trxCmbComp.setLayout(new RowLayout(SWT.HORIZONTAL));
		trxCmbSelector = new Combo(trxCmbComp, SWT.DROP_DOWN);
		trxCmbSelector.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
		}); 
		
					
		
		
		/** -------TABLE------- */
		trxTblComp = new Composite(trxDataComp, SWT.NONE);
		trxTable = new defTable(trxTblComp, SWT.BORDER | SWT.FULL_SELECTION | SWT.NO_SCROLL, 170,150);
		trxTable.get().addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
			}
		}); 
		trxDataComp.getShell().addListener(SWT.Resize, new Listener() {
			@Override
			public void handleEvent(Event e) {
				trxTable.defResize();
				}
			});
		
		
		
}
	
	
	
}
