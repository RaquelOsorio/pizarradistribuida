package Controlador;

import VO.VOCriterio;
import Vista.VistaDetalleCriterio;

public class CDetalleCriterio {
    CPrincipal cp;
    VistaDetalleCriterio vista;

    CDetalleCriterio(CPrincipal aThis, VOCriterio c) {
        cp = aThis;
        vista = new VistaDetalleCriterio(this, c);
        vista.setVisible(true);
        vista.getEstado().setText("Identificado como: "+this.cp.usuario.getUsername());
    }

    public void MataVista() {
        vista.dispose();
    }

}
