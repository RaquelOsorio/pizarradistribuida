package VO;

import java.io.Serializable;

public class VOCriterio implements Serializable {
    int ID;
    String Nombre;
    String Descripcion;
    String [] Concepto;

    public VOCriterio() {
    }

    public VOCriterio(int ID, String Nombre, String Descripcion, String[] Concepto) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.Concepto = Concepto;
    }

    public String[] getConcepto() {
        return Concepto;
    }

    public void setConcepto(String[] Concepto) {
        this.Concepto = new String[Concepto.length];
        for(int i=0; i<this.Concepto.length; i++)
            this.Concepto[i]=Concepto[i];
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    
}
