/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import network.UserType;

import common.AppPreferences;
import common.GlobalConstants;
import common.User;

/**
 * 
 * @author Robert
 */
public class LoginWindow extends JPanel {
    private static final long serialVersionUID = -6923259781857755025L;
    private JFrame thisFrame;

    public LoginWindow(JFrame thisFrame) {
        this.thisFrame = thisFrame;

    }

    public User showOptions() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.EAST;
        GridBagConstraints right = new GridBagConstraints();
        right.fill = GridBagConstraints.HORIZONTAL;
        right.anchor = GridBagConstraints.WEST;
        right.gridwidth = GridBagConstraints.REMAINDER;

        final JTextField ipField = new JTextField(15);
        ipField.setText(AppPreferences.getIP());
        final JTextField portField = new JTextField(15);
        portField.setText(AppPreferences.getPort() + "");
        final JTextField userField = new JTextField(15);
        userField.setText("");
        final JPasswordField passwordField = new JPasswordField(15);
        passwordField.setText("");
        this.add(new JLabel("Server IP Address: "), left);
        this.add(ipField, right);
        this.add(Box.createVerticalStrut(15), right); // a spacer
        this.add(new JLabel("Server Port Number: "), left);
        this.add(portField, right);
        this.add(Box.createVerticalStrut(15), right); // a spacer
        this.add(new JLabel("User: "), left);
        this.add(userField, right);
        this.add(new JLabel("Password: "), left);
        this.add(passwordField, right);

        JButton resetButton = new JButton("Reset to Default");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ipField.setText(GlobalConstants.DEFAULT_ADDRESS);
                portField.setText(GlobalConstants.DEFAULT_PORT + "");
            }
        });
        this.add(Box.createVerticalStrut(15), right); // a spacer
        this.add(new JLabel(""), left); // hacky spacing
        this.add(resetButton, right);

        int result = JOptionPane.showOptionDialog(thisFrame, this, "Login",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, new String[] { "Accept", "Cancel" }, "default");

        if (result == JOptionPane.OK_OPTION) {
            String ip = ipField.getText();
            String portStr = portField.getText();
            int portInt;
            AppPreferences.setIP(ip);
            try {
                portInt = Integer.parseInt(portStr);
                AppPreferences.setPort(portInt);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Cannot set port to \""
                        + portStr + "\". Must be a number.");
            }
            return new User(userField.getText(), new String(
                    (passwordField.getPassword())), UserType.NONE);
        } else {
            return null;
        }
    }
}
