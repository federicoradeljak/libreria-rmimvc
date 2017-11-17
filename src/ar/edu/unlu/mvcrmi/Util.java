package ar.edu.unlu.mvcrmi;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class Util {
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
