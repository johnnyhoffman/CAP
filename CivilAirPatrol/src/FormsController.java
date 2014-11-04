import java.awt.Component;

/* Placeholder for demonstrating Session MVC */
public class FormsController implements Controller {

    private FormsView view;
    private FormsModel model;

    public FormsController() {
        view = new FormsView();
        model = new FormsModel();
        view.addTab(new LocatingDataView());  // TODO: These forms should have a controller. Will have this FormsController reference the controllers for the objects, from which they will get the views
        view.addTab(new CommLogView());       // TODO: These forms should have a controller. Will have this FormsController reference the controllers for the objects, from which they will get the views
        view.addTab(new RadioMessageView());  // TODO: These forms should have a controller. Will have this FormsController reference the controllers for the objects, from which they will get the views
    }

    @Override
    public Component getViewComponent() {
        return view;
    }

}