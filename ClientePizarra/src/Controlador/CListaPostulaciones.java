/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import ControladorInterfaz.CListaPostulacionesInterface;
import Modelo.MInterfacePostulacion;
import VO.VOPostulacion;
import Vista.VistaArticuloAEvaluar;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejadorRMICliente.MissingConfigurationParameterException;

/**
 *
 * @author Francisca
 */
public class CListaPostulaciones extends UnicastRemoteObject implements CListaPostulacionesInterface{

    MInterfacePostulacion IPostulacion = null;
    CPrincipal cp;
    VistaArticuloAEvaluar vista;
    Vector postulaciones;
    CGestionarEvaluadores cGest;

    public CListaPostulaciones(CPrincipal c)throws RemoteException{

        super();
        cp=c;
        postulaciones = new Vector();
        vista=new VistaArticuloAEvaluar(this);
        this.ObtenerPostulaciones();
    }

    public VOPostulacion ObtenerPostulacion(String nom_user) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void MataVista() throws RemoteException{
        vista.dispose();
    }

    public void verDetalle() throws RemoteException {
        cGest = new CGestionarEvaluadores(this);
    }

    public void ObtenerPostulaciones() throws RemoteException {
        try {
            IPostulacion = manejadorRMICliente.manejadorRMICliente.getPostulacion(IPostulacion);
            postulaciones = IPostulacion.postEvaluarPor(cp.usuario);
            vista.addArticulosPostulados(postulaciones);
        } catch (MissingConfigurationParameterException ex) {
            Logger.getLogger(CListaPostulaciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(CListaPostulaciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CListaPostulaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    public void verDetalle(VOPostulacion articulo) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
