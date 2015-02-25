/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applications;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import userInterface.ServerWindow;

import network.ClientConnection;
import network.LoginMessage;
import network.MessageType;
import network.NetworkMessage;
import network.UserType;

import common.AppPreferences;

/**
 *
 * @author Robert
 */
public class Server extends Thread {

    private ServerSocket socket;
    private boolean run = true;
    private boolean active;
    public static List<ClientConnection> allClients;
    
    public ServerWindow UI;
    
    public int socketNo;

    public Server(int p) {

    	socketNo = p;
    	UI = new ServerWindow(this,socketNo);

        allClients = new ArrayList<ClientConnection>();
        active = false;
        this.start();
    }
    
    public void startServer(int currentPort) {
    	System.out.println(currentPort);
        try {
            this.socket = new ServerSocket(socketNo);
            this.socket.setReuseAddress(true);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        active = true;
        UI.setActive(true);
        System.out.println("Server has been activated");
    }

    public void stopServer() {
    	try {
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	this.active = false;
        UI.setActive(false);
        System.out.println("Server has been deactivated");
    	
    }
    
    
    public boolean isRunning() {
    	return active;
    }

    // Function to check credentials against db
    private UserType validate(NetworkMessage attempt) {
        if (attempt.getType() == MessageType.LOGIN) {
            // TODO check against the DB information
            return UserType.READER;
        }
        return UserType.NONE;
    }

    // What will continue to run, handle new connections and spawn threads to
    // handle the new connection
    @Override
    public void run() {
        ObjectInputStream input;
        ObjectOutputStream output;
        ClientConnection client;
        UserType type;
        try {
            while (run) {
            	if (active) {
	                Socket server = socket.accept();
	                output = new ObjectOutputStream(server.getOutputStream());
	                output.flush();
	                input = new ObjectInputStream(server.getInputStream());
	
	                // TODO before addin the client to the list of accepted
	                // connection need to validate them
	                // They should have sent a login message with the connection
	                // attempt.
	                NetworkMessage loginattempt = (NetworkMessage) input
	                        .readObject();
	
	                if ((type = validate(loginattempt)) != UserType.NONE) {
	                    // create the client connection
	
	                    client = new ClientConnection(input, output, server,
	                            ((LoginMessage) loginattempt).getUser(), type);
	                    allClients.add(client);
	                    client.start();
	                    System.out.println("Accepted client connection.");
	                } else {
	                    // Did not have correct credentials, drop them
	                    input.close();
	                    output.close();
	                    server.close();
	                }
            	}

            }
            socket.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public static void main(String argv[]) {
        database.sqlServer.CreateDatabase();
        Server server = new Server(AppPreferences.getPort());
    }

}
