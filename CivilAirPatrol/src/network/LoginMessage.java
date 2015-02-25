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
    private UserType userType; 
    public LoginMessage(String user, String password, UserType userType){
        super(MessageType.LOGIN);
        this.user = user;
        this.pass = password;
        this.userType = userType;
    }
    
    public String getUser(){
        return this.user;
    }
    public String getPass(){
        return this.pass;
    }
    public UserType getUserType(){
        return this.userType;
    }
}
