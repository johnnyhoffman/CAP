/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applications;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import network.ClientConnection;
import network.LoginMessage;
import network.MessageType;
import network.NetworkMessage;
import network.UserType;

import common.AppPreferences;
import common.User;
import java.io.IOException;

import userInterface.ServerWindow;

/**
 *
 * @author Robert
 */
public class Server extends Thread {

    private ServerSocket socket;
    boolean run = true;
    public static List<ClientConnection> allClients;
    
    ServerWindow serverWindow;

    public Server(int p) {
    	//activateServer(p);
    	serverWindow = new ServerWindow(this,p);
    }
    
    public void activateServer(int p) {

    	allClients = new ArrayList<ClientConnection>();
        try {
            this.socket = new ServerSocket(p);
            this.socket.setReuseAddress(true);
            System.out.println("Server activated");
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        this.start();
    }

    // Function to check credentials against db
    private UserType validate(NetworkMessage attempt) {
        if (attempt.getType() == MessageType.LOGIN) {
            // TODO check against the DB information
            User user = database.sqlServer.SelectFromUsersWithUserName(((LoginMessage)attempt).getUser());
            if (user != null && user.getPass().equals(((LoginMessage)attempt).getPass())){
                return user.getType();
            }else{
                return UserType.NONE;
            }
            
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
                    output.writeObject(new LoginMessage(((LoginMessage)loginattempt).getUser(),"",type));
                    client = new ClientConnection(input, output, server,
                            ((LoginMessage) loginattempt).getUser(), type);

                    allClients.add(client);
                    client.start();
                    System.out.println("Accepted client connection.");
                } else {
                    // Did not have correct credentials, drop them
                    output.writeObject(new LoginMessage("Did not provide correct credentials.","",UserType.NONE)); // send a message back with login type none if there was an error
                    input.close();
                    output.close();
                    server.close();
                
            	}
            }
            socket.close();
        } catch (IOException e) {
            System.err.println(e.toString());
        } catch (ClassNotFoundException e) {
            System.err.println(e.toString());
        }
    }

    public static void main(String argv[]) {
        database.sqlServer.CreateDatabase();
        database.sqlServer.InsertUser("Robert", "testpass", "WRITER");
        Server server = new Server(AppPreferences.getPort());
    }

}
