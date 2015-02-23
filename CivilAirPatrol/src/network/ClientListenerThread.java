/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robert
 */
public class ClientListenerThread extends Thread {

    private OnIncomingDataListener onIncomingDataListener;

    public interface OnIncomingDataListener {
        public void processNetworkMessage(NetworkMessage networkMessage);
    }

    private ObjectInputStream in;
    private boolean run = true;

    public ClientListenerThread(ObjectInputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        while (run) {
            recvMessage();
        }
    }

    private void recvMessage() {
        try {
            NetworkMessage message = (NetworkMessage) in.readObject();
            // Session instance will set an onIncoming message listener and
            // propagate the message to the correct place
            System.out.println("MESSAGE IN CLIENT LISTENER: " + message);
            if (onIncomingDataListener != null) {
                onIncomingDataListener.processNetworkMessage(message);
            }
        } catch (IOException ex) {
            this.run = false;
            TestClient.run = false;
            ex.printStackTrace();
            Logger.getLogger(ClientListenerThread.class.getName()).log(
                    Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            this.run = false;
            TestClient.run = false;
            ex.printStackTrace();
            Logger.getLogger(ClientListenerThread.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    public void setOnIncomingDataListener(
            OnIncomingDataListener onIncomingDataListener) {
        this.onIncomingDataListener = onIncomingDataListener;
    }
}
