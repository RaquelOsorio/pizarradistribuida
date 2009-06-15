package Modelo;
import VO.VOUsuario;
import VO.VOArticulo;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;


public interface MInterfaceArticulo extends Remote{
    public VOArticulo ObtenerArticulo(VOArticulo art) throws RemoteException;
    public Vector ObtenerArticulos(VOUsuario us) throws RemoteException;
    public VOArticulo GuardarArticulo(VOArticulo art) throws RemoteException;
    public void Subir(byte art[], VOArticulo a) throws RemoteException;
    public int get_ultimo_idArticulo() throws RemoteException;
    public void actualizarDatosArticulo() throws RemoteException;
    public byte[] Bajar(VOArticulo a) throws RemoteException;
}

