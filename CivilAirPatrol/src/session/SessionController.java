/**
 *  Copyright 2015 Dana Vold, Johnny Hoffman, Robert Wassenaar
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package session;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import common.OnConnectionErrorListener;

import network.AssetUpdateMessage;
import network.ChatMessage;
import network.ClientListenerThread.OnIncomingDataListener;
import network.GuiMessage;
import network.NetworkMessage;
import network.ResultMessage;
import session.SessionView.NewFormListener;
import userInterface.SearchWindow;
import assets.AssetsController;
import chat.ChatController;
import forms.FormsController;
import javax.swing.JTabbedPane;

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
                    formsController.fromDBPushParams(
                            ((GuiMessage) networkMessage).getParams(),
                            ((GuiMessage) networkMessage).getIsUpdate());
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
                    assetsController.setLists(assetUpdateMessage.getOverdue(),
                            assetUpdateMessage.getUnderdue());
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
                    System.out
                            .println("Unhandled message type \"REGISTER_MISSION_NO\"");
                    break;
                case ERROR:
                    System.out.println("Unhandled message type \"ERROR\"");
                    break;
                case ASSET_COLOR_SET:
                    System.out
                            .println("Unhandled message type \"ASSET_COLOR_SET\"");
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

        /* set Print action listener */
        view.setPrintMenuActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                print.Print.saveComponentAsJPEG(((JTabbedPane) formsController
                        .getViewComponent()).getSelectedComponent(),
                        "TestPrint.jpg");
            }
        }));

        // On window close
        view.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent winEvt) {
                onClose();
            }
        });

        OnConnectionErrorListener onConnectionErrorListener = new OnConnectionErrorListener() {
            @Override
            public void onConnectionError() {
                view.showConnectionErrorMessage();
            }
        };
        formsController.setOnConnectionErrorListener(onConnectionErrorListener);
        assetsController
                .setOnConnectionErrorListener(onConnectionErrorListener);
        chatController.setOnConnectionErrorListener(onConnectionErrorListener);
    }

    public void onClose() {
        formsController.onClose();
        assetsController.onClose();
        chatController.onClose();
    }

}
