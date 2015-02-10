/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Robert
 */
public class Server extends Thread{
    private ServerSocket socket;
    boolean run = true;
    
    private List<Socket> allClients;
    
    public Server(int p){
        
        allClients = new ArrayList<Socket>();
        try{
            this.socket = new ServerSocket(p);
            this.socket.setReuseAddress(true);
        }catch(Exception e){
            System.err.println(e.toString());
        }
    }
    public void run(){
        ObjectInputStream input;
        ObjectOutputStream output;
        NetworkMessage message;
        try{
            while(run){
                //allClients.add(sock);
                Socket server = socket.accept();
                //allClients.add(sock);
                System.out.println("Accepting client connection.");
                output = new ObjectOutputStream(server.getOutputStream());
                output.flush();
                input = new ObjectInputStream(server.getInputStream());
                //System.out.println(((NetworkMessage)input.readObject()).getMessage());
                

                System.out.println("Streams set up.");
                message = (NetworkMessage)input.readObject();
                System.out.println(message.getMessage());
                message = new NetworkMessage(MessageType.CHAT, "Welcome" );
                output.writeObject(message);

                System.out.println("Sent the message.");
                input.close();
                output.close();
                server.close();
                
            }
            socket.close();
        }catch(Exception e){
            System.err.println(e.toString());
        }
    }
    
    public static void main(String argv []){
        Server server = new Server(4444);
        server.start();
    }
}
