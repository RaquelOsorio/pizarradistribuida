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
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class ConfigurationParametersManager {

	private static final String CONFIGURATION_FILE = "SERVERIWILL2.properties";

	private static Map parameters;
	static {
		try {
			/* Lee el archivo de propiedades (si es que existe) */
			Class configurationParametersManagerClass = ConfigurationParametersManager.class;
			ClassLoader classLoader = configurationParametersManagerClass
					.getClassLoader();
			InputStream inputStream = classLoader
					.getResourceAsStream(CONFIGURATION_FILE);
			Properties properties = new Properties();
			properties.load(inputStream);
			inputStream.close();
			HashMap hashMap = new HashMap(properties);
			parameters = hashMap;
		} catch (IOException e) {
			System.out.println("ERROR: " + e.toString());
		}

	} // Static

	private ConfigurationParametersManager() {
	}

	public static String getParameter(String name)
			throws MissingConfigurationParameterException {
		String value = (String) parameters.get(name);
		if (value == null) {
			throw new MissingConfigurationParameterException(name);
		}
		return value;
	}
}// class
