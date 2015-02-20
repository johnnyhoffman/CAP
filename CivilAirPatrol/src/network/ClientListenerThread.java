/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import chat.ChatController;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robert
 */
public class ClientListenerThread extends Thread{
    private ObjectInputStream in;
    private boolean run = true;
    public ClientListenerThread(ObjectInputStream in){
        this.in = in;
    }
    @Override
    public void run(){
        while(run){
            recvMessage();
        }
    }
    
    private void recvMessage(){
        try {
            NetworkMessage message = (NetworkMessage)in.readObject();
            switch(message.getType()){
                case CHAT:
                    //TODO This is where we would need to handle the update of the chat GUI
                    //currently just outputs to STD OUT
                    //System.out.println(((ChatMessage)message).getMessage());
                    ChatController.writeChat(((ChatMessage)message).getUser() + " : " + ((ChatMessage)message).getMessage());
                    break;
                case GUI:
                    //TODO if a client recieves a GUI message, it will be to update a form they are viewing.
                    //This will not ever happen if they are logged in as a radio officer
                    break;
                case LOGIN:
                    //TODO this will be a confirmation message (hopefully), from the server 
                    //telling whether or not their connection attempt was successful
                    break;
                default:
                    break;
            }
            
        } catch (IOException ex) {
            this.run = false;
            TestClient.run = false;
            Logger.getLogger(ClientListenerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            this.run = false;
            TestClient.run = false;
            Logger.getLogger(ClientListenerThread.class.getName()).log(Level.SEVERE, null, ex);
		}
    }
}
