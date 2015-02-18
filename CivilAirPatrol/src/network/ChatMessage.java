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
    
    public ChatMessage(String message){
        super(MessageType.CHAT);
        this.message = message;
    }
    
    public String getMessage(){
        return this.message;
    }
}
