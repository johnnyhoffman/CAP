package chat;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import mvcCommon.IController;
import network.ChatMessage;
import network.ClientSocket;

import common.ClientGlobalVariables;
import common.OnConnectionErrorListener;

public class ChatController implements IController {

    private ChatView view;
    private OnConnectionErrorListener onConnectionErrorListener;

    public ChatController() {
        this.view = new ChatView();
        view.setTextChatArea("");
        view.setTextChatEntry("");
        setListeners();
    }

    @Override
    public Component getViewComponent() {
        return view;
    }

    public void setListeners() {
        view.addActionListenChatEntry(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String textEntry = view.getTextChatEntry();

                if (textEntry.isEmpty())
                    return;
                ChatMessage message = new ChatMessage(textEntry,
                        ClientGlobalVariables.USERNAME);

                try {
                    ClientSocket.getInstance().output.writeObject(message);
                } catch (IOException ex) {
                    if (onConnectionErrorListener != null) {
                        onConnectionErrorListener.onConnectionError();
                    }
                }
                view.setTextChatEntry(""); // Reset the chat entry line
            }
        });
    }

    // provide access to write to the chat area
    public void writeChat(String message) {
        String textArea = view.getTextChatArea();
        int textTest = 1;
        if (textArea == "") {
            textTest = 0;
        }
        switch (textTest) {
        case 0:
            view.setTextChatArea(message);
            break;

        default:
            view.setTextChatArea(textArea + "\n" + message);
        }
    }

    public void setOnConnectionErrorListener(OnConnectionErrorListener l) {
        this.onConnectionErrorListener = l;
    }

    public void processChatMessage(ChatMessage m) {
        writeChat(m.getUser() + " : " + m.getMessage());
    }

    public void onClose() {
    }

}