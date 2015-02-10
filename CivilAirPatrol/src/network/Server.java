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
    
    public static List<ClientConnection> allClients;
    
    public Server(int p){
        
        allClients = new ArrayList<ClientConnection>();
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
        ClientConnection client;
        try{
            while(run){
                //allClients.add(sock);
                Socket server = socket.accept();
                output = new ObjectOutputStream(server.getOutputStream());
                output.flush();
                input = new ObjectInputStream(server.getInputStream());
                client = new ClientConnection(input,output,server,"");
                allClients.add(client);
                client.start();
                System.out.println("Accepting client connection.");
                
                
                //System.out.println(((NetworkMessage)input.readObject()).getMessage());
                
            }
            socket.close();
        }catch(Exception e){
            System.err.println(e.toString());
        }
    }
    
    private void handleChatMessage(NetworkMessage message){
        
    }
    
    public static void main(String argv []){
        Server server = new Server(4444);
        server.start();
    }
}
