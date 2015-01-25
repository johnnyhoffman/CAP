package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SessionController {

    private SessionView view;
    private SessionModel model;

    private FormsController formsController;
    private ChatController chatController;
    private AssetsController assetsController;

    public SessionController() {
        view = new SessionView();
        model = new SessionModel();

        formsController = new FormsController();
        assetsController = new AssetsController();
        chatController = new ChatController();

        view.setFormsComponent(formsController.getViewComponent());
        view.setAssetsComponent(assetsController.getViewComponent());
        view.setChatComponent(chatController.getViewComponent());

        view.addNewComLogMenuItemActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formsController.newComLog();
            }
        });

        view.addNewSearchAndRescueMenuItemActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formsController.newSearchAndResue();
            }
        });

        view.addNewRadioMessageMenuItemActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formsController.newRadioMessage();
            }
        });
    }

}
