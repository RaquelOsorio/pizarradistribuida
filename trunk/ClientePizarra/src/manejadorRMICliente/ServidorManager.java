/*
 * ServidorManager.java
 */

  package manejadorRMICliente;

   import java.io.IOException;
   import java.io.InputStream;
   import java.util.HashMap;
   import java.util.Map;
   import java.util.Properties;
     
/**
 *
 * @author Veonem Group
 */
   
    public final class ServidorManager {
    
      private static final String CONFIGURATION_FILE = "ClienteIwill.properties";
      private static Map parameters;
      
      static {
         try {
         /* Lee el archivo de propiedades (si es que existe) */
            Class configurationParametersManagerClass = ServidorManager.class;
            ClassLoader classLoader = configurationParametersManagerClass.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(CONFIGURATION_FILE);
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            HashMap hashMap = new HashMap(properties);
            parameters = hashMap;
         } 
             catch (IOException e) {
               System.out.println("ERROR: " + e.toString());
            }
      
      } // Static
                
       public static String getParameter(String name) throws MissingConfigurationParameterException {
           
         String value = (String) parameters.get(name);
         if (value == null) {
            throw new MissingConfigurationParameterException(name);
         }
         return value;
      }
   }
