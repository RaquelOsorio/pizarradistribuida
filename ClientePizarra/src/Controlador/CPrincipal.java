package Controlador;

import ControladorInterfaz.CPrincipalInterface;
import VO.VOConvocatoria;
import VO.VOCriterio;
import VO.VOPostulacion;
import VO.VOTipoComentario;
import VO.VOUsuario;
import Vista.VistaPrincipal;
import java.rmi.RemoteException;
import java.util.Vector;

public class CPrincipal implements CPrincipalInterface {

    public VOUsuario usuario;
    public VistaPrincipal vista;
    public CInicio ci;
    public CSolicitudRegistro cs;
    public CAdministrarUsuarios cau;
    public CIngresarUsuario cnu;
    public CModificarUsuario cmu;
    public CPerfilUsuario cpu;
    public CAdministrarCriyCom cacc;
    public CIngresarCriterio cic;
    public CConvocatoria cc;
    public CModificarCriterio cmc;
    public CDetalleCriterio cvc;
    public CDetalleTipoComentario cvtc;
    public CModificarTipoComentario cmtc;
    public CIngresarTipoComentario citc;
    public CArticulo ca;
    public CAdmSolicitud cas;
    public CPostular cp;
    public CIngresarConvocatoria ciconv;
    public CVerConvocatoria cvconv;
    public CListaPostulaciones cpos;
    public CArticuloAEvaluar cae;
    public CInfoEvaluacion ciev;
    public CEvaluarArticulo cea;


    public CPrincipal() {
        vista = new VistaPrincipal(this);
        vista.setVisible(false);
        ci = new CInicio(this);
    }

    public void AdministarCriyCom() throws RemoteException {
        cacc = new CAdministrarCriyCom(this);
    }

    public void AdministarUsuarios() throws RemoteException {
        cau = new CAdministrarUsuarios(this);
    }

    public void Articulo() throws RemoteException {
        ca = new CArticulo(this, usuario);
    }

    public void Convocatorias() throws RemoteException {
        cc = new CConvocatoria(this);
    }

    public void LogOut() throws RemoteException {
        usuario = null;
        vista.setVisible(false);
        ci = new CInicio(this);
    }

    public void Iniciar(VOUsuario us) throws RemoteException {
        usuario = us;

        if (usuario.getUsertype().compareTo(("editor-redaccion")) == 0) {
            vista.getBUsuariosAdministrar().setVisible(false);
            vista.getBAreaAdministrar().setVisible(false);
            vista.getBCriterioAdministrar().setVisible(false);
            vista.getBArticulos().setVisible(false);
            vista.getBPostulaciones().setVisible(false);
            vista.getBArtEvaluar().setVisible(true);
            vista.getBCerrarSesion().setVisible(true);
            vista.getBConvocatoria().setVisible(true);
            vista.getBMensajes().setVisible(true);
            vista.getBPerfil().setVisible(true);
        } else if (usuario.getUsertype().compareTo(("editor")) == 0) {
            vista.getBArticulos().setVisible(false);
            vista.getBPostulaciones().setVisible(false);
            vista.getBAreaAdministrar().setVisible(true);
            vista.getBArtEvaluar().setVisible(true);
            vista.getBCerrarSesion().setVisible(true);
            vista.getBConvocatoria().setVisible(true);
            vista.getBCriterioAdministrar().setVisible(true);
            vista.getBMensajes().setVisible(true);
            vista.getBPerfil().setVisible(true);
            vista.getBUsuariosAdministrar().setVisible(true);
        } else if (usuario.getUsertype().compareTo(("autor")) == 0) {
            vista.getBUsuariosAdministrar().setVisible(false);
            vista.getBAreaAdministrar().setVisible(false);
            vista.getBCriterioAdministrar().setVisible(false);
            vista.getBArtEvaluar().setVisible(false);
            vista.getBArticulos().setVisible(true);
            vista.getBPostulaciones().setVisible(true);
            vista.getBCerrarSesion().setVisible(true);
            vista.getBConvocatoria().setVisible(true);
            vista.getBMensajes().setVisible(true);
            vista.getBPerfil().setVisible(true);
        } else if (usuario.getUsertype().compareTo(("evaluador")) == 0) {
            vista.getBUsuariosAdministrar().setVisible(false);
            vista.getBAreaAdministrar().setVisible(false);
            vista.getBCriterioAdministrar().setVisible(false);
            vista.getBArticulos().setVisible(false);
            vista.getBPostulaciones().setVisible(false);
            vista.getBArtEvaluar().setVisible(true);
            vista.getBCerrarSesion().setVisible(true);
            vista.getBConvocatoria().setVisible(true);
            vista.getBMensajes().setVisible(true);
            vista.getBPerfil().setVisible(true);
        }
        vista.setTitle("Bienvenido, " + this.usuario.getNombre() + " " + this.usuario.getApellido());
        vista.setLocationRelativeTo(null);
        vista.getJLabel8().setText("Identificado como: " + this.usuario.getUsername());
        vista.setVisible(true);
        ci.MataVista();
        ci = null;
    }

    /*public void nuevoArticuloVista(VOConvocatoria ConvocatoriaSeleccionada) throws RemoteException {
    try {
    cp = new CPostular(this, ConvocatoriaSeleccionada);
    //CPrincipal aThis, VOUsuario usuario, VOConvocatoria conv
    } catch (MissingConfigurationParameterException ex) {
    Logger.getLogger(CPrincipal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (NotBoundException ex) {
    Logger.getLogger(CPrincipal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (MalformedURLException ex) {
    Logger.getLogger(CPrincipal.class.getName()).log(Level.SEVERE, null, ex);
    }
    //CPrincipal aThis, VOUsuario usuario, VOConvocatoria conv
    }*/
    public void nuevoUsuarioVista() throws RemoteException {
        cnu = new CIngresarUsuario(this);
    }

    public void ModificarUsuarioVista(VOUsuario usuario, int fila) throws RemoteException {
        cmu = new CModificarUsuario(this, usuario, fila);
    }

    public void VerUsuarioPerfilVista(VOUsuario usuario) throws RemoteException {
        cpu = new CPerfilUsuario(this, usuario);
    }

    public void IngresarCriterioVista() {
        cic = new CIngresarCriterio(this);
    }

    public void IngresarTipoComentarioVista() {
        citc = new CIngresarTipoComentario(this);
    }

    public void ModificarCriterioVista(VOCriterio c, int fila) {
        cmc = new CModificarCriterio(this, c, fila);
    }

    public void ModificarTipoComentarioVista(VOTipoComentario c, int fila) {
        cmtc = new CModificarTipoComentario(this, c, fila);
    }

    public void VerCriterioVista(VOCriterio c) {
        cvc = new CDetalleCriterio(this, c);
    }

    public void VerTipoComentarioVista(VOTipoComentario c) {
        cvtc = new CDetalleTipoComentario(this, c);
    }

    public void verSolicitudes() throws RemoteException {
        cas = new CAdmSolicitud(this);
    }

    public void ArticulosEvaluar() throws RemoteException {
        cae = new CArticuloAEvaluar(this);
    }

    void VerConvocatoriaVista(VOConvocatoria c) {
        cvconv = new CVerConvocatoria(this, c);
    }

    void VistaInfoEvaluacion(VOPostulacion p, Vector cris, Vector tcoms) throws RemoteException {
        ciev = new CInfoEvaluacion(this, p, cris, tcoms);
    }

    void evaluarArticulo(VOPostulacion postulacion, Vector criterios, Vector tcomentarios) {
        cea = new CEvaluarArticulo(this, postulacion, criterios, tcomentarios);
    }

    void nuevaConvocatoriaVista() throws RemoteException {
        ciconv = new CIngresarConvocatoria(this);
    }

    void PostularVista(VOConvocatoria c) {
        cp = new CPostular(this, c);
    }
}
