package mvc;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class SessionView extends JFrame {
    private JMenuBar menuBar;
    private static final long serialVersionUID = 9131304647063274186L;
    private JSplitPane hSplitPane;
    private JSplitPane vSplitPane;
    private JMenuItem newComLogMenuItem;
    private JMenuItem newRadioMessageMenuItem;
    private JMenuItem newLocatingDataMenuItem;
    private JMenuItem newSearchAndRescueMenuItem;
    // XXX: Temp for testing
    private JMenuItem newItemFromJson;
    // XXX: Temp for testing
    private JMenuItem newDialog;
    private JFrame thisFrame;

    SessionView() {
        // Make Menu
        thisFrame = this; // For anonymous class referencing the JFrame
        menuBar = new JMenuBar();
        JMenu newMenu = new JMenu("New");
        menuBar.add(newMenu);
        newComLogMenuItem = new JMenuItem("Communication Log");
        newMenu.add(newComLogMenuItem);
        newRadioMessageMenuItem = new JMenuItem("Radio Message Form");
        newMenu.add(newRadioMessageMenuItem);
        newSearchAndRescueMenuItem = new JMenuItem("Search And Rescue Form");
        newMenu.add(newSearchAndRescueMenuItem);

        // XXX: Temp for testing
        newItemFromJson = new JMenuItem(
                "For demo: Open all saved forms with mission number 10");
        newMenu.add(newItemFromJson);
        // XXX: Temp for testing
        newDialog = new JMenuItem("Open Dialog");
        newMenu.add(newDialog);
        newDialog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String[] formNames = { "Communication Log",
                        "Search And Rescue", "Radio Message" };
                JComboBox<String> formTypeCombobox = new JComboBox<String>(
                        formNames);
                JTextField missionNoField = new JTextField(5);
                JTextField dateField = new JTextField(5);
                JPanel myPanel = new JPanel();
                myPanel.add(new JLabel("FormType: "));
                myPanel.add(formTypeCombobox);
                myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                myPanel.add(new JLabel("Mission Number: "));
                myPanel.add(missionNoField);
                myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                myPanel.add(new JLabel("Date:"));
                myPanel.add(dateField);

                int result = JOptionPane.showConfirmDialog(thisFrame, myPanel,
                        "Please Enter X and Y Values",
                        JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    System.out.println("m n value: " + missionNoField.getText());
                    System.out.println("date value: " + dateField.getText());
                }
            }

        });

        setJMenuBar(menuBar);

        // Split window into sections
        hSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        hSplitPane.setResizeWeight(0.85);
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

    // XXX: temp for testing
    public void addIncomingJsonActionListener(ActionListener actionListener) {
        newItemFromJson.addActionListener(actionListener);
    }

    public class MyJDialog extends JDialog {
        private static final long serialVersionUID = 1L;

        public MyJDialog(JFrame parent, String title, String message) {
            super(parent, title);
            System.out.println("creating the window..");
            // set the position of the window
            Point p = new Point(400, 400);
            setLocation(p.x, p.y);
            // Create a message
            JPanel messagePane = new JPanel();
            messagePane.add(new JLabel(message));
            // get content pane, which is usually the
            // Container of all the dialog's components.
            getContentPane().add(messagePane);
            // Create a button
            JPanel buttonPane = new JPanel();
            JButton button = new JButton("Close me");
            buttonPane.add(button);
            // set action listener on the button
            button.addActionListener(new MyActionListener());
            getContentPane().add(buttonPane, BorderLayout.PAGE_END);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            pack();
            setVisible(true);
        }

        // override the createRootPane inherited by the JDialog, to create the
        // rootPane.
        // create functionality to close the window when "Escape" button is
        // pressed
        public JRootPane createRootPane() {
            JRootPane rootPane = new JRootPane();
            KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
            Action action = new AbstractAction() {
                private static final long serialVersionUID = 1L;

                public void actionPerformed(ActionEvent e) {
                    System.out.println("escaping..");
                    setVisible(false);
                    dispose();
                }
            };
            InputMap inputMap = rootPane
                    .getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            inputMap.put(stroke, "ESCAPE");
            rootPane.getActionMap().put("ESCAPE", action);
            return rootPane;
        }

        // an action listener to be used when an action is performed
        // (e.g. button is pressed)
        class MyActionListener implements ActionListener {
            // close and dispose of the window.
            public void actionPerformed(ActionEvent e) {
                System.out.println("disposing the window..");
                setVisible(false);
                dispose();
            }
        }
    }

}
