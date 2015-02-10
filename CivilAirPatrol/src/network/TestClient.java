/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Robert
 */
public class TestClient {
    static String address = "localhost";
    static int port = 4444;
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    
    public TestClient(String a, int p) throws IOException{
        this.socket = new Socket(a, p);
        this.output = new ObjectOutputStream(this.socket.getOutputStream());
        this.output.flush();
        this.input = new ObjectInputStream(this.socket.getInputStream());
        
    }
    
    public void sendMessage(NetworkMessage message){
        
        try {
            this.output.writeObject(message);
        } catch (IOException ex) {
            Logger.getLogger(TestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void recMessage(){
        try{
            NetworkMessage netMess = (NetworkMessage)this.input.readObject();
            System.out.println(netMess.getMessage());
        }catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String argv []){
        try {
            TestClient client = new TestClient(address, port);
            client.sendMessage(new NetworkMessage(MessageType.CHAT, "This is only a test", null));
            client.recMessage();
        } catch (IOException ex) {
            Logger.getLogger(TestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
