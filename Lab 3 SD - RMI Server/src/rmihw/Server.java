package rmihw;

import Interface.InterfaceServer;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

public class Server extends UnicastRemoteObject implements InterfaceServer {

    private Hashtable users = new Hashtable();
	private ArrayList<Blackboard> blackboards = new ArrayList();
	//private ArrayList<Figure> figures = new ArrayList();

	private String message;

    //Constructor para pruebas
    public Server(String message) throws RemoteException {
        User usuario = new User("a", "a", "a", "a", "a");
        this.createUser(usuario);
        usuario = new User("b", "b", "b", "b", "b");
        this.createUser(usuario);
        usuario = new User("Alonso", "Inostrosa", "alonso", "alonso", "Alumno SD");
        this.createUser(usuario);
        usuario = new User("Carolina", "Francesca", "carolina", "carolina", "");
        this.createUser(usuario);

		this.message = message;
	}

	@Override
	public String say() throws RemoteException {
		System.out.println("Server, este es el mensaje:" + this.message);
		return this.message;
	}

    ///////////////////////////////
    //Metodos para Usuarios Comunes
    ///////////////////////////////
	public boolean login(String username, String password) throws RemoteException{
		//Chequear en BD el username y el passwd
		if(true){
			User user = new User(username, password);
			//Revisamos si es que el usuario no ha iniciado ya una sesion
			if(this.users.containsKey(user.getUsername())){
				//this.users.put(user.getUsername(),user);

                User userAux = (User )this.users.get(username);
                if(userAux.getPassword().compareTo(password)==0){
                    return true;
                }

			}
            
		}
		//No fue posible el login del usuario
		return false;
	}

	public boolean addBlackboard(User user, String nombre) throws RemoteException{
		//Crear el objeto y asociar usuario a la pizarra
        User usuario = (User) users.get(user.getUsername());
		Blackboard blackboard = new Blackboard(usuario, nombre);
        System.out.println("Nueva Pizarra: " + blackboard.getNombre() + " creada por: " + usuario.getUsername());

        blackboards.add(blackboard);
        
        //Asociar pizarra al usuario
        usuario.getOwnBlackboards().put(blackboard.getNombre(), blackboard);
        System.out.println("Cantidad de pizarras creadas por: " + usuario.getUsername() + " es " + usuario.getOwnBlackboards().size());
		return true;
	}

	public void registerToBlackboard(int blackboardId, User user) throws RemoteException{
		Blackboard auxBlackboard;
		Iterator<Blackboard> iterator = blackboards.iterator();
		while(iterator.hasNext()){
			auxBlackboard = iterator.next();
			if(auxBlackboard.getId()== blackboardId){
				auxBlackboard.addUser(user);
			}
		}
	}

    public Blackboard[] updateListOfOwnedBlackboards(User user) throws RemoteException{
        //buscar el usuario segun username y retornar sus pizarras
        int i=0;
        User usuario = (User) users.get(user.getUsername());
        Blackboard listOwnedBlackboards[] = new Blackboard[usuario.getOwnBlackboards().size()];
        for(Enumeration e =usuario.getOwnBlackboards().keys(); e.hasMoreElements();){
            String clave = (String) e.nextElement();
            listOwnedBlackboards[i] = (Blackboard) usuario.getOwnBlackboards().get(clave);
            i++;
        }
        System.out.println("Cantidad de pizarras a retornar " + listOwnedBlackboards.length);
        return listOwnedBlackboards;
    }

    ///////////////////////////////////////
    //Metodos necesarios para Usuario ROOT
    ///////////////////////////////////////
    public boolean createUser(User user) {
         if(users.containsKey(user.getUsername())){
            System.out.println("Usuario ya existe");
            return false;
        }
        //Se puede hacer la reserva
        System.out.println("Usuario agregado");
        this.users.put(user.getUsername(),user);
        return true;
    }

    public boolean deleteUser(User user) {
        users.remove(user.getUsername());
        return true;
    }

    public boolean updateUser(User user) {
        //Actualizar usuario  con datos de User, si todo OK retornar True, sino False
        User userAux = (User) users.get(user.getUsername());
        userAux.setNombres(user.getNombres());
        userAux.setApellidos(user.getApellidos());
        userAux.setPassword(user.getPassword());
        userAux.setDescricion(user.getDescricion());
        return true;
    }

    public User[] updateUserList() throws RemoteException {
        int i=0;
        User[] listOfUsers = new User[users.size()];
        for(Enumeration e =users.keys(); e.hasMoreElements();){
            String clave = (String) e.nextElement();
            listOfUsers[i] = (User) users.get(clave);
            i++;
        }
        return listOfUsers;
    }

    public boolean loginRoot(String username, String password) throws RemoteException {
        //Con la BD Obtener el passwd desde ahi.
        String passwd = "root";
        if(username.equals("root") && password.equals(passwd)){
            return true;
        }
        return false;
    }

    public User getUserByUsername(String username) throws RemoteException {
        User userAux = (User) users.get(username);
        return userAux;
    }

}
