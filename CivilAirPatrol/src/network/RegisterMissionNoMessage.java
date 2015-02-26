/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

/**
 * GetMessage will be used to send a request to the server for data
 * 
 * @author Robert
 */
public class RegisterMissionNoMessage extends NetworkMessage {

    private String missionNo;

    public RegisterMissionNoMessage(String missionNo) {
        super(MessageType.REGISTER_MISSION_NO);
        this.missionNo = missionNo;
    }

    public String getMissionNo() {
        return this.missionNo;
    }
}
