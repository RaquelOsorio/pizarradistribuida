package Controlador;

import Modelo.MInterfaceArticulo;
import Modelo.MInterfaceCriterio;
import Modelo.MInterfacePostulacion;
import Modelo.MInterfaceTipoComentario;
import VO.VOArticulo;
import VO.VOCriterio;
import VO.VOPostulacion;
import VO.VOTipoComentario;
import Vista.VistaArticuloAEvaluar;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejadorRMICliente.MissingConfigurationParameterException;
import manejadorRMICliente.manejadorRMICliente;

public class CArticuloAEvaluar {
    CPrincipal cp;
    Vector postulaciones;
    VistaArticuloAEvaluar vista;
    MInterfacePostulacion IPostulacion;
    MInterfaceCriterio ICriterio;
    MInterfaceTipoComentario ITComentario;
    Vector criterios;
    Vector tcomentarios;
    MInterfaceArticulo IArticulo;

    public CArticuloAEvaluar(CPrincipal c)throws RemoteException{
        super();
        cp=c;
        postulaciones = new Vector();
        vista=new VistaArticuloAEvaluar(this);
        this.ObtenerPostulaciones();
    }
    
    public void ObtenerPostulaciones() throws RemoteException {
        try {
            IPostulacion = manejadorRMICliente.getPostulacion(IPostulacion);
            postulaciones = IPostulacion.postEvaluarPor(cp.usuario);
            vista.addArticulosPostulados(postulaciones);
        } catch (MissingConfigurationParameterException ex) {
            Logger.getLogger(CArticuloAEvaluar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(CArticuloAEvaluar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CArticuloAEvaluar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void obtenerListaCriterios(VOPostulacion postulacion) throws RemoteException {
        try {
            criterios = new Vector();
            ICriterio = manejadorRMICliente.getCriterio(ICriterio);
            int cri[]=postulacion.getCriterios();
            for(int i=0; i<cri.length; i++) {
                VOCriterio c = new VOCriterio();
                c = ICriterio.obtenerCriterio(cri[i]);
                criterios.add(c);
            }
        } catch (MissingConfigurationParameterException ex) {
            Logger.getLogger(CInfoEvaluacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(CInfoEvaluacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CInfoEvaluacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void obtenerListaComentarios(VOPostulacion postulacion) throws RemoteException {
        try {
            tcomentarios = new Vector();
            ITComentario = manejadorRMICliente.getTipoComentario(ITComentario);
            int com[]=postulacion.getTComentarios();
            for(int i=0; i<com.length; i++) {
                VOTipoComentario c = new VOTipoComentario();
                c = ITComentario.obtenerTipoComentario(com[i]);
                tcomentarios.add(c);
            }
        } catch (MissingConfigurationParameterException ex) {
            Logger.getLogger(CInfoEvaluacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(CInfoEvaluacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CInfoEvaluacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void MataVista() {
        vista.dispose();
    }

    public void evaluarArticulo(int fila) throws RemoteException {
        this.obtenerListaComentarios((VOPostulacion)postulaciones.get(fila));
        this.obtenerListaCriterios((VOPostulacion)postulaciones.get(fila));
        cp.evaluarArticulo((VOPostulacion)postulaciones.get(fila), criterios, tcomentarios);
    }

    public void verDetalle(int fila) throws RemoteException {
        this.obtenerListaComentarios((VOPostulacion)postulaciones.get(fila));
        this.obtenerListaCriterios((VOPostulacion)postulaciones.get(fila));
        cp.VistaInfoEvaluacion((VOPostulacion)postulaciones.get(fila), criterios, tcomentarios);
    }


    public void descargarArticulo(int fila) throws RemoteException {
        try {
            IArticulo = manejadorRMICliente.getArticulo(IArticulo);
            VOArticulo n = new VOArticulo();
            n.setId(((VOPostulacion)this.postulaciones.get(fila)).getIDArticulo());
            n = IArticulo.ObtenerArticulo(n);

            File art = new File(n.getNomArch());
            vista.fc.setSelectedFile(art);
            vista.fc.showSaveDialog(vista);

            if(vista.fc.getSelectedFile()!=null) {
                byte arti[] = IArticulo.Bajar(n);
                this.GuardarArticulo(arti, vista.fc.getSelectedFile()+"");
            }

        } catch (MissingConfigurationParameterException ex) {
            Logger.getLogger(CInfoEvaluacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(CInfoEvaluacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CInfoEvaluacion.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    public void GuardarArticulo(byte art[], String a) throws RemoteException{
        BufferedOutputStream output = null;
        try {
            if(art!=null) {
                FileOutputStream nart = new FileOutputStream(a);
                output = new BufferedOutputStream(nart);
                output.write(art, 0, art.length);
                output.flush();
                output.close();
                            this.vista.VentanaFeedback("El Arituclo fue descargado correctamente", "Descargar Articulo");
            } else
                this.vista.VentanaFeedback("El Articulo no pudo ser descargado, intentalo nuevamente", "Descargar Articulo");
        } catch (Exception ex) {
            System.out.println("error al escribir");
        }
    }
}