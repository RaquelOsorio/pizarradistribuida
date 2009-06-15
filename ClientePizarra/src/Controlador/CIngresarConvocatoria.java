package Controlador;

import Vista.VistaIngresarConvocatoria;

/**
 *
 * @author Juan
 */
public class CIngresarConvocatoria {

    CPrincipal cp;
    VistaIngresarConvocatoria vista;

    public CIngresarConvocatoria(CPrincipal c) {
        cp = c;
        vista = new VistaIngresarConvocatoria(this);
        vista.setVisible(true);
    }


}
