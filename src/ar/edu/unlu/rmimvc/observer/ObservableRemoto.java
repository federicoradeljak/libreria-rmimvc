package ar.edu.unlu.rmimvc.observer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Esta clase forma parte del conjunto de interfaces y clases que permiten aplicar el patrón Observer 
 * entre el modelo que reside en el servidor y el controlador que posee cada cliente.
 * El modelo remoto debe extender de esta clase para poder notificar a los controladores de los cambios
 * mediante el método notificarObservadores().
 * Cada controlador debe suscribirse con el método agregarObservador() para recibir las actualizaciones del modelo remoto.
 *
 */
public abstract class ObservableRemoto implements Remote, IObservableRemoto {
	private ArrayList<IObservadorRemoto> observadores;
	
	public ObservableRemoto() {
		this.observadores = new ArrayList<>();
	}
	
	/* (non-Javadoc)
	 * @see ar.edu.unlu.mvcrmi.IObservableRemoto#agregarObservador(ar.edu.unlu.mvcrmi.ObservadorRemoto)
	 */
	@Override
	public void agregarObservador(IObservadorRemoto o) throws RemoteException {
		this.observadores.add(o);
	}
	
	/* (non-Javadoc)
	 * @see ar.edu.unlu.mvcrmi.IObservableRemoto#removerObservador(ar.edu.unlu.mvcrmi.ObservadorRemoto)
	 */
	@Override
	public void removerObservador(IObservadorRemoto o) throws RemoteException {
		this.observadores.remove(o);
	}
	
	/* (non-Javadoc)
	 * @see ar.edu.unlu.mvcrmi.IObservableRemoto#notificarObservadores(java.lang.Object)
	 */
	@Override
	public void notificarObservadores(Object obj) throws RemoteException {
		for (IObservadorRemoto o: this.observadores) {
			o.actualizar(obj);
			/*new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						o.actualizar(obj);
					} catch (RemoteException e) {
						System.out.println("ERROR: notificando al observador.");
						e.printStackTrace();
					}
				}
			}).start();*/
		}
	}
	
	/* (non-Javadoc)
	 * @see ar.edu.unlu.mvcrmi.IObservableRemoto#notificarObservadores()
	 */
	@Override
	public void notificarObservadores() throws RemoteException {
		this.notificarObservadores(null);
	}
}
