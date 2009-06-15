package Controlador;

import ControladorInterfaz.CPerfilUsuarioInterface;
import VO.VOUsuario;
import Vista.VistaUsuarioPerfil;

public class CPerfilUsuario implements CPerfilUsuarioInterface {
    CPrincipal cp;
    VOUsuario usuario;
    VistaUsuarioPerfil vista;

    public CPerfilUsuario(CPrincipal c, VOUsuario usuario) {
        cp=c;
        this.usuario = usuario;
        vista = new VistaUsuarioPerfil(this, usuario);
        vista.setVisible(true);
        vista.getEstado().setText("Identificado como: "+this.cp.usuario.getUsername());
    }

    public void MataVista() {
        usuario = null;
        vista.dispose();
    }

}
