package Controlador;

import VO.VOTipoComentario;
import Vista.VistaDetalleTipoComentario;

public class CDetalleTipoComentario {
    CPrincipal cp;
    VOTipoComentario comentario;
    VistaDetalleTipoComentario vista;

    CDetalleTipoComentario(CPrincipal aThis, VOTipoComentario c) {
        cp = aThis;
        comentario = c;
        vista = new VistaDetalleTipoComentario(this, c);
        vista.setVisible(true);
        vista.getEstado().setText("Identificado como: "+this.cp.usuario.getUsername());
    }

    public void MataVista() {
        vista.dispose();
    }

}
