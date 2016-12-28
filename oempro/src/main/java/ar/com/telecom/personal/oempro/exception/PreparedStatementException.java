package ar.com.telecom.personal.oempro.exception;

public class PreparedStatementException extends Exception {

	private static final long serialVersionUID = -408425537809223573L;
	//private static final String MSG_EXCEPTION = "Error en el prepared statement. ";

	public PreparedStatementException(String s, Exception e) {
		super("Error en el prepared statement. " + s, e);
	}
}
