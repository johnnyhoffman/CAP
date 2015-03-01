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
                //LoginMessage loginMessage = (LoginMessage)message;
                switch(message.getType()){
                    case ERROR:
                        JOptionPane.showMessageDialog(f, ((ErrorMessage)message).getMessage());
                        System.exit(0);
                        break;
                        
                    case LOGIN:
                        ClientGlobalVariables.USERTYPE = ((LoginMessage)message).getUserType();
                        ClientGlobalVariables.USERNAME = ((LoginMessage)message).getUser();
                        break;
                }
                
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                //TODO This is a fatal error....
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                //TODO This is a fatal error....

            }
            loginWindow.setToNotQuitOnClose();
            new SessionController();
        }
    }
}
