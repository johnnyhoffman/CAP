import java.awt.Component;

/* Placeholder for demonstrating Session MVC */
public class LocatingDataController implements Controller {

    private LocatingDataView view;
    private LocatingDataModel model;

    public LocatingDataController() {
        this.view = new LocatingDataView();
        this.model = new LocatingDataModel();
    }

    @Override
    public Component getViewComponent() {
        return view;
    }

    public void setName(String name) {
        view.setName(name);
    }
}