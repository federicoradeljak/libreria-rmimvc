package ar.edu.unlu.mvcrmi.cliente;

import java.rmi.Remote;
import java.rmi.RemoteException;

import ar.edu.unlu.mvcrmi.observer.IObservableRemoto;
import ar.edu.unlu.mvcrmi.observer.IObservadorRemoto;

/**
 * Esta interface debe ser implementada por el controlador remoto. El �nico m�todo que
 * posee es el necesario para decirle al controlador cual es el modelo con el que se comunicar�.
 */
public interface IControladorRemoto extends IObservadorRemoto {
	/**
	 * El controlador debe implmentar este m�todo y guardar el modelo remoto pasado como par�metro.
	 * 
	 * @param modeloRemoto el "stub" del modelo remoto.
	 * @throws RemoteException como es un m�todo remoto puede fallar la comunicaci�n.
	 * @see Remote
	 * @see RemoteException
	 */
	public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) throws RemoteException;
}
