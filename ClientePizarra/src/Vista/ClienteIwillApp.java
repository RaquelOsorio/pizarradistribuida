/*
 * ClienteIwillApp.java
 */

package Vista;


import Controlador.CPrincipal;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class ClienteIwillApp extends SingleFrameApplication {

    CPrincipal control;
    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        control = new CPrincipal();
 
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of ClienteIwillApp
     */
    public static ClienteIwillApp getApplication() {
        return Application.getInstance(ClienteIwillApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(ClienteIwillApp.class, args);
    }
}
