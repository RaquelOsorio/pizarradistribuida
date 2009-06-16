package Interface;

import rmihw.*;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceServer extends Remote {
	
	  public String say() throws RemoteException;

      //Metodos para Usuarios comunes
      public boolean login(String username, String password) throws RemoteException;
      public boolean loginRoot(String username, String password) throws RemoteException;
      public boolean addBlackboard(User user, String nombre) throws RemoteException;
      public void registerToBlackboard(int blackboardId, User user) throws RemoteException;
      public Blackboard[] updateListOfOwnedBlackboards(User user) throws RemoteException;

      public User getUserByUsername(String username) throws RemoteException;

      //Metodos para Usuario ROOT
      public boolean createUser(User user) throws RemoteException;
      public boolean deleteUser(User user) throws RemoteException;
      public boolean updateUser(User user) throws RemoteException;
      public User[] updateUserList() throws RemoteException;
}
