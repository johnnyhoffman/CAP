package session;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import common.DBPushParams;

import chat.ChatController;

import assets.AssetsController;
import common.GlobalConstants;

import session.SessionView.NewFormListener;
import userInterface.SearchWindow;
import database.sqlServer;
import forms.FormsController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import network.ClientSocket;
import network.LoginMessage;

public class SessionController {

    private SessionView view;
    private SessionModel model;

    private FormsController formsController;
    private ChatController chatController;
    private AssetsController assetsController;
    
    private SearchWindow searchWindow;

    public SessionController() {
        ClientSocket.getInstance().startListener();
        try {
            ClientSocket.getInstance().output.writeObject(new LoginMessage(GlobalConstants.USERNAME, "password"));
        } catch (IOException ex) {
            Logger.getLogger(SessionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        view = new SessionView();
        model = new SessionModel();

        formsController = new FormsController();
        assetsController = new AssetsController();
        chatController = new ChatController();

        view.setFormsComponent(formsController.getViewComponent());
        view.setAssetsComponent(assetsController.getViewComponent());
        view.setChatComponent(chatController.getViewComponent());

        view.addNewComLogMenuItemActionListener(new NewFormListener() {
            public void createForm(String missionNo, long date) {
                formsController.newComLog(missionNo, date);;
            }
        });

        view.addNewSearchAndRescueMenuItemActionListener(new NewFormListener() {
            public void createForm(String missionNo, long date) {
                formsController.newSearchAndRescue(missionNo, date);
            }
        });

        view.addNewRadioMessageMenuItemActionListener(new NewFormListener() {
            public void createForm(String missionNo, long date) {
                formsController.newRadioMessage(missionNo, date);
            }
        });
        
        
        view.addRetrieveFormsActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (searchWindow == null || searchWindow.isDead()) {
            		searchWindow = new SearchWindow(view.getX(),view.getY(),formsController);
            	} else {
            		searchWindow.moveToTop();
            	}
            }
        });

        // XXX: for demo
        view.addIncomingJsonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String missionNo = "10";
                List<DBPushParams> allThingsWithMissionNoX = new ArrayList<DBPushParams>();
                allThingsWithMissionNoX.addAll(sqlServer
                        .SelectFromCommLogWithMissionNum(missionNo));
                allThingsWithMissionNoX.addAll(sqlServer
                        .SelectFromRadMessWithMissionNum(missionNo));
                allThingsWithMissionNoX.addAll(sqlServer
                        .SelectFromSARWithMissionNum(missionNo));
                for (DBPushParams dbpp : allThingsWithMissionNoX) {
                    formsController.fromDBPushParams(dbpp);
                }
            }
        });
        
        // On window close
        view.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent winEvt) {
                onClose();
            }
        });
    }
    
    public void onClose() {
        formsController.onClose();
        AssetsController.onClose();
        chatController.onClose();
    }

}
