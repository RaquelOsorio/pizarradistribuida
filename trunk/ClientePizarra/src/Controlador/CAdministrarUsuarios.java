package Controlador;

import ControladorInterfaz.CAdministrarUsuariosInterface;
import Modelo.MInterfaceUsuario;
import VO.VOUsuario;
import Vista.VistaAdministrarUsuarios;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejadorRMICliente.MissingConfigurationParameterException;
import manejadorRMICliente.manejadorRMICliente;

public class CAdministrarUsuarios extends UnicastRemoteObject implements CAdministrarUsuariosInterface {
    MInterfaceUsuario IUsuario = null;
    CPrincipal cp;
    VistaAdministrarUsuarios vista;
    Vector usuarios;

    public CAdministrarUsuarios(CPrincipal c)throws RemoteException{
        super();
        cp=c;
        usuarios=new Vector();
        vista=new VistaAdministrarUsuarios(this);
        vista.getEstado().setText("Identificado como: "+this.cp.usuario.getUsername());
        this.ObtenerUsuarios();
        vista.addUsuarios(usuarios);
    }

    public void MataVista() {
        vista.dispose();
    }

    public void ModificarUsuarioVista(String nomuser) throws RemoteException {
        int idv = 0, i = 0;
        boolean encontrado=false;
        while(i<usuarios.size() && !encontrado) {
            if(((VOUsuario)usuarios.get(i)).getUsername().compareTo(nomuser)==0) {
                idv=i;
                encontrado=true;
            }
            i++;
        }
        if(encontrado)
            this.cp.ModificarUsuarioVista((VOUsuario)usuarios.get(idv), idv);
        else {
            usuarios.remove(idv);
            vista.borrarFila(idv);
            vista.VentanaFeedback("Al parecer, ese usuario no existe", "Error");
        }
    }

    public VOUsuario ObtenerUsuario(String nom_user) throws RemoteException {
        try {
            IUsuario = manejadorRMICliente.getUsuario(IUsuario);
        } catch (MissingConfigurationParameterException ex) {
            Logger.getLogger(CAdministrarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(CAdministrarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CAdministrarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        VOUsuario user = new VOUsuario();
        
        user= IUsuario.ObtenerUsuario(nom_user);
        return user;    
    }

    public void ObtenerUsuarios() throws RemoteException {
        try {
            IUsuario = manejadorRMICliente.getUsuario(IUsuario);
            usuarios = IUsuario.ObtenerUsuarios();
        } catch (MissingConfigurationParameterException ex) {
            Logger.getLogger(CAdministrarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(CAdministrarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CAdministrarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void VerSolicitudesVista() throws RemoteException {
        cp.verSolicitudes();
    }

    public void VerUsuarioPerfilVista(String nomuser) throws RemoteException {
        int idv = 0, i = 0;
        boolean encontrado=false;
        while(i<usuarios.size() && !encontrado) {
            if(((VOUsuario)usuarios.get(i)).getUsername().compareTo(nomuser)==0) {
                idv=i;
                encontrado=true;
            }
            i++;
        }
        if(encontrado)
            this.cp.VerUsuarioPerfilVista((VOUsuario)usuarios.get(idv));
        else {
            vista.VentanaFeedback("Al parecer, ese usuario no existe", "Error");
        }
    }

    public void eliminarUsuario(String nomuser) throws RemoteException {
        int verifica_elim=0;
        if(vista.VentanaConfirmacion("¿Estas seguro de que quieres eliminar a "+nomuser+"?", "Confirmacion")==0) {
            try {
                IUsuario = manejadorRMICliente.getUsuario(IUsuario);
                int idv = 0, i = 0;
                boolean encontrado=false;
                while(i<usuarios.size() && !encontrado) {
                    if(((VOUsuario)usuarios.get(i)).getUsername().compareTo(nomuser)==0) {
                        idv=i;
                        encontrado=true;
                    }
                    i++;
                }
                if(encontrado) {
                    if(((VOUsuario)usuarios.get(idv)).getId().compareTo(this.cp.usuario.getId())!=0) {
                        verifica_elim=IUsuario.eliminarUsuario((VOUsuario)usuarios.get(idv));
                        switch (verifica_elim) {
                            case 0: //se borro correctamente
                                usuarios.remove(idv);
                                vista.borrarFila(idv);
                                break;
                            case 1: //no existe
                                usuarios.remove(idv);
                                vista.borrarFila(idv);
                                vista.VentanaFeedback("Al parecer, ese usuario ya fue eliminado", "Error");
                                break;
                            case 2: //no se pudo borrar
                                vista.VentanaFeedback("Ops, algo paso y no se pudo eliminar el usuario, intentalo denuevo por favor", "Error");
                                break;
                            case 3: //no se puede borrar ese usuario
                                vista.VentanaFeedback("Ese usuario no puede ser eliminado", "Error");
                                break;
                        }
                    } else { //no se puede borrar a si mismo
                        vista.VentanaFeedback("No te puedes eliminar a ti mismo", "Error");
                    }
                } else {//no se encontro el usuario
                    usuarios.remove(idv);
                    vista.borrarFila(idv);
                    vista.VentanaFeedback("Al parecer, ese usuario ya fue eliminado", "Error");
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

    public void nuevoUsuarioVista() throws RemoteException {
        cp.nuevoUsuarioVista();
    }

    void ActualizarUsuario(VOUsuario mu, int fila) throws RemoteException {
        ((VOUsuario)usuarios.get(fila)).setNombre(mu.getNombre());
        ((VOUsuario)usuarios.get(fila)).setApellido(mu.getApellido());
        ((VOUsuario)usuarios.get(fila)).setDireccion(mu.getDireccion());
        ((VOUsuario)usuarios.get(fila)).setEmail(mu.getEmail());
        ((VOUsuario)usuarios.get(fila)).setPass(mu.getPass());
        ((VOUsuario)usuarios.get(fila)).setRut(mu.getRut());
        ((VOUsuario)usuarios.get(fila)).setTelefono(mu.getTelefono());
        ((VOUsuario)usuarios.get(fila)).setUsertype(mu.getUsertype());
        ((VOUsuario)usuarios.get(fila)).setAreas(mu.getAreas());
        vista.actualizarFila(mu, fila);
    }

    void AgregarUsuario(VOUsuario usu) {
        usuarios.add(usu);
        vista.agregarUsuarioTabla(usu);
    }
}