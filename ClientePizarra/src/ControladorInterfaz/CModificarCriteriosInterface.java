/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ControladorInterfaz;

import VO.VOCriterio;
import java.rmi.RemoteException;

/**
 *
 * @author Francisca
 */
public interface CModificarCriteriosInterface {

    public VOCriterio obtenerCriterio(int id) throws RemoteException;
    public int modCriterio(VOCriterio c) throws RemoteException;
}
