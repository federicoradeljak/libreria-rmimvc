package ar.edu.unlu.poo.rmimvc;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

/**
 * Clase que agrupa funcionalidades comúnmente uilizadas.
 */
public class Util {
	/**
	 * Función para listar todas las IPs disponibles de todas las interfaces en el equipo.
	 * 
	 * @return Lista de IPs disponibles en forma de String.
	 */
    public static ArrayList<String> getIpDisponibles() {
    	ArrayList<String> ips = new ArrayList<>();
    	Enumeration<NetworkInterface> nets;
		try {
			nets = NetworkInterface.getNetworkInterfaces();
			for (NetworkInterface netint : Collections.list(nets)) {
	        	Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
	        	for (InetAddress inetAddress : Collections.list(inetAddresses)) {
	        		if (inetAddress instanceof Inet4Address) {
	        			ips.add(inetAddress.getHostAddress());
	        		}
	        	}
	        }
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ips;
    }
}
