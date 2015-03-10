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

package network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import network.ClientListenerThread.OnIncomingDataListener;
import common.AppPreferences;

// This is a singleton class, only one instance allowed
public class ClientSocket {

    private Socket socket;
    public ObjectInputStream input;
    public ObjectOutputStream output;
    private ClientListenerThread listener;

    private ClientSocket() {
        try {
            this.socket = new Socket(AppPreferences.getIP(),
                    AppPreferences.getPort());
            this.output = new ObjectOutputStream(this.socket.getOutputStream());
            this.output.flush();
            this.input = new ObjectInputStream(this.socket.getInputStream());
            this.listener = new ClientListenerThread(input);
        } catch (Exception e) {
            System.err.println(e.toString());
            JOptionPane
                    .showMessageDialog(
                            null,
                            "Cannot connect to Server. This may be because the Server\n"
                                    + "application is not running, the IP Address or Port are incorrect,\n"
                                    + "or the network between the Client and Server applications is down.");

        }
    }

    public void closeSocket() {
        try {
            input.close();
            output.close();
            socket.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public void startListener() {
        listener.start();
    }

    public static ClientSocket getInstance() {
        return ClientSocketHolder.INSTANCE;
    }

    private static class ClientSocketHolder {
        private static final ClientSocket INSTANCE = new ClientSocket();
    }

    public void setOnIncomingDataListener(
            OnIncomingDataListener onIncomingDataListener) {
        listener.setOnIncomingDataListener(onIncomingDataListener);
    }
}
