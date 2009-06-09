/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RMICliente;

import Inteface.EjemploInterface;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Sebastián
 */
public class RMICliente {
    
        static Registry registro;
    static int RMIPort;
    static String hostName;

    public static EjemploInterface getEjemplo(EjemploInterface c)
            throws RemoteException, NotBoundException {

        java.security.AllPermission a = new java.security.AllPermission();
        System.setProperty("java.security.policy", "rmi.policy");
        
        hostName = "localhost";        
        RMIPort = 3232;
        
        registro = LocateRegistry.getRegistry(hostName, RMIPort);
        
        c = (EjemploInterface) registro.lookup("Ejemplo");

        return c;
        
    }

}
