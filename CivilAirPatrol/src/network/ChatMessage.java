package network;

/**
 * 
 * @author Robert
 */
public class ChatMessage extends NetworkMessage {

    private static final long serialVersionUID = -8615375585747174611L;
    private String message;
    private String user;

    public ChatMessage(String message, String user) {
        super(MessageType.CHAT);
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return this.message;
    }

    public String getUser() {
        return this.user;
    }
}
