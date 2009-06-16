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
	private User usuario;

	public ServerHandler() {

	}

	public void update(User usuario){
		this.usuario = usuario;
	}


    public void updateBlackboardsUser(Blackboard[] pizarras){
        Hashtable blackboards = new Hashtable();
        for(int i=0;i<pizarras.length;i++){
            blackboards.put(pizarras[i].getNombre(), pizarras[i]);
        }
        usuario.setOwnBlackboards(blackboards);
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

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

}
