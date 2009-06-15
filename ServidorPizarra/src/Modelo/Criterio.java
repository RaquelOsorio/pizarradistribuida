package Modelo;

import VO.VOCriterio;
import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class Criterio extends UnicastRemoteObject implements MInterfaceCriterio {
    File datos, datost;

    public Criterio() throws RemoteException {
        try {
            datos = new File("criterios.txt");
            datost = new File("criterios.temp.txt");
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
    public int[] agregarCriterio(VOCriterio c) throws RemoteException {
        int r[]={0, 0};
        int esta=this.BuscarCriterio(c);
        if(esta<0) {//criterio no existe
            try {
                FileWriter fw = new FileWriter(datos, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter salida = new PrintWriter(bw);
                int idc=this.get_ultimo_idCriterio()+1;
                String con="";
                if(c.getConcepto().length>0) {
                    con=this.agregaGuiones(c.getConcepto()[0]);
                    for(int i=1; i<c.getConcepto().length; i++) {
                        con+="/"+this.agregaGuiones(c.getConcepto()[i]);
                    }
                }
                String cri=idc+"";
                cri+=" "+this.agregaGuiones(c.getNombre());
                cri+=" "+con;
                cri+=" "+this.agregaGuiones(c.getDescripcion());
                salida.println(cri);
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


    public Vector obtenerCriterios() throws RemoteException {
        Vector cri=null;
        try {
            FileReader fr = new FileReader(datos);
            BufferedReader entrada = new BufferedReader(fr);
            String s, texto[];
            cri=new Vector();
            while((s = entrada.readLine()) != null) {
                texto = s.split(" ");
                String con[]=texto[2].split("/");
                for(int i=0; i<con.length; i++) {
                    con[i]=this.quitaGuiones(con[i]);
                }
                VOCriterio c = new VOCriterio(Integer.parseInt(texto[0]), this.quitaGuiones(texto[1]), this.quitaGuiones(texto[3]), con);
                cri.add(c);
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            System.err.println("FileStreamsTest: " + e);
        } catch (IOException e) {
            System.err.println("FileStreamsTest: " + e);
        }
        return cri;
    }


    public VOCriterio obtenerCriterio(int id) throws RemoteException {
        VOCriterio c=null;
        try {
            FileReader fr = new FileReader(datos);
            BufferedReader entrada = new BufferedReader(fr);
            String s, texto[];
            boolean encontrado=false;
            while((s = entrada.readLine()) != null && !encontrado) {
                texto = s.split(" ");
                if(id==Integer.parseInt(texto[0])) {
                    encontrado=true;
                    String con[]=texto[2].split("/");
                    for(int i=0; i<con.length; i++) {
                        con[i]=this.quitaGuiones(con[i]);
                    }
                    c = new VOCriterio(Integer.parseInt(texto[0]), this.quitaGuiones(texto[1]), this.quitaGuiones(texto[3]), con);
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


    public int eliminarCriterio(VOCriterio c) throws RemoteException {
        if(this.isEliminable(c)) {
            try {
                int l=0;
                String s="";
                int linea=this.BuscarCriterio(c);

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
                    actualizar_datosCriterio();
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

    public boolean isEliminable(VOCriterio c) {
        //si esta asignado a un articulo no se puede eliminar
        return true;
    }
    //recibe un VOCriterio y refleja los cambios hechos en el, en el sistema de archivos
    //retorno:
    //0: todo bien
    //1: no existe el criterio
    //2: no se pudo guardar el cambio
    public int modCriterio(VOCriterio c) throws RemoteException {
        int r=0;
        try {
            int l=0;
            String s="";
            int linea=this.BuscarCriterio(c);

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
                        String con="";
                        if(c.getConcepto().length>0) {
                            con=this.agregaGuiones(c.getConcepto()[0]);
                            for(int i=1; i<c.getConcepto().length; i++) {
                                con+="/"+this.agregaGuiones(c.getConcepto()[i]);
                            }
                        }
                        String cri=c.getID()+"";
                        cri+=" "+this.agregaGuiones(c.getNombre());
                        cri+=" "+con;
                        cri+=" "+this.agregaGuiones(c.getDescripcion());
                        salida.println(cri);
                    }
                    l++;
                }
                entrada.close();
                salida.close();
                actualizar_datosCriterio();
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
    public int BuscarCriterio(VOCriterio c) throws RemoteException {
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
    public int get_ultimo_idCriterio() throws RemoteException {
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

    public void actualizar_datosCriterio() throws RemoteException {
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
}