/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RMIServidor;

import Inteface.EjemploInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Sebastián
 */
public class Ejemplo extends UnicastRemoteObject implements EjemploInterface{
    
        public Ejemplo() throws RemoteException {
        super();
    }
        
    public void imprimir (String nom) throws RemoteException{
        
        System.out.print(nom);
    }

}
