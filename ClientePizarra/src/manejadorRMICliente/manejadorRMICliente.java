package manejadorRMICliente;

import Modelo.MInterfaceArticulo;
import Modelo.MInterfaceConvocatoria;
import Modelo.MInterfaceCriterio;
import Modelo.MInterfacePostulacion;
import Modelo.MInterfaceSolicitud;
import Modelo.MInterfaceTipoComentario;
import Modelo.MInterfaceUsuario;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class manejadorRMICliente {

    static Registry registro;
    static int RMIPort;
    static String hostName;

    public static MInterfaceArticulo getArticulo(MInterfaceArticulo c)  throws MissingConfigurationParameterException, NotBoundException,
            MalformedURLException, RemoteException {
        System.out.println("manejadorRMICliente: getArticulo");
        java.security.AllPermission a = new java.security.AllPermission();
        System.setProperty("java.security.policy", "rmi.policy");
        hostName = ServidorManager.getParameter("servidor");
        RMIPort = Integer.parseInt(ServidorManager.getParameter("portNum"));
        registro = LocateRegistry.getRegistry(hostName, RMIPort);
        c = (MInterfaceArticulo) registro.lookup("articulo");

        return c;
    }

    public static MInterfaceConvocatoria getConvocatoria(MInterfaceConvocatoria c)  throws MissingConfigurationParameterException, NotBoundException,
            MalformedURLException, RemoteException {

        System.out.println("manejadorRMICliente: getConvocatoria");
        java.security.AllPermission a = new java.security.AllPermission();
        System.setProperty("java.security.policy", "rmi.policy");
        hostName = ServidorManager.getParameter("servidor");
        RMIPort = Integer.parseInt(ServidorManager.getParameter("portNum"));
        registro = LocateRegistry.getRegistry(hostName, RMIPort);
        c = (MInterfaceConvocatoria) registro.lookup("convocatoria");

        return c;
    }

    public static MInterfacePostulacion getPostulacion(MInterfacePostulacion c) throws MissingConfigurationParameterException, NotBoundException,
        MalformedURLException, RemoteException {

            System.out.println("manejadorRMICliente: getPostulacion");
            java.security.AllPermission a = new java.security.AllPermission();
            System.setProperty("java.security.policy", "rmi.policy");
            hostName = ServidorManager.getParameter("servidor");
            RMIPort = Integer.parseInt(ServidorManager.getParameter("portNum"));
            registro = LocateRegistry.getRegistry(hostName, RMIPort);
            c = (MInterfacePostulacion) registro.lookup("postulacion");
            return c;
        }

    
    public static MInterfaceUsuario getUsuario(MInterfaceUsuario c)
            throws MissingConfigurationParameterException, NotBoundException,
            MalformedURLException, RemoteException {

        System.out.println("manejadorRMICliente: getUsuario");
        java.security.AllPermission a = new java.security.AllPermission();
        System.setProperty("java.security.policy", "rmi.policy");
        hostName = ServidorManager.getParameter("servidor");        
        RMIPort = Integer.parseInt(ServidorManager.getParameter("portNum"));
        registro = LocateRegistry.getRegistry(hostName, RMIPort);
        c = (MInterfaceUsuario) registro.lookup("usuario");

        return c;
        
    }
    
    public static MInterfaceSolicitud getSolicitud(MInterfaceSolicitud c)
            throws MissingConfigurationParameterException, NotBoundException,
            MalformedURLException, RemoteException {

        System.out.println("manejadorRMICliente: getSolicitud");
        java.security.AllPermission a = new java.security.AllPermission();
        System.setProperty("java.security.policy", "rmi.policy");
        hostName = ServidorManager.getParameter("servidor");        
        RMIPort = Integer.parseInt(ServidorManager.getParameter("portNum"));
        registro = LocateRegistry.getRegistry(hostName, RMIPort);
        c = (MInterfaceSolicitud) registro.lookup("solicitud");
        
        return c;
        
    }

    public static MInterfaceCriterio getCriterio(MInterfaceCriterio c)
            throws MissingConfigurationParameterException, NotBoundException,
            MalformedURLException, RemoteException {

        java.security.AllPermission a = new java.security.AllPermission();
        System.setProperty("java.security.policy", "rmi.policy");
        hostName = ServidorManager.getParameter("servidor");        
        RMIPort = Integer.parseInt(ServidorManager.getParameter("portNum"));
        registro = LocateRegistry.getRegistry(hostName, RMIPort);
        c = (MInterfaceCriterio) registro.lookup("criterio");
        return c;
        
    }

    public static MInterfaceTipoComentario getTipoComentario(MInterfaceTipoComentario c)
            throws MissingConfigurationParameterException, NotBoundException,
            MalformedURLException, RemoteException {

        java.security.AllPermission a = new java.security.AllPermission();
        System.setProperty("java.security.policy", "rmi.policy");
        hostName = ServidorManager.getParameter("servidor");
        RMIPort = Integer.parseInt(ServidorManager.getParameter("portNum"));
        registro = LocateRegistry.getRegistry(hostName, RMIPort);
        c = (MInterfaceTipoComentario) registro.lookup("comentario");
        return c;

    }

}
