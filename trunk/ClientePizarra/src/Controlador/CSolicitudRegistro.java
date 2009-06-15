package Controlador;

import ControladorInterfaz.CSolicitudRegistroInterface;
import Modelo.MInterfaceSolicitud;
import VO.VOSolicitud;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejadorRMICliente.MissingConfigurationParameterException;
import manejadorRMICliente.manejadorRMICliente;

public class CSolicitudRegistro extends UnicastRemoteObject implements CSolicitudRegistroInterface {
    MInterfaceSolicitud ISolicitud = null;
    CPrincipal cp=null;

    public CSolicitudRegistro (CPrincipal c) throws RemoteException{
        super();
        cp = c;
    }

    public void GuardarSolicitud(VOSolicitud usuario) throws RemoteException {
        try {
            ISolicitud = manejadorRMICliente.getSolicitud(ISolicitud);
            int respuesta = ISolicitud.GuardarSolicitud(usuario);
            switch (respuesta) {
                case 0:
                    cp.ci.vista.VentanaFeedback("La Solicitud ha sido enviada correctamente", "Solicitud");
                    cp.ci.vista.limpiarFormulario();
                    break;
                case 1:
                    cp.ci.vista.VentanaFeedback("El Nombre de Usuario ya existe, elije otro", "Solicitud");
                    break;
                case 2:
                    cp.ci.vista.VentanaFeedback("No se pudo enviar la Solicitud, por favor intentalo denuevo", "Solicitud");
                    break;
            }
        } catch (MissingConfigurationParameterException ex) {
            Logger.getLogger(CInicio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(CInicio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}