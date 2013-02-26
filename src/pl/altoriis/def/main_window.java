package pl.altoriis.def;

import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.events.SelectionAdapter;

public class main_window {
	private static Table dict_data;
	private static Table new_dict_data;
	private static TabFolder tabFolder;
	private static Composite dict_data_comp;
	private static Shell shell;
	private static Point act_size = new Point(1116, 600);
	private static Integer kaunt = 0;
	private static Integer kaunt2 = 0;
	private static ArrayList<Combo> arCombo = new ArrayList<Combo>();
	private static ArrayList<TableEditor> arEditor = new ArrayList<TableEditor>();
	private static static_data st = new static_data();

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
		cmb1.setSize(new Point(dict_data_comp.getSize().x - 200, 23));

	
		for (int r = 0; r < st.slownik.size(); r++) {

			cmb1.add(st.slownik.get(r).get(0).get(0));

		}

		dict_data = new Table(dict_data_comp, SWT.BORDER | SWT.FULL_SELECTION);
		dict_data.setLayoutData(new RowData(dict_data_comp.getSize().x, 200));
		dict_data.setHeaderVisible(true);
		dict_data.setLinesVisible(true);

		new_dict_data = new Table(dict_data_comp, SWT.BORDER | SWT.FULL_SELECTION);
		new_dict_data.setLinesVisible(true);
		new_dict_data.setLayoutData(new RowData(dict_data_comp.getSize().x, 20));
		new_dict_data.setHeaderVisible(false);

		Composite buttons = new Composite(dict_data_comp, SWT.NONE);
		buttons.setLayout(new FillLayout(SWT.HORIZONTAL));

		final Button btnUpdate = new Button(buttons, SWT.NONE);
		btnUpdate.setSize(60, 25);
		btnUpdate.setText("Update");
		btnUpdate.setEnabled(false);

		final Button btnDiscard = new Button(buttons, SWT.NONE);
		btnDiscard.setSize(60, 25);
		btnDiscard.setText("Discard");
		btnDiscard.setEnabled(false);

		// label do wyswietlenie ze nacislem
		final Label lblNewLabel_1 = new Label(dict_data_comp, SWT.NONE);
		lblNewLabel_1.setLayoutData(new RowData(344, SWT.DEFAULT));

		final Label lblNewLabel_2 = new Label(dict_data_comp, SWT.NONE);
		lblNewLabel_2.setLayoutData(new RowData(344, SWT.DEFAULT));

		// label do wyswietlenie ze nacislem

		Listener updateBtnListener = new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (event.widget == btnUpdate) {

					kaunt++;
					lblNewLabel_1.setText("nacis³em update " + Integer.toString(kaunt)); // label do wyswietlenie ze nacislem
																							
				}
			}
		};
		Listener discardBtnListener = new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (event.widget == btnDiscard) {

					kaunt2++; 
					lblNewLabel_2.setText("nacis³em discard " + Integer.toString(kaunt2)); // label do wyswietlenie ze nacislem
					btnUpdate.setEnabled(false);
					btnDiscard.setEnabled(false);
				}
			}
		};

		btnUpdate.addListener(SWT.Selection, updateBtnListener);
		btnDiscard.addListener(SWT.Selection, discardBtnListener);

		// dict_data.removeAll();
		// new_dict_data.removeAll();

		cmb1.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {


				dict_data.removeAll();
				new_dict_data.removeAll();

				dict_data.setRedraw(false);
				while (dict_data.getColumnCount() > 0) {
					dict_data.getColumns()[0].dispose();
				}

				for (int a = 0; a < Integer.parseInt(st.slownik.get(cmb1.getSelectionIndex()).get(1).get(0));

						 a++) {

					TableColumn tblclmnNewColumn = new TableColumn(dict_data, SWT.NONE);
					tblclmnNewColumn.setWidth((dict_data.getSize().x / Integer.parseInt(st.slownik.get(cmb1.getSelectionIndex()).get(1).get(0))) - 2);
					tblclmnNewColumn.setText(
							st.slownik.get(cmb1.getSelectionIndex()).get(1).get(a+1)

							);

				}
				dict_data.setRedraw(true);

				new_dict_data.setRedraw(false);
				while (new_dict_data.getColumnCount() > 0) {
					new_dict_data.getColumns()[0].dispose();
				}

				for (int a = 0; a < 
	
						Integer.parseInt(st.slownik.get(cmb1.getSelectionIndex()).get(1).get(0))
						; a++) {

					TableColumn newtblclmnNewColumn = new TableColumn(new_dict_data, SWT.NONE);
					newtblclmnNewColumn.setWidth((new_dict_data.getSize().x / Integer.parseInt(st.slownik.get(cmb1.getSelectionIndex()).get(1).get(0))) - 2);

				}
				new_dict_data.setRedraw(true);

				// -- polaczenie do bazy
				db db_con = new db();
				// pobranie danych o typach kont zwraca 2 kolumny i ilestam rekordow


				String[][] lista = db_con.get_data(
						st.slownik.get(cmb1.getSelectionIndex()).get(2).get(0)		
								,
	
						Integer.parseInt(st.slownik.get(cmb1.getSelectionIndex()).get(1).get(0))
						);

				for (int w = 0; w < lista.length; w++) {
					TableItem item = new TableItem(dict_data, SWT.NONE);
					item.setText(lista[w]);
				}

				dict_data.addListener(SWT.Selection, new Listener() {
					@Override
					public void handleEvent(Event e) {
						TableItem[] selection = dict_data.getSelection();
						new_dict_data.removeAll();
						TableItem updated = new TableItem(new_dict_data, SWT.NONE);

						for (int k = 0; k < 
								Integer.parseInt(st.slownik.get(cmb1.getSelectionIndex()).get(1).get(0))
								; k++) {
							updated.setText(k, selection[0].getText(k));
							btnUpdate.setEnabled(false);
							btnDiscard.setEnabled(false);

						}

					}
				});

				// ten listener powduje bledy
				new_dict_data.addListener(SWT.MouseDown, new Listener() {
					@Override
					public void handleEvent(Event event) {

						/*
						 * Boolean checkTables[] = new
						 * Boolean[new_dict_data.getColumnCount()];
						 * 
						 * for (int i=0; i<dict_data.getColumnCount(); i++) {
						 * 
						 * checkTables[i] =
						 * dict_data.getItem(dict_data.getSelectionIndex
						 * ()).getText(i) ==
						 * new_dict_data.getItem(new_dict_data.
						 * getSelectionIndex()).getText(i);
						 * 
						 * }
						 * 
						 * 
						 * if (Arrays.asList(checkTables).contains(false)) {
						 * 
						 * lblNewLabel_1.setText(dict_data.getItem(dict_data.
						 * getSelectionIndex()).getText(0)); // label do
						 * wyswietlenie ze nacislem
						 * lblNewLabel_2.setText(new_dict_data
						 * .getItem(new_dict_data
						 * .getSelectionIndex()).getText(0)); // label do
						 * wyswietlenie ze nacislem
						 * 
						 * btnUpdate.setEnabled(true);
						 * btnDiscard.setEnabled(true); }
						 */
						// edytor

						arCombo.clear();
						arEditor.clear();
						for (int z = 0; z < dict_data.getColumnCount(); z++) {
							arCombo.add(new Combo(dict_data, SWT.NONE));
							arCombo.get(z).add("item " + z);
							arEditor.add(new TableEditor(new_dict_data));
						}

						TableItem[] items = dict_data.getItems();
						for (int t = 0; t < items.length; t++) {
							for (int w = 0; w < dict_data.getColumnCount(); w++) {

								arCombo.get(w).setText(items[t].getText(w));
								arEditor.get(w).grabHorizontal = true;
								arEditor.get(w).grabVertical = true;
								arEditor.get(w).setEditor(arCombo.get(w), items[t], w);
							}

						}

						// koniec edytora

					}
				});

			} // tu sie konczy wewnetrzny selection event -- przed tym trzeba
				// wstawic kod by by³ uruchomiony po zmianie w comboboxie
		}); // tu sie konczy listener dla comboboxa

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
		shell.setLayout(new BorderLayout(0, 0));

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
				// /////////////// TUTAJ WSTAWIAJ
				for (int u = 0; u < arEditor.size(); u++) {
					arEditor.get(u).dispose();
				}
				for (int u = 0; u < arCombo.size(); u++) {
					arCombo.get(u).dispose();
				}
				arEditor.clear();
				arCombo.clear();

			}
		});
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
