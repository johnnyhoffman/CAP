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
public class GuiMessage extends NetworkMessage {
    private static final long serialVersionUID = 6246753614374221315L;
    private DBPushParams params;
    private boolean isUpdate;

    public GuiMessage(DBPushParams params) {
        super(MessageType.GUI);
        isUpdate = false;
        this.params = params;
    }

    public DBPushParams getParams() {
        return params;
    }

    public void setIsUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public boolean getIsUpdate() {
        return isUpdate;
    }
}
