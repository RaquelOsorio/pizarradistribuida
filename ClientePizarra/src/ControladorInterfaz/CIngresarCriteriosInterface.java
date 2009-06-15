/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ControladorInterfaz;

import VO.VOCriterio;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Francisca
 */
public interface CIngresarCriteriosInterface extends Remote{

        public int agregarCriterio(VOCriterio c) throws RemoteException;
   
}
