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

import security.BCrypt;

import common.AppPreferences;
import common.User;
import java.io.IOException;
import network.ErrorMessage;

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
        // activateServer(p);
        serverWindow = new ServerWindow(this, p);
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

    public void quit() {
        run = false;
    }
    
    

    // Function to check credentials against db
    private UserType validate(NetworkMessage attempt) {
        if (attempt.getType() == MessageType.LOGIN) {
            // TODO check against the DB information
            User user = database.sqlServer
                    .SelectFromUsersWithUserName(((LoginMessage) attempt)
                            .getUser());
            if (user == null) {
            	return UserType.NONE;
            }
            
            //TODO Dana here.  This next line consistently crashes for me, even when inputting 2 strings,
            // so I've commented it out.
            //boolean passMatch = BCrypt.checkpw(((LoginMessage) attempt).getPass(), user.getPass());
            
            // the following line is a temporary replacement for the previous line
            boolean passMatch = user.getPass().equals(((LoginMessage) attempt).getPass());
            if (user != null && passMatch) {
                for (ClientConnection cc : allClients) {
                    // TODO: Make sure user is not a duplicate and there is only
                    // one writer
                    if (user.getUser().equals(cc.getUserName())){
                        return UserType.USERINUSE;
                    }else if((user.getType() == UserType.WRITER) && 
                            (cc.getUserType() == UserType.WRITER)){
                        return UserType.WRITEINUSE;
                    }
                }
                return user.getType();
            } else {
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
                type = validate(loginattempt);
                if (type == UserType.READER || type == UserType.WRITER) {
                    // create the client connection
                    output.writeObject(new LoginMessage(
                            ((LoginMessage) loginattempt).getUser(), "", type));
                    client = new ClientConnection(input, output, server,
                            ((LoginMessage) loginattempt).getUser(), type);

                    allClients.add(client);
                    client.start();
                    System.out.println("Accepted client connection.");
                } else {
                    // Did not have correct credentials, drop them
                    switch(type){
                        case WRITEINUSE:
                            output.writeObject(new ErrorMessage(
                                "Already a writer logged in."));
                            break;
                        case USERINUSE:
                            output.writeObject(new ErrorMessage(
                                "User name already in use."));
                            break;
                        case NONE:
                        	output.writeObject(new ErrorMessage(
                                    "Did not provide correct credentials."));
                            //output.writeObject(new LoginMessage(
                            //    "Did not provide correct credentials.", "",
                            //    UserType.NONE));
                            break;
                    }
                     // send a message back with login
                                             // type none if there was an error
                    input.close();
                    output.close();
                    server.close();

                }
            }
            socket.close();
        } catch (IOException e) {
            System.err.println(e.toString());
        } catch (ClassNotFoundException e) {
        }
        System.exit(0);
    }

    public static void main(String argv[]) {
        database.sqlServer.CreateDatabase();
        database.sqlServer.InsertUser("Robert",
                BCrypt.hashpw("testpass", BCrypt.gensalt()), "WRITER");
        database.sqlServer.InsertUser("Johnny",
                BCrypt.hashpw("testpass", BCrypt.gensalt()), "WRITER");
        database.sqlServer.InsertUser("Dana",
                BCrypt.hashpw("testpass", BCrypt.gensalt()), "WRITER");
        database.sqlServer.InsertUser("Reader",
                BCrypt.hashpw("passs", BCrypt.gensalt()), "READER");
        database.sqlServer.InsertUser("Reader1",
                BCrypt.hashpw("passs", BCrypt.gensalt()), "READER");
        database.sqlServer.InsertUser("Reader2",
                BCrypt.hashpw("passs", BCrypt.gensalt()), "READER");
        new Server(AppPreferences.getPort());
    }

}
