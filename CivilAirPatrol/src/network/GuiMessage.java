/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import common.DBPushParams;

/**
 *
 * @author Robert
 */
public class GuiMessage extends NetworkMessage{
    
    private DBPushParams params;
    public GuiMessage(DBPushParams params){
        super(MessageType.GUI);
        this.params = params;
    }
    
    public DBPushParams getParams(){
        return params;
    }
}
