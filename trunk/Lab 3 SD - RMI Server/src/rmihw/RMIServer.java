package rmihw;

import Interface.InterfaceServer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;

public class RMIServer {

	public static Registry iniciarRegistro() throws NotBoundException,
			MalformedURLException, RemoteException {

		int portNum;
		Registry registro = null;

        //Cargando configuracion
        Properties prop = new Properties();
        InputStream input = null;
        boolean flagFileExists = false;
        try {
            input = new FileInputStream("server.properties");
            flagFileExists = true;
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro archivo de configuracion");
        }
        try {
            prop.load(input);
        } catch (IOException ex) {
            System.out.println("No se pudo cargar el archivo de configuracion");
        }

        if(prop.containsKey("port")){
            portNum = Integer.valueOf(prop.getProperty("port"));
        }else{
            System.out.println("Numero de puerto no encontrado en properties, usando default: 3232");
            portNum = 3232;
        }

		registro = startRegistry(portNum);
		//System.out.println("RMI server Inicializado");
		return registro;
	}

	// Arranca un registro RMI local si no existiera, en cierto puerto.
	private static Registry startRegistry(int RMIPortNum)
			throws RemoteException {

		Registry registro;
		try {
			// crear el registro y ligar el nombre y objeto.
			registro = LocateRegistry.getRegistry(RMIPortNum);
			// Lanza una excepción si no existe el registro
			registro.list();
		} catch (RemoteException e) {
			// No hay un registro válido en el puerto.
			registro = LocateRegistry.createRegistry(RMIPortNum);
			registro.list();
		}
		return registro;
	}

	/**
	 * 
	 * @Metodos: Se instancian las clases y se publican con algun nombre
	 */

	public static void setAgendaServer(Registry registro,InterfaceServer agendaServer) throws NotBoundException,MalformedURLException, RemoteException {
		registro.rebind("AgendaServer", agendaServer);
	}

}
