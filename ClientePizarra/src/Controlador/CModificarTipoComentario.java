package Controlador;

import Modelo.MInterfaceTipoComentario;
import VO.VOTipoComentario;
import Vista.VistaModificarTipoComentario;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejadorRMICliente.MissingConfigurationParameterException;
import manejadorRMICliente.manejadorRMICliente;

public class CModificarTipoComentario {
    CPrincipal cp;
    VOTipoComentario comentario;
    int fila;
    VistaModificarTipoComentario vista;
    MInterfaceTipoComentario ITipoComentario;

    public CModificarTipoComentario(CPrincipal aThis, VOTipoComentario c, int fila) {
        cp = aThis;
        comentario = c;
        this.fila = fila;
        vista = new VistaModificarTipoComentario(this);
        vista.setVisible(true);
        vista.getEstado().setText("Identificado como: "+this.cp.usuario.getUsername());
        vista.CargarDatos(comentario);
    }

    public void ModificarTipoComentarioPresionado(VOTipoComentario x) throws RemoteException {
        try {
            ITipoComentario = manejadorRMICliente.getTipoComentario(ITipoComentario);
            x.setID(comentario.getID());
            int respuesta = ITipoComentario.modTipoComentario(x);
            switch(respuesta) {
                case 0://todo bien
                    cp.cacc.ActualizarTipoComentario(x, this.fila);
                    vista.VentanaFeedback("El Tipo de Comentario fue modificado correctamente", "Agregar Tipo de Comentario");
                    break;
                case 1://ya existe
                    vista.VentanaFeedback("Al parecer el Tipo de Comentario no existe", "Agregar Tipo de Comentario");
                    break;
                case 2://no se pudo guardar
                    vista.VentanaFeedback("No se pudo modificar el Tipo de Comentario, por favor intentalo denuevo", "Agregar Tipo de Comentario");
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
    
    public void MataVista() {
        vista.dispose();
    }

}
