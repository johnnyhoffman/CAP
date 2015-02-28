package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import common.GlobalConstants;

public class AddUserDialogue {
    private JFrame frame;

    private JButton goButton;
    private JButton cancelButton;

    JTextField userNameField;
    JTextField userPasswordField1;
    JTextField userPasswordField2;

    private static final int USER_DIALOGUE_WIDTH = 520;
    private static final int USER_DIALOGUE_HEIGHT = 220;

    public AddUserDialogue(boolean deletion) {

        frame = new JFrame();

        frame = new JFrame();
        frame.setSize(USER_DIALOGUE_WIDTH, USER_DIALOGUE_HEIGHT);
        frame.setLayout(new GridLayout(3, 1));
        frame.setBackground(new Color(230, 230, 230));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // adds user name input
        JPanel userNamePanel = new JPanel();
        JLabel portLabel = new JLabel("User Name: ");
        userNamePanel.setPreferredSize(new Dimension(126, 24));
        userNameField = new JTextField(15);
        userNameField.setDocument(new TextDocumentForLimitedTextFields(GlobalConstants.USERNAME_MAX_LEN, 1));
        userNameField.setText("");
        userNameField.setSize(120, 20);
        userNamePanel.add(portLabel);
        userNamePanel.add(userNameField);
        frame.add(userNamePanel);

        if (!deletion) {

            JPanel passwordPanel = new JPanel();
            passwordPanel.setPreferredSize(new Dimension(126, 24));

            JLabel passwordLabel1 = new JLabel("enter password: ");
            userPasswordField1 = new JTextField(15);
            userPasswordField1
                    .setDocument(new TextDocumentForLimitedTextFields(GlobalConstants.PASSWORD_MAX_LEN, 1));
            userPasswordField1.setText("");
            userPasswordField1.setSize(120, 20);
            passwordPanel.add(passwordLabel1);
            passwordPanel.add(userPasswordField1);

            JLabel passwordLabel2 = new JLabel("confirm password: ");
            userPasswordField2 = new JTextField(15);
            userPasswordField2
                    .setDocument(new TextDocumentForLimitedTextFields(8, 1));
            userPasswordField2.setText("");
            userPasswordField2.setSize(120, 20);
            passwordPanel.add(passwordLabel2);
            passwordPanel.add(userPasswordField2);
            frame.add(passwordPanel);

        }

        JPanel buttonPanel = new JPanel();
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new CancelListener());
        if (deletion) {
            goButton = new JButton("remove user from registry");
            goButton.addActionListener(new deleteUserListener());
        } else {
            goButton = new JButton("add user to registry");
            goButton.addActionListener(new createUserListener());
        }

        buttonPanel.add(cancelButton);
        buttonPanel.add(goButton);
        frame.add(buttonPanel);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        if (deletion) {
            frame.setTitle("Remove user from Database User Registry");
        } else {
            frame.setTitle("Add new user to Database User Registry");
        }
        frame.setPreferredSize(new Dimension(USER_DIALOGUE_WIDTH,
                USER_DIALOGUE_HEIGHT));
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

    }

    private class CancelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
        }
    }

    private class createUserListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // TODO this mostly works but I'm not sure so I'm commenting it out
            /**
             * String userName = userNameField.getText(); User user =
             * sqlServer.SelectFromUsersWithUserName(userName); if (user !=
             * null) { createWarningMessage(
             * "User with that name already exists! Aborting."); } else if
             * (userNameField.getText().length() == 0) {
             * createWarningMessage("User name cannot be empty!"); } else if
             * (userPasswordField1
             * .getText().equals(userPasswordField2.getText())) {
             * System.out.println(userNameField.getText().length());
             * //sqlServer.InsertUser(userNameField.getText(),
             * userPasswordField1.getText(), "WRITER"); frame.dispose(); } else
             * { createWarningMessage("Passwords do not match!"); }
             */
        }
    }

    private class deleteUserListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // TODO make this delete a user
            frame.dispose();
        }
    }

    public void createWarningMessage(String s) {
        JFrame f = new JFrame();
        f.setSize(80, 40);
        f.setLayout(new GridLayout(1, 1));
        f.setBackground(new Color(235, 225, 225));
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel warning = new JLabel(s);
        JPanel warningPanel = new JPanel();
        warningPanel.add(warning);
        f.add(warningPanel);

        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setTitle("Warning");
        f.setPreferredSize(new Dimension(USER_DIALOGUE_WIDTH,
                USER_DIALOGUE_HEIGHT));
        f.pack();
        f.setResizable(false);
        f.setVisible(true);
    }
}
