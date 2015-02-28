package session;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import network.UserType;

import common.ClientGlobalVariables;
import common.DateTimePicker;

public class SessionView extends JFrame {

    public interface NewFormListener {
        public void createForm(String missionNo, long date);
    }

    private JMenuBar menuBar;
    private static final long serialVersionUID = 9131304647063274186L;
    private JSplitPane hSplitPane;
    private JSplitPane vSplitPane;
    private JMenuItem newDialog;
    // private JMenuItem prefsDialog;
    private JMenuItem searchDatabaseMenuItem;
    private JFrame thisFrame;
    private NewFormListener newComLogMenuListener;
    private NewFormListener newRadioMessageMenuListener;
    private NewFormListener newSearchAndRescueListener;

    SessionView() {
        // Make Menu
        thisFrame = this; // For anonymous class referencing the JFrame
        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        searchDatabaseMenuItem = new JMenuItem("Search/Open Forms");
        fileMenu.add(searchDatabaseMenuItem);

        // New Forms
        newDialog = new JMenuItem("New Form");
        // Only writers have the option to add a new form
        if (ClientGlobalVariables.USERTYPE == UserType.WRITER) {
            fileMenu.add(newDialog);
        }
        newDialog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] formNames = { "Communication Log",
                        "Search And Rescue", "Radio Message" };
                JComboBox formTypeCombobox = new JComboBox(formNames);
                JTextField missionNoField = new JTextField(5);
                DateTimePicker dateTimePicker = new DateTimePicker();

                JPanel dialogPanel = new JPanel();
                dialogPanel.setLayout(new GridBagLayout());
                GridBagConstraints left = new GridBagConstraints();
                left.anchor = GridBagConstraints.EAST;
                GridBagConstraints right = new GridBagConstraints();
                right.fill = GridBagConstraints.HORIZONTAL;
                right.anchor = GridBagConstraints.WEST;
                right.gridwidth = GridBagConstraints.REMAINDER;
                dialogPanel.add(new JLabel("Form Type: "), left);
                dialogPanel.add(formTypeCombobox, right);
                dialogPanel.add(Box.createVerticalStrut(15), right); // a spacer
                dialogPanel.add(new JLabel("Mission Number: "), left);
                dialogPanel.add(missionNoField, right);
                dialogPanel.add(Box.createVerticalStrut(15), right); // a spacer
                dialogPanel.add(new JLabel("Date:"), left);

                dialogPanel.add(dateTimePicker, right);

                int result = JOptionPane.showOptionDialog(thisFrame,
                        dialogPanel, "New Form", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, new String[] {
                                "Create Form", "Cancel" }, "default");

                if (result == JOptionPane.OK_OPTION) {
                    String missionNo = missionNoField.getText();
                    long date = dateTimePicker.getDateLong();
                    switch (formTypeCombobox.getSelectedIndex()) {
                    case (0):
                        newComLogMenuListener.createForm(missionNo, date);
                        break;
                    case (1):
                        newSearchAndRescueListener.createForm(missionNo, date);
                        break;
                    case (2):
                        newRadioMessageMenuListener.createForm(missionNo, date);
                        break;
                    default:
                        break;
                    }
                }
            }

        });

        // Preference changing dialog -- Commented out unless we decide we need
        // it or something like it
        // prefsDialog = new JMenuItem("Preferences");
        // fileMenu.add(prefsDialog);
        // prefsDialog.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // new PrefsWindow(thisFrame);
        // }
        // });

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

    public void addNewComLogMenuItemActionListener(NewFormListener l) {
        newComLogMenuListener = l;
    }

    public void addNewRadioMessageMenuItemActionListener(NewFormListener l) {
        newRadioMessageMenuListener = l;
    }

    public void addNewSearchAndRescueMenuItemActionListener(NewFormListener l) {
        newSearchAndRescueListener = l;
    }

    public void addRetrieveFormsActionListener(ActionListener actionListener) {
        searchDatabaseMenuItem.addActionListener(actionListener);
    }
}
