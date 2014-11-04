import java.awt.Component;

import javax.swing.JTabbedPane;

public class FormsView extends JTabbedPane {

    private static final long serialVersionUID = 6729721366331724456L;

    public FormsView() {
        
    }

    public void addTab(Component component) {
        add(component);
    }
}
