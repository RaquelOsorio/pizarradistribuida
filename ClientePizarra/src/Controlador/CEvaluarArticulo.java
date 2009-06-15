package Controlador;

import VO.VOPostulacion;
import Vista.VistaEvaluarArticulo;
import java.util.Vector;

public class CEvaluarArticulo {
    CPrincipal cp;
    VistaEvaluarArticulo vista;
    VOPostulacion postulacion;
    Vector criterios;
    Vector tcomentarios;

    public CEvaluarArticulo(CPrincipal c, VOPostulacion p, Vector cris, Vector coms) {
        cp = c;
        postulacion = p;
        criterios = cris;
        tcomentarios = coms;
        vista = new VistaEvaluarArticulo(this, p, cris, coms);
        vista.setVisible(true);
    }

}
