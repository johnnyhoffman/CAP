import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

/* Placeholder for demonstrating Session MVC */
public class FormsModel {

    private List<Controller> formControllers;
    private int comLogCount = 0;
    private int locatingDataCount = 0;
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
        CommLogController comCont = new CommLogController();
        formControllers.add(comCont);
        comLogCount++;
        comCont.setName("Com Log " + comLogCount);
        return comCont.getViewComponent();
    }

    public Component newLocatingData() {
        locatingDataCount++;
        LocatingDataController locDataCont = new LocatingDataController("Locating Data " + locatingDataCount);
        formControllers.add(locDataCont);
        return locDataCont.getViewComponent();
    }

    public Component newSearchAndRescue() {
        SearchAndRescueController searchRescCont = new SearchAndRescueController();
        formControllers.add(searchRescCont);
        searchRescCount++;
        searchRescCont.setName("Search and Rescue " + searchRescCount);
        return searchRescCont.getViewComponent();
    }

    public Component newRadioMessage() {
        RadioMessageController radMesCont = new RadioMessageController();
        formControllers.add(radMesCont);
        radioMessageCount++;
        radMesCont.setName("Radio Message " + radioMessageCount);
        return radMesCont.getViewComponent();
    }

}