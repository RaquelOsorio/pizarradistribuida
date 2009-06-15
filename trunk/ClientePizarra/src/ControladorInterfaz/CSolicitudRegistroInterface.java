/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ControladorInterfaz;

import VO.VOSolicitud;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Francisca
 */
public interface CSolicitudRegistroInterface extends Remote {

    public void GuardarSolicitud(VOSolicitud usuario) throws RemoteException;
    //String[] cargarTipoUsuario() throws RemoteException;
    //String[] cargarAreasInteres() throws RemoteException; 
}
