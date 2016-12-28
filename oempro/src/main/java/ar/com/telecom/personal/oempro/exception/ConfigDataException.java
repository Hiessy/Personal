package ar.com.telecom.personal.oempro.exception;

public class ConfigDataException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2685961169202197018L;
	//private static final String MSG_EXCEPTION = "Error en el archivo de configuración. ";

	public ConfigDataException(String msg) {
		super("Error en el archivo de configuración. " + msg);
	}
}
