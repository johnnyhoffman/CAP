/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import network.UserType;

/**
 *
 * @author Robert
 */
public class User {
    private String userName;
    private String password;
    private UserType type;
    
    public User(String user, String password, UserType userType){
        this.password = password;
        this.userName = user;
        this.type = userType;
    }
    public void setType(UserType type){
        this.type = type;
    }
    public String getUser(){
        return this.userName;
    }
    public String getPass(){
        return this.password;
    }
    public UserType getType(){
        return this.type;
    }
}
