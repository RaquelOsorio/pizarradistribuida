/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rmihw;

import java.util.Enumeration;
import java.util.Hashtable;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import rmihw.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
//import model.*;
//import controller.*;


/**
 *
 * @author alonso
 */
public class MainWindowView implements Observer  {
    private Display display;
	private ServerHandler serverHandler;

	private MainWindowController controller;

	//Widgets de la ventana
	private Shell shell = new Shell(this.display);
	/*Button reserve = new Button(shell, SWT.PUSH);
    Button updateAgenda = new Button(shell, SWT.PUSH);
	Table entradasTabla = new Table(grilla, SWT.BORDER);*/

    //Pizarras propias del usuario
    private TabFolder tabFolder = new TabFolder(shell,SWT.BORDER);
    private Composite compOwnedBlackboards = new Composite(tabFolder, SWT.BORDER);
    private TabItem tabItemOwnedBlackboards = new TabItem(tabFolder,SWT.NONE);
    private Button addOwnedBlackboard = new Button(compOwnedBlackboards, SWT.PUSH);
    private Button openSelectedOwnedBlackboards = new Button(compOwnedBlackboards, SWT.PUSH);

    private Composite compOtherBlackboards = new Composite(tabFolder, SWT.BORDER);
    private TabItem tabItemOtherBlackboards = new TabItem(tabFolder,SWT.NONE);
    private Button openSelectedOtherBlackboards = new Button(compOtherBlackboards, SWT.PUSH);
    private Button updateListOtherBlackboards = new Button(compOtherBlackboards, SWT.PUSH);


    private Table tableListOwnedBlackboards = new Table(compOwnedBlackboards, SWT.BORDER);
    private Table tableListOtherBlackboards = new Table(compOtherBlackboards, SWT.BORDER);

    private Menu menuBar = new Menu(shell, SWT.BAR);
    private MenuItem fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
    private Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
    private MenuItem fileExitItem = new MenuItem(fileMenu, SWT.PUSH);

 	public MainWindowView(Display display, ServerHandler serverHandler, boolean serverIsOn) {

		this.display = display;
		this.serverHandler = serverHandler;
		this.serverHandler.registrar(this);
		//Conectar controlador
		this.connectController(this.createController());

		//Dibujar la ventana
		this.createWindow(serverIsOn);

	}

    //Dibujamos la ventana y sus componentes
	public void createWindow(boolean serverIsOn){

		//Creacion de la ventana y su respectivo objeto para Layout
		//Shell shell = new Shell(this.display);
        shell.setText("Pizarras");
		shell.setLayout(new FormLayout());

        //Barra de Menu
        fileMenuHeader.setText("&Opciones");
        fileMenuHeader.setMenu(fileMenu);
        fileExitItem.setText("&Salir");

        //Tabs para las listas de pizarras propias
        tabItemOwnedBlackboards.setText("Pizarras Propias");
        tabItemOwnedBlackboards.setControl(compOwnedBlackboards);
        compOwnedBlackboards.setLayout(new FormLayout());
        
        //Botones Tab Pizarras Propias
        addOwnedBlackboard.setText("Crear");
        openSelectedOwnedBlackboards.setText("Abrir");
        
        //FormLayout para la pestana Pizarras Propias
        TableColumn nombreOwnedBlackboard = new TableColumn(tableListOwnedBlackboards,SWT.LEFT);
		nombreOwnedBlackboard.setText("Nombre");
		nombreOwnedBlackboard.setWidth(120);
        tableListOwnedBlackboards.setHeaderVisible(true);
        tableListOwnedBlackboards.setLinesVisible(true);

        FormData formFillOwned1 = new FormData();
		formFillOwned1.top = new FormAttachment(0, 10);
		formFillOwned1.bottom = new FormAttachment(100, -10);
        formFillOwned1.left = new FormAttachment(0, 10);
		formFillOwned1.right = new FormAttachment(80, 0);
        tableListOwnedBlackboards.setLayoutData(formFillOwned1);

        FormData formFillOwned2 = new FormData();
		formFillOwned2.top = new FormAttachment(0, 10);
		formFillOwned2.bottom = new FormAttachment(10, 0);
        formFillOwned2.left = new FormAttachment(80, 10);
		formFillOwned2.right = new FormAttachment(100, -10);
        addOwnedBlackboard.setLayoutData(formFillOwned2);

        FormData formFillOwned3 = new FormData();
		formFillOwned3.top = new FormAttachment(10, 10);
		formFillOwned3.bottom = new FormAttachment(20, 0);
        formFillOwned3.left = new FormAttachment(80, 10);
		formFillOwned3.right = new FormAttachment(100, -10);
        openSelectedOwnedBlackboards.setLayoutData(formFillOwned3);



        //Tabs para las listas de las otras pizarras
        tabItemOtherBlackboards.setText("Otras Pizarras");
        tabItemOtherBlackboards.setControl(compOtherBlackboards);
        compOtherBlackboards.setLayout(new FormLayout());

        //Botones Tab Otras Pizarras
        openSelectedOtherBlackboards.setText("Abrir");
        updateListOtherBlackboards.setText("Actualizar Lista");

        //FormLayout para la pestana Otras Pizarras
        TableColumn nombreOtherBlackboard = new TableColumn(tableListOtherBlackboards,SWT.LEFT);
		nombreOtherBlackboard.setText("Nombre");
		nombreOtherBlackboard.setWidth(120);
        TableColumn ownerOtherBlackboard = new TableColumn(tableListOtherBlackboards,SWT.LEFT);
		ownerOtherBlackboard.setText("Propietario");
		ownerOtherBlackboard.setWidth(120);
        tableListOtherBlackboards.setHeaderVisible(true);
        tableListOtherBlackboards.setLinesVisible(true);

        FormData formFillOther1 = new FormData();
		formFillOther1.top = new FormAttachment(0, 10);
		formFillOther1.bottom = new FormAttachment(100, -10);
        formFillOther1.left = new FormAttachment(0, 10);
		formFillOther1.right = new FormAttachment(80, 0);
        tableListOtherBlackboards.setLayoutData(formFillOther1);

        FormData formFillOther2 = new FormData();
		formFillOther2.top = new FormAttachment(0, 10);
		formFillOther2.bottom = new FormAttachment(10, 0);
        formFillOther2.left = new FormAttachment(80, 10);
		formFillOther2.right = new FormAttachment(100, -10);
        updateListOtherBlackboards.setLayoutData(formFillOther2);

        FormData formFillOther3 = new FormData();
		formFillOther3.top = new FormAttachment(10, 10);
		formFillOther3.bottom = new FormAttachment(20, 0);
        formFillOther3.left = new FormAttachment(80, 10);
		formFillOther3.right = new FormAttachment(100, -10);
        openSelectedOtherBlackboards.setLayoutData(formFillOther3);


        //FormLayout para la Ventana
        FormData formFillWindow = new FormData();
		formFillWindow.top = new FormAttachment(0, 10);
		formFillWindow.left = new FormAttachment(0, 10);
		formFillWindow.bottom = new FormAttachment(100, -10);
		formFillWindow.right = new FormAttachment(100,-10);
		tabFolder.setLayoutData(formFillWindow);

        shell.setMenuBar(menuBar);
	    shell.pack();
	    shell.open();
        if(!serverIsOn){
            MessageBox alert = new MessageBox(shell);
            alert.setMessage("No se pudo conectar con el servidor");
            alert.open();
            shell.dispose();
        }
	    while (!shell.isDisposed()) {
	      if (!display.readAndDispatch()) {
	        display.sleep();
	      }
	    }
	    display.dispose();

	}

	public void connectController(MainWindowController controller){

		//Primero borramos cualquier captura de evento asociada anteriormente
		if(this.controller!=null){
            this.addOwnedBlackboard.removeSelectionListener(null);
            this.openSelectedOtherBlackboards.removeSelectionListener(null);
            this.openSelectedOwnedBlackboards.removeSelectionListener(null);
            this.updateListOtherBlackboards.removeSelectionListener(null);
            this.fileExitItem.removeSelectionListener(null);
		}
		this.controller = controller;

		//Añadimos captura de evento a los botones
		this.addOwnedBlackboard.addSelectionListener(controller);
        this.openSelectedOtherBlackboards.addSelectionListener(controller);
        this.openSelectedOwnedBlackboards.addSelectionListener(controller);
        this.updateListOtherBlackboards.addSelectionListener(controller);
        this.fileExitItem.addSelectionListener(controller);

	}

	private MainWindowController createController(){
		return new MainWindowController(this,serverHandler);
	}

    //updateUserList()
    public void update() {
		// Actualizar grilla con elementos de la agenda
        System.out.println("Actualizacion Lista de Pizarras del Usuario");

        this.tableListOwnedBlackboards.clearAll();
        this.tableListOwnedBlackboards.setItemCount(0);

        //Ordenamos elementos de la tabla
        //Generamos el arreglo para luego ordenarlo
        Hashtable listOfBlackboardsOwn = this.serverHandler.getUsuario().getOwnBlackboards();
        Blackboard[] blackboardsOrdenados = new Blackboard[listOfBlackboardsOwn.size()];

        int i = 0;
        for(Enumeration e = listOfBlackboardsOwn.keys(); e.hasMoreElements();){
            String clave = (String) e.nextElement();
            blackboardsOrdenados[i] = (Blackboard) listOfBlackboardsOwn.get(clave);
            i++;
        }
        for(i=0;i<blackboardsOrdenados.length;i++){
            for(int j=i;j<blackboardsOrdenados.length;j++){
                if(blackboardsOrdenados[i].getNombre().compareTo(blackboardsOrdenados[j].getNombre())>0){
                    Blackboard blackboardAux = blackboardsOrdenados[i];
                    blackboardsOrdenados[i] = blackboardsOrdenados[j];
                    blackboardsOrdenados[j] = blackboardAux;
                }
            }
        }

        for(i=0;i<blackboardsOrdenados.length;i++){
            TableItem tableItem= new TableItem(tableListOwnedBlackboards, SWT.NONE);
            tableItem.setText(new String[] {blackboardsOrdenados[i].getNombre()});
        }

	}

	public ServerHandler getServerHandler() {
		return serverHandler;
	}

	public void setServerHandler(ServerHandler serverHandler) {
		this.serverHandler = serverHandler;
	}

    public Button getAddOwnedBlackboard() {
        return addOwnedBlackboard;
    }

    public Composite getCompOtherBlackboards() {
        return compOtherBlackboards;
    }

    public Composite getCompOwnedBlackboards() {
        return compOwnedBlackboards;
    }

    public MainWindowController getController() {
        return controller;
    }

    public Display getDisplay() {
        return display;
    }

    public MenuItem getFileExitItem() {
        return fileExitItem;
    }

    public Menu getFileMenu() {
        return fileMenu;
    }

    public MenuItem getFileMenuHeader() {
        return fileMenuHeader;
    }

    public Menu getMenuBar() {
        return menuBar;
    }

    public Button getOpenSelectedOtherBlackboards() {
        return openSelectedOtherBlackboards;
    }

    public Button getOpenSelectedOwnedBlackboards() {
        return openSelectedOwnedBlackboards;
    }

    public Shell getShell() {
        return shell;
    }

    public TabFolder getTabFolder() {
        return tabFolder;
    }

    public TabItem getTabItemOtherBlackboards() {
        return tabItemOtherBlackboards;
    }

    public TabItem getTabItemOwnedBlackboards() {
        return tabItemOwnedBlackboards;
    }

    public Table getTableListOtherBlackboards() {
        return tableListOtherBlackboards;
    }

    public Table getTableListOwnedBlackboards() {
        return tableListOwnedBlackboards;
    }

    public Button getUpdateListOtherBlackboards() {
        return updateListOtherBlackboards;
    }
    

}
