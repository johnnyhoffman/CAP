/**
 *  Copyright 2015 Dana Vold, Johnny Hoffman, Robert Wassenaar
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
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
        userField.setDocument(new TextDocumentForLimitedTextFields(
                GlobalConstants.USERNAME_MAX_LEN, 1));
        userField.setText("");
        final JPasswordField passwordField = new JPasswordField(15);
        passwordField.setText("");
        passwordField.setDocument(new TextDocumentForLimitedTextFields(
                GlobalConstants.PASSWORD_MAX_LEN, 1));
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

    public void setToQuitOnClose() {
        thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void setToNotQuitOnClose() {
        thisFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
}
