package Controlador;

import Modelo.MInterfaceCriterio;
import Modelo.MInterfaceTipoComentario;
import VO.VOCriterio;
import VO.VOTipoComentario;
import Vista.VistaAdministrarCriyCom;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejadorRMICliente.MissingConfigurationParameterException;
import manejadorRMICliente.manejadorRMICliente;

public class CAdministrarCriyCom {
    MInterfaceCriterio ICriterio = null;
    MInterfaceTipoComentario IComentario = null;
    CPrincipal cp;
    VistaAdministrarCriyCom vista;
    Vector cri;
    Vector com;
    
    CAdministrarCriyCom(CPrincipal aThis) throws RemoteException {
        cp = aThis;
        vista = new VistaAdministrarCriyCom(this);
        cri = new Vector();
        com = new Vector();
        vista.setVisible(true);
        vista.getEstado().setText("Identificado como: "+this.cp.usuario.getUsername());
        this.ObtenerCriterios();
        vista.cargarCriterios(cri);
        this.ObtenerTiposComentarios();
        vista.cargarComentarios(com);
    }

    public void IngresarCriterioVista() throws RemoteException {
        cp.IngresarCriterioVista();
    }

    public void IngresarTipoComentarioVista() {
        cp.IngresarTipoComentarioVista();
    }

    public void MataVista() {
        this.cri=null;
        this.com=null;
        vista.dispose();
    }

    public void ModificarCriterioVista(String id) {
        int idcri = Integer.parseInt(id);
        boolean encontrado=false;
        int i=0, idv=0;
        while(i<cri.size() && !encontrado) {
            if(((VOCriterio)cri.get(i)).getID()==idcri) {
                idv=i;
                encontrado=true;
            }
            i++;
        }
        if(encontrado) {
            cp.ModificarCriterioVista((VOCriterio)cri.get(idv), idv);
        }
    }

    public void ModificarTipoComentarioVista(String id) {
        int idcri = Integer.parseInt(id);
        boolean encontrado=false;
        int i=0, idv=0;
        while(i<com.size() && !encontrado) {
            if(((VOTipoComentario)com.get(i)).getID()==idcri) {
                idv=i;
                encontrado=true;
            }
            i++;
        }
        if(encontrado) {
            cp.ModificarTipoComentarioVista((VOTipoComentario)com.get(idv), idv);
        }
    }
    
    public void VerCriterioVista(String id) {
        int idcri = Integer.parseInt(id);
        boolean encontrado=false;
        int i=0, idv=0;
        while(i<cri.size() && !encontrado) {
            if(((VOCriterio)cri.get(i)).getID()==idcri) {
                idv=i;
                encontrado=true;
            }
            i++;
        }
        if(encontrado) {
            cp.VerCriterioVista((VOCriterio)cri.get(idv));
        }
    }

    public void ObtenerCriterios() throws RemoteException {
        try {
            ICriterio = manejadorRMICliente.getCriterio(ICriterio);
            cri = ICriterio.obtenerCriterios();
        } catch (MissingConfigurationParameterException ex) {
            Logger.getLogger(CAdministrarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(CAdministrarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CAdministrarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ObtenerTiposComentarios() throws RemoteException {
        try {
            IComentario = manejadorRMICliente.getTipoComentario(IComentario);
            com = IComentario.obtenerTiposComentario();
        } catch (MissingConfigurationParameterException ex) {
            Logger.getLogger(CAdministrarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(CAdministrarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CAdministrarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void VerTipoComentarioVista(String id) {
        int idcri = Integer.parseInt(id);
        boolean encontrado=false;
        int i=0, idv=0;
        while(i<com.size() && !encontrado) {
            if(((VOTipoComentario)com.get(i)).getID()==idcri) {
                idv=i;
                encontrado=true;
            }
            i++;
        }
        if(encontrado) {
            cp.VerTipoComentarioVista((VOTipoComentario)com.get(idv));
        }
    }

    public void eliminarCriterio(String ecri) throws RemoteException {
        int verifica_elim=0;
        int idcri = Integer.parseInt(ecri);
        if(vista.VentanaConfirmacion("¿Estas seguro de que quieres eliminar este Criterio?", "Confirmacion")==0) {
            try {
                ICriterio = manejadorRMICliente.getCriterio(ICriterio);
                int idv = 0, i = 0;
                boolean encontrado=false;
                while(i<cri.size() && !encontrado) {
                    if(((VOCriterio)cri.get(i)).getID()==idcri) {
                        idv=i;
                        encontrado=true;
                    }
                    i++;
                }
                if(encontrado) {
                    verifica_elim=ICriterio.eliminarCriterio((VOCriterio)cri.get(idv));
                    switch (verifica_elim) {
                        case 0: //se borro correctamente
                            cri.remove(idv);
                            vista.borrarCriFila(idv);
                            break;
                        case 1: //no existe
                            cri.remove(idv);
                            vista.borrarCriFila(idv);
                            vista.VentanaFeedback("Al parecer, ese Criterio ya fue eliminado", "Error");
                            break;
                        case 2: //no se pudo borrar
                            vista.VentanaFeedback("Ops, algo paso y no se pudo eliminar el Criterio, intentalo denuevo por favor", "Error");
                            break;
                        case 3: //no se puede borrar
                            vista.VentanaFeedback("Ese Criterio no puede ser eliminado", "Error");
                            break;
                    }
                } else { //no existe
                    vista.VentanaFeedback("Al parecer, ese Criterio ya fue eliminado", "Error");
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
    
    public void eliminarTipoComentario(String ecom) throws RemoteException {
        int verifica_elim=0;
        int idcom = Integer.parseInt(ecom);
        if(vista.VentanaConfirmacion("¿Estas seguro de que quieres eliminar este Tipo de Comentario?", "Confirmacion")==0) {
            try {
                IComentario = manejadorRMICliente.getTipoComentario(IComentario);
                int idv = 0, i = 0;
                boolean encontrado=false;
                while(i<com.size() && !encontrado) {
                    if(((VOTipoComentario)com.get(i)).getID()== idcom) {
                        idv=i;
                        encontrado=true;
                    }
                    i++;
                }
                if(encontrado) {
                    verifica_elim=IComentario.eliminarTipoComentario((VOTipoComentario)com.get(idv));
                    switch (verifica_elim) {
                        case 0: //se borro correctamente
                            com.remove(idv);
                            vista.borrarComFila(idv);
                            break;
                        case 1: //no existe
                            com.remove(idv);
                            vista.borrarComFila(idv);
                            vista.VentanaFeedback("Al parecer, ese Tipo de Comentario ya fue eliminado", "Error");
                            break;
                        case 2: //no se pudo borrar
                            vista.VentanaFeedback("Ops, algo paso y no se pudo eliminar el Tipo de Comentario, intentalo denuevo por favor", "Error");
                            break;
                        case 3: //no se puede borrar
                            vista.VentanaFeedback("Ese Tipo de Comentario no puede ser eliminado", "Error");
                            break;
                    }
                } else { //no existe
                    vista.VentanaFeedback("Al parecer, ese Tipo de Comentario ya fue eliminado", "Error");
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

    public VOCriterio obtenerCriterio(int id) throws RemoteException{
    try {
          ICriterio = manejadorRMICliente.getCriterio(ICriterio);
        } catch (MissingConfigurationParameterException ex) {
            Logger.getLogger(CAdministrarCriyCom.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(CAdministrarCriyCom.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CAdministrarCriyCom.class.getName()).log(Level.SEVERE, null, ex);
        }
        VOCriterio criterio = new VOCriterio();
        
        criterio= ICriterio.obtenerCriterio(id);
        return criterio;    
    }
    
    public int eliminarCriterio(VOCriterio c) throws RemoteException{
    int verifica_elim_cri=0;
        try {
            ICriterio = manejadorRMICliente.getCriterio(ICriterio);
        } catch (MissingConfigurationParameterException ex) {
            Logger.getLogger(CAdministrarCriyCom.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(CAdministrarCriyCom.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CAdministrarCriyCom.class.getName()).log(Level.SEVERE, null, ex);
        }
        verifica_elim_cri=ICriterio.eliminarCriterio(c);
        return verifica_elim_cri;
    }

    void ActualizarCriterio(VOCriterio c, int fila) {
        ((VOCriterio)cri.get(fila)).setNombre(c.getNombre());
        ((VOCriterio)cri.get(fila)).setDescripcion(c.getDescripcion());
        ((VOCriterio)cri.get(fila)).setConcepto(c.getConcepto());
        vista.ActualizarCriterioFila(c, fila);
    }

    void ActualizarTipoComentario(VOTipoComentario c, int fila) {
        ((VOTipoComentario)com.get(fila)).setNombre(c.getNombre());
        ((VOTipoComentario)com.get(fila)).setDescripcion(c.getDescripcion());
        vista.ActualizarTipoComentarioFila(c, fila);
    }

    void agregarCriterioTabla(VOCriterio c) {
        cri.add(c);
        vista.agregarCriterio(c);
    }

    void agregarTipoComentarioTabla(VOTipoComentario x) {
        com.add(x);
        vista.agregarTipoComentario(x);
    }
}