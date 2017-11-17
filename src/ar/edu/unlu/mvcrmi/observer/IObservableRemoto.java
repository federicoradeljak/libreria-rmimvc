package ar.edu.unlu.mvcrmi.observer;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Esta es la interface con los m�todos p�blicos de la clase ObservableRemoto. Extiende la interface Remote
 * ya que el observable ser� accedido remotamente, por eso todos los m�todos pueden generar una RemoteException.
 * 
 */
public interface IObservableRemoto extends Remote {

	/**
	 * Agrega un observador para que pueda recibir las actualizaciones de cambios de estado.
	 * @param o observador a agregar.
	 * @throws RemoteException
	 */
	public void agregarObservador(IObservadorRemoto o) throws RemoteException;

	/**
	 * Saca un observador para que deje de recibir actualizaciones en los cambios de estado.
	 * @param o observador a remover.
	 * @throws RemoteException
	 */
	public void removerObservador(IObservadorRemoto o) throws RemoteException;

	/**
	 * Notifica a cada uno de los observadores pas�ndoles el par�metro obj.
	 * @param obj objeto a pasar con la notificaci�n de cambio de estado.
	 * @throws RemoteException
	 */
	public void notificarObservadores(Object obj) throws RemoteException;

	/**
	 * Notifica a cada uno de los observadores de un cambio de estado.
	 * @throws RemoteException
	 */
	public void notificarObservadores() throws RemoteException;


}