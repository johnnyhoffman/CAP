package mvc;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* Placeholder for demonstrating Session MVC */
public class ChatController implements IController {

    private ChatView view;
    private ChatModel model;

    public ChatController() {
        this.view = new ChatView();
        this.model = new ChatModel();
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
                // What do i do when the user hits enter
                // TODO this is where we need to send the chat message to server
                // instead of just printing to the chatArea
                String textArea = view.getTextChatArea();
                String textEntry = view.getTextChatEntry();

                if (textEntry.isEmpty())
                    return;

                int textTest = 1;
                if (textArea == "") {
                    textTest = 0;
                }
                switch (textTest) {
                case 0:
                    view.setTextChatArea(textEntry);
                    break;

                default:
                    view.setTextChatArea(textArea + "\n" + textEntry);
                }

                view.setTextChatEntry("");

            }
        });

    }

    public void onClose() {
    }

}