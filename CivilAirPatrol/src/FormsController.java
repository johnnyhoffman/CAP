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

    public void newComLog() {
        view.addTab(model.newComLog());
        view.setSelectedIndex(view.getTabCount() - 1);
    }

    public void newLocatingData() {
        view.addTab(model.newLocatingData());
        view.setSelectedIndex(view.getTabCount() - 1);
    }

    public void newRadioMessage() {
        view.addTab(model.newRadioMessage());
        view.setSelectedIndex(view.getTabCount() - 1);
    }
}