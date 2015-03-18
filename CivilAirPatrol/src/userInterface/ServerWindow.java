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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import FormTracker.Server;

public class ServerWindow {

    private JFrame mainFrame;

    private JTextField portTextField;

    private JButton goButton;
    private JButton newUserButton;
    private JButton deleteUserButton;

    public static int SERVER_VIEW_WIDTH = 600;
    public static int SERVER_VIEW_HEIGHT = 400;

    private Boolean busy;
    private Server server;

    public ServerWindow(Server server, int initialPort) {
        this.server = server;

        mainFrame = new JFrame();
        mainFrame.setSize(SERVER_VIEW_WIDTH, SERVER_VIEW_HEIGHT);
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.setBackground(new Color(230, 230, 230));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // JLabel tooltip = new JLabel("CAP Forms Database Server",
        // JLabel.CENTER);
        // tooltip.setSize(100, 60);
        // mainFrame.add(tooltip);

        JPanel portTextPanel = new JPanel();
        JLabel portLabel = new JLabel("Port: ");
        portTextPanel.setPreferredSize(new Dimension(126, 24));
        portTextField = new JTextField();
        portTextField.setDocument(new TextDocumentForLimitedTextFields(8, 0));
        portTextField.setText("" + initialPort);
        portTextField.setSize(120, 20);
        portTextPanel.add(portLabel);
        portTextPanel.add(portTextField);
        mainFrame.add(portTextPanel);

        JPanel buttonPanel = new JPanel();

        goButton = new JButton("Start Server");
        goButton.addActionListener(new ServerGoButtonListener());
        buttonPanel.add(goButton);

        newUserButton = new JButton("Register New User");
        newUserButton.addActionListener(new NewUserButtonListener());
        buttonPanel.add(newUserButton);

        deleteUserButton = new JButton("Remove User Registration");
        deleteUserButton.addActionListener(new DeleteUserButtonListener());
        buttonPanel.add(deleteUserButton);

        mainFrame.add(buttonPanel);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("Civil Air Patrol Forms - Database Server");
        mainFrame.setPreferredSize(new Dimension(SERVER_VIEW_WIDTH,
                SERVER_VIEW_HEIGHT));
        mainFrame.pack();
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);

        this.busy = false;
    }

    /**
     * This listener is for starting up the server with the given port
     * 
     * @author danavold
     * 
     */
    private class ServerGoButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (!busy) {
                server.activateServer(Integer.parseInt(portTextField.getText()));
                goButton.setEnabled(false);
                goButton.setText("Server is Running");
                portTextField.setEnabled(false);
            }
        }
    }

    /**
     * This listener is for adding a new user to the list of registered users
     * 
     * @author danavold
     */
    private class NewUserButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new AddUserDialogue(false);
        }
    }

    /**
     * Remove a user from the list of registered users
     * 
     * @author danavold
     */
    private class DeleteUserButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new AddUserDialogue(true);
        }
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(mainFrame, message);
    }

}
