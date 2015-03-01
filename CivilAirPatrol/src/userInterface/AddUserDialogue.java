package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import security.BCrypt;

import common.GlobalConstants;
import common.User;

import database.sqlServer;

public class AddUserDialogue {
    private JFrame frame;

    private JButton goButton;
    private JButton cancelButton;

    JTextField userNameField;
    JPasswordField userPasswordField1;
    JPasswordField userPasswordField2;

    JCheckBox writerCheckBox;

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
        userNameField.setDocument(new TextDocumentForLimitedTextFields(
                GlobalConstants.USERNAME_MAX_LEN, 1));
        userNameField.setText("");
        userNameField.setSize(120, 20);
        userNamePanel.add(portLabel);
        userNamePanel.add(userNameField);
        frame.add(userNamePanel);

        if (!deletion) {

            JPanel passwordPanel1 = new JPanel();
            passwordPanel1.setPreferredSize(new Dimension(126, 24));
            JPanel passwordPanel2 = new JPanel();
            passwordPanel2.setPreferredSize(new Dimension(126, 24));

            JPanel passwordPanel = new JPanel();
            passwordPanel.setPreferredSize(new Dimension(126, 24));
            writerCheckBox = new JCheckBox("New user can write files");
            passwordPanel.add(writerCheckBox);
            frame.add(passwordPanel);

            JLabel passwordLabel1 = new JLabel("enter password: ");
            userPasswordField1 = new JPasswordField(15);
            userPasswordField1
                    .setDocument(new TextDocumentForLimitedTextFields(
                            GlobalConstants.PASSWORD_MAX_LEN, 1));
            userPasswordField1.setText("");
            userPasswordField1.setSize(120, 20);
            passwordPanel1.add(passwordLabel1);
            passwordPanel1.add(userPasswordField1);

            JLabel passwordLabel2 = new JLabel("confirm password: ");
            userPasswordField2 = new JPasswordField(15);
            userPasswordField2
                    .setDocument(new TextDocumentForLimitedTextFields(8, 1));
            userPasswordField2.setText("");
            userPasswordField2.setSize(120, 20);
            passwordPanel2.add(passwordLabel2);
            passwordPanel2.add(userPasswordField2);
            frame.add(passwordPanel1);
            frame.add(passwordPanel2);

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
            String userName = userNameField.getText();
            String userPassword1 = new String(userPasswordField1.getPassword());
            String userPassword2 = new String(userPasswordField1.getPassword());
            User user = sqlServer.SelectFromUsersWithUserName(userName);

            if (user != null) {

                javax.swing.JOptionPane.showMessageDialog(frame,
                        "User with that name already exists! Aborting.");
            } else if (userNameField.getText().length() == 0) {
                javax.swing.JOptionPane.showMessageDialog(frame,
                        "User name cannot be empty! Aborting.");
            } else if (userPassword1.equals(userPassword2)) {
                String writerStatus;
                if (writerCheckBox.isSelected()) {
                    writerStatus = "WRITER";
                } else {
                    writerStatus = "READER";
                }
                sqlServer.InsertUser(userName,
                        BCrypt.hashpw(userPassword1, BCrypt.gensalt()),
                        writerStatus);
                frame.dispose();
                javax.swing.JOptionPane.showMessageDialog(frame, "New user \""
                        + userName + "\" created.");
            } else {
                javax.swing.JOptionPane.showMessageDialog(frame,
                        "Passwords do not match! Aborting operation.");
            }

        }
    }

    private class deleteUserListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // TODO make this delete a user
            String userName = userNameField.getText();
            javax.swing.JOptionPane
                    .showMessageDialog(
                            frame,
                            "This feature is incomplete. Users cannot be deleted yet. This will be added soon.");
            frame.dispose();
        }
    }
}
