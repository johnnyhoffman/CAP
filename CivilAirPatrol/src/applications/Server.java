/**
 *  Copyright 2015 Dana Vold, Johnny Hoffman, Robert Wassenaar
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package applications;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import network.ClientConnection;
import network.ErrorMessage;
import network.LoginMessage;
import network.MessageType;
import network.NetworkMessage;
import network.UserType;
import security.BCrypt;
import userInterface.ServerWindow;

import common.AppPreferences;
import common.User;

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
        } catch (BindException e) {
            serverWindow.showErrorMessage("Cannot start server: "
                    + e.getMessage());
            System.exit(0);
        } catch (Exception e) {
            serverWindow.showErrorMessage("Unexpected fatal error: "
                    + e.getMessage());
            System.exit(0);
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

            boolean passMatch = BCrypt.checkpw(
                    ((LoginMessage) attempt).getPass(), user.getPass());
            if (user != null && passMatch) {
                for (ClientConnection cc : allClients) {
                    // TODO: Make sure user is not a duplicate and there is only
                    // one writer
                    if (user.getUser().equals(cc.getUserName())) {
                        return UserType.USERINUSE;
                    } else if ((user.getType() == UserType.WRITER)
                            && (cc.getUserType() == UserType.WRITER)) {
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
                // Did not have correct credentials, drop them
                String mess;
                switch (type) {
                case READER:
                    // Follow through to same code block as writer, so no break
                case WRITER:
                    // create the client connection
                    output.writeObject(new LoginMessage(
                            ((LoginMessage) loginattempt).getUser(), "", type));
                    client = new ClientConnection(input, output, server,
                            ((LoginMessage) loginattempt).getUser(), type);

                    allClients.add(client);
                    client.start();
                    System.out.println("Accepted client connection.");
                    
                    break;
                case WRITEINUSE:
                    mess = "Already a writer logged in.";
                    output.writeObject(new ErrorMessage(mess));
                    // send a message back with login
                    // type none if there was an error
                    input.close();
                    output.close();
                    server.close();
                    break;
                case USERINUSE:
                    mess = "User name already in use.";
                    output.writeObject(new ErrorMessage(mess));
                    // send a message back with login
                    // type none if there was an error
                    input.close();
                    output.close();
                    server.close();
                    break;
                case NONE:
                    mess = "Did not provide correct credentials.";
                    output.writeObject(new ErrorMessage(mess));
                    // send a message back with login
                    // type none if there was an error
                    input.close();
                    output.close();
                    server.close();
                    break;
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
        new Server(AppPreferences.getPort());
    }

}
