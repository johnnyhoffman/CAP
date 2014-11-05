import java.awt.Component;

/* Placeholder for demonstrating Session MVC */
public class FormsController implements Controller {

    private FormsView view;
    private FormsModel model;

    public FormsController() {
        view = new FormsView();
        model = new FormsModel();
        for (Component c : model.getTabs()) {
            view.addTab(c);
        }
    }

    @Override
    public Component getViewComponent() {
        return view;
    }

}