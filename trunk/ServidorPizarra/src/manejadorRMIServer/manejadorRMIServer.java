package manejadorRMIServer;


import Modelo.*;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class manejadorRMIServer {

    public static Registry iniciarRegistro()
            throws NotBoundException, MalformedURLException, RemoteException {

        String portNum;
        Registry registro = null;

        try {
            portNum = ConfigurationParametersManager.getParameter("DataSource/portNum");
            int RMIPortNum = Integer.parseInt(portNum);
            registro = startRegistry(RMIPortNum);
            return registro;

        } catch (MissingConfigurationParameterException ex) {
            Logger.getLogger(manejadorRMIServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return registro;
    }
    
    // Arranca un registro RMI local si no existiera, en cierto puerto.
    private static Registry startRegistry(int RMIPortNum)
            throws RemoteException {

        Registry registro;
        try {
            // crear el registro y ligar el nombre y objeto.
            registro = LocateRegistry.getRegistry(RMIPortNum);
            // Lanza una excepción si no existe el registro
            registro.list();
        } catch (RemoteException e) {
            // No hay un registro válido en el puerto.
            registro = LocateRegistry.createRegistry(RMIPortNum);
            registro.list();
        }
        return registro;
    }
    
    /**
     *
     *  @Metodos: Se instancian las clases y se publican con algun nombre
     */    
    
    public static void setUsuario(Registry registro)
            throws NotBoundException, MalformedURLException, RemoteException {
        System.out.println("manejadorRMIServer: setUsuario");
        MInterfaceUsuario login = (MInterfaceUsuario) new Usuario();
      
        registro.rebind("usuario", login);
    
    }
    public static void setArticulo(Registry registro)
            throws NotBoundException, MalformedURLException, RemoteException {

        MInterfaceArticulo articulo = (MInterfaceArticulo) new Articulo();

        registro.rebind("articulo", articulo);

    }

    public static void setConvocatoria(Registry registro)
            throws NotBoundException, MalformedURLException, RemoteException {

        MInterfaceConvocatoria Lconvocatoria = (MInterfaceConvocatoria) new Convocatoria();

        registro.rebind("convocatoria", Lconvocatoria);


    }
    public static void setPostulacion(Registry registro)
            throws NotBoundException, MalformedURLException, RemoteException {
System.out.println("manejadorRMIServer: setPostulacion");
        MInterfacePostulacion postulacion = (MInterfacePostulacion) new Postulacion();

        registro.rebind("postulacion", postulacion);

    }
    public static void setSolicitud(Registry registro)
            throws NotBoundException, MalformedURLException, RemoteException {
        System.out.println("manejadorRMIServer: setSolicitud");
        MInterfaceSolicitud solicitud = (MInterfaceSolicitud) new Solicitud();

        registro.rebind("solicitud", solicitud);

    }
    public static void setCriterio(Registry registro)
            throws NotBoundException, MalformedURLException, RemoteException {
        System.out.println("manejadorRMIServer: setCriterio");
        MInterfaceCriterio criterio = (MInterfaceCriterio) new Criterio();

        registro.rebind("criterio", criterio);

    }

    public static void setTipoComentario(Registry registro)
            throws NotBoundException, MalformedURLException, RemoteException {
        System.out.println("manejadorRMIServer: setTipoComentario");
        MInterfaceTipoComentario comentario = (MInterfaceTipoComentario) new TipoComentario();

        registro.rebind("comentario", comentario);

    }

    //para todos los modelos igual;


}