package ControladorInterfaz;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CAdmSolicitudInterface extends Remote{
    public void ObtenerSolicitudes() throws RemoteException;
}
