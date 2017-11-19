package ar.edu.unlu.rmimvc.servidor;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import ar.edu.unlu.rmimvc.MVCRMIException;
import ar.edu.unlu.rmimvc.ServidorRMI;

/**
 * Esta clase representa al servidor que hará disponible el modelo remotamente.
 */
public class Servidor extends ServidorRMI {

	/**
	 * Constructor de la clase.
	 * 
	 * @param host IP en la que el servidor escuchará peticiciones.
	 * @param port puerto en el que el servidor escuchará peticiciones.
	 */
	public Servidor(String host, int port) {
		super(host, port);
	}
	
	/**
	 * Inicia el servidor con el objeto del modelo pasado como parámetro y devuelve el "stub" del modelo que
	 * permite usar el objeto del modelo remotamente.
	 *
	 * @param <T> el tipo genérico
	 * @param modelo objeto del modelo que quedará accesible remotamente.
	 * @return objeto "stub" del mismo tipo e interfaz que el modelo pasado como parámetro.
	 * @throws RemoteException error lanzado por problemas de comunicación en la red.
	 * @throws MVCRMIException error lanzado por problemas registrando el modelo en el servidor RMI.
	 * @see RemoteException
	 */
	public <T extends Remote> T iniciar(T modelo) throws RemoteException, MVCRMIException {
		this.iniciarServidorRMI();
		try {
			return this.exportarObjeto("MVCRMI/Modelo", modelo);
		} catch (AlreadyBoundException e) {
			throw new MVCRMIException("Error al registrar el modelo en el servidor RMI local, ya se encuentra registrado.");
		}
	}
}
