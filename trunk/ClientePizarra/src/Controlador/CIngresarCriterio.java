package Controlador;

import Modelo.MInterfaceCriterio;
import VO.VOCriterio;
import Vista.VistaIngresarCriterio;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejadorRMICliente.MissingConfigurationParameterException;
import manejadorRMICliente.manejadorRMICliente;

public class CIngresarCriterio {
    CPrincipal cp;
    VistaIngresarCriterio vista;
    MInterfaceCriterio ICriterio;

    public CIngresarCriterio() throws RemoteException {
        super();    
    }

    public CIngresarCriterio(CPrincipal aThis) {
        cp = aThis;
        vista = new VistaIngresarCriterio(this);
        vista.setVisible(true);
        vista.getEstado().setText("Identificado como: "+this.cp.usuario.getUsername());
    }

    public void MataVista() {
        vista.dispose();
    }

    public void agregarCriterio(VOCriterio c) throws RemoteException{
        try {
            ICriterio = manejadorRMICliente.getCriterio(ICriterio);
            int respuesta[] = ICriterio.agregarCriterio(c);
            switch(respuesta[0]) {
                case 0://todo bien
                    c.setID(respuesta[1]);
                    cp.cacc.agregarCriterioTabla(c);
                    vista.limpiarDatos();
                    vista.VentanaFeedback("El Criterio fue agregado correctamente", "Agregar Criterio");
                    break;
                case 1://ya existe
                    vista.VentanaFeedback("El Criterio ya existe", "Agregar Criterio");
                    break;
                case 2://no se pudo guardar
                    vista.VentanaFeedback("No se pudo agregar el Criterio, por favor intentalo denuevo", "Agregar Criterio");
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
