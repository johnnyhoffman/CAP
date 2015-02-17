package assets;

import java.awt.Component;

import mvcCommon.IController;

/* Placeholder for demonstrating Session MVC */
public class AssetsController implements IController {

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

    public static void onClose() {
    }

}