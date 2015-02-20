/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import common.GlobalConstants;
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
    static int port = GlobalConstants.DEFAULT_PORT;
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private ClientListenerThread listener;
    public static boolean run = true;
    
    public TestClient(String a, int p) throws IOException{
        this.socket = new Socket(a, p);
        this.output = new ObjectOutputStream(this.socket.getOutputStream());
        this.output.flush();
        this.input = new ObjectInputStream(this.socket.getInputStream());
        this.listener = new ClientListenerThread(input);
    }
    
    public void startListener(){
        this.listener.start();
    }
    public void sendMessage(NetworkMessage message){
        
        try {
            this.output.writeObject(message);
        } catch (IOException ex) {
            Logger.getLogger(TestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public static void main(String argv []){
        try {
            TestClient client = new TestClient(address, port);
            client.startListener();
            client.sendMessage(new LoginMessage("Robert", "password"));
            client.sendMessage(new ChatMessage("This is only a test", "USER"));
            int count = 1;
            while(run){
                try {
                    //SIT AND WAIT
                    Thread.sleep(2000);
                    client.sendMessage(new ChatMessage("This is only a test " + count++, "USER"));
                } catch (InterruptedException ex) {
                    Logger.getLogger(TestClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //client.socket.close();
        } catch (IOException ex) {
            Logger.getLogger(TestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
