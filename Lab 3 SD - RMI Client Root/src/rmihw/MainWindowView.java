/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rmihw;

import java.util.Enumeration;
import java.util.Hashtable;
import rmihw.*;
//import model.*;
//import controller.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 *
 * @author alonso
 */
public class MainWindowView implements Observer  {
    private Display display;
	private ServerHandler serverHandler;

	private MainWindowController controller;

	//Widgets de la ventana
	Shell shell = new Shell(this.display);
	/*Button reserve = new Button(shell, SWT.PUSH);
    Button updateAgenda = new Button(shell, SWT.PUSH);
	Table entradasTabla = new Table(grilla, SWT.BORDER);*/

    Button addUser = new Button(shell, SWT.PUSH);
    Button updateUser = new Button(shell, SWT.PUSH);
    Button removeUser = new Button(shell, SWT.PUSH);
    Button updateUserList = new Button(shell, SWT.PUSH);
	Composite userGrid = new Composite(shell, SWT.BORDER);
	Table userList = new Table(userGrid, SWT.BORDER);

	public MainWindowView(Display display, ServerHandler agenda, boolean serverIsOn) {
		this.display = display;
		this.serverHandler = agenda;
		this.serverHandler.registrar(this);
		//Conectar controlador
		this.connectController(this.createController());

		//Dibujar la ventana
		this.createWindowRoot(serverIsOn);

	}

    //Dibujamos la ventana y sus componentes
	public void createWindowRoot(boolean serverIsOn){

		//Creacion de la ventana y su respectivo objeto para Layout
		//Shell shell = new Shell(this.display);
        shell.setText("Mantencion de Usuarios - Administrador");
		shell.setLayout(new FormLayout());

        addUser.setText("Agregar Usuario");
		updateUser.setText("Editar Usuario");
        removeUser.setText("Eliminar Usuario");
        updateUserList.setText("Actualizar Lista");

		//Composite grilla = new Composite(shell, SWT.BORDER);
		userGrid.setLayout(new FillLayout(SWT.VERTICAL));

		//Creacion de la tabla (Grilla)
		userList.setLinesVisible(true);
		userList.setHeaderVisible(true);

		//Creacion de las columnas de la tabla
		TableColumn nombres = new TableColumn(userList,SWT.LEFT);
		nombres.setText("Nombres");
		nombres.setWidth(120);

		TableColumn apellidos = new TableColumn(userList,SWT.LEFT);
		apellidos.setText("Apellidos");
		apellidos.setWidth(120);

		TableColumn userName = new TableColumn(userList,SWT.LEFT);
		userName.setText("Username");
		userName.setWidth(120);

        TableColumn password = new TableColumn(userList,SWT.LEFT);
		password.setText("Password");
		password.setWidth(120);
        password.setWidth(0);

		TableColumn descripcion = new TableColumn(userList,SWT.LEFT);
		descripcion.setText("Descripcion");
		descripcion.setWidth(120);

		//Ubicacion y tamagno proporcional de la grilla
		FormData formFill1 = new FormData();
		formFill1.top = new FormAttachment(0, 10);
		formFill1.left = new FormAttachment(0, 10);
		formFill1.bottom = new FormAttachment(90, 0);
		formFill1.right = new FormAttachment(80,0);
		userGrid.setLayoutData(formFill1);

        //Ubicacion y tamagno proporcional del boton "Actualizar Lista"
		FormData formFill2 = new FormData();
		formFill2.top = new FormAttachment(0, 10);
		formFill2.bottom = new FormAttachment(10, 0);
        formFill2.left = new FormAttachment(80, 10);
		formFill2.right = new FormAttachment(100,-10);
        updateUserList.setLayoutData(formFill2);

		//Ubicacion y tamagno proporcional del boton "Agregar Usuario"
		FormData formFill3 = new FormData();
		formFill3.top = new FormAttachment(10, 10);
		formFill3.bottom = new FormAttachment(20, 0);
        formFill3.left = new FormAttachment(80, 10);
		formFill3.right = new FormAttachment(100,-10);
		addUser.setLayoutData(formFill3);

        //Ubicacion y tamagno proporcional del boton "Actualizar Usuario"
		FormData formFill4 = new FormData();
		formFill4.top = new FormAttachment(20, 10);
		formFill4.bottom = new FormAttachment(30, 0);
        formFill4.left = new FormAttachment(80, 10);
		formFill4.right = new FormAttachment(100,-10);
		updateUser.setLayoutData(formFill4);

        //Ubicacion y tamagno proporcional del boton "Actualizar Usuario"
		FormData formFill5 = new FormData();
		formFill5.top = new FormAttachment(30, 10);
		formFill5.bottom = new FormAttachment(40, 0);
        formFill5.left = new FormAttachment(80, 10);
		formFill5.right = new FormAttachment(100,-10);
		removeUser.setLayoutData(formFill5);

	    shell.pack();
	    shell.open();
        if(!serverIsOn){
            MessageBox alert = new MessageBox(shell);
            alert.setMessage("No se pudo conectar con el servidor");
            int result = alert.open();
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
            this.updateUserList.removeSelectionListener(null);
            this.addUser.removeSelectionListener(null);
            this.updateUser.removeSelectionListener(null);
            this.removeUser.removeSelectionListener(null);
		}
		this.controller = controller;

		//Añadimos captura de evento al boton "Reservar una hora"
		this.updateUserList.addSelectionListener(controller);
        this.addUser.addSelectionListener(controller);
        this.updateUser.addSelectionListener(controller);
        this.removeUser.addSelectionListener(controller);


	}

	private MainWindowController createController(){
		return new MainWindowController(this,serverHandler);
	}

    //updateUserList()
    public void update() {
		// Actualizar grilla con elementos de la agenda
        System.out.println("Actualizacion Lista de Usuarios");

        userList.clearAll();
        userList.setItemCount(0);

        //Ordenamos elementos de la tabla
        //Generamos el arreglo para luego ordenarlo
        Hashtable listOfUsers = serverHandler.getUsuarios();
        User[] usuariosOrdenados = new User[listOfUsers.size()];

        int i = 0;
        for(Enumeration e = listOfUsers.keys(); e.hasMoreElements();){
            String clave = (String) e.nextElement();
            usuariosOrdenados[i] = (User) listOfUsers.get(clave);
            i++;
        }
        for(i=0;i<usuariosOrdenados.length;i++){
            for(int j=i;j<usuariosOrdenados.length;j++){
                if(usuariosOrdenados[i].getNombres().compareTo(usuariosOrdenados[j].getNombres())>0){
                    User userAux = usuariosOrdenados[i];
                    usuariosOrdenados[i] = usuariosOrdenados[j];
                    usuariosOrdenados[j] = userAux;
                }
            }

        }

        for(i=0;i<usuariosOrdenados.length;i++){
            TableItem tableItem= new TableItem(userList, SWT.NONE);
            tableItem.setText(
                    new String[] {usuariosOrdenados[i].getNombres(),usuariosOrdenados[i].getApellidos(),usuariosOrdenados[i].getUsername(),usuariosOrdenados[i].getPassword(),usuariosOrdenados[i].getDescricion()});
        }

	}

	public ServerHandler getAgenda() {
		return serverHandler;
	}

	public void setAgenda(ServerHandler agenda) {
		this.serverHandler = agenda;
	}

	public Table getUserList() {
		return userList;
	}

	public void setUserList(Table userList) {
		this.userList = userList;
	}

	public Display getDisplay() {
		return display;
	}

	public Shell getShell() {
		return shell;
	}

    public Button getAddUser() {
        return addUser;
    }

    public void setAddUser(Button addUser) {
        this.addUser = addUser;
    }

    public Button getRemoveUser() {
        return removeUser;
    }

    public void setRemoveUser(Button removeUser) {
        this.removeUser = removeUser;
    }

    public Button getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Button updateUser) {
        this.updateUser = updateUser;
    }

    public Button getUpdateUserList() {
        return updateUserList;
    }

    public void setUpdateUserList(Button updateUserList) {
        this.updateUserList = updateUserList;
    }

    public Composite getUserGrid() {
        return userGrid;
    }

    public void setUserGrid(Composite userGrid) {
        this.userGrid = userGrid;
    }

    
	
}
