/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmihw;

import Interface.InterfaceServer;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.*;

/**
 *
 * @author alonso
 */
public class MainWindowController implements SelectionListener {

    private MainWindowView mainWindowView;
    private CreateUserWindowView createUserWindowView = null;
    private boolean flagReserveWindowOpen;
    private ServerHandler manejador;
    //private ReserveWindowView reserveWindowView;

    public MainWindowController(MainWindowView mainWindowView, ServerHandler manejador) {
        this.mainWindowView = mainWindowView;
        //this.reserveWindow = reserveWindow;
        this.manejador = manejador;
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void widgetSelected(SelectionEvent e) {

    //Flag utilizado para forzar la actualizacion de la lista de usuarios
    boolean forceUpdateListOfUsers = false;

        System.out.println(e.getSource());
        //Clic sobre el boton "Agregar Usuario"
        if (mainWindowView.getAddUser().equals((Button) e.getSource())) {
            //Abrir a la ventana "ReserveWindowView" y
            //controlamos que no se abra más de una a la vez
            if (this.flagReserveWindowOpen == false) {
                createUserWindowView = new CreateUserWindowView(mainWindowView.getDisplay(), this);
            }
            if (createUserWindowView.getShell().isDisposed()) {
                createUserWindowView = new CreateUserWindowView(mainWindowView.getDisplay(), this);
            }
        }


        //Clic sobre el boton "Actualizar Usuario"
        if (mainWindowView.getUpdateUser().equals((Button) e.getSource())) {
            //Elemento seleccionado
            int selectionIndex = mainWindowView.getUserList().getSelectionIndex();
            System.out.println("Elemento seleccionado para update" + selectionIndex);
            if (selectionIndex >= 0) {
                //Obtener Usuario
                String selectedNombres = mainWindowView.getUserList().getItem(selectionIndex).getText(0);
                String selectedApellidos = mainWindowView.getUserList().getItem(selectionIndex).getText(1);
                String selectedUsername = mainWindowView.getUserList().getItem(selectionIndex).getText(2);
                String selectedPassword = mainWindowView.getUserList().getItem(selectionIndex).getText(3);
                String selectedDescripcion = mainWindowView.getUserList().getItem(selectionIndex).getText(4);

                User userToUpdate = new User(selectedNombres,selectedApellidos,selectedUsername,selectedPassword,selectedDescripcion);

                //Abrir a la ventana "ReserveWindowView" y
                //controlamos que no se abra más de una a la vez
                if (this.flagReserveWindowOpen == false) {
                    createUserWindowView = new CreateUserWindowView(mainWindowView.getDisplay(),this,userToUpdate);
                }
                if (createUserWindowView.getShell().isDisposed()) {
                    createUserWindowView = new CreateUserWindowView(mainWindowView.getDisplay(),this,userToUpdate);
                }
            }else{
                MessageBox alert = new MessageBox(mainWindowView.getShell(), SWT.ICON_WARNING);
                alert.setMessage("Seleccione un usuario a actualizar");
                int result = alert.open();
            }

        //Una vez creada la ventana, esta podrá editar al usuario
        }

        //Clic sobre el boton Guardar en la ventana secundaria (Crear Usuario)
        if (createUserWindowView != null) {
            if (createUserWindowView.getGuardar().equals((Button) e.getSource()) && createUserWindowView.getFlagSaveOrUpdate()) {
               
                //Obtener datos desde la vista
                String nombres = createUserWindowView.getNombreText().getText();
                String apellidos = createUserWindowView.getApellidosText().getText();
                String userName = createUserWindowView.getUserNameText().getText();
                String password = createUserWindowView.getPasswordText().getText();
                String descripcion = createUserWindowView.getDescripcionText().getText();

                if (nombres.equals("") || apellidos.equals("") || userName.equals("") || password.equals("")) {
                    MessageBox alert = new MessageBox(createUserWindowView.getShell(), SWT.ICON_WARNING);
                    alert.setMessage("Por favor, llene los campos");
                    int result = alert.open();
                } else {
                    //Crear objeto User
                    User usuario = new User(nombres, apellidos, userName, password, descripcion);

                    //Procedimiento para Crear un usuario (interaccion con el server).
                    //Al server se le enviará el User (objeto) y server deberá ver si hay no esta duplicado.
                    //Si ya existiera devuelve falso, sino retornará true y crea el usuario en el server.
                    InterfaceServer server = null;
                    try {
                        server = RMICliente.getInterfaceServerRMI(server);
                    } catch (RemoteException ex) {
                        MessageBox alert = new MessageBox(mainWindowView.getShell());
                        alert.setMessage("No se pudo conectar con el servidor");
                        int result = alert.open();
                    //Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NotBoundException ex) {
                        //Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                        MessageBox alert = new MessageBox(mainWindowView.getShell());
                        alert.setMessage("No se pudo conectar con el servidor");
                        int result = alert.open();
                    }


                    boolean resp;
                    try {
                        resp = server.createUser(usuario);
                        if (resp) {
                            System.out.println("Usuario Creado");
                            MessageBox alert = new MessageBox(createUserWindowView.getShell());
                            alert.setMessage("El Usuario fue creado");
                            int result = alert.open();
                            mainWindowView.update();
                            createUserWindowView.getShell().dispose();
                        } else {
                            //Avisar que hubo un problema
                            System.out.println("Hubo un problema con la creacion del usuario");
                            MessageBox alert = new MessageBox(createUserWindowView.getShell());
                            alert.setMessage("Hubo un problema con la creacion del usuario");
                            int result = alert.open();
                        }
                    } catch (RemoteException ex) {
                        System.out.println("Hubo un problema en la conexion con el server en la creacion del usuario");
                        MessageBox alert = new MessageBox(createUserWindowView.getShell());
                        alert.setMessage("Hubo un problema en la conexion con el servidor en la creacion del usuario");
                        int result = alert.open();
                    }
                }
            }
            //Para forzar la recarga de la lista de usuarios luego de crear o editar alguno
            forceUpdateListOfUsers = true;
        }

        //Clic sobre el boton Guardar en la ventana secundaria (Actualizar Usuario)
        if (createUserWindowView != null) {
            if (createUserWindowView.getGuardar().equals((Button) e.getSource()) && !createUserWindowView.getFlagSaveOrUpdate()) {

                //Obtener datos desde la vista
                String nombres = createUserWindowView.getNombreText().getText();
                String apellidos = createUserWindowView.getApellidosText().getText();
                String userName = createUserWindowView.getUserNameText().getText();
                String password = createUserWindowView.getPasswordText().getText();
                String descripcion = createUserWindowView.getDescripcionText().getText();

                if (nombres.equals("") || apellidos.equals("") || userName.equals("") || password.equals("")) {
                    MessageBox alert = new MessageBox(createUserWindowView.getShell());
                    alert.setMessage("Por favor, llene los campos");
                    int result = alert.open();
                } else {
                    //Crear objeto User
                    User usuario = new User(nombres, apellidos, userName, password, descripcion);

                    //Procedimiento para Crear un usuario (interaccion con el server).
                    //Al server se le enviará el User (objeto) y server deberá ver si hay no esta duplicado.
                    //Si ya existiera devuelve falso, sino retornará true y crea el usuario en el server.
                    InterfaceServer server = null;
                    try {
                        server = RMICliente.getInterfaceServerRMI(server);
                    } catch (RemoteException ex) {
                        MessageBox alert = new MessageBox(mainWindowView.getShell());
                        alert.setMessage("No se pudo conectar con el servidor");
                        int result = alert.open();
                    //Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NotBoundException ex) {
                        //Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                        MessageBox alert = new MessageBox(mainWindowView.getShell());
                        alert.setMessage("No se pudo conectar con el servidor");
                        int result = alert.open();
                    }


                    boolean resp;
                    try {
                        resp = server.updateUser(usuario);
                        if (resp) {
                            System.out.println("Usuario Actualizado");
                            MessageBox alert = new MessageBox(createUserWindowView.getShell());
                            alert.setMessage("El Usuario fue Actualizado");
                            int result = alert.open();
                            mainWindowView.update();
                            createUserWindowView.getShell().dispose();
                        } else {
                            //Avisar que hubo un problema
                            System.out.println("Hubo un problema con la actualizacion del usuario");
                            MessageBox alert = new MessageBox(createUserWindowView.getShell());
                            alert.setMessage("Hubo un problema con la actualizacion del usuario");
                            int result = alert.open();
                        }
                    } catch (RemoteException ex) {
                        System.out.println("Hubo un problema en la conexion con el server en la actualizacion del usuario");
                        MessageBox alert = new MessageBox(createUserWindowView.getShell());
                        alert.setMessage("Hubo un problema en la conexion con el servidor en la actualizacion del usuario");
                        int result = alert.open();
                    }
                }
            }
            //Para forzar la recarga de la lista de usuarios luego de crear o editar alguno
            forceUpdateListOfUsers = true;
        }

        //Clic sobre el boton "Eliminar Usuario"
        if (mainWindowView.getRemoveUser().equals((Button) e.getSource())) {
            //Elemento seleccionado
            int selectionIndex = mainWindowView.getUserList().getSelectionIndex();
            System.out.println("Elemento seleccionado " + selectionIndex);
            if (selectionIndex >= 0) {
                //Pedir confirmacion
                MessageBox alert = new MessageBox(mainWindowView.getShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
                alert.setMessage("seguro que desea borrar el usuario?");
                int result = alert.open();
                if (result == SWT.YES) {
                    //Borrar User
                    String selectedUser = mainWindowView.getUserList().getItem(selectionIndex).getText(2);
                    System.out.println("Selected username for remove " + selectedUser);
                    User userToDelete = new User(selectedUser);

                    InterfaceServer interfaceServer = null;
                    try {
                        interfaceServer = RMICliente.getInterfaceServerRMI(interfaceServer);
                        if (interfaceServer.deleteUser(userToDelete)) {
                            alert = new MessageBox(mainWindowView.getShell(), SWT.ICON_INFORMATION);
                            alert.setMessage("El usuario fue borrado");
                            result = alert.open();
                        }
                    } catch (RemoteException ex) {
                        alert = new MessageBox(mainWindowView.getShell(), SWT.ICON_ERROR);
                        alert.setMessage("No se pudo borrar el usuario");
                        result = alert.open();
                    } catch (NotBoundException ex) {
                        alert = new MessageBox(mainWindowView.getShell(), SWT.ICON_ERROR);
                        alert.setMessage("El usuario no pudo ser borrado");
                        result = alert.open();
                    }

                    //Para forzar la recarga de la lista de usuarios luego de borrar alguno
                    forceUpdateListOfUsers = true;
                }
            }else{
                MessageBox alert = new MessageBox(mainWindowView.getShell(), SWT.ICON_WARNING);
                alert.setMessage("Debe seleccionar un usuario de la tabla para poder borrarlo");
                int result = alert.open();
            }

        }

        //Actualizar Lista de usuarios desde el server.
        //Server enviará todas los usuarios para poder actualizar lista local del cliente (root).
        if (mainWindowView.getUpdateUserList().equals((Button) e.getSource()) || forceUpdateListOfUsers) {
            System.out.println("Se pide lista usuarios al server");
            InterfaceServer interfaceServer = null;
            try {
                interfaceServer = RMICliente.getInterfaceServerRMI(interfaceServer);
            } catch (RemoteException ex) {
                MessageBox alert = new MessageBox(mainWindowView.getShell());
                alert.setMessage("No se pudo conectar con el servidor");
                int result = alert.open();
            } catch (NotBoundException ex) {
                MessageBox alert = new MessageBox(mainWindowView.getShell());
                alert.setMessage("No se pudo conectar con el servidor");
                int result = alert.open();
            //Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                //Recibe entradas de la agenda desde server
                User[] entradasListaUsuarios = interfaceServer.updateUserList();

                manejador.usuariosFromArray(entradasListaUsuarios);


                //Enviar entradas a la agenda

                System.out.println("Lista de Usuarios recibida");
            } catch (RemoteException ex) {
                MessageBox alert = new MessageBox(mainWindowView.getShell());
                alert.setMessage("No se pudo conectar con el servidor");
                int result = alert.open();
            //Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public MainWindowView getMainWindowView() {
        return mainWindowView;
    }

    public void setMainWindowView(MainWindowView mainWindowView) {
        this.mainWindowView = mainWindowView;
    }

    public CreateUserWindowView getReserveWindowView() {
        return createUserWindowView;
    }

    public void setReserveWindowView(CreateUserWindowView reserveWindowView) {
        this.createUserWindowView = reserveWindowView;
    }

    public boolean isFlagReserveWindowOpen() {
        return flagReserveWindowOpen;
    }

    public void setFlagReserveWindowOpen(boolean flagReserveWindowOpen) {
        this.flagReserveWindowOpen = flagReserveWindowOpen;
    }

    public ServerHandler getManejador() {
        return manejador;
    }

    public void setManejador(ServerHandler manejador) {
        this.manejador = manejador;
    }
}
