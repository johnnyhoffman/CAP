import java.awt.Component;

/* Placeholder for demonstrating Session MVC */
public class RadioMessageController implements Controller {

    private RadioMessageView view;
    private RadioMessageModel model;

    public RadioMessageController() {
        this.view = new RadioMessageView();
        this.model = new RadioMessageModel();
    }

    @Override
    public Component getViewComponent() {
        return view;
    }

    public void setName(String name) {
        view.setName(name);
    }

}