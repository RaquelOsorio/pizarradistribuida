package Modelo;


import VO.VOArticulo;
import VO.VOPostulacion;
import VO.VOUsuario;
import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class Postulacion extends UnicastRemoteObject implements MInterfacePostulacion {
    File datos, datost;
    //estructura:
    //idpost idart idconv estado evaluadores editores Tcomentarios criterios nomuser titulo descripcion
    public Postulacion() throws RemoteException {
        try {
            datos = new File("postulaciones.txt");
            datost = new File("postulaciones.temp.txt");
            if(!datos.exists()) {
                datos.createNewFile();
            }
            if(!datost.exists()) {
                datost.createNewFile();
            }
        } catch (FileNotFoundException e) {
            System.err.println("FileStreams: " + e);
        } catch (IOException e) {
            System.err.println("FileStreams: " + e);
        }
    }
    //ide: id del evaluador o editor de redaccion
    //tipoev: 0 para evaluador, 1 para editor de redaccion
    //salida:
    //0: ok;
    //1: error al copiar;
    //2: no existe la postulacion;
    //3: el evaluador ya esta asignado (por implementar)
    //revisar que pasa si el evaluador ya esta asignado
    public int AsignarEvaluador(VOPostulacion p, int ide, int tipoev) throws RemoteException {
        int r=1, estado=0;
        try {
            int idpost=p.getID();
            String s="";
            String l[];
            String ev;
            FileReader fr = new FileReader(datos);
            BufferedReader entrada = new BufferedReader(fr);

            FileWriter fw = new FileWriter(datost);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter salida = new PrintWriter(bw);

            while((s=entrada.readLine())!=null) {
                l=s.split(" ");
                if(Integer.parseInt(l[0])==idpost) {
                    if(l[4+tipoev].split("-").length>0) {
                        ev=l[4+tipoev]+"-"+ide;
                    } else {
                        ev=ide+"";
                    }
                    estado=1;
                    l[4+tipoev]=ev;
                    salida.println(l[0]+" "+l[1]+" "+l[2]+" "+estado+" "+l[4]+" "+l[5]+" "+l[6]+" "+l[7]+" "+l[8]+" "+l[9]+" "+l[10]);
                    r=0;

                    String sev[] = ev.split("-");
                    int iev[] = new int [sev.length];

                    for(int i=0; i<iev.length; i++)
                        iev[i]=Integer.parseInt(sev[i]);
                    if(tipoev==0) p.setEvaluadores(iev);
                    else p.setEditorRed(iev);
                } else {
                    salida.println(s);
                }
            }
            salida.close();
            entrada.close();
            if(r==0) {

                VOArticulo art = new VOArticulo();
                art.setId(p.getIDArticulo());
                Articulo artt = new Articulo();
                art=artt.ObtenerArticulo(art);
                switch (estado) {
                    case 0:
                        art.setEtapaSeguimiento("sin-evaluadores");
                        break;
                    case 1:
                        art.setEtapaSeguimiento("en-evaluacion");
                        break;
                }
                artt.ModificarArticulo(art);
                this.actualizar_datosPostulacion();
                return r;
            } else return 2;
        } catch (FileNotFoundException e) {
            System.err.println("FileStreamsTest: " + e);
            return 1;
        } catch (IOException e) {
            System.err.println("FileStreamsTest: " + e);
            return 1;
        }
    }

    /**
     * 
     * @param p: VOPostulacion
     * @param ide: id del evaluador o editor de redaccion
     * @param tipoev: 0: evaluador, 1: editor de redaccion
     * @return 0:todo bien,
     * 1:error al copiar,
     * 2: no existe la postulacion,
     * 3: no existe el evaluador
     * @throws java.rmi.RemoteException
     */
    public int DesasignarEvaluador(VOPostulacion p, int ide, int tipoev) throws RemoteException {
        //idpost idart idconv estado evaluadores editores Tcomentarios criterios nomuser titulo descripcion
        int r=1;
        int estado=1;
        try {
            int idpost=p.getID();
            String s="";
            String l[];
            String ev[];
            FileReader fr = new FileReader(datos);
            BufferedReader entrada = new BufferedReader(fr);

            FileWriter fw = new FileWriter(datost);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter salida = new PrintWriter(bw);

            while((s=entrada.readLine())!=null) {
                l=s.split(" ");
                String nev="-";
                if(Integer.parseInt(l[0])==idpost) {
                    if(l[4+tipoev].split("-").length>0) {
                        ev=l[4+tipoev].split("-");

                        int i=0;
                        while(i<ev.length) {
                            if(Integer.parseInt(ev[i])== ide) {//es el que quiero borrar
                                r=0;//lo encontre y no copio el evaluador
                                if(ev.length==1) {//tenia uno solo y lo borre
                                    nev="-";
                                    estado=0;
                                }
                            } else {//no es el que quiero borrar
                                if (i==1 && r==0) nev = ev[i];//borre el primero, entonces el segundo queda como primero
                                else //todavia no lo encuentro
                                    if(i>0) nev=nev+"-"+ev[i]; //voi copiando los que sigen como evaluadores
                                    else nev=ev[i];
                            }
                            i++;
                        }
                        if(r>0) r=3;
                    } else {
                        r=3;
                    }
                    if(tipoev==0)
                        salida.println(l[0]+" "+l[1]+" "+l[2]+" "+estado+" "+nev+" "+l[5]+" "+l[6]+" "+l[7]+" "+l[8]+" "+l[9]+" "+l[10]);
                    else
                        salida.println(l[0]+" "+l[1]+" "+l[2]+" "+estado+" "+l[4]+" "+nev+" "+l[6]+" "+l[7]+" "+l[8]+" "+l[9]+" "+l[10]);
                } else {
                    salida.println(s);
                }
            }
            salida.close();
            entrada.close();
            if(r==0) {
                VOArticulo art = new VOArticulo();
                art.setId(p.getIDArticulo());
                Articulo artt = new Articulo();
                art=artt.ObtenerArticulo(art);
                switch (estado) {
                    case 0:
                        art.setEtapaSeguimiento("sin-evaluadores");
                        break;
                    case 1:
                        art.setEtapaSeguimiento("en-evaluacion");
                        break;
                }
                artt.ModificarArticulo(art);

                this.actualizar_datosPostulacion();
                return r;
            } else return 2;
        } catch (FileNotFoundException e) {
            System.err.println("FileStreamsTest: " + e);
            return 1;
        } catch (IOException e) {
            System.err.println("FileStreamsTest: " + e);
            return 1;
        }
    }
    //p: VOPostulacion de la cual se quiere obtener los evaluadores o editores
    //t: 0 para evaluadores, 1: para editores de redaccion
    //retorna un vector con los usuarios evaluadores
    public Vector ObtenerEvaluadores(VOPostulacion p, int t) throws RemoteException {

        Vector ev=new Vector();
        Usuario n=new Usuario();
        VOUsuario u=null;
        String val[]=new String[1];
        String tipo[]=new String[1];
        tipo[0]="id";
        int eval[];
        if(t==0) eval=p.getEvaluadores();
        else eval=p.getEditorRed();
        for(int i=0; i<eval.length; i++) {
            val[0]=eval[i]+"";
            u=n.obtenerdatosUsuario(val, tipo);
            ev.add(u);
        }
        return ev;
    }

    //copia lo del archivo temporal al definitivo
    public void actualizar_datosPostulacion() throws RemoteException  {
        try {
            String s="";
            FileReader fr = new FileReader(datost);
            BufferedReader entrada = new BufferedReader(fr);

            FileWriter fw = new FileWriter(datos);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter salida = new PrintWriter(bw);

            while((s=entrada.readLine())!=null) {
                salida.println(s);
            }
            salida.close();
            entrada.close();
        } catch (FileNotFoundException e) {
            System.err.println("FileStreamsTest: " + e);
        } catch (IOException e) {
            System.err.println("FileStreamsTest: " + e);
        }
    }

    public void GuardarPostulacion(VOPostulacion p) throws RemoteException {
        //idpost idart idconv estado evaluadores editores Tcomentarios criterios nomuser titulo descripcion
        try {
            FileWriter fw = new FileWriter(datos, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter salida = new PrintWriter(bw);

            int idp=this.get_ultimo_idPostulacion()+1;
            String ev, ed, TC, Cri;
            if(p.getEvaluadores().length>0) {
                ev=p.getEvaluadores()[0]+"";
                for(int i=1; i<p.getEvaluadores().length; i++) {
                    ev+="-"+p.getEvaluadores()[i];
                }
            } else {
                ev="-";
            }
            if(p.getEditorRed().length>0) {
                ed=p.getEditorRed()[0]+"";
                for(int i=1; i<p.getEditorRed().length; i++) {
                    ed+="-"+p.getEditorRed()[i];
                }
            } else {
                ed="-";
            }
            if(p.getTComentarios().length>0) {
                TC=p.getTComentarios()[0]+"";
                for(int i=1; i<p.getTComentarios().length; i++) {
                    TC+="-"+p.getTComentarios()[i];
                }
            } else {
                TC="-";
            }
            if(p.getCriterios().length>0) {
                Cri=p.getCriterios()[0]+"";
                for(int i=1; i<p.getCriterios().length; i++) {
                    Cri+="-"+p.getCriterios()[i];
                }
            } else {
                Cri="-";
            }
            p.setID(idp);
            System.out.println(idp+" "+p.getTitulo()+" "+p.getDescripción()+" "+p.getIDArticulo()+" "+p.getIDConvocatoria()+" "+ev+" "+ed+" "+TC+" "+Cri);
            salida.println(idp+" "+
                    p.getIDArticulo()+" "+
                    p.getIDConvocatoria()+" "+
                    p.getEstado()+" "+
                    ev+" "+
                    ed+" "+
                    TC+" "+
                    Cri+" "+
                    p.getUser()+" "+
                    this.agregaGuiones(p.getTitulo())+" "+
                    this.agregaGuiones(p.getDescripción()));
            salida.close();
        } catch (FileNotFoundException e) {
            System.err.println("FileStreamsTest: " + e);
        } catch (IOException e) {
            System.err.println("FileStreamsTest: " + e);
        }
    }

    //obtiene el id de la ultima postulacion registrado
    public int get_ultimo_idPostulacion() throws RemoteException  {
        int idu=0;
        try {
            FileReader fr = new FileReader(datos);
            BufferedReader entrada = new BufferedReader(fr);
            String s, p="";
            String texto[]=new String[10];
            while((s = entrada.readLine()) != null) p=s;
            System.out.println("p1: "+p);
            if(p!=null && p.compareTo("")!=0) {
                texto=p.split(" ");
                System.out.println("p2: "+p);
                if(texto[0].compareTo("")!=0) {
                    idu=Integer.parseInt(texto[0]);
                    System.out.println("idp: "+idu);
                } else
                    idu=0;
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            System.err.println("FileStreamsTest: " + e);
        } catch (IOException e) {
            System.err.println("FileStreamsTest: " + e);
        }
        return idu;
    }

    public Vector obtenerPostulaciones() throws RemoteException {

        Vector post =new Vector();

        try {
            FileReader fr = new FileReader(datos);
            BufferedReader entrada = new BufferedReader(fr);
            String s;
            String texto[]=new String[11];
            while((s = entrada.readLine()) != null) {
                texto=s.split(" ");
                int Evaluadores[]= new int[texto[4].split("-").length];
                int EditorRed[]= new int[texto[5].split("-").length];
                int TComentarios[]=new int[texto[6].split("-").length];
                int Criterios[]=new int[texto[7].split("-").length];

                String ev[]=texto[4].split("-");
                String ed_red[]=texto[5].split("-");
                String tcom[]=texto[6].split("-");
                String cri[]=texto[7].split("-");


                for(int i=0; i<Evaluadores.length; i++) {
                    Evaluadores[i]=Integer.parseInt(ev[i]);
                }

                 for(int i=0; i<EditorRed.length; i++) {
                    EditorRed[i]=Integer.parseInt(ed_red[i]);
                }

                 for(int i=0; i<TComentarios.length; i++) {
                    TComentarios[i]=Integer.parseInt(tcom[i]);
                }

                 for(int i=0; i<Criterios.length; i++) {
                    Criterios[i]=Integer.parseInt(cri[i]);
                }
                //idpost idart idconv estado evaluadores editores Tcomentarios criterios nomuser titulo descripcion
//                String Titulo, String descripcion,int ID, int IDArticulo, int IDConvocatoria, int[] Evaluadores, int[] EditorRed, int[] TComentarios, int[] Criterios,
//                         String User, int estado
                VOPostulacion p = new VOPostulacion(
                        this.quitaGuiones(texto[9]),
                        this.quitaGuiones(texto[10]),
                        Integer.parseInt(texto[0]),
                        Integer.parseInt(texto[1]),
                        Integer.parseInt(texto[2]),
                        Evaluadores,
                        EditorRed,
                        TComentarios,
                        Criterios,
                        texto[8],
                        Integer.parseInt(texto[3]));
                post.add(p);
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            System.err.println("FileStreamsTest: " + e);
        } catch (IOException e) {
            System.err.println("FileStreamsTest: " + e);
        }
        return post;
    }
    public String agregaGuiones(String s) {
        s=s.replace(" ", "-");
        return s;
    }

    public String quitaGuiones(String s) {
        s=s.replace("-", " ");
        return s;
    }
    /**
     * busca todas las postulaciones a las que un evaluador ha sido asignado
     * @param u: VOUsuario del evaluador a quien buscar las postulaciones a evaluar
     * @return un Vector con todas las postulaciones a las que un evaluador ha sido asignado
     */
    public Vector postEvaluarPor(VOUsuario u)  throws RemoteException {
        Vector post=new Vector();
        String idu=u.getId();
        try {
            FileReader fr = new FileReader(datos);
            BufferedReader entrada = new BufferedReader(fr);
            String s;
            String texto[]=new String[11];
            while((s = entrada.readLine()) != null) {
                texto=s.split(" ");
                String ev[]=texto[4].split("-");
                String ed_red[]=texto[5].split("-");
                
                int j=0;
                boolean encontrado=false;
                if(u.getUsertype().compareTo("evaluador")==0)
                    while(j<ev.length && !encontrado) {
                        if(ev[j].compareTo(idu)==0)
                            encontrado=true;
                        j++;
                    }
                else if(u.getUsername().compareTo("editor-redaccion")==0)
                    while(j<ed_red.length && !encontrado) {
                        if(ed_red[j].compareTo(idu)==0)
                            encontrado=true;
                        j++;
                    }

                if(encontrado) {
                    String tcom[]=texto[6].split("-");
                    String cri[]=texto[7].split("-");

                    int Evaluadores[]= new int[texto[4].split("-").length];
                    int EditorRed[]= new int[texto[5].split("-").length];
                    int TComentarios[]=new int[texto[6].split("-").length];
                    int Criterios[]=new int[texto[7].split("-").length];

                    for(int i=0; i<Evaluadores.length; i++) {
                        Evaluadores[i]=Integer.parseInt(ev[i]);
                    }

                     for(int i=0; i<EditorRed.length; i++) {
                        EditorRed[i]=Integer.parseInt(ed_red[i]);
                    }

                     for(int i=0; i<TComentarios.length; i++) {
                        TComentarios[i]=Integer.parseInt(tcom[i]);
                    }

                     for(int i=0; i<Criterios.length; i++) {
                        Criterios[i]=Integer.parseInt(cri[i]);
                    }

                    VOPostulacion p = new VOPostulacion(
                            this.quitaGuiones(texto[9]),
                            this.quitaGuiones(texto[10]),
                            Integer.parseInt(texto[0]),
                            Integer.parseInt(texto[1]),
                            Integer.parseInt(texto[2]),
                            Evaluadores,
                            EditorRed,
                            TComentarios,
                            Criterios,
                            texto[8],
                            Integer.parseInt(texto[3]));
                    post.add(p);
                }
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            System.err.println("FileStreamsTest: " + e);
        } catch (IOException e) {
            System.err.println("FileStreamsTest: " + e);
        }
        return post;
    }
}
