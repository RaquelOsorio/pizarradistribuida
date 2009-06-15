/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ControladorInterfaz;

import VO.VOUsuario;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Francisca
 */
public interface CIngresarUsuarioInterface extends Remote{

    public void GuardarUsuario(VOUsuario usuario) throws RemoteException;
   // public Vector cargarTipoUsuario() throws RemoteException;
   // public Vector cargarAreasInteres() throws RemoteException;
}

