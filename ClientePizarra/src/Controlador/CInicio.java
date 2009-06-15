package Controlador;
//lala
import ControladorInterfaz.CInicioInterface;
import Modelo.MInterfaceUsuario;
import VO.VOSolicitud;
import VO.VOUsuario;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejadorRMICliente.MissingConfigurationParameterException;
import manejadorRMICliente.manejadorRMICliente;
import Vista.VistaInicio;
 
public class CInicio implements CInicioInterface {

    MInterfaceUsuario IUsuario = null;
    // declarar controladores secundarios
    VistaInicio vista;
    public static CPrincipal cp;



    public CInicio(CPrincipal c) {
        cp = c;
        vista=new VistaInicio(this);
        vista.setVisible(true);
    }

    public VOUsuario loginC(VOUsuario usuario) throws RemoteException {

        VOUsuario respuesta = new VOUsuario();

        try {
            String user = usuario.getUsername();
            String pass = usuario.getPass();
            IUsuario = manejadorRMICliente.getUsuario(IUsuario);
            respuesta = IUsuario.ObtenerUsuario(user, pass);
        } catch (MissingConfigurationParameterException ex) {
            Logger.getLogger(CInicio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(CInicio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }
    @SuppressWarnings("static-access")
    public void login(VOUsuario usu) throws RemoteException {
        VOUsuario us = this.loginC(usu);
        if (us !=null) {
            vista.registrado();
            vista.setVisible(false);
            cp.Iniciar(us);
        } else {
            vista.noregistrado();
        }
    }

    public void GuardarSolicitud(VOSolicitud usuario) throws RemoteException {
        cp.cs = new CSolicitudRegistro(cp);
        cp.cs.GuardarSolicitud(usuario);
        cp.cs=null;
    }

    public VOUsuario buscarUsuario(VOUsuario usuario) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void MataVista() throws RemoteException {
        vista.dispose();
    }
}
