import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class SessionView extends JFrame {

    private static final long serialVersionUID = 9131304647063274186L;
    private JSplitPane hSplitPane;

    SessionView() {
        hSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        this.add(hSplitPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);

    }

    public void setFormsComponent(Component viewComponent) {
        hSplitPane.setLeftComponent(viewComponent);
    }

    public void setAssetsComponent(Component viewComponent) {
        hSplitPane.setRightComponent(viewComponent);
    }

}
