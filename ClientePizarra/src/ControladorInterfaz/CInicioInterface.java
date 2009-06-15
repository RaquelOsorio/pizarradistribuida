package ControladorInterfaz;

import VO.VOSolicitud;
import VO.VOUsuario;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CInicioInterface extends Remote{
    public VOUsuario loginC(VOUsuario usuario) throws RemoteException;
    public VOUsuario buscarUsuario(VOUsuario usuario) throws RemoteException;
    public void GuardarSolicitud(VOSolicitud usuario) throws RemoteException;
    public void MataVista() throws RemoteException;
    //public Vector getConvocatorias() throws RemoteException;
}
