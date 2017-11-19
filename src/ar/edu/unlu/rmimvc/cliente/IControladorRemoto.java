package ar.edu.unlu.rmimvc.cliente;

import java.rmi.Remote;
import java.rmi.RemoteException;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;

/**
 * Esta interface debe ser implementada por el controlador remoto. El único método que
 * posee es el necesario para decirle al controlador cual es el modelo con el que se comunicará.
 */
public interface IControladorRemoto extends IObservadorRemoto {
	
	/**
	 * El controlador debe implmentar este método y guardar el modelo remoto pasado como parámetro.
	 *
	 * @param <T> el tipo genérico.
	 * @param modeloRemoto el "stub" del modelo remoto.
	 * @throws RemoteException como es un método remoto puede fallar la comunicación.
	 * @see Remote
	 * @see RemoteException
	 */
	public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) throws RemoteException;
}
