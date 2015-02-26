/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import network.ClientListenerThread.OnIncomingDataListener;
import common.AppPreferences;

/**
 *
 * @author Robert
 */

// This is a singleton class, only one instance allowed
public class ClientSocket {

    private Socket socket;
    public ObjectInputStream input;
    public ObjectOutputStream output;
    private ClientListenerThread listener;

    private ClientSocket() {
        try {
            this.socket = new Socket(AppPreferences.getIP(), AppPreferences.getPort());
            this.output = new ObjectOutputStream(this.socket.getOutputStream());
            this.output.flush();
            this.input = new ObjectInputStream(this.socket.getInputStream());
            this.listener = new ClientListenerThread(input);
        } catch (Exception e) {
            System.err.println(e.toString());
            JOptionPane.showMessageDialog(null, "Cannot connect to Server. This may be because the Server\n"
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

    public void setOnIncomingDataListener(OnIncomingDataListener onIncomingDataListener) {
        listener.setOnIncomingDataListener(onIncomingDataListener);
    }
}
