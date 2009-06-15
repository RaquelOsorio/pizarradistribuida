package Controlador;

import Modelo.MInterfaceConvocatoria;
import VO.VOConvocatoria;
import VO.VOUsuario;
import Vista.VistaConvocatoria;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejadorRMICliente.MissingConfigurationParameterException;
import manejadorRMICliente.manejadorRMICliente;

public class CConvocatoria {
    MInterfaceConvocatoria IConvocatoria = null;
    CPrincipal cp;
    CPostular cpostular;
    VistaConvocatoria vista;
    Vector convocatorias;
    VOUsuario usuario;

    public CConvocatoria(CPrincipal c) throws RemoteException {
        super();
        cp = c;
        convocatorias = new Vector();
        vista = new VistaConvocatoria(this);
        this.Iniciar();
        this.ObtenerConvocatorias();
    }

    public void Iniciar() throws RemoteException {

        if (cp.usuario.getUsertype().compareTo(("autor")) == 0) {
            vista.getBAgregarConvocatoria().setVisible(false);
            vista.getBModificarConvocatoria().setVisible(false);
            vista.getBEliminarConvocatoria().setVisible(false);
    
        }
        vista.setLocationRelativeTo(null);
        vista.getEstado().setText("Identificado como: " + cp.usuario.getUsername());
        vista.setVisible(true);
    }

    public void MataVista() {
        vista.dispose();
    }

    public void ObtenerConvocatorias() throws RemoteException {
        try {
            IConvocatoria = manejadorRMICliente.getConvocatoria(IConvocatoria);
            convocatorias = IConvocatoria.getConvocatorias();
            vista.addConvocatorias(convocatorias);
        } catch (MissingConfigurationParameterException ex) {
            Logger.getLogger(CConvocatoria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(CConvocatoria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CConvocatoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void PostularVista(int idc) {
        int idv = 0, i = 0;
        boolean encontrado=false;
        while(i<convocatorias.size() && !encontrado) {
            if(((VOConvocatoria)convocatorias.get(i)).getID()==idc) {
                idv=i;
                encontrado=true;
            }
            i++;
        }
        if(encontrado) {
            cp.PostularVista((VOConvocatoria)convocatorias.get(idv));
        } else {
            vista.VentanaFeedback("Al parecer, esa convocatoria ya no existe", "Error");
        }
    }

    public void VerConvocatoriaVista(int idc) {
        int idv = 0, i = 0;
        boolean encontrado=false;
        while(i<convocatorias.size() && !encontrado) {
            if(((VOConvocatoria)convocatorias.get(i)).getID()==idc) {
                idv=i;
                encontrado=true;
            }
            i++;
        }
        if(encontrado) {
            cp.VerConvocatoriaVista((VOConvocatoria)convocatorias.get(idv));
        } else {
            vista.VentanaFeedback("Al parecer, esa convocatoria ya no existe", "Error");
        }    }

    public Vector getConvocatorias(){
        return this.convocatorias;
    }

    public void nuevaVerConvocatoriaVista() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

        public void nuevoUsuarioVista() throws RemoteException {
        cp.nuevoUsuarioVista();
    }
    public void nuevaConvocatoriaVista() throws RemoteException {
         cp.nuevaConvocatoriaVista();
    }
//    public void nuevoarticulo(VOConvocatoria conv) throws MissingConfigurationParameterException, NotBoundException, MalformedURLException {
//        try {
//
//            cpostular = new CPostular(this, this.usuario, id);
//        } catch (RemoteException ex) {
//            Logger.getLogger(CConvocatoria.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    void matapostular() {
     
    }

}
