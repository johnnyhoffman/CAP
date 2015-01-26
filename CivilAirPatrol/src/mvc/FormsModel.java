package mvc;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import database.sqlServer;

/* Placeholder for demonstrating Session MVC */
public class FormsModel {

    private List<IFormController> formControllers;
    private JsonParser jsonParser;

    FormsModel() {
        jsonParser = new JsonParser();
        formControllers = new ArrayList<IFormController>();
    }

    public boolean has(String name) {
        //TODO: Johnny working here. need to make FormComponent have a getName field for comparing.
        return false;
    }

    public List<Component> getTabs() {
        List<Component> tabs = new ArrayList<Component>();
        for (IFormController c : formControllers) {
            tabs.add(c.getViewComponent());
        }
        return tabs;
    }

    public CommLogController newComLog() {
        int id = sqlServer.RetrieveNextFormId();
        CommLogController comCont = new CommLogController(id, "Com Log " + id);
        formControllers.add(comCont);
        return comCont;
    }


    public SearchAndRescueController newSearchAndRescue() {
        int id = sqlServer.RetrieveNextFormId();
        SearchAndRescueController searchRescCont = new SearchAndRescueController(id, "Search and Rescue " + id);
        formControllers.add(searchRescCont);
        return searchRescCont;
    }

    public RadioMessageController newRadioMessage() {
        int id = sqlServer.RetrieveNextFormId();
        RadioMessageController radMesCont = new RadioMessageController(id, "Radio Message " + id);
        formControllers.add(radMesCont);
        return radMesCont;
    }

    public RadioMessageController radioMessageFromJson(DBPushParams pushParams) {
        RadioMessageController radMesCont = new RadioMessageController(pushParams.id ,(JsonObject) jsonParser.parse(pushParams.json));
        formControllers.add(radMesCont);
        return radMesCont;
    }

    public CommLogController comLogFromJson(DBPushParams pushParams) {
        CommLogController comCont = new CommLogController(pushParams.id ,(JsonObject) jsonParser.parse(pushParams.json));
        
        formControllers.add(comCont);
        return comCont;
    }

    public SearchAndRescueController searchAndRescueFromJson(DBPushParams pushParams) {
        SearchAndRescueController searchRescCont = new SearchAndRescueController(pushParams.id ,(JsonObject) jsonParser.parse(pushParams.json));
        formControllers.add(searchRescCont);
        return searchRescCont;
    }

    public void remove(IFormController controller) {
        formControllers.remove(controller);
    }

}