package network;
import java.io.IOException;
import java.net.*;
/**
 * Created for CAP project.  
 * This object lets messages be sent between a network,
 * and is meant to work in conjunction with a 
 * NetworkReceiver object run in a separate machine
 * 
 * @author Dana Vold
 * A java multicast tutorial found at
 * http://lycog.com/programming/multicast-programming-java/
 * on Nov 19, 2014 was used as a reference when creating this
 * class.
 *
 */

public class NetworkSender {

	public static final int DEFAULT_PORT = 8117;
	public static final String DEFAULT_ADDRESS = "224.6.3.33";

	private int port;
	private InetAddress address;

	private DatagramSocket socket = null;
	private DatagramPacket outPacket = null;
	private byte[] buf; // buffer for message sent
	
	private boolean canSend;
	
	/**
	 * This class lets messages be sent to NetworkReceivers.
	 * 
	 * @param currentPort
	 * @param currentAddress
	 * @throws IOException
	 */
	public void commonConstructorDetails() throws IOException {

		
		socket = new DatagramSocket();
		canSend = true;
		
		//testLoop();
	}

	/**
	 * This class lets messages be sent to NetworkReceivers.
	 * 
	 * @param currentPort
	 * @param currentAddress
	 * @throws IOException
	 */
	public NetworkSender(int currentPort, String currentAddress) throws IOException {
		port = currentPort;
		address = InetAddress.getByName(currentAddress);
		commonConstructorDetails();
	}

	public NetworkSender(String currentAddress) throws IOException {
		port = DEFAULT_PORT;
		address = InetAddress.getByName(currentAddress);
		commonConstructorDetails();
	}

	public NetworkSender(int currentPort) throws IOException {
		port = currentPort;
		address = InetAddress.getByName(DEFAULT_ADDRESS);
		commonConstructorDetails();
	}

	public NetworkSender() throws IOException {
		port = DEFAULT_PORT;
		address = InetAddress.getByName(DEFAULT_ADDRESS);
		commonConstructorDetails();
	}
	
	/**
	 * Begin an endless loop, where we repeatedly send messages. 
	 * For use in debugging.
	 * 
	 * @throws IOException
	 */
	private void testLoop() throws IOException {

		long counter = 0;
		String msg;
		
		while (true) {
			msg = "multicast test "+counter;
			counter++;
			
			sendMessage(msg);
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public boolean sendMessage(String s) throws IOException {
		if (canSend) {
			canSend = false;
			buf = s.getBytes();
			
			outPacket = new DatagramPacket(buf, buf.length, address, port);
			socket.send(outPacket);
			canSend = true;
			return true;
		} else {
			return false;
		}
	}
}
