package ControladorInterfaz;

import VO.VOUsuario;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CPrincipalInterface extends Remote{

    public void AdministarUsuarios() throws RemoteException;
    public void ArticulosEvaluar() throws RemoteException;
    public void LogOut() throws RemoteException;
    public void Iniciar(VOUsuario us) throws RemoteException;

}
