package network;

/**
 * GetMessage will be used to send a request to the server for data
 * 
 * @author Robert
 */
public class RegisterMissionNoMessage extends NetworkMessage {

    private static final long serialVersionUID = 612742740275772931L;
    private String missionNo;

    public RegisterMissionNoMessage(String missionNo) {
        super(MessageType.REGISTER_MISSION_NO);
        this.missionNo = missionNo;
    }

    public String getMissionNo() {
        return this.missionNo;
    }
}
