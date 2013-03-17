package pl.altoriis.def;

/**
 * TODO 1) Add transactions based on car usage: reference to car, amount of fuel etc. 
 * TODO 2) Home cost focus: ex. TCO, ROI etc. 
 */

import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import swing2swt.layout.BorderLayout;

public class mainWindow {
	private static defTable dictTable;
	private static TabFolder mainTabFolder;
	private static Composite dictDataComp;
	public static Shell shell;
	private static Point act_size = new Point(1116, 600);
	public static staticData st = new staticData();
	public static Display display;
	private static Button dictBtnUpdate;
	private static Button dictBtnSave;
	private static Button dictBtnDiscard;
	private static Button dictBtnAdd;
	private static Button dictBtnDel;
	//private static Button dictCBDel;
	private static Combo dictCmbSelector;
	public static Label infoBar;

	private void dictUpdateOn() {
		dictBtnUpdate.setEnabled(false);
		dictBtnSave.setEnabled(true);
		dictBtnDiscard.setEnabled(true);
		dictBtnAdd.setEnabled(false);
		dictCmbSelector.setEnabled(false);
		dictBtnDel.setEnabled(false);
		infoBar.setText("");
	};

	private void dictUpdateOff() {
		dictBtnUpdate.setEnabled(true);
		dictBtnSave.setEnabled(false);
		dictBtnDiscard.setEnabled(false);
		dictBtnAdd.setEnabled(true);
		dictCmbSelector.setEnabled(true);
		dictBtnDel.setEnabled(true);

	};

	private void dictBtnsOff() {
		dictBtnUpdate.setEnabled(false);
		dictBtnSave.setEnabled(false);
		dictBtnDiscard.setEnabled(false);
		dictBtnAdd.setEnabled(true);
		dictCmbSelector.setEnabled(true);
		dictBtnDel.setEnabled(false);
		infoBar.setText("");

	};
	
	

	

	private void drawDictTab() {

		TabItem tbtmDictionaries = new TabItem(mainTabFolder, SWT.NONE);
		tbtmDictionaries.setText("S³owniki");
		dictDataComp = new Composite(mainTabFolder, SWT.NONE);
		tbtmDictionaries.setControl(dictDataComp);
		RowLayout rl_composite = new RowLayout(SWT.VERTICAL);
		dictDataComp.setLayout(rl_composite);
		dictDataComp.setSize(act_size.x - 200, act_size.y);

		Composite combo_holder = new Composite(dictDataComp, SWT.NONE);

		dictCmbSelector = new Combo(combo_holder, SWT.DROP_DOWN);
		dictCmbSelector.setSize(new Point(dictDataComp.getSize().x - 600, 23));

		for (int r = 0; r < st.get().size(); r++) {
			dictCmbSelector.add(st.get().get(r).get(0));
		}

		dictTable = new defTable(dictDataComp, SWT.BORDER | SWT.FULL_SELECTION);
		dictTable.get().setLayoutData(new RowData(dictDataComp.getSize().x, dictDataComp.getSize().y - 230));
		dictTable.get().setHeaderVisible(true);
		dictTable.get().setLinesVisible(true);

		Composite buttons = new Composite(dictDataComp, SWT.NONE);
		buttons.setLayout(new FillLayout(SWT.HORIZONTAL));

		dictBtnUpdate = new Button(buttons, SWT.NONE);
		dictBtnUpdate.setSize(60, 25);
		dictBtnUpdate.setText("Update");

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
		
		//dictCBDel = new Button(buttons, SWT.CHECK);
		//dictCBDel.setText("Activate delete");

		dictBtnsOff();

		Listener dictUpdateBtnListener = new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (event.widget == dictBtnUpdate) {
					if (dictTable.addEditor()) {
						dictUpdateOn();
					}
				}
			}
		};
		dictBtnUpdate.addListener(SWT.Selection, dictUpdateBtnListener);

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
															
					if (st.yesNo("Skasowaæ rekord ?","Skasowaæ rekord ?")){
						if (dictTable.deleteLine()) {
							dictBtnsOff();
						}	
					}
					
				}
			}
		};
		dictBtnDel.addListener(SWT.Selection, dictDelBtnListener);
		
		
		
		dictCmbSelector.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			
				
				dictBtnsOff();
				Integer tempIndex = dictCmbSelector.getSelectionIndex();
				//dictTable.clearTable();
				dictTable.addMetaData(
						st.get().get(tempIndex).get(6),
						st.get().get(tempIndex).get(7),
						st.retAsArray(st.get().get(tempIndex).get(4)),
						st.retAsArray(st.get().get(tempIndex).get(3)),
						st.retAsArray(st.get().get(tempIndex).get(2)),
						st.retAsArray(st.get().get(tempIndex).get(5)));
				dictTable.populateTable(getDictTableData(tempIndex));
				
			
				dictTable.get().addSelectionListener(new SelectionListener() {
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {
						dictTable.clearEditor();
						dictUpdateOff();
					}

					@Override
					public void widgetSelected(SelectionEvent arg0) {
						dictTable.clearEditor();
						dictUpdateOff();
					}
				}); // koniec listnera tabeli roboczej
				
			}
		}); // koniec listenera comboboxa

	}
	
	private ArrayList<ArrayList<String>> getDictTableData(Integer indexOf){
		
		db dbCon = new db();
		
		ArrayList<ArrayList<String>> dictTableDataTemp = dbCon.getData(st.get().get(indexOf).get(1));
		//System.out.println(st.get().get(0).get(2));
				
		dbCon.finalize();
		
		return dictTableDataTemp;
	}

	private void setMainMenu() {

		/**
		 * Main menu. For now it is useless. But it shows the idea. Change it.
		 */

		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		MenuItem mntmMenu = new MenuItem(menu, SWT.CASCADE);
		mntmMenu.setText("Menu");

		Menu menu_1 = new Menu(mntmMenu);
		mntmMenu.setMenu(menu_1);

		MenuItem mntmMenu_1 = new MenuItem(menu_1, SWT.NONE);
		mntmMenu_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				dictTable.clearEditor();
				
				
				
			}
		});

		mntmMenu_1.setText("Menu 1");

		MenuItem mntmMenu_2 = new MenuItem(menu_1, SWT.NONE);
		mntmMenu_2.setText("Menu 2");

		/**
		 * End of main menu.
		 */

	}

	private void drawTopPanel() {

		/**
		 * Top tab. For now it is useless. But it shows the idea. Change it.
		 */
		Composite top = new Composite(shell, SWT.NONE);
		top.setLayoutData(BorderLayout.NORTH);
		top.setLayout(new FillLayout(SWT.VERTICAL));

		Label lblDomowyEkspertFinansowy = new Label(top, SWT.BORDER | SWT.SHADOW_IN | SWT.CENTER);
		lblDomowyEkspertFinansowy.setText("Domowy Ekspert Finansowy v. 666");
		infoBar = new Label(top, SWT.BORDER | SWT.CENTER);

		/**
		 * End of top tab.
		 */

	}

	private void drawLeftPanel() {

		/**
		 * Left tab. For now it is useless. But it shows the idea. Change it.
		 */
		Composite left = new Composite(shell, SWT.NONE);
		left.setLayoutData(BorderLayout.WEST);
		RowLayout rl_left = new RowLayout(SWT.VERTICAL);
		rl_left.fill = true;
		rl_left.center = true;
		left.setLayout(rl_left);

		Group grpSuma = new Group(left, SWT.NONE);
		grpSuma.setText("Suma");
		grpSuma.setLayoutData(new RowData(100, 25));

		Label lblNewLabel = new Label(grpSuma, SWT.NONE);
		lblNewLabel.setBounds(10, 18, 55, 15);
		lblNewLabel.setText("5 550,56");

		Group grpEkonto = new Group(left, SWT.NONE);
		grpEkonto.setText("eKonto");
		grpEkonto.setLayoutData(new RowData(100, 25));

		final Label label = new Label(grpEkonto, SWT.NONE);
		label.setText("10 000,00");
		label.setBounds(10, 18, 55, 15);

		Group grpGotwka = new Group(left, SWT.NONE);
		grpGotwka.setText("Got\u00F3wka");
		grpGotwka.setLayoutData(new RowData(100, 25));

		final Label label_1 = new Label(grpGotwka, SWT.NONE);
		label_1.setText("15 004,56");
		label_1.setBounds(10, 18, 55, 15);

		/**
		 * TODO This is just some debug for resizing. remove it in the future.
		 */
		shell.addListener(SWT.Resize, new Listener() {
			@Override
			public void handleEvent(Event e) {
				Rectangle rect = shell.getClientArea();
				act_size = new Point(rect.width, rect.height);
				label_1.setText(Integer.toString(act_size.x));

			}
		});
		/**
		 * End of dummy listener
		 */

		/**
		 * End of left tab.
		 */

	}

	public void open() {
		/**
		 * Open the window and do the MAGIC
		 */

		display = Display.getDefault();
		shell = new Shell();
		shell.setLocation(20, 20);
		shell.setMinimumSize(new Point(200, 200));
		shell.setSize(act_size);
		shell.setText("DEF");
		shell.setLayout(new BorderLayout(5, 5));

		setMainMenu();
		drawTopPanel();
		drawLeftPanel();

		mainTabFolder = new TabFolder(shell, SWT.NONE);
		mainTabFolder.setLayoutData(null);
		drawDictTab();

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}

		}

		/**
		 * End of open method
		 */

	}

	public static void main(String[] args) {

		/**
		 * Launch the application.
		 * 
		 * @param args
		 */
		try {
			mainWindow window = new mainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
