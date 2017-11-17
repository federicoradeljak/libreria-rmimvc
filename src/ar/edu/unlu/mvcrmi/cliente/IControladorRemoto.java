package ar.edu.unlu.mvcrmi.cliente;

import java.rmi.RemoteException;

import ar.edu.unlu.mvcrmi.observer.IObservableRemoto;
import ar.edu.unlu.mvcrmi.observer.IObservadorRemoto;

public interface IControladorRemoto extends IObservadorRemoto {
	public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) throws RemoteException;
}
