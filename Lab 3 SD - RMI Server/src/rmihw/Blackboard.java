/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rmihw;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author alonso
 */
public class Blackboard implements Serializable{

	private int id;
    private String nombre;

	private User owner;			//Creador de la pizarra
	private ArrayList<User> users;		//Quienes dibujan en la pizarra
	//private ArrayList<Figure> figures;	//Figuras dibujadas por users y owner

	public Blackboard(int id) {
		this.id = id;
	}

	public Blackboard(User owner, String nombre) {
		this.owner = owner;
        this.nombre = nombre;
	}

	public Blackboard(int id, User owner, String nombre) {
		this.id = id;
		this.owner = owner;
        this.nombre = nombre;
	}

	public void addUser(User user){
		if(!this.users.contains(user)){
			this.users.add(user);

		}
	}

	public void removeUser(User user){
		this.users.remove(user);
	}


	//Getters & Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

        public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}
