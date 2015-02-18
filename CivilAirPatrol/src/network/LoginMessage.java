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
public class LoginMessage extends NetworkMessage{
    private String user;
    private String pass;
    public LoginMessage(String user, String password){
        super(MessageType.LOGIN);
        this.user = user;
        this.pass = password;
    }
    
    public String getUser(){
        return this.user;
    }
    public String getPass(){
        return this.pass;
    }
}
