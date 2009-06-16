/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rmihw;

import java.io.Serializable;
import java.util.Hashtable;

/**
 *
 * @author alonso
 */
public class User implements Serializable  {
	private String nombres;
    private String apellidos;
    private String username;
	private String password;
    private String descripcion;

    private Hashtable ownBlackboards = new Hashtable();
    
    public User(String username) {
        this.username = username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public User(String nombres, String apellidos, String username, String password, String descripcion) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.username = username;
        this.password = password;
        this.descripcion = descripcion;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDescricion() {
        return descripcion;
    }

    public void setDescricion(String descricion) {
        this.descripcion = descricion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Hashtable getOwnBlackboards() {
        return ownBlackboards;
    }

    public void setOwnBlackboards(Hashtable ownBlackboards) {
        this.ownBlackboards = ownBlackboards;
    }



}
