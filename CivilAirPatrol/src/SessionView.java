import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;

public class SessionView extends JFrame {
    private JMenuBar menuBar;
    private static final long serialVersionUID = 9131304647063274186L;
    private JSplitPane hSplitPane;
    private JSplitPane vSplitPane;

    SessionView() {
        menuBar = new JMenuBar();
        JMenu newMenu = new JMenu("New");
        menuBar.add(newMenu);
        newMenu.add(new JMenuItem("Communication Log"));
        setJMenuBar(menuBar);
        hSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        hSplitPane.setResizeWeight(0.7);
        vSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        vSplitPane.setResizeWeight(0.5);
        hSplitPane.setRightComponent(vSplitPane);
        this.add(hSplitPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public void setFormsComponent(Component viewComponent) {
        hSplitPane.setLeftComponent(viewComponent);
    }

    public void setAssetsComponent(Component viewComponent) {
        vSplitPane.setTopComponent(viewComponent);
    }

    public void setChatComponent(Component viewComponent) {
        vSplitPane.setBottomComponent(viewComponent);
    }

}
