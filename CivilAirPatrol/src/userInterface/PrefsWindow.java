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
import javax.swing.JTextField;

import common.AppPreferences;
import common.GlobalConstants;

public class PrefsWindow extends JPanel {
    private static final long serialVersionUID = 7166122540503963971L;

    public PrefsWindow(JFrame thisFrame) {
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
        this.add(new JLabel("Server IP Address: "), left);
        this.add(ipField, right);
        this.add(Box.createVerticalStrut(15), right); // a spacer
        this.add(new JLabel("Server Port Number: "), left);
        this.add(portField, right);

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

        int result = JOptionPane.showOptionDialog(thisFrame, this,
                "Preferences", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, new String[] { "Apply",
                        "Cancel" }, "default");

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
        }
    }
}