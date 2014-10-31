import javax.swing.JFrame;

/* Placeholder for demonstrating Session MVC */
public class AssetsController {

    private AssetsView view;
    private AssetsModel model;

    public AssetsController() {
        this.view = new AssetsView();
        this.model = new AssetsModel();
    }

    public JFrame getFrame() {
        return view;
    }

}