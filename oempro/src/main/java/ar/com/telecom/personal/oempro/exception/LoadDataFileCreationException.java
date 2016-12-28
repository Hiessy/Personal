package ar.com.telecom.personal.oempro.exception;

public class LoadDataFileCreationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3293698846511976176L;
	//private static final String MSG_EXCEPTION = "Error creando archivo. ";

	public LoadDataFileCreationException(String s, Exception e) {
		super("Error creando archivo. " + s, e);
	}
}
