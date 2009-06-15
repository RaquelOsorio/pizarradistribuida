/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RMIServidor;

import Inteface.EjemploInterface;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Sebastián
 */
public class RMIServidor {
      
    public static Registry iniciarRegistro()
            throws NotBoundException, MalformedURLException, RemoteException {

        int portNum;
        Registry registro = null;

        portNum = 3232;
        registro = startRegistry(portNum);
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
     *  @Metodos: Se instancian las clases y se publican con algun nombre
     */    
    
    public static void setEjemplo(Registry registro)
            throws NotBoundException, MalformedURLException, RemoteException {

        EjemploInterface ejem = new Ejemplo();
        
        registro.rebind("Ejemplo", ejem);
    } 
    
    
}
