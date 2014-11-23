import java.awt.Component;

/* Placeholder for demonstrating Session MVC */
public class SearchAndRescueController implements Controller {

    private SearchAndRescueView view;
    private SearchAndRescueModel model;

    public SearchAndRescueController() {
        this.view = new SearchAndRescueView();
        this.model = new SearchAndRescueModel();
    }

    @Override
    public Component getViewComponent() {
        return view;
    }

    public void setName(String name) {
        view.setName(name);
    }

}