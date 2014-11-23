package network;
import java.io.IOException;
import java.net.*;


/**
 * Created for CAP project.  
 * This object gets messages sent by NetworkSender objects.
 * The NetworkSender object can be run on a different program,
 * with a different machine.
 * 
 * @author Dana Vold
 * A java multicast tutorial found at
 * http://lycog.com/programming/multicast-programming-java/
 * on Nov 19, 2014 was used as a reference when creating this
 * class.
 *
 */

public class NetworkReceiver {
	
	MulticastSocket socket;
	DatagramPacket pack;
	byte[] buf;
	
	private int port;
	private InetAddress address;
	
	private static final int BUFFER_SIZE = 1024;

	public NetworkReceiver(int currentPort, String currentAddress) throws IOException {
		port = currentPort;
		address = InetAddress.getByName(currentAddress);
		commonConstructorDetails();
	}

	public NetworkReceiver(int currentPort) throws IOException {
		port = currentPort;
		address = InetAddress.getByName(NetworkSender.DEFAULT_ADDRESS);
		commonConstructorDetails();
	}

	public NetworkReceiver(String currentAddress) throws IOException {
		port = NetworkSender.DEFAULT_PORT;
		address = InetAddress.getByName(currentAddress);
		commonConstructorDetails();
	}

	public NetworkReceiver() throws IOException {
		port = NetworkSender.DEFAULT_PORT;
		address = InetAddress.getByName(NetworkSender.DEFAULT_ADDRESS);
		commonConstructorDetails();
	}
	
	private void commonConstructorDetails() throws IOException {
		
		socket = new MulticastSocket(port);
		socket.joinGroup(address);
		buf = new byte[BUFFER_SIZE];
		
		//keepReading();
	}
	/**
	 * Begin an endless loop, where we repeatedly get messages 
	 * and print them to the console. 
	 * For use in debugging.
	 * 
	 * @throws IOException
	 */
	public void keepReading() throws IOException {
		while (true) {
			System.out.println("Reading new line...");
			System.out.println(getNext());
		}
	}
	
	/**
	 * Blocks and waits for the next message.  
	 * 
	 * @return The sender, followed by the data sent, in string format
	 * @throws IOException
	 */
	public String getNext() throws IOException {
		pack = new DatagramPacket(buf, buf.length);
		socket.receive(pack);
		String s = new String(buf,0,pack.getLength());
		InetAddress sender = pack.getAddress();
		
		//TODO this should return a new object type containing "sender" and "message" strings
		return sender + " : "+ s;
	}
	
}
