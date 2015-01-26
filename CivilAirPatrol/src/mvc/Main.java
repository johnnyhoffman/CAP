package mvc;

public class Main {

    public static void main(String[] args) {
        database.sqlServer.CreateDatabase();
        new SessionController();
    }
}
