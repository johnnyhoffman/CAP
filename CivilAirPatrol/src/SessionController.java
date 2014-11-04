public class SessionController {

    private SessionView view;
    private SessionModel model;

    private Controller formsController;
    private Controller chatController;
    private Controller assetsController;

    public SessionController() {
        view = new SessionView();
        model = new SessionModel();

        formsController = new FormsController();
        assetsController = new AssetsController();
        chatController = new ChatController();
        // view.setFormsComponent(new CommLogView()); //TODO I added this in to see my form to build
        view.setFormsComponent(formsController.getViewComponent());
        view.setAssetsComponent(assetsController.getViewComponent());
        view.setChatComponent(chatController.getViewComponent());

        // this.view.addChangeListener(new SaveListener());
    }

    // class SaveListener implements ActionListener {
    // @Override
    // public void actionPerformed(ActionEvent arg0) {
    // }
    // }

}
