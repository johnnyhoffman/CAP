/* Placeholder for demonstrating Session MVC */
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FormsView extends JFrame {
    private static final long serialVersionUID = -6570963467593221692L;

    FormsView() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);
        this.add(new JLabel("THIZ IZ FORMZ"));
    }

}