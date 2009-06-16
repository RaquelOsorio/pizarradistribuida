package rmihw;

import Interface.InterfaceServer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RMICliente {

	static Registry registro;
	static int RMIPort;
	static String hostName;
    Properties props;


	public static InterfaceServer getInterfaceServerRMI(InterfaceServer c)
			throws RemoteException, NotBoundException {

        //Cargando configuracion
        Properties prop = new Properties();
        InputStream input = null;
        boolean flagFileExists = false;
        try {
            input = new FileInputStream("cliente.properties");
            flagFileExists = true;
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro archivo de configuracion");
        }
        try {
            prop.load(input);
        } catch (IOException ex) {
            System.out.println("No se pudo cargar el archivo de configuracion");
        }

		java.security.AllPermission a = new java.security.AllPermission();
		System.setProperty("java.security.policy", "rmi.policy");


        if(prop.containsKey("server")){
            hostName = (String) prop.getProperty("server");
        }else{
            System.out.println("Nombre de servidor no encontrado en properties, usando default: localhost");
            hostName = "localhost";
        }

        if(prop.containsKey("port")){
            RMIPort = Integer.valueOf(prop.getProperty("port"));
        }else{
            System.out.println("Numero de puerto no encontrado en properties, usando default: 3232");
            RMIPort = 3232;
        }

		


		registro = LocateRegistry.getRegistry(hostName, RMIPort);

        
		c = (InterfaceServer) registro.lookup("AgendaServer");
        
		return c;
	}
}
