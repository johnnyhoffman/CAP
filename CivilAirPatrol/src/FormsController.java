import java.awt.Component;

/* Placeholder for demonstrating Session MVC */
public class FormsController implements Controller {

    private FormsView view;
    private FormsModel model;

    public FormsController() {
        this.view = new FormsView();
        this.model = new FormsModel();
    }

    @Override
    public Component getViewComponent() {
        return view;
    }

}