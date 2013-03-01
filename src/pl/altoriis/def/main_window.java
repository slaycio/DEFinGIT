package pl.altoriis.def;


import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.events.SelectionAdapter;

public class main_window {
	private static defTable defTable ;
	private static TabFolder tabFolder;
	private static Composite dict_data_comp;
	private static Shell shell;
	private static Point act_size = new Point(1116, 600);
	private static staticData st = new staticData();

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */

	
	public static void draw_center() {
		tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setLayoutData(null);

		TabItem tbtmOpcjaNumer = new TabItem(tabFolder, SWT.NONE);
		tbtmOpcjaNumer.setText("Opcja numer 1");

		TabItem tbtmOpcjaNumer_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmOpcjaNumer_1.setText("Opcja numer 2");

		TabItem tbtmDictionaries = new TabItem(tabFolder, SWT.NONE);
		tbtmDictionaries.setText("S³owniki");

		dict_data_comp = new Composite(tabFolder, SWT.NONE);

		tbtmDictionaries.setControl(dict_data_comp);
		RowLayout rl_composite = new RowLayout(SWT.VERTICAL);
		dict_data_comp.setLayout(rl_composite);
		dict_data_comp.setSize(act_size.x - 200, act_size.y);

		Composite combo_holder = new Composite(dict_data_comp, SWT.NONE);

		final Combo cmb1 = new Combo(combo_holder, SWT.DROP_DOWN);
		cmb1.setSize(new Point(dict_data_comp.getSize().x - 600, 23));

		
		for (int r = 0; r < st.get().size(); r++) {
			cmb1.add(st.get().get(r).get(0));
		}

		defTable = new defTable(dict_data_comp, SWT.BORDER | SWT.FULL_SELECTION);
		defTable.get().setLayoutData(new RowData(dict_data_comp.getSize().x, dict_data_comp.getSize().y - 200));
		defTable.get().setHeaderVisible(true);
		defTable.get().setLinesVisible(true);
		
		Composite buttons = new Composite(dict_data_comp, SWT.NONE);
		buttons.setLayout(new FillLayout(SWT.HORIZONTAL));

		final Button btnUpdate = new Button(buttons, SWT.NONE);
		btnUpdate.setSize(60, 25);
		btnUpdate.setText("Update");
		btnUpdate.setEnabled(false);

		final Button btnSave = new Button(buttons, SWT.NONE);
		btnSave.setSize(60, 25);
		btnSave.setText("Save");
		btnSave.setEnabled(false);

		final Button btnDiscard = new Button(buttons, SWT.NONE);
		btnDiscard.setSize(60, 25);
		btnDiscard.setText("Discard");
		btnDiscard.setEnabled(false);

		Listener updateBtnListener = new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (event.widget == btnUpdate) {

					btnUpdate.setEnabled(false);
					btnSave.setEnabled(true);
					btnDiscard.setEnabled(true);
					cmb1.setEnabled(false);
					
					defTable.addEditor();

				}
			}
		};

		Listener saveBtnListener = new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (event.widget == btnSave) {

					defTable.clearEditor();
					btnUpdate.setEnabled(true);
					btnSave.setEnabled(false);
					btnDiscard.setEnabled(false);
					cmb1.setEnabled(true);
				}
			}
		};

		Listener discardBtnListener = new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (event.widget == btnDiscard) {

					defTable.clearEditor();
					btnUpdate.setEnabled(true);
					btnSave.setEnabled(false);
					btnDiscard.setEnabled(false);
					cmb1.setEnabled(true);
				}
			}
		};

		btnUpdate.addListener(SWT.Selection, updateBtnListener);
		btnSave.addListener(SWT.Selection, saveBtnListener);
		btnDiscard.addListener(SWT.Selection, discardBtnListener);

		cmb1.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				defTable.get().removeAll();
				defTable.get().setRedraw(false);
				btnUpdate.setEnabled(false);
				btnSave.setEnabled(false);
				btnDiscard.setEnabled(false);
				
				
				while (defTable.get().getColumnCount() > 0) {
					defTable.get().getColumns()[0].dispose();
				}

				// -- polaczenie do bazy
				db dbCon = new db();
				
				ArrayList<ArrayList<String>> lista = dbCon.getData(
						st.get().get(cmb1.getSelectionIndex()).get(1)
						);

			
				
				for (int a = 0; a < lista.get(0).size();a++){
					TableColumn tNColumn = new TableColumn(defTable.get(), SWT.NONE);
					tNColumn.setWidth((defTable.get().getSize().x / lista.get(0).size() ) - 2);
					tNColumn.setText(lista.get(0).get(a));
					}
				
				
				defTable.get().setRedraw(true);

				for (int w = 1; w < lista.size(); w++) {
					TableItem item = new TableItem(defTable.get(), SWT.NONE);
					for(int g = 0;g < lista.get(w).size();g++){
						item.setText(g,lista.get(w).get(g));
					}	
				}
		
			 
				defTable.get().setData(lista.toArray());
				
				defTable.get().addSelectionListener(new SelectionListener() {

					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {
						btnUpdate.setEnabled(true);
						btnSave.setEnabled(false);
						btnDiscard.setEnabled(false);
						}

					@Override
					public void widgetSelected(SelectionEvent arg0) {

						//if (defTable.isEdited) 
							defTable.clearEditor();

						btnUpdate.setEnabled(true);
						btnSave.setEnabled(false);
						btnDiscard.setEnabled(false);
						cmb1.setEnabled(true);

					}}); // koniec listnera tabeli roboczej
				dbCon.finalize();
			}}); // koniec listenera comboboxa

	}

	// ////////////////////MAIN////////////////////////////////

	public static void main(String[] args) {
		try {
			main_window window = new main_window();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window and do the MAGIC
	 */
	public void open() {
		Display display = Display.getDefault();
		shell = new Shell();
		shell.setLocation(20, 20);
		shell.setMinimumSize(new Point(200, 200));
		shell.setSize(act_size);
		shell.setText("DEF");
		shell.setLayout(new BorderLayout(5, 5));

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
				// Listener dla menu 1
				defTable.clearEditor();
		}});
		
		mntmMenu_1.setText("Menu 1");

		MenuItem mntmMenu_2 = new MenuItem(menu_1, SWT.NONE);
		mntmMenu_2.setText("Menu 2");

		Composite top = new Composite(shell, SWT.NONE);
		top.setLayoutData(BorderLayout.NORTH);
		top.setLayout(new FillLayout(SWT.HORIZONTAL));

		Label lblDomowyEkspertFinansowy = new Label(top, SWT.BORDER | SWT.SHADOW_IN | SWT.CENTER);
		lblDomowyEkspertFinansowy.setText("Domowy Ekspert Finansowy v. 666");

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

		draw_center();
		shell.addListener(SWT.Resize, new Listener() {
			@Override
			public void handleEvent(Event e) {
				Rectangle rect = shell.getClientArea();
				act_size = new Point(rect.width, rect.height);
				label_1.setText(Integer.toString(act_size.x));

			}
		});

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}

		}
	}
}
