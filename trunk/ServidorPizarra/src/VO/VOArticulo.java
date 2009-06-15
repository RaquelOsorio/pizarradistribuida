package VO;

import java.io.Serializable;

public class VOArticulo implements Serializable{
    int id;
    int IDAutor;
    int IDConvocatoria;
    String Nombre;
    String Fecha;
    String NomArch;
    String Ubicacion;
    String EtapaSeguimiento;
    String Sancion;
    String Descripcion;

    public VOArticulo() {
    }

    public VOArticulo(int id, int IDAutor, int IDConvocatoria, String Nombre, String Fecha, String NomArch, String Ubicacion, String EtapaSeguimiento, String Sancion, String Descripcion) {
        this.id = id;
        this.IDAutor = IDAutor;
        this.IDConvocatoria = IDConvocatoria;
        this.Nombre = Nombre;
        this.Fecha = Fecha;
        this.NomArch = NomArch;
        this.Ubicacion = Ubicacion;
        this.EtapaSeguimiento = EtapaSeguimiento;
        this.Sancion = Sancion;
        this.Descripcion = Descripcion;
    }

    

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getEtapaSeguimiento() {
        return EtapaSeguimiento;
    }

    public void setEtapaSeguimiento(String EtapaSeguimiento) {
        this.EtapaSeguimiento = EtapaSeguimiento;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public int getIDAutor() {
        return IDAutor;
    }

    public void setIDAutor(int IDAutor) {
        this.IDAutor = IDAutor;
    }

    public int getIDConvocatoria() {
        return IDConvocatoria;
    }

    public void setIDConvocatoria(int IDConvocatoria) {
        this.IDConvocatoria = IDConvocatoria;
    }

    public String getNomArch() {
        return NomArch;
    }

    public void setNomArch(String NomArch) {
        this.NomArch = NomArch;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getSancion() {
        return Sancion;
    }

    public void setSancion(String Sancion) {
        this.Sancion = Sancion;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String Ubicacion) {
        this.Ubicacion = Ubicacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
