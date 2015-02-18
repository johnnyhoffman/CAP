/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import common.DBPushParams;

/**
 *
 * @author Robert
 */
public class ClientConnection extends Thread {
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket socket;
    private String user;
    
    public ClientConnection(ObjectInputStream in, ObjectOutputStream out, Socket socket, String user){
        this.input = in;
        this.output = out;
        this.socket = socket;
        this.user = user;
    }
    public ObjectInputStream getInputStream(){
        return this.input;
    }
    public ObjectOutputStream getOutputStream(){
        return this.output;
    }
    public String getUserName(){
        return this.user;
    }
    public Socket getSocket(){
        return this.socket;
    }
    public boolean closeConnection(){
        try{
            this.input.close();
            this.output.close();
            this.socket.close();
            Server.allClients.remove(this);
            return true;
        }catch(Exception e){
            System.err.println(e.toString());
            return false;
        }
    }
    @Override
    public void run(){
        //TODO this will be where i handle the input coming from the clients
        NetworkMessage message;
        boolean run = true;
        while (run){
            try{
                message = (NetworkMessage)this.input.readObject();
                switch(message.getType()){
                    case CHAT:
                        handleChat(message);
                        break;
                    case GUI:
                        handleGuiPush(message);
                        break;
                    case LOGIN:
                        handleLogin(message);
                        break;
                    default:
                        break;
                }
            }catch(IOException e){
                closeConnection();
                System.err.println(e.toString());
                return;
            }catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private void handleChat(NetworkMessage message){
        //TODO echo the message to all authenticated clients....
        for(ClientConnection c : Server.allClients){
            try {
                System.out.println("sending message to " + c.getUserName()); //For debug purposes
                c.output.writeObject(message);
            } catch (IOException ex) {
                Logger.getLogger(ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    private void handleGuiPush(NetworkMessage message){
        //TODO echo the message to all non-radio officer connections...and update db
        //will start with just pushing to the db
        DBPushParams pushParams = ((GuiMessage)message).getParams();
        System.out.println(pushParams.json + "\n" + pushParams.id + "\n"
                + pushParams.missionNo + "\n" + pushParams.date);

        switch (pushParams.type) {
        case CL:
            database.sqlServer.UpdateCommLog(pushParams.json, pushParams.id,
                    pushParams.missionNo, pushParams.date);
            break;
        case RM:
            database.sqlServer.UpdateRADMESS(pushParams.json, pushParams.id,
                    pushParams.missionNo, pushParams.date);
            break;
        case SAR:
            database.sqlServer.UpdateSAR(pushParams.json, pushParams.id,
                    pushParams.missionNo, pushParams.date);
            break;
        }
    }
    private void handleLogin(NetworkMessage message){
        //TODO this should actually never happen, if it does client is doing something strange..
        //decided to make login the first message and is required to be validated before starting clientConnection
    }
}
