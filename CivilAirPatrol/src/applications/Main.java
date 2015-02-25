package applications;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import common.ClientGlobalVariables;
import common.User;

import network.ChatMessage;
import network.ClientListenerThread.OnIncomingDataListener;
import network.ClientSocket;
import network.GuiMessage;
import network.LoginMessage;
import network.NetworkMessage;
import network.ResultMessage;

import session.SessionController;
import userInterface.LoginWindow;

public class Main {
    public static void main(String[] args) {
        final JFrame f = new JFrame();
        LoginWindow loginWindow = new LoginWindow(f);
        final User user = loginWindow.showOptions();
        
        System.out.println("User: " + user.getUser()+ " pass: " + user.getPass());
        if (user != null){
            ClientSocket sock = ClientSocket.getInstance();
            try {
                sock.output.writeObject(new LoginMessage(
                        user.getUser(), user.getPass(), null));
                
                //want to block for a response so dont really need to set up a listener here
                NetworkMessage message = (NetworkMessage)sock.input.readObject();  
                LoginMessage loginMessage = (LoginMessage)message;
                switch(loginMessage.getUserType()){
                    case NONE:
                        JOptionPane.showMessageDialog(f, "Incorrect credentials provided.");
                        f.dispose();
                        System.exit(0);
                        break;
                    default:
                        ClientGlobalVariables.USERTYPE = loginMessage.getUserType();
                        ClientGlobalVariables.USERNAME = loginMessage.getUser();
                        break;
                }
                
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                //TODO This is a fatal error....
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                //TODO This is a fatal error....
			}
        
            new SessionController();
        }
    }
}
