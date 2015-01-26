package mvc;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class RadioMessageModel extends ScheduledPushModelAbstraction {

    private DataContainers.RadioMessage data;
    private Gson gson;
    int id;

    public RadioMessageModel(int id, String name) {
        this.id = id;
        database.sqlServer.InsertRADIOMESS("{}", id,
                -1, "DATE"); //XXX: Temp
        data = new DataContainers.RadioMessage(name);
        gson = new Gson();
        // for debugging revert to creation method below
        // gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /* methods for updating fields */

    public RadioMessageModel(int id, JsonObject json) {
        this.id = id;
        gson = new Gson();
        // for debugging revert to creation method below
        // gson = new GsonBuilder().setPrettyPrinting().create();
        jsonDeserialize(json);
    }

    public int getID() {
        return id;
    }

    public void updateHeaderMsgNum(String s) {
        data.header.msgNum = s;
        schedulePush();
    }

    public void updateHeaderPrecedence(String s) {
        data.header.precedence = s;
        schedulePush();
    }

    public void updateHeaderDtg(String s) {
        data.header.dtg = s;
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

    public String getHeaderPrecedence() {
        return data.header.precedence;
    }

    public String getHeaderDtg() {
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
        int missionNo = -1; // XXX: Need to get mission no
        String date = new SimpleDateFormat(GlobalConstants.DATE_FORMAT)
                .format(new Date()); // TODO: Eventually use date input in form
        return new DBPushParams(FormType.RM, json, id, missionNo, date);
    }

}
