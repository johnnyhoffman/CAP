public class SessionController {

    private SessionView view;
    private SessionModel model;

    public SessionController(SessionView view, SessionModel model) {
        this.view = view;
        this.model = model;

        // this.view.addChangeListener(new SaveListener());
    }

    // class SaveListener implements ActionListener {
    // @Override
    // public void actionPerformed(ActionEvent arg0) {
    // }
    // }

}
