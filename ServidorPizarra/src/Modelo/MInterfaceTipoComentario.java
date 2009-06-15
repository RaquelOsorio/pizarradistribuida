package Modelo;

import VO.VOTipoComentario;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface MInterfaceTipoComentario extends Remote {
    public int[] agregarTipoComentario(VOTipoComentario tc) throws RemoteException;
    public Vector obtenerTiposComentario() throws RemoteException;
    public VOTipoComentario obtenerTipoComentario(int id) throws RemoteException;
    public int eliminarTipoComentario(VOTipoComentario tc) throws RemoteException;
    public int modTipoComentario(VOTipoComentario tc) throws RemoteException;
    public int BuscarTipoComentario(VOTipoComentario c) throws RemoteException;
    public int get_ultimo_idTipoComentario() throws RemoteException;
    public void actualizar_datosTipoComentario() throws RemoteException;
}
