import java.awt.Component;

/* Placeholder for demonstrating Session MVC */
public class CommLogController implements Controller {

    private CommLogView view;
    private CommLogModel model;

    public CommLogController() {
        this.view = new CommLogView();
        this.model = new CommLogModel();
    }

    @Override
    public Component getViewComponent() {
        return view;
    }

}