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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            if (onIncomingDataListener != null) {
                onIncomingDataListener.processNetworkMessage(message);
            }
        } catch (IOException ex) {
            this.run = false;
            ex.printStackTrace();
            Logger.getLogger(ClientListenerThread.class.getName()).log(
                    Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            this.run = false;
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
