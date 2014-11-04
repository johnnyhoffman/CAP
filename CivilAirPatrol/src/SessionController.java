public class SessionController {

    private SessionView view;
    private SessionModel model;

    private Controller formsController;
    private Controller assetsController;

    public SessionController(SessionView view, SessionModel model) {
        this.view = view;
        this.model = model;

        formsController = new FormsController();
        assetsController = new AssetsController();
        this.view.setFormsComponent(new CommLogView()); //TODO I added this in to see my form to build
        //this.view.setFormsComponent(formsController.getViewComponent());
        this.view.setAssetsComponent(assetsController.getViewComponent());

        // this.view.addChangeListener(new SaveListener());
    }

    // class SaveListener implements ActionListener {
    // @Override
    // public void actionPerformed(ActionEvent arg0) {
    // }
    // }

}
