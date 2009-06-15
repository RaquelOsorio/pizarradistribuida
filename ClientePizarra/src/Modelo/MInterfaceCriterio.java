package Modelo;

import VO.VOCriterio;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface MInterfaceCriterio extends Remote {
    public int[] agregarCriterio(VOCriterio c) throws RemoteException;
    public Vector obtenerCriterios() throws RemoteException;
    public VOCriterio obtenerCriterio(int id) throws RemoteException;
    public int eliminarCriterio(VOCriterio c) throws RemoteException;
    public int modCriterio(VOCriterio c) throws RemoteException;
    public int BuscarCriterio(VOCriterio c)  throws RemoteException;
    public int get_ultimo_idCriterio() throws RemoteException;
    public void actualizar_datosCriterio() throws RemoteException;
}
