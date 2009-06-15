/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ControladorInterfaz;

import VO.VOPostulacion;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Francisca
 */
public interface CListaPostulacionesInterface extends Remote{

    public VOPostulacion ObtenerPostulacion(String nom_user) throws RemoteException;
    public void MataVista()throws RemoteException;
    public void verDetalle() throws RemoteException;
}
