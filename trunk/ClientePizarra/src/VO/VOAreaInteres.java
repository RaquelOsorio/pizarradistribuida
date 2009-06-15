package VO;

public class VOAreaInteres {
    private int id;
    private String Nombre;

    public VOAreaInteres() {
    }

    public VOAreaInteres(int id, String Nombre) {
        this.id = id;
        this.Nombre = Nombre;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
}
