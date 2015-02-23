package session;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import common.GlobalConstants;
import network.ClientListenerThread.OnIncomingDataListener;
import network.ClientSocket;
import network.LoginMessage;
import network.NetworkMessage;

public class SessionModel {
    private ClientSocket sock;

    public SessionModel() {

        sock = ClientSocket.getInstance();
        sock.startListener();
        try {
            ClientSocket.getInstance().output.writeObject(new LoginMessage(
                    GlobalConstants.USERNAME, "password"));
        } catch (IOException ex) {
            Logger.getLogger(SessionController.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
    
    public void setIncomingDataListener(OnIncomingDataListener onIncomingDataListener) {
        sock.setOnIncomingDataListener(onIncomingDataListener);
    }
}
