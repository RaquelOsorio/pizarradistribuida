package Controlador;

import Modelo.MInterfaceCriterio;
import Modelo.MInterfaceTipoComentario;
import VO.VOPostulacion;
import Vista.VistaInfoEvaluacion;
import java.rmi.RemoteException;
import java.util.Vector;

public class CInfoEvaluacion {
    CPrincipal cp;
    VistaInfoEvaluacion vista;
    VOPostulacion postulacion;
    MInterfaceCriterio ICriterio;
    MInterfaceTipoComentario ITComentario;
    Vector criterios;
    Vector tcomentarios;

    public CInfoEvaluacion(CPrincipal c, VOPostulacion p, Vector cris, Vector coms) throws RemoteException {
        cp=c;
        postulacion = p;
        criterios = cris;
        tcomentarios = coms;
        vista = new VistaInfoEvaluacion(this);
        vista.setVisible(true);
        vista.cargaPostulacion(postulacion);
        vista.cargaListaCriterios(criterios);
        vista.cargaListaComentarios(tcomentarios);
    }

    

    public void evaluarArticulo() {
        cp.evaluarArticulo(postulacion, criterios, tcomentarios);
    }
}