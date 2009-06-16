/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rmihw;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;


/**
 *
 * @author alonso
 */
public class ServerHandler {
    private ArrayList escuchadores = new ArrayList();
	private Hashtable usuarios = new Hashtable();

	public ServerHandler() {

	}

	//Actualiza la lista de usuarios, llamada remota.
	public void update(Hashtable usuarios){
		this.usuarios = usuarios;
	}

    //Crea el HashTable de Usuarios con respuesta del server
    public void usuariosFromArray(User[] usuarios){
        System.out.println("Entradas del array al Hash");
        this.usuarios.clear();
        for(int i=0; i<usuarios.length; i++){
            this.usuarios.put(usuarios[i].getUsername(),usuarios[i]);
        }
        this.updateObservers();
    }

	//Para MVC
	public void registrar(Observer o){
		escuchadores.add(o);
		o.update();
	}

	public void elimRegistro(Observer o){
		escuchadores.remove(o);
	}

	public void updateObservers(){
		Iterator i = escuchadores.iterator();
		while (i.hasNext()){
			Observer o = (Observer) i.next();
			o.update();
		}
	}

    public Hashtable getUsuarios(){
        return usuarios;
    }
}
