package ar.com.telecom.personal.oempro.dao;

import ar.com.telecom.personal.oempro.security.DESEncryptImpl;
import ar.com.telecom.personal.oempro.service.util.PropertiesLoader;
import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ConnectionManager {
	private static Connection instance = null;
	private static Properties props = PropertiesLoader.getConfigProperties();
	private static final Logger logger = LoggerFactory.getLogger(ConnectionManager.class);
	private static String pass = "";
	private static DESEncryptImpl desEncrypt = null;

	public static Connection getConnection() {
		if (instance == null) {
			try {
				desEncrypt = new DESEncryptImpl(props.getProperty("security.key"));
				pass = desEncrypt.decrypt(props.getProperty("jdbc.password"));

				instance = newConnection();
			} catch (Exception e) {
				logger.error("Error obteniendo conexion a la base de datos", e);
			}
		}

		return instance;
	}

	private static Connection newConnection() {
		Connection conn = null;

		try {
			DriverManager.registerDriver(new Driver());

			conn = DriverManager.getConnection(props.getProperty("jdbc.url"), props.getProperty("jdbc.username"), pass);

			logger.info("Se creo una nueva conexión con la base de datos");

		} catch (Exception e) {

			logger.error("Error obteniendo conexion a la base de datos", e);
		}

		return conn;
	}
}
