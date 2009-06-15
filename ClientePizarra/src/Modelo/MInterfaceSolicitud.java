package Modelo;
import VO.VOSolicitud;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface MInterfaceSolicitud extends Remote {
    public int get_ultimo_idSolicitud() throws RemoteException;
    public int GuardarSolicitud(VOSolicitud sol) throws RemoteException;
    public VOSolicitud ObtenerSolicitud(VOSolicitud sol) throws RemoteException;
    public void actualizar_datosSolicitud() throws RemoteException;
    public Vector ObtenerSolicitudes() throws RemoteException;
    public int buscarSolicitud(String val[], String tipo[]) throws RemoteException;
    public int EliminarSolicitud(VOSolicitud sol) throws RemoteException;
}
