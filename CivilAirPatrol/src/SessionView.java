import java.awt.Component;
import java.awt.event.ActionListener;

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
    private JMenuItem newComLogMenuItem;
    private JMenuItem newRadioMessageMenuItem;
    private JMenuItem newLocatingDataMenuItem;
    private JMenuItem newSearchAndRescueMenuItem;

    SessionView() {
        // Make Menu
        menuBar = new JMenuBar();
        JMenu newMenu = new JMenu("New");
        menuBar.add(newMenu);
        newComLogMenuItem = new JMenuItem("Communication Log");
        newMenu.add(newComLogMenuItem);
        newRadioMessageMenuItem = new JMenuItem("Radio Message Form");
        newMenu.add(newRadioMessageMenuItem);
        newSearchAndRescueMenuItem = new JMenuItem("Search And Rescue Form");
        newMenu.add(newSearchAndRescueMenuItem);
        setJMenuBar(menuBar);

        // Split window into sections
        hSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        hSplitPane.setResizeWeight(0.7);
        vSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        vSplitPane.setResizeWeight(0.5);
        hSplitPane.setRightComponent(vSplitPane);
        this.add(hSplitPane);

        this.setSize(400, 500); // Must set some size, even though it starts
                                // maximized. Else, un-maximizizing the window
                                // may make it ~1 pixel wide
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

    public void addNewComLogMenuItemActionListener(ActionListener actionListener) {
        newComLogMenuItem.addActionListener(actionListener);
    }

    public void addNewRadioMessageMenuItemActionListener(
            ActionListener actionListener) {
        newRadioMessageMenuItem.addActionListener(actionListener);
    }

    public void addNewLocatingDataMenuItemActionListener(
            ActionListener actionListener) {
        newLocatingDataMenuItem.addActionListener(actionListener);
    }

    public void addNewSearchAndRescueMenuItemActionListener(
            ActionListener actionListener) {
        newSearchAndRescueMenuItem.addActionListener(actionListener);
    }

}
