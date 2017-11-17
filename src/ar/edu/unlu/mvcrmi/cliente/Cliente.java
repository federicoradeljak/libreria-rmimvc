package ar.edu.unlu.mvcrmi.cliente;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import ar.edu.unlu.mvcrmi.ServidorRMI;
import ar.edu.unlu.mvcrmi.observer.IObservableRemoto;

/**
 * Esta clase representa al cliente que se conectará con el servidor y hará disponible
 * remotamente el controlador.
 */
public class Cliente extends ServidorRMI {
	private String serverHost;
	private int serverPort;

	/**
	 * Constructor de la clase.
	 * @param host IP en la que el cliente escuchará las peticiones.
	 * @param port host en el que el cliente escuchará las peticiones.
	 * @param serverHost IP del servidor al que conectarse.
	 * @param serverPort puerto del servidor al que conectarse.
	 */
	public Cliente(String host, int port, String serverHost, int serverPort) {
		super(host, port);
		this.serverHost = serverHost;
		this.serverPort = serverPort;
	}

	/**
	 * Inicia el servidor local con el objeto controlador y conecta con el servidor con el modelo remoto. Además
	 * devuelve el objeto "stub" del controlador que permite usar el objeto del modelo remotamente
	 * @param controlador objeto del controlador que quedará accesible remotamente.
	 * @return controladorRemoto objeto "stub" del controlador que quedará accesible remotamente.
	 * @throws AlreadyBoundException
	 * @throws AccessException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	@SuppressWarnings("unchecked")
	public <T extends Remote & IObservableRemoto> IControladorRemoto iniciar(IControladorRemoto controlador) throws AlreadyBoundException, AccessException, RemoteException, NotBoundException {
		this.iniciarServidorRMI();
		Registry registro = LocateRegistry.getRegistry(this.serverHost, this.serverPort);
		T modeloRemoto = (T) registro.lookup("MVCRMI/Modelo");
		controlador.setModeloRemoto(modeloRemoto);
		
		IControladorRemoto controladorRemoto = this.exportarObjeto("MVCRMI/Cliente", controlador);
		modeloRemoto.agregarObservador(controladorRemoto);
		return controladorRemoto;
	}
}
