package VO;

import java.io.Serializable;

public class VOSolicitud  implements Serializable{
    private int id;
    private String NombreUsuario;
    private String TipoUsuario;
    private String Contraseña;
    private String Nombre;
    private String Apellido;
    private String rut;
    private String Email;
    private String Telefono;
    private String Domicilio;
    private int areas[];

    public VOSolicitud() {
    }

    public VOSolicitud(int id, String NombreUsuario, String TipoUsuario, String Contraseña, String Nombre, String Apellido, String rut, String Email, String Telefono, String Domicilio, int areas[]) {
        this.id = id;
        this.NombreUsuario = NombreUsuario;
        this.TipoUsuario = TipoUsuario;
        this.Contraseña = Contraseña;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.rut = rut;
        this.Email = Email;
        this.Telefono = Telefono;
        this.Domicilio = Domicilio;
        this.areas = new int[areas.length];
        for(int i=0; i<this.areas.length; i++)
            this.areas[i]=areas[i];
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    public String getDomicilio() {
        return Domicilio;
    }

    public void setDomicilio(String Domicilio) {
        this.Domicilio = Domicilio;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getTipoUsuario() {
        return TipoUsuario;
    }

    public void setTipoUsuario(String TipoUsuario) {
        this.TipoUsuario = TipoUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public int[] getAreas() {
        return areas;
    }

    public void setAreas(int[] areas) {
        this.areas = new int[areas.length];
        for(int i=0; i<this.areas.length; i++)
            this.areas[i] = areas[i];
    }
}
