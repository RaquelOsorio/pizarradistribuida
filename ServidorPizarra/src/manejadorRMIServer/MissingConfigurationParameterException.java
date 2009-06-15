/*
 * Created on 16-07-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package manejadorRMIServer;

/**
 * @author Virtual Dark Priest
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class MissingConfigurationParameterException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -928165392230327054L;
	private String parameterName;
	
	public MissingConfigurationParameterException(String parameterName) {
		super("Faltan par�metros de configuraci�n: '" + parameterName + "'");
		this.parameterName = parameterName;
	}
	
	public String getParameterName() {
		return parameterName;
	}

}