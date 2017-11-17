package ar.edu.unlu.mvcrmi.observer;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Esta interface forma parte del conjunto de interfaces y clases que permiten aplicar el patr�n Observer 
 * entre el modelo que reside en el servidor y el controlador que posee cada cliente. Extiende la interface Remote
 * ya que el observable ser� accedido remotamente, por eso todos los m�todos pueden generar una RemoteException.
 * El controlador de cada cliente remoto debe implementar esta interface para poder suscribirse a los cambios en el modelo.
 * 
 */
public interface IObservadorRemoto extends Remote {
	/**
	 * Este m�todo es llamado cada vez que el modelo notifica de un cambio de estado.
	 * @param o objeto que envia el observable.
	 * @throws RemoteException
	 */
	public void actualizar(Object o) throws RemoteException;
}
