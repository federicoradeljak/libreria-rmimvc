package ar.edu.unlu.poo.rmimvc;

/**
 * Excepción que es lanzada por la librería RMIMVC por problemas al buscar y registrar modelo/controlador en los registros RMI.
 * También podrá ser utilizada en el futuro para informar otros errores.
 */
public class RMIMVCException extends Exception {
	private static final long serialVersionUID = 8585722747207599218L;

	public RMIMVCException(String mensaje) {
		super(mensaje);
	}
}
