package ServidorPizarra;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

public class ServerIwill2App extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new ServerIwill2View(this));
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
     * @return the instance of ServerIwill2App
     */
    public static ServerIwill2App getApplication() {
        return Application.getInstance(ServerIwill2App.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(ServerIwill2App.class, args);
    }
}
