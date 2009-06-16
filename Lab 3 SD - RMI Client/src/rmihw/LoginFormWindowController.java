/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmihw;

import Interface.InterfaceServer;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.MessageBox;

/**
 *
 * @author alonso
 */
public class LoginFormWindowController implements SelectionListener {

    private LoginFormWindow loginWindow;

    public LoginFormWindowController(LoginFormWindow loginWindow) {
        this.loginWindow = loginWindow;
    }

    public void widgetSelected(SelectionEvent e) {
        System.out.println(e.getSource());
        //Clic sobre el boton "Agregar Usuario"
        if (loginWindow.getLogin().equals((Button) e.getSource())) {
            String userName = loginWindow.getUserNameText().getText();
            String password = loginWindow.getPasswordText().getText();

            if (userName.equals("") || password.equals("")) {
                MessageBox alert = new MessageBox(loginWindow.getShell(), SWT.ICON_WARNING);
                alert.setMessage("Por favor, llene los campos");
                alert.open();
            } else {
                //Intertar hacer login.
                InterfaceServer server = null;

                try {
                    server = RMICliente.getInterfaceServerRMI(server);
                } catch (RemoteException ex) {
                    MessageBox alerta = new MessageBox(loginWindow.getShell());
                    alerta.setMessage("No se pudo conectar con el servidor");
                    alerta.open();
                } catch (NotBoundException ex) {
                    MessageBox alerta = new MessageBox(loginWindow.getShell());
                    alerta.setMessage("No se pudo conectar con el servidor");
                    alerta.open();
                }

                boolean resp;
                try {
                    resp = server.login(userName, password);
                    if (resp) {
                        System.out.println("Login OK");
                        //Obtener el usuario
                        User user = server.getUserByUsername(userName);
                        loginWindow.getServerHandler().update(user);
                        loginWindow.getShell().dispose();
                        MainWindowView mainWindow = new MainWindowView(loginWindow.getDisplay(),loginWindow.getServerHandler(),loginWindow.getServerIsOn());
                    } else {
                        //Avisar que hubo un problema
                        System.out.println("Login NO OK");
                        MessageBox alert = new MessageBox(loginWindow.getShell(), SWT.ICON_ERROR);
                        alert.setMessage("Nombre de usuario o Password incorrectos");
                        alert.open();
                    }
                } catch (RemoteException ex) {
                    System.out.println("Hubo un problema en la conexion con el server en la verificacion del usuario");
                    MessageBox alert = new MessageBox(loginWindow.getShell());
                    alert.setMessage("Hubo un problema en la conexion con el servidor en la verificacion del usuario");
                    alert.open();
                }
            }
        }
    }

    public void widgetDefaultSelected(SelectionEvent arg0) {
    }
}
