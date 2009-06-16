package rmihw;

import Interface.InterfaceServer;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class Main {

	public static void main(String[] args) throws RemoteException, NotBoundException {
		
		//Este codigo inicializa el servidor
		Registry registro;
		try {                        
            //Se inicia el registro del puerto para escuchar
            registro = RMIServer.iniciarRegistro();
            //Se instancian las clases para callback, y se publican con algun nombre


            InterfaceServer interfaceServer = new Server("Hello World!");
            RMIServer.setAgendaServer(registro, interfaceServer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        

	}

}
