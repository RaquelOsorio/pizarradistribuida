package VO;

import java.io.Serializable;

public class VOTipoComentario implements Serializable {
    int ID;
    String Nombre;
    String Descripcion;

    public VOTipoComentario() {
    }

    public VOTipoComentario(int ID, String Nombre, String Descripcion) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
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
