package Controlador;

import ControladorInterfaz.CIngresarUsuarioInterface;
import Modelo.MInterfaceSolicitud;
import Modelo.MInterfaceUsuario;
import VO.VOUsuario;
import Vista.VistaUsuarioIngresar;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejadorRMICliente.MissingConfigurationParameterException;
import manejadorRMICliente.manejadorRMICliente;

public class CIngresarUsuario extends UnicastRemoteObject implements CIngresarUsuarioInterface {
    VistaUsuarioIngresar vista;
    MInterfaceUsuario IUsuario = null;
    MInterfaceSolicitud ISolicitud = null;
    CPrincipal cp;

    CIngresarUsuario(CPrincipal aThis) throws RemoteException {
        cp = aThis;
        vista = new VistaUsuarioIngresar(this);
        vista.setVisible(true);
        vista.getEstado().setText("Identificado como: "+this.cp.usuario.getUsername());
    }

    public void GuardarUsuario(VOUsuario usu) throws RemoteException {
        try {
            ISolicitud = manejadorRMICliente.getSolicitud(ISolicitud);
            String val[] = {usu.getUsername()};
            String tipo[] = {"nomuser"};
            int exSol = ISolicitud.buscarSolicitud(val, tipo);
            if(exSol<0) {
                IUsuario = manejadorRMICliente.getUsuario(IUsuario);
                int respuesta[] = IUsuario.GuardarUsuario(usu);
                switch (respuesta[0]) {
                    case 0:
                        usu.setId(""+respuesta[1]);
                        cp.cau.AgregarUsuario(usu);
                        vista.VentanaFeedback("El Usuario ha sido creado correctamente", "Ingresar Usuario");
                        vista.limpiarFormulario();
                        break;
                    case 1:
                        vista.VentanaFeedback("El Nombre de Usuario ya existe, elije otro", "Ingresar Usuario");
                        break;
                    case 2:
                        vista.VentanaFeedback("No se pudo crear el Usuario, por favor intentalo denuevo", "Ingresar Usuario");
                        break;
                }
            } else {
                vista.VentanaFeedback("El Nombre de Usuario ya existe, elije otro", "Ingresar Usuario");
            }
        } catch (MissingConfigurationParameterException ex) {
            Logger.getLogger(CInicio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(CInicio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void MataVista() {
        vista.dispose();
    }
}