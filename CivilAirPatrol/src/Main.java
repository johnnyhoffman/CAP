public class Main {

    public static void main(String[] args) {
        SessionView sessionView = new SessionView();
        SessionModel sessionModel = new SessionModel();
        new SessionController(sessionView, sessionModel);
        sessionView.setVisible(true);
    }

}
