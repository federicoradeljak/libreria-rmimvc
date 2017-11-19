package ar.edu.unlu.rmimvc;

/**
 * Excepción que es lanzada por la librería MVCRMI por problemas al buscar y registrar modelo/controlador en los registros RMI.
 * También podrá ser utilizada en el futuro para informar otros errores.
 */
public class MVCRMIException extends Exception {
	private static final long serialVersionUID = 8585722747207599218L;

	public MVCRMIException(String mensaje) {
		super(mensaje);
	}
}
