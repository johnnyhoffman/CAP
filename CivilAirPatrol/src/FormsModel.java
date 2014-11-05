import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

/* Placeholder for demonstrating Session MVC */
public class FormsModel {

    private List<Controller> formControllers;

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
        return comCont.getViewComponent();
    }

    public Component newLocatingData() {
        LocatingDataController locDataCont = new LocatingDataController();
        formControllers.add(locDataCont);
        return locDataCont.getViewComponent();
    }

    public Component newRadioMessage() {
        RadioMessageController radMesCont = new RadioMessageController();
        formControllers.add(radMesCont);
        return radMesCont.getViewComponent();
    }

}
