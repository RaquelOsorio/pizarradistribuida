/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ControladorInterfaz;

import VO.VOArticulo;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Francisca
 */
public interface CPostulacionesInterface extends Remote{

     public VOArticulo ObtenerArticulo(VOArticulo art) throws RemoteException;

}

