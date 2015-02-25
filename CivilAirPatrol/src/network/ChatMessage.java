/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

/**
 *
 * @author Robert
 */
public class ChatMessage extends NetworkMessage{
    
    private String message;
    private String user;
    
    public ChatMessage(String message, String user){
        super(MessageType.CHAT);
        this.message = message;
        this.user = user;
    }
    
    public String getMessage(){
        return this.message;
    }
    public String getUser(){
        return this.user;
    }
}
