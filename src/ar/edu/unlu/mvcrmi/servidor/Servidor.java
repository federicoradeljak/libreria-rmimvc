package ar.edu.unlu.mvcrmi.servidor;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import ar.edu.unlu.mvcrmi.ServidorRMI;

/**
 * Esta clase representa al servidor que hará disponible el modelo remotamente.
 */
public class Servidor extends ServidorRMI {

	/**
	 * Constructor de la clase.
	 * @param host IP en la que el servidor escuchará peticiciones.
	 * @param port puerto en el que el servidor escuchará peticiciones.
	 */
	public Servidor(String host, int port) {
		super(host, port);
	}
	
	/**
	 * Inicia el servidor con el objeto del modelo pasado como parámetro y devuelve el "stub" del modelo que
	 * permite usar el objeto del modelo remotamente.
	 * @param modelo objeto del modelo que quedará accesible remotamente.
	 * @return objeto "stub" del mismo tipo e interfaz que el modelo pasado como parámetro.
	 * @throws RemoteException
	 * @throws AlreadyBoundException
	 */
	public <T extends Remote> T iniciar(T modelo) throws RemoteException, AlreadyBoundException {
		this.iniciarServidorRMI();
		return this.exportarObjeto("MVCRMI/Modelo", modelo);
	}
}
