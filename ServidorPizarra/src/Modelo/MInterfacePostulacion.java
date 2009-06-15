package Modelo;

import VO.VOPostulacion;
import VO.VOUsuario;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface MInterfacePostulacion extends Remote {
    public int AsignarEvaluador(VOPostulacion p, int ide, int tipoev) throws RemoteException;
    public int DesasignarEvaluador(VOPostulacion p, int ide, int tipoev) throws RemoteException;
    public Vector ObtenerEvaluadores(VOPostulacion p, int t) throws RemoteException;
    public void actualizar_datosPostulacion() throws RemoteException;
    public void GuardarPostulacion(VOPostulacion p) throws RemoteException;
    public int get_ultimo_idPostulacion() throws RemoteException;
    public Vector obtenerPostulaciones()throws RemoteException;
    public Vector postEvaluarPor(VOUsuario u)  throws RemoteException;
}