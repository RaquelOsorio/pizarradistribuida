/*
 * MissingConfigurationParameterException.java
 */

package manejadorRMICliente;

/**
 *
 * @author Veonem Group
 */

public class MissingConfigurationParameterException extends Exception {

    private static final long serialVersionUID = -928165392230327054L;
    private String parameterName;

    public MissingConfigurationParameterException(String parameterName) {
        
        super("Faltan parámetros de configuración: '" + parameterName + "'");
        this.parameterName = parameterName;
    }

    public String getParameterName() {
        
        return parameterName;
    }

}