package Modelo;

import VO.VOSolicitud;
import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class Solicitud extends UnicastRemoteObject implements MInterfaceSolicitud{
    File datos, datost;

    public Solicitud() throws RemoteException{
        try {
            datos = new File("solicitudes.txt");
            datost = new File("solicitudes.temp.txt");
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

    public int get_ultimo_idSolicitud() throws RemoteException{
        int idu=0;
        try {
            FileReader fr = new FileReader(datos);
            BufferedReader entrada = new BufferedReader(fr);
            String s, p="";
            String texto[]=new String[10];
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

    //guarda los datos del objeto en un archivo de texto
    //retorno:
    //0: guardo correctamente
    //1: nombre de usuario ya existe
    //2: problema para grabar
    public int GuardarSolicitud(VOSolicitud sol) throws RemoteException{
        System.out.println("Servidor - Solicitud: guardar solicitud");
        int r=0;
        try {
            String val[] = {sol.getNombreUsuario()};
            String tipo[] = {"nomuser"};
            int eSol=this.buscarSolicitud(val, tipo);
            Usuario us=new Usuario();
            int eUs=us.buscarUsuario(val, tipo);
            if(eSol<0 && eUs<0) {
                FileWriter fw = new FileWriter(datos, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter salida = new PrintWriter(bw);
                int idu=get_ultimo_idSolicitud()+1;
                sol.setId(idu);
                String area="";
                if(sol.getAreas().length>0) {
                    area=sol.getAreas()[0]+"";
                    for(int i=1; i<sol.getAreas().length; i++)
                        area+="-"+sol.getAreas()[i];
                } else {
                    area="-";
                }
                salida.println(idu+" "+
                        sol.getNombreUsuario()+" "+
                        sol.getTipoUsuario()+" "+
                        sol.getContraseña()+" "+
                        this.agregaGuiones(sol.getNombre())+" "+
                        this.agregaGuiones(sol.getApellido())+" "+
                        this.agregaGuiones(sol.getRut())+" "+
                        this.agregaGuiones(sol.getEmail())+" "+
                        this.agregaGuiones(sol.getTelefono())+" "+
                        this.agregaGuiones(sol.getDomicilio())+" "+
                        area);
                salida.close();
            } else r=1;
        } catch (FileNotFoundException e) {
            System.err.println("FileStreamsTest: " + e);
            r=2;
        } catch (IOException e) {
            System.err.println("FileStreamsTest: " + e);
            r=2;
        }
        return r;
    }

    public VOSolicitud ObtenerSolicitud(VOSolicitud sol) throws RemoteException{
        int ids=sol.getId();
        try {
            FileReader fr = new FileReader(datos);
            BufferedReader entrada = new BufferedReader(fr);
            String s;
            String texto[]=new String[11];
            int num;
            boolean encontrado=false;
            while((s = entrada.readLine()) != null && !encontrado) {

                texto=s.split(" ");

                num=Integer.parseInt(texto[0]);

                if(num==ids) {
                    encontrado=true;
                    int area[] = new int[texto[10].split("-").length];
                    String ar[]=texto[10].split("-");
                    for(int i=0; i<area.length; i++) {
                        area[i]=Integer.parseInt(ar[i]);
                    }
                    VOSolicitud so=new VOSolicitud(num, texto[1], texto[2], texto[3], texto[4], texto[5], texto[6], texto[7], texto[8], texto[9], area);
                    return so;
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

    public void actualizar_datosSolicitud() throws RemoteException{
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

    public Vector ObtenerSolicitudes() throws RemoteException{
        Vector sol=new Vector();
        try {
            FileReader fr = new FileReader(datos);
            BufferedReader entrada = new BufferedReader(fr);
            String s;
            String texto[]=new String[11];
            while((s = entrada.readLine()) != null) {
                texto=s.split(" ");
                int area[] = new int[texto[10].split("-").length];
                String ar[]=texto[10].split("-");
                for(int i=0; i<area.length; i++) {
                    area[i]=Integer.parseInt(ar[i]);
                }
                VOSolicitud x=new VOSolicitud(
                        Integer.parseInt(texto[0]),
                        this.quitaGuiones(texto[1]),
                        this.quitaGuiones(texto[2]),
                        this.quitaGuiones(texto[3]),
                        this.quitaGuiones(texto[4]),
                        this.quitaGuiones(texto[5]),
                        this.quitaGuiones(texto[6]),
                        this.quitaGuiones(texto[7]),
                        this.quitaGuiones(texto[8]),
                        this.quitaGuiones(texto[9]),
                        area);
                sol.add(x);
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            System.err.println("FileStreamsTest: " + e);
        } catch (IOException e) {
            System.err.println("FileStreamsTest: " + e);
        }
        return sol;
    }

    public int buscarSolicitud(String val[], String tipo[]) throws RemoteException{
        int linea=-1;
        if(tipo.length==val.length)
        try {
            FileReader fr = new FileReader(datos);
            BufferedReader entrada = new BufferedReader(fr);
            String s;
            String texto[]=new String[11];
            int n, l=0;
            int campo[]=new int[tipo.length];
            boolean encontrado=false;
            for(int i=0; i<tipo.length; i++) {
                if(tipo[i].compareTo("id")==0) campo[i]=0;
                if(tipo[i].compareTo("nombre")==0) campo[i]=4;
                if(tipo[i].compareTo("apellido")==0) campo[i]=5;
                if(tipo[i].compareTo("nomuser")==0) campo[i]=1;
                if(tipo[i].compareTo("email")==0) campo[i]=7;
                if(tipo[i].compareTo("rut")==0) campo[i]=6;
                if(tipo[i].compareTo("pass")==0) campo[i]=3;
            }
            while((s = entrada.readLine()) != null && !encontrado) {
                texto=s.split(" ");
                int j=0;
                boolean seguir=true;
                while(j<campo.length && seguir) {
                    if(texto[campo[j]].toLowerCase().compareTo(val[j].toLowerCase())!=0) {
                        seguir=false;
                    } else {
                        j++;
                    }
                }
                if(seguir) {
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

    public int EliminarSolicitud(VOSolicitud sol) throws RemoteException{
        try {
            String val[]=new String[1];
            String tipo[]=new String[1];
            String s;
            val[0]=sol.getId()+"";
            tipo[0]="id";
            int l=0;
            int linea=buscarSolicitud(val, tipo);

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
                actualizar_datosSolicitud();
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
    public String agregaGuiones(String s) {
        s=s.replace(" ", "-");
        return s;
    }

    public String quitaGuiones(String s) {
        s=s.replace("-", " ");
        return s;
    }
}