/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;
import VO.VOConvocatoria;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author Juan
 */
public interface MInterfaceConvocatoria extends Remote{
    public VOConvocatoria getConvocatoria(VOConvocatoria con) throws RemoteException;
    public Vector getConvocatorias() throws RemoteException;

}
