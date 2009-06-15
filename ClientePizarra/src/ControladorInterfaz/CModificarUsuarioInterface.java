package ControladorInterfaz;

import VO.VOUsuario;
import java.rmi.RemoteException;

public interface CModificarUsuarioInterface {
    public void ModificarUsuario(VOUsuario moduser) throws RemoteException;
}
