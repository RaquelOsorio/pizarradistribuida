package VO;

import java.io.Serializable;

public class VOUsuario implements Serializable{

    private String id;
    private String username;
    private String usertype;
    private String pass;
    private String nombre;
    private String apellido;
    private String rut;
    private String email;
    private String direccion;
    private String telefono;
    private int areas[];

    public VOUsuario() {
    }
    
    public VOUsuario ( String idu, String NombreUsuario,String TipoUsuario,String Contrasena,String Nombre,String Apellido, String rut, String Email, String Telefono,String Domicilio, int areas[]){
        this.id=idu;
        this.username=NombreUsuario;
        this.usertype=TipoUsuario;
        this.pass=Contrasena;
        this.nombre=Nombre;
        this.apellido=Apellido;
        this.rut=rut;
        this.email=Email;
        this.telefono=Telefono;
        this.direccion=Domicilio;
        this.areas = new int[areas.length];
        for(int i=0; i<this.areas.length; i++)
            this.areas[i]=areas[i];
    }
    public String getApellido() {
        return apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPass() {
        return pass;
    }

    public String getRut() {
        return rut;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getUsername() {
        return username;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
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
