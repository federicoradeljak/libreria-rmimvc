package ar.edu.unlu.rmimvc;

/**
 * Excepci�n que es lanzada por la librer�a MVCRMI por problemas al buscar y registrar modelo/controlador en los registros RMI.
 * Tambi�n podr� ser utilizada en el futuro para informar otros errores.
 */
public class MVCRMIException extends Exception {
	private static final long serialVersionUID = 8585722747207599218L;

	public MVCRMIException(String mensaje) {
		super(mensaje);
	}
}
