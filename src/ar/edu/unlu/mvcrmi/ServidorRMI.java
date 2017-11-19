package ar.edu.unlu.mvcrmi;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Esta clase abstracta es la base para la clase Cliente y Servidor que 
 * inician un servidor RMI local para la comunicaci�n.
 *
 */
public abstract class ServidorRMI {
	private String host;
	private int port;
	private Registry registro;

	/**
	 * Constructor de la clase.
	 * 
	 * @param host IP en la que el servidor RMI escuchar� peticiones.
	 * @param port puerto en el que el servidor RMI escuchar� peticiones.
	 */
	public ServidorRMI(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	/**
	 * Inicia el servidor RMI.
	 * 
	 * @throws RemoteException error lanzado por problemas en la conexi�n de red.
	 */
	public void iniciarServidorRMI() throws RemoteException {
		System.setProperty("java.rmi.server.hostname", this.host);
		this.registro = LocateRegistry.createRegistry(this.port);
	}
	
	/**
	 * Exporta un objeto al repositorio RMI para que sea accedido remotamente y 
	 * devuelve un objeto "stub" que ejecuta sus m�todos en el objeto remoto.
	 * El objeto pasado como par�metro debe implementar la interface java.rmi.Remote o alguna
	 * interf�z que herede de ella.
	 *
	 * @param <T> tipo gen�rico.
	 * @param nombre el nombre con el que ser� registrado el objeto.
	 * @param object objeto a registrar.
	 * @return un objeto "stub" del mismo tipo que el par�metro object.
	 * @throws RemoteException error lanzado por problemas de comunicaci�n en la red.
	 * @throws AlreadyBoundException error lanzado por registrar un objeto que ya se encuentra registrado.
	 */
	@SuppressWarnings("unchecked")
	public <T extends Remote> T exportarObjeto(String nombre, T object) throws RemoteException, AlreadyBoundException {
		T stub = (T) UnicastRemoteObject.exportObject(object, this.port);
		this.registro.bind(nombre, stub);
		return stub;
	}
}
