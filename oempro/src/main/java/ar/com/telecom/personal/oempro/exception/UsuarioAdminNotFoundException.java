package ar.com.telecom.personal.oempro.exception;

public class UsuarioAdminNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1899280468664686669L;
//	private static final String MSG_EXCEPTION = "Error obteniendo el usuario administrador. El proceso no puede continuar. ";

	public UsuarioAdminNotFoundException(String msg) {
		super("Error obteniendo el usuario administrador. El proceso no puede continuar. " + msg);
	}

	public UsuarioAdminNotFoundException() {
	}
}
