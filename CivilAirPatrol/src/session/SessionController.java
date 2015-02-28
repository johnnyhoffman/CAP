package session;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import network.AssetUpdateMessage;
import network.ChatMessage;
import network.ClientListenerThread.OnIncomingDataListener;
import network.GetMessage;
import network.GuiMessage;
import network.NetworkMessage;
import network.ResultMessage;
import session.SessionView.NewFormListener;
import userInterface.SearchWindow;
import assets.AssetsController;
import chat.ChatController;
import forms.FormsController;

public class SessionController {

    private SessionView view;
    private SessionModel model;

    private FormsController formsController;
    private ChatController chatController;
    private AssetsController assetsController;

    private SearchWindow searchWindow;

    public SessionController() {
        view = new SessionView();
        model = new SessionModel();

        formsController = new FormsController();
        assetsController = new AssetsController();
        chatController = new ChatController();

        view.setFormsComponent(formsController.getViewComponent());
        view.setAssetsComponent(assetsController.getViewComponent());
        view.setChatComponent(chatController.getViewComponent());

        model.setIncomingDataListener(new OnIncomingDataListener() {
            @Override
            public void processNetworkMessage(NetworkMessage networkMessage) {
                switch (networkMessage.getType()) {
                case CHAT:
                    chatController
                            .processChatMessage((ChatMessage) networkMessage);
                    break;
                case GUI:
                    formsController.fromDBPushParams(((GuiMessage)networkMessage).getParams(), ((GuiMessage)networkMessage).getIsUpdate());
                    break;
                case RESULT:
                    if (searchWindow != null) {
                        searchWindow
                                .setResultsWindow(((ResultMessage) networkMessage)
                                        .getResults());
                    }
                    break;
                case ASSET_UPDATE:
                    AssetUpdateMessage assetUpdateMessage = (AssetUpdateMessage) networkMessage;
                    assetsController.setLists(assetUpdateMessage.getOverdue(), assetUpdateMessage.getUnderdue());
                    break;
                case GET:
                    System.out.println("Unhandled message type \"GET\"");
                    break;
                case GET_SINGLE:
                    System.out.println("Unhandled message type \"GET_SINGLE\"");
                    break;
                case LOGIN:
                    System.out.println("Unhandled message type \"LOGIN\"");
                    break;
                case NEW_FORM:
                    System.out.println("Unhandled message type \"NEW_FORM\"");
                    break;
                case REGISTER_MISSION_NO:
                    System.out.println("Unhandled message type \"REGISTER_MISSION_NO\"");
                    break;
                }
            }
        });

        view.addNewComLogMenuItemActionListener(new NewFormListener() {
            public void createForm(String missionNo, long date) {
                formsController.newComLog(missionNo, date);
                ;
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
                    searchWindow = new SearchWindow(view.getX(), view.getY(),
                            formsController);
                } else {
                    searchWindow.moveToTop();
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
        assetsController.onClose();
        chatController.onClose();
    }

}
