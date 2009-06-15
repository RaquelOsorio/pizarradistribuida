package Controlador;

import ControladorInterfaz.CAdmSolicitudInterface;
import Modelo.MInterfaceSolicitud;
import Modelo.MInterfaceUsuario;
import VO.VOSolicitud;
import VO.VOUsuario;
import Vista.VistaAdmSolicitud;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejadorRMICliente.MissingConfigurationParameterException;
import manejadorRMICliente.manejadorRMICliente;

public class CAdmSolicitud extends UnicastRemoteObject implements CAdmSolicitudInterface{
    MInterfaceSolicitud ISolicitud=null;
    MInterfaceUsuario IUsuario=null;
    CPrincipal cp;
    VistaAdmSolicitud vista;
    Vector sols = new Vector();

    public CAdmSolicitud(CPrincipal cp) throws RemoteException {
        super();
        this.cp = cp;
        this.vista = new VistaAdmSolicitud(this);
        this.vista.setVisible(true);
        this.ObtenerSolicitudes();
        vista.CargarSolicitudes(sols);
    }

    public void AceptarSolicitud(String nomuser, int s) throws RemoteException {
        try {
            int elim, guardar[];
            
            
            int idv = 0, i = 0;
            boolean encontrado=false;
            while(i<sols.size() && !encontrado) {
                if(((VOSolicitud)sols.get(i)).getNombreUsuario().compareTo(nomuser)==0) {
                    idv=i;
                    encontrado=true;
                }
                i++;
            }
            if(encontrado) {
                VOSolicitud sol = (VOSolicitud)sols.get(idv);
                VOUsuario usu = new VOUsuario();
                usu.setUsername(sol.getNombreUsuario());
                usu.setNombre(sol.getNombre());
                usu.setApellido(sol.getApellido());
                usu.setPass(sol.getContraseña());
                usu.setDireccion(sol.getDomicilio());
                usu.setRut(sol.getRut());
                usu.setTelefono(sol.getTelefono());
                usu.setUsertype(sol.getTipoUsuario());
                usu.setEmail(sol.getEmail());
                usu.setAreas(sol.getAreas());
                IUsuario = manejadorRMICliente.getUsuario(IUsuario);
                guardar=IUsuario.GuardarUsuario(usu);
                
                switch (guardar[0]) {
                    case 0: //se guardo el usuario correctamente
                        usu.setId(guardar[1]+"");
                        ISolicitud = manejadorRMICliente.getSolicitud(ISolicitud);
                        elim=ISolicitud.EliminarSolicitud(sol);
                        switch (elim) {
                            case 0: //se elimino la solicitud correctamente
                                cp.cau.AgregarUsuario(usu);
                                sols.remove(idv);
                                vista.borrarFila(idv);
                                if(s==vista.getSolCargada()) {
                                    vista.limpiarDetalle();

                                }
                                break;
                            case 1: //no existe
                                sols.remove(idv);
                                vista.borrarFila(idv);
                                vista.VentanaFeedback("Al parecer, esa solicitud ya fue aceptada o rechazada", "Error");
                                break;
                            case 2: //no se pudo eliminar la solicitud
                                vista.VentanaFeedback("Ops, algo paso y no se pudieron realizar los cambios, intentalo denuevo por favor", "Error");
                                break;
                        }
                        break;
                    case 1: //no existe
                        sols.remove(idv);
                        vista.borrarFila(idv);
                        vista.VentanaFeedback("Al parecer, esa solicitud ya fue aceptada o rechazada", "Error");
                        break;
                    case 2: //no se pudo borrar
                        vista.VentanaFeedback("Ops, algo paso y no se pudieron realizar los cambios, intentalo denuevo por favor", "Error");
                        break;
                }
            } else {//no se encontro la solicitud
                sols.remove(idv);
                vista.borrarFila(idv);
                vista.VentanaFeedback("Al parecer, esa solicitud ya fue aceptada o rechazada", "Error");
            }
        } catch (MissingConfigurationParameterException ex) {
            Logger.getLogger(CAdministrarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(CAdministrarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CAdministrarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ObtenerSolicitudes() throws RemoteException {
        try {
            ISolicitud = manejadorRMICliente.getSolicitud(ISolicitud);
            sols = ISolicitud.ObtenerSolicitudes();
        } catch (MissingConfigurationParameterException ex) {
            Logger.getLogger(CAdministrarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(CAdministrarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CAdministrarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void RechazarSolicitud(String nomuser) throws RemoteException {
        int verifica_elim=0;
        if(vista.VentanaConfirmacion("¿Estas seguro de que quieres eliminar a "+nomuser+"?", "Confirmacion")==0) {
            try {
                ISolicitud = manejadorRMICliente.getSolicitud(ISolicitud);
                int idv = 0, i = 0;
                boolean encontrado=false;
                while(i<sols.size() && !encontrado) {
                    if(((VOSolicitud)sols.get(i)).getNombreUsuario().compareTo(nomuser)==0) {
                        idv=i;
                        encontrado=true;
                    }
                    i++;
                }
                if(encontrado) {
                    verifica_elim=ISolicitud.EliminarSolicitud((VOSolicitud)sols.get(idv));
                    switch (verifica_elim) {
                        case 0: //se borro correctamente
                            sols.remove(idv);
                            vista.borrarFila(idv);
                            break;
                        case 1: //no existe
                            sols.remove(idv);
                            vista.borrarFila(idv);
                            vista.VentanaFeedback("Al parecer, esa solicitud ya fue aceptada o rechazada", "Error");
                            break;
                        case 2: //no se pudo borrar
                            vista.VentanaFeedback("Ops, algo paso y no se pudieron realizar los cambios, intentalo denuevo por favor", "Error");
                            break;
                    }
                } else {//no se encontro el usuario
                    sols.remove(idv);
                    vista.borrarFila(idv);
                    vista.VentanaFeedback("Al parecer, esa solicitud ya fue aceptada o rechazada", "Error");
                }
            } catch (MissingConfigurationParameterException ex) {
                Logger.getLogger(CAdministrarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
                Logger.getLogger(CAdministrarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(CAdministrarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void VerDetalleSolicitudVista(String nomuser) {
        int idv = 0, i = 0;
        boolean encontrado=false;
        while(i<sols.size() && !encontrado) {
            if(((VOSolicitud)sols.get(i)).getNombreUsuario().compareTo(nomuser)==0) {
                idv=i;
                encontrado=true;
            }
            i++;
        }
        if(encontrado) {
            System.out.println("encontrado");
            vista.cargaSolicitud((VOSolicitud)sols.get(idv));
        } else {
            vista.VentanaFeedback("Al parecer, ese usuario no existe", "Error");
        }
    }

}
