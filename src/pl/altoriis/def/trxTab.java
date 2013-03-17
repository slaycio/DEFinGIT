package pl.altoriis.def;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class trxTab {

	private TabItem localTabItem;
	private static Composite trxDataComp;
	private static Combo trxCmbSelector;
	
	
	public TabItem get() {
		return localTabItem;
	}

	public void set(TabItem localTabItem) {
		this.localTabItem = localTabItem;
	}
	
	
	public trxTab(TabFolder parent, int style, Point setSize) {
		localTabItem = new TabItem(parent, style);
		
		localTabItem.setText("S³owniki");
		trxDataComp = new Composite(parent, SWT.NONE);
		localTabItem.setControl(trxDataComp);
		RowLayout rl_composite = new RowLayout(SWT.VERTICAL);
		trxDataComp.setLayout(rl_composite);
		trxDataComp.setSize(setSize.x - 200, setSize.y);
		drawTrxTab();
		
	}
	
	private void drawTrxTab() {

				
		Composite trxComboHolder = new Composite(trxDataComp, SWT.NONE);

		trxCmbSelector = new Combo(trxComboHolder, SWT.DROP_DOWN);
		trxCmbSelector.setSize(new Point(trxDataComp.getSize().x - 600, 23));

		
		trxCmbSelector.add("abcd");
		

		defTable trxTable = new defTable(trxDataComp, SWT.BORDER | SWT.FULL_SELECTION);
		trxTable.get().setLayoutData(new RowData(trxDataComp.getSize().x, trxDataComp.getSize().y - 230));
		trxTable.get().setHeaderVisible(true);
		trxTable.get().setLinesVisible(true);

		Composite trxButtons = new Composite(trxDataComp, SWT.NONE);
		trxButtons.setLayout(new FillLayout(SWT.HORIZONTAL));

		Button trxBtnUpdate = new Button(trxButtons, SWT.NONE);
		trxBtnUpdate.setSize(60, 25);
		trxBtnUpdate.setText("Update");

		
		
	}
	
	
}
