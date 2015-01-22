package mvc;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

/* Placeholder for demonstrating Session MVC */
public class FormsModel {

    private List<Controller> formControllers;
    private int comLogCount = 0;
    private int searchRescCount = 0;
    private int radioMessageCount = 0;

    FormsModel() {
        formControllers = new ArrayList<Controller>();
    }

    public List<Component> getTabs() {
        List<Component> tabs = new ArrayList<Component>();
        for (Controller c : formControllers) {
            tabs.add(c.getViewComponent());
        }
        return tabs;
    }

    public Component newComLog() {
        comLogCount++;
        CommLogController comCont = new CommLogController("Com Log " + comLogCount);
        formControllers.add(comCont);
        return comCont.getViewComponent();
    }


    public Component newSearchAndRescue() {
        searchRescCount++;
        SearchAndRescueController searchRescCont = new SearchAndRescueController("Search and Rescue " + searchRescCount);
        formControllers.add(searchRescCont);
        return searchRescCont.getViewComponent();
    }

    public Component newRadioMessage() {
        radioMessageCount++;
        RadioMessageController radMesCont = new RadioMessageController("Radio Message " + radioMessageCount);
        formControllers.add(radMesCont);
        return radMesCont.getViewComponent();
    }

}