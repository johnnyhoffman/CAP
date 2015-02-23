package common;

import session.SessionController;

public class Main {

    public static void main(String[] args) {
        AppPreferences.setIP("140.160.60.151");
        new SessionController();
    }
}
