/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.Serializable;

/**
 *
 * @author Robert
 */
public class NetworkMessage implements Serializable {
    protected static final long serialVersionUID = 1112122200L;
    
    private MessageType type;
    private String message;
    
    public NetworkMessage(MessageType t, String m){
        this.type = t;
        this.message = m;
    }
    
    public MessageType getType(){
        return this.type;
    }
    
    public String getMessage(){
        return this.message;
    }
}
