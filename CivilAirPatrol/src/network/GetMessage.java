/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

/**
 *  GetMessage will be used to send a request to the server for data
 * @author Robert
 */
public class GetMessage extends NetworkMessage{
    
    private static final long serialVersionUID = -3641656218757595654L;
    private DBRequest request;
   
    public GetMessage(DBRequest request){
        super(MessageType.GET);
        this.request = request;
    }
    
    public DBRequest getRequest(){
        return this.request;
    }
}
