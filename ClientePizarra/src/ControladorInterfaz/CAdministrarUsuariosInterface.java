package ControladorInterfaz;

import VO.VOUsuario;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CAdministrarUsuariosInterface extends Remote{

    public VOUsuario ObtenerUsuario(String nom_user) throws RemoteException;
    public void eliminarUsuario(String nomser) throws RemoteException;
    public void MataVista() throws RemoteException;
    public void ModificarUsuarioVista(String nomuser) throws RemoteException;
}
