package applications;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import network.ClientSocket;
import network.LoginMessage;
import network.NetworkMessage;
import session.SessionController;
import userInterface.LoginWindow;

import common.ClientGlobalVariables;
import common.User;
import network.ErrorMessage;

public class Main {
    public static void main(String[] args) {
        final JFrame f = new JFrame();
        //Making it a large window, otherwise you can just lose the frame...
        f.setSize(400, 500); // Must set some size, even though it starts
                                // maximized. Else, un-maximizizing the window
                                // may make it ~1 pixel wide
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        f.setVisible(true);
        LoginWindow loginWindow = new LoginWindow(f);
        loginWindow.setToQuitOnClose();
        final User user = loginWindow.showOptions();
        f.dispose();
        if (user != null){
            ClientSocket sock = ClientSocket.getInstance();
            try {
                sock.output.writeObject(new LoginMessage(
                        user.getUser(), user.getPass(), null));
                //want to block for a response so dont really need to set up a listener here
                NetworkMessage message = (NetworkMessage)sock.input.readObject();  
                switch(message.getType()){
                    case ERROR:
                        JOptionPane.showMessageDialog(f, ((ErrorMessage)message).getMessage());
                        System.exit(0);
                        break;
                    case LOGIN:
                        ClientGlobalVariables.USERTYPE = ((LoginMessage)message).getUserType();
                        ClientGlobalVariables.USERNAME = ((LoginMessage)message).getUser();
                        break;
                default:
                    JOptionPane.showMessageDialog(f, "Encountered invalid message from server");
                    System.exit(1);;
                }
                
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(f, "Fatal: " + ex.getMessage());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(f, "Fatal: " + ex.getMessage());
            }
            loginWindow.setToNotQuitOnClose();
            new SessionController();
        }
    }
}
