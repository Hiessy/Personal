package ar.com.telecom.personal.oempro.service.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesLoader {
	
	private static List<Properties> propertiesList = new ArrayList<Properties>();
	private static final Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);

	private static final int CONFIG_PROPERTIES = 0;

	static {
		propertiesList.add(new Properties());
		loadProperties();
	}

	public static void loadProperties() {
		try {
			((Properties) propertiesList.get(0)).load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));

		} catch (Exception e) {
			logger.error("Error cargando los archivos de configuración", e);
		}
	}

	public static Properties getConfigProperties() {
		return (Properties) propertiesList.get(CONFIG_PROPERTIES);
	}
	
	/**
	 * Gets the app.version property value from
	 * the ./main.properties file of the base folder
	 *
	 * @return app.version string
	 * @throws IOException
	 */
	public static Properties getProperties() throws IOException{

	   // String versionString = null;

	    //to load application's properties, we use this class
	    Properties mainProperties = new Properties();

	    FileInputStream file;

	    //the base folder is ./, the root of the main.properties file  
	    String path = "/controlm/integracion/resources/config.properties";

	    //load the file handle for main.properties
	    file = new FileInputStream(path);

	    //load all the properties from this file
	    mainProperties.load(file);

	    //we have loaded the properties, so close the file handle
	    file.close();

	    //retrieve the property we are intrested, the app.version
	   // versionString = mainProperties.getProperty("app.version");

	    return mainProperties;
	}

}
	

