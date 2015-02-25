package forms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import network.ClientSocket;
import network.NewFormMessage;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import common.DBPushParams;

import forms.ScheduledPushModelAbstraction.FormType;

/* Placeholder for demonstrating Session MVC */
public class FormsModel {

    private List<IFormController> formControllers;
    private JsonParser jsonParser;

    FormsModel() {
        jsonParser = new JsonParser();
        formControllers = new ArrayList<IFormController>();
    }

    public boolean has(int id) {
        for (IFormController c : formControllers) {
            if (c.getID() == id) {
                return true;
            }
        }
        return false;
    }

    public IFormController get(int id) {
        for (IFormController c : formControllers) {
            if (c.getID() == id) {
                return c;
            }
        }
        return null;
    }

    public List<FormComponent> getTabs() {
        List<FormComponent> tabs = new ArrayList<FormComponent>();
        for (IFormController c : formControllers) {
            tabs.add(c.getViewComponent());
        }
        return tabs;
    }

    public void newComLog(String missionNo, long date) {
        try {
            ClientSocket.getInstance().output.writeObject(new NewFormMessage(
                    FormType.CL, missionNo, date));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void newSearchAndRescue(String missionNo, long date) {
        try {
            ClientSocket.getInstance().output.writeObject(new NewFormMessage(
                    FormType.SAR, missionNo, date));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void newRadioMessage(String missionNo, long date) {
        try {
            ClientSocket.getInstance().output.writeObject(new NewFormMessage(
                    FormType.RM, missionNo, date));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public RadioMessageController radioMessageFromJson(DBPushParams pushParams) {
        RadioMessageController radMesCont = new RadioMessageController(
                pushParams.id, (JsonObject) jsonParser.parse(pushParams.json));
        formControllers.add(radMesCont);
        return radMesCont;
    }

    public CommLogController comLogFromJson(DBPushParams pushParams) {
        CommLogController comCont = new CommLogController(pushParams.id,
                (JsonObject) jsonParser.parse(pushParams.json));

        formControllers.add(comCont);
        return comCont;
    }

    public SearchAndRescueController searchAndRescueFromJson(
            DBPushParams pushParams) {
        SearchAndRescueController searchRescCont = new SearchAndRescueController(
                pushParams.id, (JsonObject) jsonParser.parse(pushParams.json));
        formControllers.add(searchRescCont);
        return searchRescCont;
    }

    public void remove(IFormController controller) {
        formControllers.remove(controller);
    }

}