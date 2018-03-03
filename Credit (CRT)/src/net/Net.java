package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * Create a server with all of it's relevant components. Send, receive package
 * and deal with them accordingly;
 * 
 * @author George Argyrousis
 *
 */
public class Net implements Runnable{
	
	/** The ip address of the Client */
	private InetAddress ip;
	
	/** The socket of the connection */
	private DatagramSocket socket;
	
	/** The port number */
	private int port;
	
	/** The state of the thread running*/
	private boolean running = true;

	/**
	 * Construct all variables and set the current player of the game to an
	 * online player;
	 * 
	 * @param The ip
	 * @param The port
	 */
	public Net(String address, int port) {
		this.port = port;
		try {
			ip = InetAddress.getByName(address);
			socket = new DatagramSocket(port, ip);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	/** Main running method receiving packets */
	public void run() {
		while(running){
			byte data[] = new byte[1024];
			receive(new DatagramPacket(data, data.length));
		}
		socket.close();
	}
	
	public void getMacAddress(){
		Enumeration<NetworkInterface> addresses;
		try {
			addresses = NetworkInterface.getNetworkInterfaces();
			while(addresses.hasMoreElements()) {
			      byte mac[] = addresses.nextElement().getHardwareAddress();

			      if(mac != null) {
			        System.out.print("Current MAC address : ");

			        StringBuilder sb = new StringBuilder();
			        for (int i = 0; i < mac.length; i++) {
			          sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? ":" : ""));
			        }
			        System.out.println(sb.toString());
			      }
			    }
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	

	/** Receive the actual packet */
	public void receive(DatagramPacket packet) {
		try {
			socket.receive(packet);
			String data = new String(packet.getData());
			System.out.println(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Send data to a receipient;
	 * 
	 * @param The data.
	 * @param The ip address of the receipient.
	 * @param The port of the receipient.
	 */
	public void send(byte data[], InetAddress ip, int port) {
		try {
			socket.send(new DatagramPacket(data, data.length, ip, port));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Get the ip address */
	public String getIp(){
		String bits[] = this.ip.toString().split("/");
		if(bits.length > 1){
			return bits[1];
		}
		return bits[0];
	}
	
	/** Set the ip Address */
	public void setIp(InetAddress ip) {
		this.ip = ip;
	}

	/** Set the port */
	public void setPort(int port) {
		this.port = port;
	}

	/** Get the port */
	public int getPort(){
		return this.port;
	}
}
