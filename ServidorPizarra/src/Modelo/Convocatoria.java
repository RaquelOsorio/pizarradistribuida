package Modelo;

import VO.VOConvocatoria;
import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class Convocatoria extends UnicastRemoteObject implements MInterfaceConvocatoria{
    File datos, datost;
    int numvar;

    public Convocatoria() throws RemoteException{
        this.numvar = 5;
        try {
            datos = new File("convocatorias.txt");
            datost = new File("convocatorias.temp.txt");
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

    public VOConvocatoria getConvocatoria(VOConvocatoria con) throws RemoteException{
        
        int id=con.getID();
        
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
                
                int Areas[]=new int[texto[4].split("-").length];
                String areas[]=texto[4].split("-");
                            
                for(int i=0; i<Areas.length; i++) {
                    Areas[i]=Integer.parseInt(areas[i]);
                }
                
                if(num==id) {
                    encontrado=true;
                    VOConvocatoria m=new VOConvocatoria(num, texto[1], texto[2], texto[3],Areas);
                    return m;
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

    public Vector getConvocatorias() throws RemoteException{
        Vector conv=new Vector();
        try {
            FileReader fr = new FileReader(datos);
            BufferedReader entrada = new BufferedReader(fr);
            String s;
            String texto[]=new String[numvar];
            while((s = entrada.readLine()) != null) {
                texto=s.split(" ");
                                
                int Areas[]=new int[texto[4].split("-").length];
                String areas[]=texto[4].split("-");
                            
                for(int i=0; i<Areas.length; i++) {
                    Areas[i]=Integer.parseInt(areas[i]);
                }
                VOConvocatoria x=new VOConvocatoria(Integer.parseInt(texto[0]), texto[1], texto[2], texto[3],Areas);
                conv.add(x);
            }
            entrada.close();
        } catch (FileNotFoundException e) {
            System.err.println("FileStreamsTest: " + e);
        } catch (IOException e) {
            System.err.println("FileStreamsTest: " + e);
        }
        return conv;
    }
}
