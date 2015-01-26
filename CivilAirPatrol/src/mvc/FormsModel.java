package mvc;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

/* Placeholder for demonstrating Session MVC */
public class FormsModel {

    private List<IFormController> formControllers;
    private int comLogCount = 0;
    private int searchRescCount = 0;
    private int radioMessageCount = 0;

    FormsModel() {
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
        comLogCount++;
        CommLogController comCont = new CommLogController("Com Log " + comLogCount);
        formControllers.add(comCont);
        return comCont;
    }


    public SearchAndRescueController newSearchAndRescue() {
        searchRescCount++;
        SearchAndRescueController searchRescCont = new SearchAndRescueController("Search and Rescue " + searchRescCount);
        formControllers.add(searchRescCont);
        return searchRescCont;
    }

    public RadioMessageController newRadioMessage() {
        radioMessageCount++;
        RadioMessageController radMesCont = new RadioMessageController("Radio Message " + radioMessageCount);
        formControllers.add(radMesCont);
        return radMesCont;
    }

    public RadioMessageController radioMessageFromJson(JsonObject json) {
        RadioMessageController radMesCont = new RadioMessageController(json);
        formControllers.add(radMesCont);
        return radMesCont;
    }

    public CommLogController comLogFromJson(JsonObject json) {
        CommLogController comCont = new CommLogController(json);
        formControllers.add(comCont);
        return comCont;
    }

    public SearchAndRescueController searchAndRescueFromJson(JsonObject json) {
        SearchAndRescueController searchRescCont = new SearchAndRescueController(json);
        formControllers.add(searchRescCont);
        return searchRescCont;
    }

    public void remove(IFormController controller) {
        formControllers.remove(controller);
    }

}