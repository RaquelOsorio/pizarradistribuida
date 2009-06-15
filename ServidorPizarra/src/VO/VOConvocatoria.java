package VO;

import java.io.Serializable;

public class VOConvocatoria implements Serializable {
    int ID;
    String Titulo;
    String Fecha;
    String Descripcion;
    private int areas[];

    public VOConvocatoria() {
    }

    public VOConvocatoria(int ID, String Titulo, String Fecha, String Descripcion, int Areas[]) {
        this.ID = ID;
        this.Titulo = Titulo;
        this.Fecha = Fecha;
        this.Descripcion = Descripcion;
                
        this.areas = new int[Areas.length];
        for(int i=0; i<this.areas.length; i++)
            this.areas[i]=Areas[i];
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public int[] getAreas() {
        return areas;
    }

    public void setAreas(int[] areas) {
        this.areas = areas;
    }


}