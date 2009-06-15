package Controlador;

import Modelo.MInterfaceTipoComentario;
import VO.VOTipoComentario;
import Vista.VistaIngresarTipoComentario;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejadorRMICliente.MissingConfigurationParameterException;
import manejadorRMICliente.manejadorRMICliente;

public class CIngresarTipoComentario {
    CPrincipal cp;
    VistaIngresarTipoComentario vista;
    MInterfaceTipoComentario ITipoComentario;

    public CIngresarTipoComentario(CPrincipal aThis) {
        cp = aThis;
        vista = new VistaIngresarTipoComentario(this);
        vista.setVisible(true);
        vista.getEstado().setText("Identificado como: "+this.cp.usuario.getUsername());
    }

    public void MataVista() {
        vista.dispose();
    }

    public void agregarTipoComentario(VOTipoComentario x) throws RemoteException {
        try {
            ITipoComentario = manejadorRMICliente.getTipoComentario(ITipoComentario);
            int respuesta[] = ITipoComentario.agregarTipoComentario(x);
            switch(respuesta[0]) {
                case 0://todo bien
                    x.setID(respuesta[1]);
                    cp.cacc.agregarTipoComentarioTabla(x);
                    vista.limpiarDatos();
                    vista.VentanaFeedback("El Tipo de Comentario fue agregado correctamente", "Agregar Tipo de Comentario");
                    break;
                case 1://ya existe
                    vista.VentanaFeedback("El Tipo de Comentario ya existe", "Agregar Tipo de Comentario");
                    break;
                case 2://no se pudo guardar
                    vista.VentanaFeedback("No se pudo agregar el Tipo de Comentario, por favor intentalo denuevo", "Agregar Tipo de Comentario");
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