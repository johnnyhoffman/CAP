package mvc;

import java.awt.Component;

/* Placeholder for demonstrating Session MVC */
public class AssetsController implements Controller {

    private AssetsView view;
    private AssetsModel model;

    public AssetsController() {
        this.view = new AssetsView();
        this.model = new AssetsModel();
    }

    @Override
    public Component getViewComponent() {
        return view;
    }

}