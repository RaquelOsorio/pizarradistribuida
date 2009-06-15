/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import ControladorInterfaz.CModificarCriteriosInterface;
import Modelo.MInterfaceCriterio;
import VO.VOCriterio;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejadorRMICliente.MissingConfigurationParameterException;
import manejadorRMICliente.manejadorRMICliente;

/**
 *
 * @author Francisca
 */
public class CModificarCriterios extends  UnicastRemoteObject implements CModificarCriteriosInterface{
 MInterfaceCriterio ICri = null;

    public CModificarCriterios() throws RemoteException {   
    
        super();
}
     
      
    public int modCriterio(VOCriterio c) throws RemoteException {
     CModificarCriteriosInterface cri_mod1= new CModificarCriterios();
     int notifica=0;
     notifica=cri_mod1.modCriterio(c);
     return notifica;    
 
         
   }

    public VOCriterio obtenerCriterio(int id) throws RemoteException {
        try {
            ICri = manejadorRMICliente.getCriterio(ICri);
        } catch (MissingConfigurationParameterException ex) {
            Logger.getLogger(CModificarCriterios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(CModificarCriterios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CModificarCriterios.class.getName()).log(Level.SEVERE, null, ex);
        }
        VOCriterio criterio = new VOCriterio();
        
        criterio= ICri.obtenerCriterio(id);
        return criterio;      
    }

}
