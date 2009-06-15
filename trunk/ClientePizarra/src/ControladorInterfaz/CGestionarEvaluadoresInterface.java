/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ControladorInterfaz;

import VO.VOPostulacion;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author Sebastián
 */
public interface CGestionarEvaluadoresInterface extends Remote{

    public void MataVista() throws RemoteException;
    public void asignarEvaluadores(Vector evaluadores) throws RemoteException;
    public int AsignarEvaluador(VOPostulacion p, int ide, int tipoev) throws RemoteException;
    public int DesasignarEvaluador(VOPostulacion p, int ide, int tipoev) throws RemoteException;
    public Vector ObtenerEvaluadores(VOPostulacion p, int t) throws RemoteException;
    public void actualizar_datosPostulacion() throws RemoteException;
    public void setComboBoxEvaluadores(int area)throws RemoteException;
}
