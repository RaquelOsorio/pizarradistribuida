/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import ControladorInterfaz.CArticuloInterface;
import Modelo.MInterfaceArticulo;
import VO.VOUsuario;
import Vista.VistaArticulo;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejadorRMICliente.MissingConfigurationParameterException;
import manejadorRMICliente.manejadorRMICliente;

/**
 *
 * @author Francisca
 */
public class CArticulo extends UnicastRemoteObject implements CArticuloInterface {

    CPrincipal control;
    VOUsuario usuario;

    MInterfaceArticulo IArticulo = null;
    VistaArticulo vista;
    Vector articulos;



    public CArticulo() throws RemoteException {
       super(); 
       }


    CArticulo(CPrincipal aThis, VOUsuario usuario) throws RemoteException {
        super();
        control = aThis;
        this.usuario=usuario;
        articulos = new Vector();
        vista = new VistaArticulo(this);
        this.Iniciar();
        this.ObtenerArticulos();

    }

    public void MataVista() {
        vista.dispose();
    }

    private void Iniciar() {
        if (usuario.getUsertype().compareTo(("autor")) == 0) {
            vista.BModificar().setVisible(false);
            vista.BEliminar().setVisible(false);
            vista.BVerDetalle().setVisible(false);
        }
        vista.setTitle("Bienvenido, " + this.usuario.getNombre() + " " + this.usuario.getApellido());
        vista.setLocationRelativeTo(null);
        vista.getEstado().setText("Identificado como: " + this.usuario.getUsername());
        vista.setVisible(true);    }

    private void ObtenerArticulos() {
        try {
            IArticulo = manejadorRMICliente.getArticulo(IArticulo);
            articulos = IArticulo.ObtenerArticulos(this.usuario);
            vista.addArticulos(articulos);
        } catch (MissingConfigurationParameterException ex) {
            Logger.getLogger(CArticulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(CArticulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CArticulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(CArticulo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

/*public class llamada_menu_articulo (int numero_menu){
        switch(numero_menu){
            case numero_menu==1: //adm. usuarios
            CArticuloInterface guia= (CArticuloInterface) new CModificarArticulo();
            break;
            case numero_menu==2: //adm. usuarios
            CArticuloInterface guia= (CArticuloInterface) new CModificarArticulo();
            break;
            case numero_menu==1: //adm. usuarios
            CArticuloInterface guia= (CArticuloInterface) new CModificarArticulo();
            break;
*/
}
