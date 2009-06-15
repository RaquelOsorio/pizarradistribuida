package Modelo;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import VO.VOArticulo;
import VO.VOUsuario;

public class Articulo extends UnicastRemoteObject implements MInterfaceArticulo{

    boolean subido, subiendo;
    File datos, datost;
    int numvar;

    public Articulo() throws RemoteException {
        this.subido=false;
        this.subiendo=false;
        this.numvar = 10;
        try {
            datos = new File("articulos.txt");
            datost = new File("articulos.temp.txt");
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

    public VOArticulo ObtenerArticulo(VOArticulo art) throws RemoteException {
        int idart=art.getId();
        VOArticulo n;
        try {
            FileReader fr = new FileReader(datos);
            BufferedReader entrada = new BufferedReader(fr);
            String s;
            String texto[]=new String[numvar];
            int num;
            boolean encontrado=false;
            while((s = entrada.readLine()) != null && !encontrado) {
                texto=s.split(" ");
                num=Integer.parseInt(texto[0]);
                if(num==idart) {
                    encontrado=true;
                    n=new VOArticulo(
                            num,
                            Integer.parseInt(texto[1]),
                            Integer.parseInt(texto[2]),
                            this.quitaGuiones(texto[3]),
                            texto[4],
                            this.quitaGuiones(texto[5]),
                            texto[6],
                            texto[7],
                            texto[8],
                            this.quitaGuiones(texto[9]));
                    return n;
                }
            }
            entrada.close();
            return null;
        } catch (FileNotFoundException e) {
            System.err.println("FileStreamsTest: " + e);
            return null;
        } catch (IOException e) {
            System.err.println("FileStreamsTest: " + e);
            return null;
        }
    }

    public Vector ObtenerArticulos(VOUsuario us) throws RemoteException {

        String idau=us.getId();
        Vector sol=new Vector();

        try {
            FileReader fr = new FileReader(datos);
            BufferedReader entrada = new BufferedReader(fr);
            String s;
            String texto[]=new String[numvar];
            while((s = entrada.readLine()) != null) {
                texto=s.split(" ");
                if(texto[1].compareTo(idau)==0) {
                    VOArticulo x=new VOArticulo(
                            Integer.parseInt(texto[0]),
                            Integer.parseInt(texto[1]),
                            Integer.parseInt(texto[2]),
                            this.quitaGuiones(texto[3]),
                            texto[4],
                            this.quitaGuiones(texto[5]),
                            texto[6],
                            texto[7],
                            texto[8],
                            this.quitaGuiones(texto[9]));
                    sol.add(x);
                }
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            System.err.println("FileStreamsTest: " + e);
        } catch (IOException e) {
            System.err.println("FileStreamsTest: " + e);
        }
        return sol;
    }

    //agregar verificaciones
    public VOArticulo GuardarArticulo(VOArticulo art) throws RemoteException {
        int r[]={0,0};
        try {
            
            FileWriter fw = new FileWriter(datos, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter salida = new PrintWriter(bw);
            int ida=get_ultimo_idArticulo()+1;
            art.setId(ida);
            String NomArch = art.getNomArch();
            String ext=NomArch.substring(NomArch.lastIndexOf(".")+1, NomArch.length());
            art.setUbicacion("articulos/"+art.getIDAutor()+"/"+art.getId()+"."+ext);
            art.setEtapaSeguimiento("sin-evaluadores");
            art.setSancion("sin-sancion");
            String a=art.getId()+" "+
                    art.getIDAutor()+" "+
                    art.getIDConvocatoria()+" "+
                    this.agregaGuiones(art.getNombre())+" "+
                    art.getFecha()+" "+
                    this.agregaGuiones(art.getNomArch())+" "+
                    art.getUbicacion()+" "+
                    art.getEtapaSeguimiento()+" "+
                    art.getSancion()+" "+
                    this.agregaGuiones(art.getDescripcion());
            salida.println(a);
            r[0]=0;
            r[1]=ida;
            salida.close();
        } catch (FileNotFoundException e) {
            System.err.println("FileStreamsTest: " + e);
            r[0]=1;
        } catch (IOException e) {
            System.err.println("FileStreamsTest: " + e);
            r[0]=1;
        }
        return art;
    }

    public void Subir(byte art[], VOArticulo a) throws RemoteException{
        BufferedOutputStream output = null;
        try {
            File carpeta = new File(a.getUbicacion().split("/")[0]+"/"+a.getUbicacion().split("/")[1]);
            carpeta.mkdirs();
            FileOutputStream nart = new FileOutputStream(a.getUbicacion());
            output = new BufferedOutputStream(nart);
            output.write(art, 0, art.length);
            output.flush();
            output.close();
        } catch (Exception ex) {
            System.out.println("error al escribir");
        }
    }

    public byte[] Bajar(VOArticulo a) throws RemoteException{
        String nombre = a.getUbicacion();
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
            buffer = null;
        }
        return(buffer);
    }

    public int get_ultimo_idArticulo() throws RemoteException {
        int idu=0;
        try {
            FileReader fr = new FileReader(datos);
            BufferedReader entrada = new BufferedReader(fr);
            String s, p="";
            String texto[]=new String[numvar];
            while((s = entrada.readLine()) != null) p=s;

            if(p!=null && p.compareTo("")!=0) {
                texto=p.split(" ");
                idu=Integer.parseInt(texto[0]);
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            System.err.println("FileStreamsTest: " + e);
        } catch (IOException e) {
            System.err.println("FileStreamsTest: " + e);
        }
        return idu;
    }

    public void actualizarDatosArticulo() throws RemoteException {
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

    public int EliminarArticulo(VOArticulo a) {
        try {
            int l=0;
            String s="";
            int linea=0;//deberia llamar al buscarArticulo;

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
                this.actualizarDatosArticulo();
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
    }

    public int ModificarArticulo(VOArticulo a) {
        int r=0;
        return r;
    }

    public boolean isSubiendo() {
        return subiendo;
    }

    public void setSubiendo(boolean subiendo) {
        this.subiendo = subiendo;
    }

    public boolean isSubido() {
        return subido;
    }

    public void setSubido(boolean subir) {
        this.subido = subir;
    }

    public String agregaGuiones(String s) {
        s=s.replace(" ", "-");
        return s;
    }

    public String quitaGuiones(String s) {
        s=s.replace("-", " ");
        return s;
    }
}