package Modelo;

import VO.*;
import java.rmi.RemoteException;
import java.util.Vector;

public class pruebas {

    public static void main(String[] args) throws RemoteException {
//CREAR POSTULACIONES
//        int [] ev=new int[]{};
//        int [] ed=new int[]{};
//        int [] tc=new int[]{};
//        int [] cri=new int[]{};
//        VOPostulacion p=new VOPostulacion(1, 2, 2, ev, ed, tc, cri);
//        Postulacion po=new Postulacion();
//        po.GuardarPostulacion(p);
//        System.out.println("postulacion: "+p.getID());

//ASIGNAR EVALUADORES
//        po.AsignarEvaluador(p, 2, 0);
//        po.AsignarEvaluador(p, 3, 0);

//CREAR USUARIOS
//        int [] area=new int[]{2,3};
//        VOUsuario u = new VOUsuario("1", "FVera", "evaluador", "pass", "Favian", "Vera", "159172090", "fave@gmail.com", "2213214", "mi-casa", area);
//        VOUsuario u1 = new VOUsuario("1", "FAlvares", "autor", "pass", "Francisca", "Alvaro", "159172090", "pancha@gmail.com", "2213214", "mi-casa", area);
//        VOUsuario u2 = new VOUsuario("1", "Jperes", "editor-redaccion", "pass", "Juan", "Peres", "159172090", "peres@gmail.com", "2213214", "mi-casa", area);
//        VOUsuario u3 = new VOUsuario("1", "Aflores", "editor", "lalapass", "Alvaro", "Flores", "159172090", "alvaro@gmail.com", "2213214", "mi-casa", area);
//        Usuario us = new Usuario();
//        us.GuardarUsuario(u);
//        us.GuardarUsuario(u1);
//        us.GuardarUsuario(u2);
//        us.GuardarUsuario(u3);
//        System.out.println("postulacion: "+u.getId());

//CREAR ARTICULOS
//        int id, int IDAutor, int IDConvocatoria, String Nombre, String Fecha, String NomArch, String Ubicacion, String EtapaSeguimiento, String Sancion, String Descripcion
//        VOArticulo a = new VOArticulo(1, 1, 2, "Articulo de Alvaro 2", "26-05-2009", "lala2.doc", "-", "sin-evaluadores", "sin-sancion", "Esta es la descripcion del articulo 2");
//        Articulo art = new Articulo();
//        art.GuardarArticulo(a);

//CREAR CONVOCATORIAS
//        Convocatoria c= new Convocatoria();
//        Vector cc=c.getConvocatorias();
//        System.out.println("numero de conv: "+cc.size());
//        VOConvocatoria co=(VOConvocatoria)cc.get(0);
//        System.out.println("Titulo de conv: "+co.getTitulo());

//CREAR CRITERIOS
//        Criterio cri=new Criterio();
//      int ID, String Nombre, String Descripcion, String[] Concepto
//        String con[]=new String[]{"excelente", "muy bueno", "bueno", "casi bueno", "maomeno", "malo", "muy malo"};
//        VOCriterio c=new VOCriterio(1, "Criterio 2", "Descripcion del criterio 2", con);
//        System.out.println("agrego?: "+cri.agregarCriterio(c));
//CREAR TIPOS DE COMENTARIOS
//        TipoComentario tc=new TipoComentario();
//        int ID, String Nombre, String Descripcion
//        VOTipoComentario tcom=new VOTipoComentario(1, "Tipo de comentario 2", "descripcion del tipo de comentario 2");
//        System.out.println("agrego?: "+tc.agregarTipoComentario(tcom));
//MODIFICAR TIPO DE COMENTARIO
//        TipoComentario tc=new TipoComentario();
//        VOTipoComentario tcom=tc.obtenerTipoComentario(1);
//        tcom.setNombre("Nuevo titulo comentario 1");
//        tc.modTipoComentario(tcom);
    }

}
