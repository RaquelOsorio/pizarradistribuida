/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmihw;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;

/**
 *
 * @author alonso
 */
public class LoginFormWindow {

    private Display display;
    private Shell shell = new Shell(this.display, SWT.TITLE | SWT.CLOSE);
    private Text userNameText;
    private Text passwordText;
    private Button login = new Button(shell, SWT.PUSH);
    
    private boolean serverIsOn;
    private ServerHandler serverHandler;

    private LoginFormWindowController controller;

    public LoginFormWindow(Display display,ServerHandler serverHandler, boolean serverIsOn) {
        this.display = display;
        this.serverIsOn = serverIsOn;
        this.serverHandler = serverHandler;
        
        //Conectar controlador
		this.connectController(this.createController());

        this.createWindow();

    }

    public void createWindow() {
        if (!serverIsOn) {
            MessageBox alert = new MessageBox(this.shell, SWT.ICON_ERROR);
            alert.setMessage("No se ha podido establecer conexion con el servidor");
            alert.open();
            this.display.dispose();
        } else {
            shell.setLocation(30, 30);
            shell.setSize(500, 500);
            shell.setText("Ingreso");
           

            Label userNameLabel = new Label(shell, SWT.NONE);
            userNameLabel.setText("Username:");
            userNameLabel.setBounds(5, 5, 70, 25);

            userNameText = new Text(shell, SWT.BORDER);
            userNameText.setBounds(80, 5, 150, 25);

            Label passwordLabel = new Label(shell, SWT.NONE);
            passwordLabel.setText("Password:");
            passwordLabel.setBounds(5, 35, 70, 25);

            passwordText = new Text(shell, SWT.BORDER | SWT.PASSWORD);
            passwordText.setBounds(80, 35, 150, 25);

            //login = new Button(shell, SWT.PUSH);
            login.setText("Entrar");
            login.setBounds(5, 60, 150, 20);



            shell.pack();
            shell.open();

            while (!shell.isDisposed()) {
                if (!display.readAndDispatch()) {
                    display.sleep();
                }
            }
            display.dispose();

        }
    }

    public void connectController(LoginFormWindowController controller){
		//Primero borramos cualquier captura de evento asociada anteriormente
		if(this.controller!=null){
            this.login.removeSelectionListener(null);
		}
		this.controller = controller;

		//Añadimos captura de evento al boton "Reservar una hora"
		this.login.addSelectionListener(controller);
	}

	private LoginFormWindowController createController(){
		return new LoginFormWindowController(this);
	}


    public Button getLogin() {
        return login;
    }

    public Text getPasswordText() {
        return passwordText;
    }

    public Shell getShell(){
        return this.shell;
    }

    public Text getUserNameText() {
        return userNameText;
    }

    public Display getDisplay() {
        return display;
    }


    public ServerHandler getServerHandler() {
        return serverHandler;
    }

    public boolean getServerIsOn() {
        return serverIsOn;
    }

}
