/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rmihw;

//import view.*;
import Interface.InterfaceServer;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import org.eclipse.swt.widgets.*;

/**
 *
 * @author alonso
 */
public class Main {
    private static InterfaceServer serverHandling;
	public static void main(String[] args) {
        boolean serverIsOn;
        try {
            serverIsOn = checkServer();
        } catch (RemoteException ex) {
            serverIsOn=false;
        } catch (NotBoundException ex) {
            serverIsOn=false;
        }

		Display display = new Display();

        ServerHandler serverHandler = new ServerHandler();

        //Este es el login, y es quien se encarga de abrir la ventana principal si todo OK
        LoginFormWindow loginFormWindow = new LoginFormWindow(display,serverHandler,serverIsOn);

    }

    public static boolean checkServer() throws RemoteException, NotBoundException{
        try {
                serverHandling = RMICliente.getInterfaceServerRMI(serverHandling);
            } catch (RemoteException ex) {
                return false;
            } catch (NotBoundException ex) {
                return false;
            }
        return true;
    }

}
