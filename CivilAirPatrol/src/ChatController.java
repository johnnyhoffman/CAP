import java.awt.Component;

/* Placeholder for demonstrating Session MVC */
public class ChatController implements Controller {

    private ChatView view;
    private ChatModel model;

    public ChatController() {
        this.view = new ChatView();
        this.model = new ChatModel();
    }

    @Override
    public Component getViewComponent() {
        return view;
    }

}