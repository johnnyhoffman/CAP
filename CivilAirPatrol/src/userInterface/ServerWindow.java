package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import applications.Server;


import network.ClientSocket;
import network.DBRequest;
import network.GetMessage;


public class ServerWindow {
	
	private JFrame mainFrame;
	
	private JLabel mainLabel;
	private JTextField portTextField;
	
	private JButton goButton;

	public static int SERVER_VIEW_WIDTH = 600;
	public static int SERVER_VIEW_HEIGHT = 400;

	private Boolean busy;
	private Boolean serverActive;
	
	private Server server;
	
	public ServerWindow(Server server, int initialPort) {
		this.server = server;
		
		
		mainFrame = new JFrame();
		mainFrame.setSize(SERVER_VIEW_WIDTH, SERVER_VIEW_HEIGHT);
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.setBackground(new Color(230, 230, 230));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel tooltip = new JLabel(
                "CAP Forms Database Server",
                JLabel.CENTER);
        tooltip.setSize(100, 60);
		mainFrame.add(tooltip);
		
		
        JPanel portTextPanel = new JPanel();
        JLabel portLabel = new JLabel("Port: ");
        portTextPanel.setPreferredSize(new Dimension(126, 24));
		portTextField = new JTextField();
		portTextField.setDocument(new TextDocumentForLimitedTextFields(8,true));
		portTextField.setText(""+initialPort);
		portTextField.setSize(120, 20);
		portTextPanel.add(portLabel);
		portTextPanel.add(portTextField);
		mainFrame.add(portTextPanel);
		
        JPanel buttonPanel = new JPanel();
        goButton = new JButton("Start Server");
        goButton.addActionListener(new ServerGoButtonListener());
        buttonPanel.add(goButton);
        mainFrame.add(buttonPanel);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("Civil Air Patrol Forms-Database Server");
        mainFrame.setPreferredSize(new Dimension(SERVER_VIEW_WIDTH, SERVER_VIEW_HEIGHT));
        mainFrame.pack();
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
        
        this.busy = false;
	}
	
	
	
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
	

}
