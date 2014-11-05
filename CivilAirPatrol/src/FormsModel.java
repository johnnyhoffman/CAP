import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

/* Placeholder for demonstrating Session MVC */
public class FormsModel {

    private List<Controller> formControllers;
    
    FormsModel(){
        formControllers = new ArrayList<Controller>();
        
        formControllers.add(new CommLogController());
        formControllers.add(new RadioMessageController());
        formControllers.add(new LocatingDataController());
    }

    public List<Component> getTabs() {
        List<Component> tabs = new ArrayList<Component>();
        for (Controller c: formControllers) {
            tabs.add(c.getViewComponent());
        }
        return tabs;
    }

}
