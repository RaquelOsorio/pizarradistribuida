/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Modelo.MInterfaceConvocatoria;
import VO.VOConvocatoria;
import Vista.VistaVerConvocatoria;

/**
 *
 * @author Juan
 */
public class CVerConvocatoria {
    CPrincipal cp;
    VOConvocatoria Conv;
    VistaVerConvocatoria vista;
    MInterfaceConvocatoria IConvocatoria;

    public CVerConvocatoria(CPrincipal aThis, VOConvocatoria conv){
        super();
        this.Conv=conv;
        cp=aThis;
        vista = new VistaVerConvocatoria(this);
        vista.setVisible(true);
        vista.CargarDatosConvocatoria(conv);
    }


}
