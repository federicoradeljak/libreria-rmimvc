package ar.edu.unlu.rmimvc;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Esta clase abstracta es la base para la clase Cliente y Servidor que 
 * inician un servidor RMI local para la comunicación.
 *
 */
public abstract class ServidorRMI {
	private String host;
	private int port;
	private Registry registro;

	/**
	 * Constructor de la clase.
	 * 
	 * @param host IP en la que el servidor RMI escuchará peticiones.
	 * @param port puerto en el que el servidor RMI escuchará peticiones.
	 */
	public ServidorRMI(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	/**
	 * Inicia el servidor RMI.
	 * 
	 * @throws RemoteException error lanzado por problemas en la conexión de red.
	 */
	public void iniciarServidorRMI() throws RemoteException {
		System.setProperty("java.rmi.server.hostname", this.host);
		this.registro = LocateRegistry.createRegistry(this.port);
	}
	
	/**
	 * Exporta un objeto al repositorio RMI para que sea accedido remotamente y 
	 * devuelve un objeto "stub" que ejecuta sus métodos en el objeto remoto.
	 * El objeto pasado como parámetro debe implementar la interface java.rmi.Remote o alguna
	 * interfáz que herede de ella.
	 *
	 * @param <T> tipo genérico.
	 * @param nombre el nombre con el que será registrado el objeto.
	 * @param object objeto a registrar.
	 * @return un objeto "stub" del mismo tipo que el parámetro object.
	 * @throws RemoteException error lanzado por problemas de comunicación en la red.
	 * @throws AlreadyBoundException error lanzado por registrar un objeto que ya se encuentra registrado.
	 */
	@SuppressWarnings("unchecked")
	public <T extends Remote> T exportarObjeto(String nombre, T object) throws RemoteException, AlreadyBoundException {
		T stub = (T) UnicastRemoteObject.exportObject(object, this.port);
		this.registro.bind(nombre, stub);
		return stub;
	}
}
