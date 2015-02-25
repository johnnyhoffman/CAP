package session;

import network.ClientListenerThread.OnIncomingDataListener;
import network.ClientSocket;


public class SessionModel {
    private ClientSocket sock;

    public SessionModel() {

        sock = ClientSocket.getInstance();
        sock.startListener();

    }
    
    public void setIncomingDataListener(OnIncomingDataListener onIncomingDataListener) {
        sock.setOnIncomingDataListener(onIncomingDataListener);
    }
}
