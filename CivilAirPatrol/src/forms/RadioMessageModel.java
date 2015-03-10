/**
 *  Copyright 2015 Dana Vold, Johnny Hoffman, Robert Wassenaar
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package forms;

import java.io.IOException;

import network.ClientSocket;
import network.GuiMessage;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import common.DBPushParams;
import common.DataContainers;

public class RadioMessageModel extends ScheduledPushModelAbstraction {

    private DataContainers.RadioMessage data;
    private Gson gson;
    int id;

    /* methods for updating fields */

    public RadioMessageModel(int id, JsonObject json) {
        this.id = id;
        gson = new Gson();
        jsonDeserialize(json);
    }

    public RadioMessageModel(int id, String name, String missionNo, long date) {
        this.id = id;
        data = new DataContainers.RadioMessage(name);
        data.header.missionNo = missionNo;
        data.header.dtg = date;
        gson = new Gson();
        try {
            ClientSocket.getInstance().output.writeObject(new GuiMessage(
                    new DBPushParams(FormType.RM, gson.toJson(data), id,
                            missionNo, date)));
        } catch (IOException e) {
            onConnectionError();
        }
    }

    public int getID() {
        return id;
    }

    public void updateHeaderMissionNo(String s) {
        data.header.missionNo = s;
        schedulePush();
    }

    public void updateHeaderMsgNum(String s) {
        data.header.msgNum = s;
        schedulePush();
    }

    public void updateHeaderPrecedence(String s) {
        data.header.precedence = s;
        schedulePush();
    }

    public void updateHeaderDtg(long l) {
        data.header.dtg = l;
        schedulePush();
    }

    public void updateHeaderFrom(String s) {
        data.header.from = s;
        schedulePush();
    }

    public void updateHeaderTo(String s) {
        data.header.to = s;
        schedulePush();
    }

    public void updateHeaderInfo(String s) {
        data.header.info = s;
        schedulePush();
    }

    public void updateHeaderSubj(String s) {
        data.header.subj = s;
        schedulePush();
    }

    public void updateHeaderGroupCnt(String s) {
        data.header.groupCnt = s;
        schedulePush();
    }

    public void updateMessage(String s) {
        data.message = s;
        schedulePush();
    }

    public void updateMessageRecievedFrom(String s) {
        data.messageRecieved.from = s;
        schedulePush();
    }

    public void updateMessageRecievedDtg(String s) {
        data.messageRecieved.dtg = s;
        schedulePush();
    }

    public void updateMessageRecievedRecievingOperatorInitials(String s) {
        data.messageRecieved.RecievingOperatorInitials = s;
        schedulePush();
    }

    public void updateMessageSentTo(String s) {
        data.messageSent.to = s;
        schedulePush();
    }

    public void updateMessageSentDtg(String s) {
        data.messageSent.dtg = s;
        schedulePush();
    }

    public void updateMessageSentSendingOperatorInitials(String s) {
        data.messageSent.sendingOperatorInitials = s;
        schedulePush();
    }

    public String getHeaderMsgNum() {
        return data.header.msgNum;
    }

    public String getHeaderMissionNo() {
        return data.header.missionNo;
    }

    public String getHeaderPrecedence() {
        return data.header.precedence;
    }

    public long getHeaderDtg() {
        return data.header.dtg;
    }

    public String getHeaderFrom() {
        return data.header.from;
    }

    public String getHeaderTo() {
        return data.header.to;
    }

    public String getHeaderInfo() {
        return data.header.info;
    }

    public String getHeaderSubj() {
        return data.header.subj;
    }

    public String getHeaderGroupCnt() {
        return data.header.groupCnt;
    }

    public String getMessage() {
        return data.message;
    }

    public String getMessageRecievedFrom() {
        return data.messageRecieved.from;
    }

    public String getMessageRecievedDtg() {
        return data.messageRecieved.dtg;
    }

    public String getMessageRecievedRecievingOperatorInitials() {
        return data.messageRecieved.RecievingOperatorInitials;
    }

    public String getMessageSentTo() {
        return data.messageSent.to;
    }

    public String getMessageSentDtg() {
        return data.messageSent.dtg;
    }

    public String getMessageSentSendingOperatorInitials() {
        return data.messageSent.sendingOperatorInitials;
    }

    public void jsonDeserialize(JsonObject json) {
        data = gson.fromJson(json, DataContainers.RadioMessage.class);
        modelLoaded();
    }

    @Override
    public DBPushParams prepareForPush() {
        String json = gson.toJson(data);
        return new DBPushParams(FormType.RM, json, id, data.header.missionNo,
                data.header.dtg);
    }

}
