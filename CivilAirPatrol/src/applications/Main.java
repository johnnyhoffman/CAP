package applications;

import javax.swing.JFrame;

import session.SessionController;
import userInterface.PrefsWindow;

public class Main {

    public static void main(String[] args) {
        JFrame f = new JFrame();
        new PrefsWindow(f);
        new SessionController();
    }
}
