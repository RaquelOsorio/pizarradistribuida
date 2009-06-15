package Controlador;

import Modelo.MInterfaceArticulo;
import Modelo.MInterfaceConvocatoria;
import Modelo.MInterfacePostulacion;
import VO.VOArticulo;
import VO.VOConvocatoria;
import VO.VOPostulacion;
import Vista.VistaPostular;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejadorRMICliente.MissingConfigurationParameterException;
import manejadorRMICliente.manejadorRMICliente;

public class CPostular {
    CPrincipal cp;
    VOConvocatoria Conv;
    VistaPostular vista;
    MInterfaceArticulo IArticulo;
    MInterfaceConvocatoria IConvocatoria;
    MInterfacePostulacion IPostulacion;
    File articulo;

    public CPostular(CPrincipal aThis, VOConvocatoria conv){
        super();
        this.Conv=conv;
        cp=aThis;
        vista = new VistaPostular(this);
        vista.setVisible(true);
        vista.CargarDatosConvocatoria(conv);
    }

    public void GuardarArticulo(VOArticulo articulo) throws RemoteException {
        try {
            IArticulo = manejadorRMICliente.getArticulo(IArticulo);
            IArticulo.GuardarArticulo(articulo);
//            control.MataVista();
        } catch (MissingConfigurationParameterException ex) {
            Logger.getLogger(CPostular.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(CPostular.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CPostular.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void MataVista() {
        vista.dispose();
    }

    public void Postular(VOArticulo art) throws RemoteException {
        try {
            VOArticulo nart;

            art.setIDAutor(Integer.parseInt(cp.usuario.getId()));
            art.setIDConvocatoria(Conv.getID());
            art.setFecha("09-06-2009");
            IArticulo = manejadorRMICliente.getArticulo(IArticulo);
            nart=IArticulo.GuardarArticulo(art);
            int vacio[]=new int[0];
            VOPostulacion post = new VOPostulacion(nart.getNombre(), nart.getDescripcion(), 1, nart.getId(), nart.getIDConvocatoria(), vacio, vacio, vacio, vacio, cp.usuario.getUsername(), 0);
            IPostulacion = manejadorRMICliente.getPostulacion(IPostulacion);
            IPostulacion.GuardarPostulacion(post);
            if(nart!=null) {
                    byte artb[]=this.leerDatos(articulo.getAbsolutePath());
                    IArticulo.Subir(artb, nart);
                    vista.limpiarPostulacion();
                    this.articulo=null;
                    vista.VentanaFeedback("La postulacion se realizo correctamente", "Postulacion");
            } else {
                    vista.VentanaFeedback("Ops, algo paso y no pudo ser guardada tu postulacion, por favor intentalo denuevo", "Postulacion");
            }
        } catch (MissingConfigurationParameterException ex) {
            Logger.getLogger(CPostular.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(CPostular.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CPostular.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getnuevoArtid() throws RemoteException {
        int result = 0;
        try {
            IArticulo = manejadorRMICliente.getArticulo(IArticulo);
            result = IArticulo.get_ultimo_idArticulo();
        } catch (MissingConfigurationParameterException ex) {
            Logger.getLogger(CPostular.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(CPostular.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CPostular.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public String id(){
           return ""+this.Conv.getID();
    }

    public void suicidio() {
           vista.dispose();
//           control.matapostular();
    }

    public void setArticulo(File articulo) {
        this.articulo = articulo;
    }

    

    public byte[] leerDatos(String nombre){
        if (nombre == null)
            return null;
        File file = new File(nombre);
        if(file.length()==0L)
            return null;
        byte buffer[] = new byte[(int)file.length()];
        try {
            BufferedInputStream input = new
            BufferedInputStream(new FileInputStream(nombre));
            input.read(buffer,0,buffer.length);
            input.close();
        } catch(Exception e) {
            System.out.println("FileServant Error: "+e.getMessage());
            e.printStackTrace();
        }
        return(buffer);
    }

    
    public static int sizeArchivo(String nombre){
        if(nombre ==null)
            return -1;
        File file = new File(nombre);
        if(file.length() == 0L)
            return -1;
        return (int)file.length()/1024;
    }
}
