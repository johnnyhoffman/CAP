package mvc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RadioMessageModel extends ScheduledPushModelAbstraction{

    private DataContainers.RadioMessage data;
    private Gson gson;

    public RadioMessageModel(String name) {
        data = new DataContainers.RadioMessage(name);
        // gson = new Gson();
        // XXX: for debugging, revert to above creation method later
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /* methods for updating fields */

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

    public String jsonSerialize() {
        return gson.toJson(data);
    }

}
