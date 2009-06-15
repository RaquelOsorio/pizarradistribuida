package Modelo;

import VO.VOAreaInteres;
import VO.VOUsuario;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface MInterfaceUsuario extends Remote {
    public int[] GuardarUsuario(VOUsuario usu)  throws RemoteException;
    public int get_ultimo_idUsuario() throws RemoteException;
    public int buscar_usuario_id(VOUsuario usu) throws RemoteException;
    public int buscarUsuario(String val[], String tipo[]) throws RemoteException;
    public VOUsuario obtenerdatosUsuario(String val[], String tipo[]) throws RemoteException;
    public void actualizar_datosUsuario() throws RemoteException;
    public int ModificarUsuario(VOUsuario usu)  throws RemoteException;
    public VOUsuario ObtenerUsuario(String nu, String p) throws RemoteException;
    public VOUsuario ObtenerUsuario(String nu) throws RemoteException;
    public int eliminarUsuario(VOUsuario u) throws RemoteException;
    public Vector ObtenerUsuarios() throws RemoteException;
    public Vector ObtenerUsuariosEvaluadores(VOAreaInteres areaInt) throws RemoteException;
}
