package Modelo;

import VO.VOTipoComentario;
import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class TipoComentario extends UnicastRemoteObject implements MInterfaceTipoComentario {
    File datos, datost;

    public TipoComentario() throws RemoteException {
        try {
            datos = new File("tipocomentarios.txt");
            datost = new File("tipocomentarios.temp.txt");
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

    //agrega criterios
    //retorno:
    //0: todo bien
    //1: ya existe
    //2: no se pudo guardar
    public int[] agregarTipoComentario(VOTipoComentario tc) throws RemoteException {
        int r[]={0,0};
        int esta=this.BuscarTipoComentario(tc);
        if(esta<0) {//tc no existe
            try {
                FileWriter fw = new FileWriter(datos, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter salida = new PrintWriter(bw);
                int idc=this.get_ultimo_idTipoComentario()+1;
                String tcom=idc+"";
                tcom+=" "+this.agregaGuiones(tc.getNombre());
                tcom+=" "+this.agregaGuiones(tc.getDescripcion());
                salida.println(tcom);
                salida.close();
                r[0]=0;
                r[1]=idc;
            } catch (FileNotFoundException e) {
                System.err.println("FileStreamsTest: " + e);
                r[0]=2;
            } catch (IOException e) {
                System.err.println("FileStreamsTest: " + e);
                r[0]=2;
            }

        } else //criterio ya existe
            r[0]=1;
        return r;
    }


    public Vector obtenerTiposComentario() throws RemoteException {
        Vector tcom=null;
        try {
            FileReader fr = new FileReader(datos);
            BufferedReader entrada = new BufferedReader(fr);
            String s, texto[];
            tcom=new Vector();
            while((s = entrada.readLine()) != null) {
                texto = s.split(" ");
                VOTipoComentario c = new VOTipoComentario(Integer.parseInt(texto[0]), this.quitaGuiones(texto[1]), this.quitaGuiones(texto[2]));
                tcom.add(c);
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            System.err.println("FileStreamsTest: " + e);
        } catch (IOException e) {
            System.err.println("FileStreamsTest: " + e);
        }
        return tcom;
    }


    public VOTipoComentario obtenerTipoComentario(int id) throws RemoteException {
        VOTipoComentario c=null;
        try {
            FileReader fr = new FileReader(datos);
            BufferedReader entrada = new BufferedReader(fr);
            String s, texto[];
            boolean encontrado=false;
            while((s = entrada.readLine()) != null && !encontrado) {
                texto = s.split(" ");
                if(id==Integer.parseInt(texto[0])) {
                    System.out.println("tc encontrado: "+s);
                    encontrado=true;
                    c = new VOTipoComentario(Integer.parseInt(texto[0]), this.quitaGuiones(texto[1]), this.quitaGuiones(texto[2]));
                }
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            System.err.println("FileStreamsTest: " + e);
        } catch (IOException e) {
            System.err.println("FileStreamsTest: " + e);
        }
        return c;
    }


    public int eliminarTipoComentario(VOTipoComentario tc) throws RemoteException {
        if(this.isEliminable(tc)) {
            try {
                int l=0;
                String s="";
                int linea=this.BuscarTipoComentario(tc);

                if(linea>=0) {
                    FileReader fr = new FileReader(datos);
                    BufferedReader entrada = new BufferedReader(fr);

                    FileWriter fw = new FileWriter(datost, false);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter salida = new PrintWriter(bw);

                    while((s = entrada.readLine())!=null) {
                        if(l!=linea) {
                            salida.println(s);
                        }
                        l++;
                    }
                    entrada.close();
                    salida.close();
                    actualizar_datosTipoComentario();
                    return 0;
                } else {
                    return 1;
                }

            } catch (FileNotFoundException e) {
                System.err.println("FileStreamsTest: " + e);
                return 2;
            } catch (IOException e) {
                System.err.println("FileStreamsTest: " + e);
                return 2;
            }
        } else
            return 3;
    }

    //recibe un VOCriterio y refleja los cambios hechos en el, en el sistema de archivos
    //retorno:
    //0: todo bien
    //1: no existe el criterio
    //2: no se pudo guardar el cambio
    public int modTipoComentario(VOTipoComentario tc) throws RemoteException {
        int r=0;
        try {
            int l=0;
            String s="";
            int linea=this.BuscarTipoComentario(tc);
            System.out.println("linea: "+linea);
            if(linea>=0) {
                FileReader fr = new FileReader(datos);
                BufferedReader entrada = new BufferedReader(fr);

                FileWriter fw = new FileWriter(datost, false);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter salida = new PrintWriter(bw);

                while((s = entrada.readLine())!=null) {
                    if(l!=linea) {
                        salida.println(s);
                    } else {
                        String cri=tc.getID()+"";
                        cri+=" "+this.agregaGuiones(tc.getNombre());
                        cri+=" "+this.agregaGuiones(tc.getDescripcion());
                        salida.println(cri);
                    }
                    l++;
                }
                entrada.close();
                salida.close();
                actualizar_datosTipoComentario();
                r=0;
            } else {
                r=1;
            }

        } catch (FileNotFoundException e) {
            System.err.println("FileStreamsTest: " + e);
            r=2;
        } catch (IOException e) {
            System.err.println("FileStreamsTest: " + e);
            r=2;
        }
        return r;
    }
    //busca un criterio en el sistema
    //retorno:
    //-1= criterio no existe
    //n: linea, en el archivo, en donde esta el criterio
    public int BuscarTipoComentario(VOTipoComentario c) throws RemoteException {
        int linea=-1;
        int id=c.getID();
        try {
            FileReader fr = new FileReader(datos);
            BufferedReader entrada = new BufferedReader(fr);
            String s, texto[];
            int n, l=0;
            boolean encontrado=false;
            while((s = entrada.readLine()) != null && !encontrado) {
                texto = s.split(" ");
                if(id==Integer.parseInt(texto[0])) {
                    encontrado=true;
                    linea=l;
                }
                l++;
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            System.err.println("FileStreamsTest: " + e);
        } catch (IOException e) {
            System.err.println("FileStreamsTest: " + e);
        }
        return linea;
    }

    //obtiene el id del ultimo criterio registrado
    public int get_ultimo_idTipoComentario() throws RemoteException {
        int idc=0;
        try {
            FileReader fr = new FileReader(datos);
            BufferedReader entrada = new BufferedReader(fr);
            String s, p="";
            String texto[];
            while((s = entrada.readLine()) != null) p=s;

            if(p!=null && p.compareTo("")!=0) {
                texto=p.split(" ");
                System.out.println("p: "+p);
                if(texto[0].compareTo("")!=0)
                    idc=Integer.parseInt(texto[0]);
                else
                    idc=0;
            }

            entrada.close();
        } catch (FileNotFoundException e) {
            System.err.println("FileStreamsTest: " + e);
        } catch (IOException e) {
            System.err.println("FileStreamsTest: " + e);
        }
        return idc;
    }

    public String agregaGuiones(String s) {
        s=s.replace(" ", "-");
        return s;
    }

    public String quitaGuiones(String s) {
        s=s.replace("-", " ");
        return s;
    }

    public void actualizar_datosTipoComentario() throws RemoteException {
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

    private boolean isEliminable(VOTipoComentario tc) {
        //si esta asignado a un articulo no se puede eliminar
        return true;
    }
}
