package Modelo;

import VO.VOAreaInteres;
import VO.VOUsuario;
import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class Usuario extends UnicastRemoteObject implements MInterfaceUsuario {
    File datos, datost;

    public Usuario() throws RemoteException {
        try {
            datos = new File("usuarios.txt");
            datost = new File("usuarios.temp.txt");
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

    //guarda los datos del objeto en un archivo de texto
    //retorno:
    //0: guardo correctamente
    //1: nombre de usuario ya existe
    //2: problema para grabar
    public int[] GuardarUsuario(VOUsuario u)  throws RemoteException {
        int r[]={0,0};
        try {
            FileWriter fw = new FileWriter(datos, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter salida = new PrintWriter(bw);
            int e;
            String val[]=new String[]{u.getUsername()};
            String tipo[]=new String[]{"nomuser"};
            e=this.buscarUsuario(val, tipo);
            if(e<0) {
                int idu=get_ultimo_idUsuario()+1;
                u.setId(idu+"");
                String area="";
                if(u.getAreas().length>0) {
                    area=u.getAreas()[0]+"";
                    for(int i=1; i<u.getAreas().length; i++)
                        area+="-"+u.getAreas()[i];
                } else {
                    area="-";
                }
                String m=
                        idu+" "+
                        this.agregaGuiones(u.getUsername())+" "+
                        this.agregaGuiones(u.getUsertype())+" "+
                        u.getPass()+" "+
                        this.agregaGuiones(u.getNombre())+" "+
                        this.agregaGuiones(u.getApellido())+" "+
                        this.agregaGuiones(u.getRut())+" "+
                        this.agregaGuiones(u.getEmail())+" "+
                        this.agregaGuiones(u.getTelefono())+" "+
                        this.agregaGuiones(u.getDireccion())+" "+
                        area;
                salida.println(m);
                
                r[0]=0;
                r[1]=idu;
            } else
                r[0]=1;
            salida.close();
        } catch (FileNotFoundException e) {
            System.err.println("FileStreamsTest: " + e);
            r[0]=2;
        } catch (IOException e) {
            System.err.println("FileStreamsTest: " + e);
            r[0]=2;
        }
        return r;
    }
    //obtiene el id del ultimo usuario registrado
    public int get_ultimo_idUsuario()  throws RemoteException  {
        int idu=0;
        try {
            FileReader fr = new FileReader(datos);
            BufferedReader entrada = new BufferedReader(fr);
            String s, p="";
            String texto[]=new String[11];
            while((s = entrada.readLine()) != null) p=s;

            if(p!=null && p.compareTo("")!=0) {
                texto=p.split(" ");
                if(texto[0].compareTo("")!=0)
                    idu=Integer.parseInt(texto[0]);
                else
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
    //busca un usuario segun su id y retorna la linea donde esta
    public int buscar_usuario_id(VOUsuario usu)  throws RemoteException  {
        int linea=-1;
        int id=Integer.parseInt(usu.getId());
        try {
            FileReader fr = new FileReader(datos);
            BufferedReader entrada = new BufferedReader(fr);
            String s, texto="";
            int n, num, l=0;
            boolean encontrado=false;
            while((s = entrada.readLine()) != null && !encontrado) {

                texto=s;
                n=texto.indexOf(" ");
                texto=texto.substring(0, n);
                num=Integer.parseInt(texto);

                if(num==id) {
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
    
    public Vector ObtenerUsuarios() throws RemoteException{

        Vector uss=new Vector();

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
                VOUsuario x=new VOUsuario(
                        texto[0],
                        texto[1],
                        this.quitaGuiones(texto[2]),
                        texto[3],
                        this.quitaGuiones(texto[4]),
                        this.quitaGuiones(texto[5]),
                        this.quitaGuiones(texto[6]),
                        this.quitaGuiones(texto[7]),
                        this.quitaGuiones(texto[8]),
                        this.quitaGuiones(texto[9]),
                        area);
                uss.add(x);
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            System.err.println("FileStreamsTest: " + e);
        } catch (IOException e) {
            System.err.println("FileStreamsTest: " + e);
        }
        return uss;
    }

    public Vector ObtenerUsuariosEvaluadores(VOAreaInteres areaInt) throws RemoteException {

        Vector uss = new Vector();
        int id = areaInt.getId();
        boolean estaArea = false;

        try {
            FileReader fr = new FileReader(datos);
            BufferedReader entrada = new BufferedReader(fr);
            String s;
            String texto[] = new String[11];
            while ((s = entrada.readLine()) != null) {
                texto = s.split(" ");
                int area[] = new int[texto[10].split("-").length];
                String ar[] = texto[10].split("-");
                for (int i = 0; i < area.length; i++) {
                    area[i] = Integer.parseInt(ar[i]);
                    if (area[i] == id) {
                        estaArea = true;
                    }
                }
                if (estaArea) {

                    VOUsuario x = new VOUsuario(
                            texto[0],
                            texto[1],
                            this.quitaGuiones(texto[2]),
                            texto[3],
                            this.quitaGuiones(texto[4]),
                            this.quitaGuiones(texto[5]),
                            this.quitaGuiones(texto[6]),
                            this.quitaGuiones(texto[7]),
                            this.quitaGuiones(texto[8]),
                            this.quitaGuiones(texto[9]),
                            area);
                    uss.add(x);
                    estaArea = false;
                }

            }
            entrada.close();
        } catch (FileNotFoundException e) {
            System.err.println("FileStreamsTest: " + e);
        } catch (IOException e) {
            System.err.println("FileStreamsTest: " + e);
        }
        return uss;
    }
    
    //String val[]: son los valores a buscarUsuario
    //String tipo: son los campos en los que buscarUsuario los valores de val[]
    //retorna el numero de linea donde esta ese usuario, si no esta -1
    public int buscarUsuario(String val[], String tipo[])  throws RemoteException  {
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
    //String val[]: son los valores a buscarUsuario
    //String tipo: son los campos en los que buscarUsuario los valores de val[]
    //recupera todos los datos registrados del usuario y los setea en el objeto
    public VOUsuario obtenerdatosUsuario(String[] val, String[] tipo) throws RemoteException  {
        VOUsuario usu = null;
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
                        j++;
                    } else {
                        j++;
                    }
                }
                if(seguir) {
                    encontrado=true;
                }
                l++;
            }
            if(encontrado) {
                int area[] = new int[texto[10].split("-").length];
                String ar[]=texto[10].split("-");
                for(int i=0; i<area.length; i++) {
                    area[i]=Integer.parseInt(ar[i]);
                }
                usu=new VOUsuario(
                        texto[0],
                        texto[1],
                        this.quitaGuiones(texto[2]),
                        texto[3],
                        this.quitaGuiones(texto[4]),
                        this.quitaGuiones(texto[5]),
                        this.quitaGuiones(texto[6]),
                        this.quitaGuiones(texto[7]),
                        this.quitaGuiones(texto[8]),
                        this.quitaGuiones(texto[9]),
                        area);
                entrada.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("FileStreamsTest: " + e);
        } catch (IOException e) {
            System.err.println("FileStreamsTest: " + e);
        }
        return usu;
    }

    public void actualizar_datosUsuario() throws RemoteException  {
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
    //elimina un usuario del sistema
    //retorno:
    //0: todo bien
    //1: no existe
    //2: no pudo borrar
    //3: no se puede borrar ese usuario
    public int eliminarUsuario(VOUsuario u) throws RemoteException  {
        if(this.isEliminable(u)) {
            String r=u.getId();
            try {
                String val[]=new String[1];
                String tipo[]=new String[1];
                String s;
                val[0]=r;
                tipo[0]="id";
                int l=0;
                int linea=buscarUsuario(val, tipo);

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
                    actualizar_datosUsuario();
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
        } else {
            return 3;
        }
    }

    public boolean isEliminable(VOUsuario us) {
        //revisar si tiene articulos
        //si es evaluador o editor de redaccion, revisar si esta asignado a una avaluacion
        return true;
    }
    public int ModificarUsuario(VOUsuario usu)  throws RemoteException {
        try {
            int linea=buscar_usuario_id(usu);
            int l=0;
            String s;
            String area="";
            int ar[]=usu.getAreas();
            if(ar.length>0) {
                area=ar[0]+"";
                for(int i=1; i<ar.length; i++) {
                    area+="-"+ar[i];
                }
            } else {
                area="-";
            }
            String ns= usu.getId()+" "+
                        usu.getUsername()+" "+
                        this.agregaGuiones(usu.getUsertype())+" "+
                        usu.getPass()+" "+
                        this.agregaGuiones(usu.getNombre())+" "+
                        this.agregaGuiones(usu.getApellido())+" "+
                        this.agregaGuiones(usu.getRut())+" "+
                        this.agregaGuiones(usu.getEmail())+" "+
                        this.agregaGuiones(usu.getTelefono())+" "+
                        this.agregaGuiones(usu.getDireccion())+" "+
                        area;
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
                        salida.println(ns);
                    }
                    l++;
                }
                entrada.close();
                salida.close();
                actualizar_datosUsuario();
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

    public VOUsuario ObtenerUsuario(String nu, String p) throws RemoteException  {
        
        try {
            FileReader fr = new FileReader(datos);
            BufferedReader entrada = new BufferedReader(fr);
            String s;
            String texto[]=new String[11];
            String val[]=new String[2];
            val[0]=nu;
            val[1]=p;
            int l=0;
            int campo[]=new int[2];
            boolean encontrado=false;
            campo[0]=1;
            campo[1]=3;

            while((s = entrada.readLine()) != null && !encontrado) {
                texto=s.split(" ");
                int j=0;
                boolean seguir=true;
                while(j<campo.length && seguir) {
                    if(texto[campo[j]].toLowerCase().compareTo(val[j].toLowerCase())!=0) {
                        seguir=false;
                        j++;
                    } else {
                        j++;
                    }
                }
                if(seguir) {
                    encontrado=true;
                }
                l++;
            }
            if(encontrado) {
                int area[] = new int[texto[10].split("-").length];
                String ar[]=texto[10].split("-");
                for(int i=0; i<area.length; i++) {
                    area[i]=Integer.parseInt(ar[i]);
                }
                VOUsuario n=new VOUsuario(
                        texto[0],
                        texto[1],
                        this.quitaGuiones(texto[2]),
                        texto[3],
                        this.quitaGuiones(texto[4]),
                        this.quitaGuiones(texto[5]),
                        this.quitaGuiones(texto[6]),
                        this.quitaGuiones(texto[7]),
                        this.quitaGuiones(texto[8]),
                        this.quitaGuiones(texto[9]),
                        area);
                entrada.close();
                return n;
            } else {
                entrada.close();
                return null;
            }

        } catch (FileNotFoundException e) {
            System.err.println("FileStreamsTest: " + e);
            return null;
        } catch (IOException e) {
            System.err.println("FileStreamsTest: " + e);
            return null;
        }
    }
    public VOUsuario ObtenerUsuario(String nu) throws RemoteException  {
        try {
            FileReader fr = new FileReader(datos);
            BufferedReader entrada = new BufferedReader(fr);
            String s;
            String texto[]=new String[11];
            String val[]=new String[1];
            val[0]=nu;
            int l=0;
            int campo[]=new int[1];
            boolean encontrado=false;
            campo[0]=1;

            while((s = entrada.readLine()) != null && !encontrado) {
                texto=s.split(" ");
                int j=0;
                boolean seguir=true;
                while(j<campo.length && seguir) {
                    if(texto[campo[j]].toLowerCase().compareTo(val[j].toLowerCase())!=0) {
                        seguir=false;
                        j++;
                    } else {
                        j++;
                    }
                }
                if(seguir) {
                    encontrado=true;
                }
                l++;
            }
            if(encontrado) {
                int area[] = new int[texto[10].split("-").length];
                String ar[]=texto[10].split("-");
                for(int i=0; i<area.length; i++) {
                    area[i]=Integer.parseInt(ar[i]);
                }
                VOUsuario n=new VOUsuario(
                        texto[0],
                        texto[1],
                        this.quitaGuiones(texto[2]),
                        texto[3],
                        this.quitaGuiones(texto[4]),
                        this.quitaGuiones(texto[5]),
                        this.quitaGuiones(texto[6]),
                        this.quitaGuiones(texto[7]),
                        this.quitaGuiones(texto[8]),
                        this.quitaGuiones(texto[9]),
                        area);
                entrada.close();
                return n;
            } else {
                entrada.close();
                return null;
            }

        } catch (FileNotFoundException e) {
            System.err.println("FileStreamsTest: " + e);
            return null;
        } catch (IOException e) {
            System.err.println("FileStreamsTest: " + e);
            return null;
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