package mvc;

import java.awt.Component;

import javax.swing.JTabbedPane;

public class FormsView extends JTabbedPane {

    private static final long serialVersionUID = 6729721366331724456L;

    public FormsView() {

    }

    public void addTab(Component component) {

        // XXX: This didn't work but keeping it around until I find a better way
        // javax.swing.JScrollPane scroll = new
        // javax.swing.JScrollPane(component);
        // scroll.setName(component.getName());
        // add(scroll);
        add(component);
    }
}
