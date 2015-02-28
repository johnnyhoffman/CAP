/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import common.DBPushParams;
import java.util.List;

/**
 * 
 * @author Robert
 */
public class ResultMessage extends NetworkMessage {
    private static final long serialVersionUID = -7754252435400212783L;
    private List<DBPushParams> results;
    boolean hasJSON;

    public ResultMessage(List<DBPushParams> results, boolean hasJSON) {
        super(MessageType.RESULT);
        this.results = results;
        this.hasJSON = hasJSON;
    };

    public List<DBPushParams> getResults() {
        return this.results;
    }
}
