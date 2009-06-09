/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Inteface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Sebastián
 */
public interface EjemploInterface extends Remote{
public void imprimir (String nom) throws RemoteException;
}
