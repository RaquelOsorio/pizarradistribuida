package Controlador;

import Modelo.MInterfaceCriterio;
import VO.VOCriterio;
import Vista.VistaModificarCriterio;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejadorRMICliente.MissingConfigurationParameterException;
import manejadorRMICliente.manejadorRMICliente;

public class CModificarCriterio {
    CPrincipal cp;
    VOCriterio criterio;
    VistaModificarCriterio vista;
    MInterfaceCriterio ICriterio;
    int fila;
    
    public CModificarCriterio(CPrincipal aThis, VOCriterio c, int fila) {
        cp = aThis;
        criterio = c;
        this.fila = fila;
        vista = new VistaModificarCriterio(this);
        vista.setVisible(true);
        vista.getEstado().setText("Identificado como: "+this.cp.usuario.getUsername());
        vista.CargarDatos(criterio);
    }

    public void MataVista() {
        vista.dispose();
    }
    public void ModificarCriterio(VOCriterio c) throws RemoteException {
        try {
            c.setID(criterio.getID());
            ICriterio = manejadorRMICliente.getCriterio(ICriterio);
            int verifica_mod = ICriterio.modCriterio(c);
            switch (verifica_mod) {
                case 0: //se modifico correctamente
                    this.cp.cacc.ActualizarCriterio(c, this.fila);
                    vista.VentanaFeedback("El Criterio fue modificado correctamente", "Error");
                    break;
                case 1: //no existe
                    vista.VentanaFeedback("Al parecer, ese Criterio no existe", "Error");
                    break;
                case 2: //no se pudo modificar
                    vista.VentanaFeedback("Ops, algo paso y no se pudo modificar el Criterio, intentalo denuevo por favor", "Error");
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
}