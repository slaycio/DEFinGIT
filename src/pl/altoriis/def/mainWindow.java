package pl.altoriis.def;

/**
 * TODO 1) Add transactions based on car usage: reference to car, amount of fuel etc. 
 * TODO 2) Home cost focus: TCO, ROI etc. 
 * TODO Dynamic resize 
 */


import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import swing2swt.layout.BorderLayout;
import org.eclipse.wb.swt.SWTResourceManager;

public class mainWindow {
	
	private String defVer ="alpha 0.01";
	public static mainWindow window;
	public Shell shell;
	public TabFolder mainTabFolder;
	public Integer mX = 500;
	public Integer mY = 500;
    public Display display;
		

	public static mainWindow get() {
		return window;
	}
	
	private void setMainMenu() {

		/**
		 * TODO Main menu. For now it is useless. But it shows the idea. Change it.
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
		 * TODO Top tab. For now it is useless. But it shows the idea. Change it.
		 */
		Composite top = new Composite(shell, SWT.NONE);
		top.setLayoutData(BorderLayout.NORTH);
		top.setLayout(new FillLayout(SWT.VERTICAL));

		Label lblDomowyEkspertFinansowy = new Label(top, SWT.BORDER | SWT.CENTER);
		lblDomowyEkspertFinansowy.setText("Domowy Ekspert Finansowy");
		
		/**
		 * End of top tab.
		 */

	}
	
	private void drawBottomPanel() {

		/**
		 * TODO Bottom tab. For now it is useless. But it shows the idea. Change it.
		 */
		Composite Bottom = new Composite(shell, SWT.NONE);
		Bottom.setLayoutData(BorderLayout.SOUTH);
		Bottom.setLayout(new FillLayout(SWT.VERTICAL));

		
		Label tempLbl = new Label(Bottom, SWT.NONE | SWT.RIGHT);
		//tempLbl.setBackground(new Color(mainWindow.get().display,255,255,255));
		tempLbl.setText("(c) p.     DEF "+defVer+"   ");
	

		/**
		 * End of Bottom tab.
		 */

	}
	

	private void drawLeftPanel() {

		/**
		 * TODO Left tab. For now it is useless. But it shows the idea. Change it.
		 */
		Composite left = new Composite(shell, SWT.NONE);
		left.setLayoutData(BorderLayout.WEST);
		RowLayout rl_left = new RowLayout(SWT.VERTICAL);
		rl_left.fill = true;
		rl_left.center = true;
		left.setLayout(rl_left);

		Group grpSuma = new Group(left, SWT.NONE);
		grpSuma.setText("Shell");
		grpSuma.setLayoutData(new RowData(100, 25));

		final Label lblNewLabel = new Label(grpSuma, SWT.NONE);
		lblNewLabel.setBounds(5, 15, 90, 15);
		lblNewLabel.setText("5 550,56");

		Group grpEkonto = new Group(left, SWT.NONE);
		grpEkonto.setText("Tabela");
		grpEkonto.setLayoutData(new RowData(100, 25));

		final Label label = new Label(grpEkonto, SWT.NONE);
		label.setText("10 000,00");
		label.setBounds(5, 15, 90, 15);

		Group grpGotwka = new Group(left, SWT.NONE);
		grpGotwka.setText("Inne");
		grpGotwka.setLayoutData(new RowData(100, 25));

		final Label label_1 = new Label(grpGotwka, SWT.NONE);
		label_1.setText("15 004,56");
		label_1.setBounds(5, 15, 90, 15);

		/**
		 * TODO This is just some debug for resizing. remove it in the future.
		 */
		shell.addListener(SWT.Resize, new Listener() {
			@Override
			public void handleEvent(Event e) {
				
				lblNewLabel.setText("w:"+shell.getClientArea().width+" h:"+shell.getClientArea().height);
				label.setText("w:"+shell.getClientArea().width+" h:"+shell.getClientArea().height);
				label_1.setText("w:"+shell.getClientArea().width+" h:"+shell.getClientArea().height);
				
			}
		});
		/**
		 * End of dummy listener
		 */

		/**
		 * End of left tab.
		 */

	}

	private void drawMainPanel() {
		
		mainTabFolder = new TabFolder(shell, SWT.NONE);
		mainTabFolder.setLayoutData(null);
		mainTabFolder.setSize(sD.a2p(shell.getClientArea(),100,100));
		//trxTabX = 
				new trxTab (mainTabFolder, SWT.NONE);
		//dictTabX = 
				new dictTab(mainTabFolder, SWT.NONE);	
		
	}
	
	public void open() {
		/**
		 * Open the window and do the MAGIC
		 */

		display = Display.getDefault();
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(mainWindow.class, "/pl/altoriis/def/DivineIntervention.jpg"));
		shell.setMinimumSize(1000,600);
		shell.setBounds(mX/2,mY/2,display.getClientArea().width-mX,display.getClientArea().height-mY);
		shell.setText("DEF");
		shell.setLayout(new BorderLayout(5, 5));

		setMainMenu();
		drawTopPanel();
		drawLeftPanel();
		drawBottomPanel();
		drawMainPanel();
			
		
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
		 */
		try {
			window = new mainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
