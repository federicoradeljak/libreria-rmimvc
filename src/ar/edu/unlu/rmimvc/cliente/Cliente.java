package ar.edu.unlu.rmimvc.cliente;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import ar.edu.unlu.rmimvc.MVCRMIException;
import ar.edu.unlu.rmimvc.ServidorRMI;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

/**
 * Esta clase representa al cliente que se conectar� con el servidor y har� disponible
 * remotamente el controlador.
 */
public class Cliente extends ServidorRMI {
	private String serverHost;
	private int serverPort;

	/**
	 * Constructor de la clase.
	 * 
	 * @param host IP en la que el cliente escuchar� las peticiones.
	 * @param port host en el que el cliente escuchar� las peticiones.
	 * @param serverHost IP del servidor al que conectarse.
	 * @param serverPort puerto del servidor al que conectarse.
	 */
	public Cliente(String host, int port, String serverHost, int serverPort) {
		super(host, port);
		this.serverHost = serverHost;
		this.serverPort = serverPort;
	}

	/**
	 * Inicia el servidor local con el objeto controlador y conecta con el servidor con el modelo remoto. Adem�s
	 * devuelve el objeto "stub" del controlador que permite usar el objeto del modelo remotamente
	 *
	 * @param <T> el tipo gen�rico.
	 * @param controlador objeto del controlador que quedar� accesible remotamente.
	 * @return controladorRemoto objeto "stub" del controlador que quedar� accesible remotamente.
	 * @throws MVCRMIException excepci�n lanzada por problemas al registrar el controlador en el servidor RMI o buscar el modelo en el servidor RMI remoto.
	 * @throws RemoteException excepci�n lanzada por problemas de comunicaci�n de red con objetos remotos.
	 */
	@SuppressWarnings("unchecked")
	public <T extends Remote & IObservableRemoto> IControladorRemoto iniciar(IControladorRemoto controlador) throws MVCRMIException, RemoteException {
		this.iniciarServidorRMI();
		Registry registro = LocateRegistry.getRegistry(this.serverHost, this.serverPort);
		T modeloRemoto;
		try {
			modeloRemoto = (T) registro.lookup("MVCRMI/Modelo");
		} catch (NotBoundException e) {
			throw new MVCRMIException("No se encontr� el modelo en el registro remoto.");
		}
		controlador.setModeloRemoto(modeloRemoto);
		
		IControladorRemoto controladorRemoto;
		try {
			controladorRemoto = this.exportarObjeto("MVCRMI/Cliente", controlador);
		} catch (AlreadyBoundException e) {
			throw new MVCRMIException("Error al registrar el controlador en el servidor RMI local.");
		}
		modeloRemoto.agregarObservador(controladorRemoto);
		return controladorRemoto;
	}
}
