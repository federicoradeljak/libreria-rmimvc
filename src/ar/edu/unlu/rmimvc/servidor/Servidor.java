package ar.edu.unlu.rmimvc.servidor;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import ar.edu.unlu.rmimvc.MVCRMIException;
import ar.edu.unlu.rmimvc.ServidorRMI;

/**
 * Esta clase representa al servidor que har� disponible el modelo remotamente.
 */
public class Servidor extends ServidorRMI {

	/**
	 * Constructor de la clase.
	 * 
	 * @param host IP en la que el servidor escuchar� peticiciones.
	 * @param port puerto en el que el servidor escuchar� peticiciones.
	 */
	public Servidor(String host, int port) {
		super(host, port);
	}
	
	/**
	 * Inicia el servidor con el objeto del modelo pasado como par�metro y devuelve el "stub" del modelo que
	 * permite usar el objeto del modelo remotamente.
	 *
	 * @param <T> el tipo gen�rico
	 * @param modelo objeto del modelo que quedar� accesible remotamente.
	 * @return objeto "stub" del mismo tipo e interfaz que el modelo pasado como par�metro.
	 * @throws RemoteException error lanzado por problemas de comunicaci�n en la red.
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
