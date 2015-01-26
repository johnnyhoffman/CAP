package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import database.sqlServer;

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
                formsController.newSearchAndRescue();
            }
        });

        view.addNewRadioMessageMenuItemActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formsController.newRadioMessage();
            }
        });

        // XXX: for demo
        view.addIncomingJsonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int missionNo = 10;
                List<DBPushParams> allThingsWithMissionNoX = new ArrayList<DBPushParams>();
                allThingsWithMissionNoX.addAll(sqlServer
                        .SelectFromCommLog(missionNo));
                allThingsWithMissionNoX.addAll(sqlServer
                        .SelectFromRadMess(missionNo));
                allThingsWithMissionNoX.addAll(sqlServer
                        .SelectFromSAR(missionNo));
                for (DBPushParams dbpp : allThingsWithMissionNoX) {
                    formsController.fromDBPushParams(dbpp);
                }
            }
        });
    }

}
