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
public class ErrorMessage extends NetworkMessage{
    
    private String message;
    
    public ErrorMessage(String m){
        super(MessageType.ERROR);
        this.message = m;
    }
    
    public String getMessage(){
        return this.message;
    }
}
