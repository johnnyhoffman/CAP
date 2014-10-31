import javax.swing.JFrame;

/* Placeholder for demonstrating Session MVC */
public class FormsController {

    private FormsView view;
    private FormsModel model;

    public FormsController() {
        this.view = new FormsView();
        this.model = new FormsModel();
    }

    public JFrame getFrame() {
        return view;
    }

}