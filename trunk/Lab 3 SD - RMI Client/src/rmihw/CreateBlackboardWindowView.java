/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rmihw;

import rmihw.MainWindowController;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 *
 * @author alonso
 */
public class CreateBlackboardWindowView {

	private Display display;

	private Shell shell = new Shell(this.display, SWT.TITLE | SWT.MIN | SWT.CLOSE);
	private GridLayout gridLayout = new GridLayout();
	private Text nombreText;
    private Text apellidosText;
    private Text userNameText;
    private Text passwordText;
	private Text descripcionText;
	private Button guardar;

	private MainWindowController controller;

    private boolean flagSaveOrUpdate;
    private Blackboard blackboard;

    public CreateBlackboardWindowView(Display display, MainWindowController controller) {

        this.flagSaveOrUpdate = true;

		this.display = display;
		this.createWindow();
		this.controller = controller;
		this.connectController(controller);

		//indicamos al controller que la ventana está abierta, para que no abra más ventanas
		this.controller.setFlagCreateBlackboardWindowOpen(true);
	}

	public CreateBlackboardWindowView(Display display, MainWindowController controller, Blackboard blackboard) {

        this.flagSaveOrUpdate = false;
        this.blackboard=blackboard;

		this.display = display;
		this.createWindow(blackboard);
		this.controller = controller;
		this.connectController(controller);

		//indicamos al controller que la ventana está abierta, para que no abra más ventanas
		this.controller.setFlagCreateBlackboardWindowOpen(true);
	}

	//Dibujamos la ventana y sus componentes para la creacion de un usuario
	public void createWindow(){

        shell.setText("Crear Pizarra");
		//Creacion de la ventana y su respectivo objeto para Layout
		shell.setLayout(new FormLayout());
        shell.setBounds(300, 300, 500, 500);

		gridLayout.numColumns = 2;
		shell.setLayout(gridLayout);

        //Etiquetas (Label) y Campos (Text)
		Label nombreLabel = new Label(shell, SWT.NONE);
		nombreLabel.setText("Nombre:");
		nombreText = new Text(shell, SWT.BORDER);

   		/*Label descripcionLabel = new Label(shell, SWT.NONE);
		descripcionLabel.setText("Descripcion:");
		descripcionText = new Text(shell, SWT.BORDER);*/


		guardar = new Button(shell, SWT.PUSH);
        guardar.setText("Guardar");


		//Ubicacion y orden de etiquetas
		GridData data = new GridData();
        data.widthHint = 80;
        nombreLabel.setLayoutData(data);

		/*data = new GridData();
		data.widthHint = 80;
		descripcionLabel.setLayoutData(data);*/

		//Ubicacion y orden entradas de datos
		GridData data2 = new GridData(GridData.FILL_HORIZONTAL);
        data2.widthHint = 150;
		nombreText.setLayoutData(data2);

		/*data2 = new GridData(GridData.FILL_HORIZONTAL);
        data2.widthHint = 150;
		descripcionText.setLayoutData(data2);*/

		GridData data3 = new GridData();
		data3.horizontalSpan = 2;
		guardar.setLayoutData(data3);


	    shell.pack();
	    shell.open();
	}

    //Dibujamos la ventana y sus componentes, en este caso para actualizar un usuario
	public void createWindow(Blackboard blackboard){

        shell.setText("Crear Usuario");
		//Creacion de la ventana y su respectivo objeto para Layout
		shell.setLayout(new FormLayout());
        shell.setBounds(300, 300, 500, 500);

		gridLayout.numColumns = 2;
		shell.setLayout(gridLayout);

        //Etiquetas (Label) y Campos (Text)
		Label nombreLabel = new Label(shell, SWT.NONE);
		nombreLabel.setText("Nombres");
		nombreText = new Text(shell, SWT.BORDER);
        nombreText.setText(String.valueOf(blackboard.getId()));

   		
		/*Label descripcionLabel = new Label(shell, SWT.NONE);
		descripcionLabel.setText("Descripcion:");
		descripcionText = new Text(shell, SWT.BORDER);
        descripcionText.setText(user.getDescricion());*/


		guardar = new Button(shell, SWT.PUSH);
        guardar.setText("Actualizar");

		//Ubicacion y orden de etiquetas
		GridData data = new GridData();
        data.widthHint = 80;
        nombreLabel.setLayoutData(data);

		
		/*data = new GridData();
		data.widthHint = 80;
		descripcionLabel.setLayoutData(data);*/

		//Ubicacion y orden entradas de datos
		GridData data2 = new GridData(GridData.FILL_HORIZONTAL);
        data2.widthHint = 150;
		nombreText.setLayoutData(data2);

		
		data2 = new GridData(GridData.FILL_HORIZONTAL);
        data2.widthHint = 150;
		descripcionText.setLayoutData(data2);

		GridData data3 = new GridData();
		data3.horizontalSpan = 2;
		guardar.setLayoutData(data3);


	    shell.pack();
	    shell.open();
	}

	public void connectController(MainWindowController controller){
        if(this.controller!=null){
			this.guardar.removeSelectionListener(controller);
		}
		this.controller = controller;
		//Añade captura de eventos al boton
		this.guardar.addSelectionListener(this.controller);
	}


	//Getters & Setters
	public Display getDisplay() {
		return display;
	}

	public void setDisplay(Display display) {
		this.display = display;
	}

	public Shell getShell() {
		return shell;
	}

	public void setShell(Shell shell) {
		this.shell = shell;
	}

	public GridLayout getGridLayout() {
		return gridLayout;
	}

	public void setGridLayout(GridLayout gridLayout) {
		this.gridLayout = gridLayout;
	}

    public Text getApellidosText() {
        return apellidosText;
    }

    public void setApellidosText(Text apellidosText) {
        this.apellidosText = apellidosText;
    }

    public Text getDescripcionText() {
        return descripcionText;
    }

    public void setDescripcionText(Text descripcionText) {
        this.descripcionText = descripcionText;
    }

    public Button getGuardar() {
        return guardar;
    }

    public void setGuardar(Button guardar) {
        this.guardar = guardar;
    }

    public Text getNombreText() {
        return nombreText;
    }

    public void setNombreText(Text nombreText) {
        this.nombreText = nombreText;
    }

    public Text getPasswordText() {
        return passwordText;
    }

    public void setPasswordText(Text passwordText) {
        this.passwordText = passwordText;
    }

    public boolean getFlagSaveOrUpdate() {
        return flagSaveOrUpdate;
    }

    public Text getUserNameText() {
        return userNameText;
    }

    public void setUserNameText(Text userNameText) {
        this.userNameText = userNameText;
    }


	
}
