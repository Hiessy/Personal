package ar.com.telecom.personal.oempro.exception;

public class LoadDataDirectoryException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 997700764784775823L;
	private static String MSG_EXCEPTION = "No se pudo crear el directorio para guardar los archivos de LOAD DATA";

	public LoadDataDirectoryException() {
		super(MSG_EXCEPTION);
	}
}
