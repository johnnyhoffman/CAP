package common;

import mvc.SessionController;

public class Main {

    public static void main(String[] args) {
        database.sqlServer.CreateDatabase();
        new SessionController();
    }
}
