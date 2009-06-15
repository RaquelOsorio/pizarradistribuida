package Controlador;

import ControladorInterfaz.CModificarUsuarioInterface;
import Modelo.MInterfaceUsuario;
import VO.VOUsuario;
import Vista.VistaUsuarioModificar;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejadorRMICliente.MissingConfigurationParameterException;
import manejadorRMICliente.manejadorRMICliente;

public class CModificarUsuario extends UnicastRemoteObject implements CModificarUsuarioInterface {
    CPrincipal cp;
    VistaUsuarioModificar vista;
    MInterfaceUsuario IUsuario = null;
    VOUsuario usuario;
    int fila;


    public CModificarUsuario(CPrincipal c, VOUsuario us, int fila) throws RemoteException {
        cp = c;
        usuario = us;
        this.fila = fila;
        vista = new VistaUsuarioModificar(this);
        vista.setVisible(true);
        vista.getEstado().setText("Identificado como: "+this.cp.usuario.getUsername());
        vista.cargarDatos(usuario);
    }


    

    public void ModificarUsuario(VOUsuario moduser) throws RemoteException {
        try {
            int verifica_mod;
            moduser.setId(usuario.getId());
            moduser.setUsername(usuario.getUsername());
            IUsuario = manejadorRMICliente.getUsuario(IUsuario);
            verifica_mod=IUsuario.ModificarUsuario(moduser);
            switch (verifica_mod) {
                case 0: //se modifico correctamente
                    this.cp.cau.ActualizarUsuario(moduser, this.fila);
                    vista.VentanaFeedback("El usuario fue modificado correctamente", "Error"); 
                    break;
                case 1: //no existe
                    vista.VentanaFeedback("Al parecer, ese usuario no existe", "Error");
                    break;
                case 2: //no se pudo modificar
                    vista.VentanaFeedback("Ops, algo paso y no se pudo modificar el usuario, intentalo denuevo por favor", "Error");
                    break;
            }
        } catch (MissingConfigurationParameterException ex) {
            Logger.getLogger(CAdministrarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(CAdministrarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CAdministrarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void MataVista() {
        this.usuario = null;
        this.IUsuario = null;
        vista.dispose();
    }

}