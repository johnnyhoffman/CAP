package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import applications.Server;
import assets.AssetColorSingletonForServer;
import assets.AssetStatus;
import assets.AssetTrackerServerSide;

import com.google.gson.Gson;

import common.DBPushParams;
import common.DataContainers;
import common.DataContainers.CommunicationsLog;
import common.DataContainers.RadioMessage;
import common.DataContainers.SearchAndRescue;
import database.sqlServer;

/**
 * 
 * @author Robert
 */
public class ClientConnection extends Thread {

    public interface OnAssetUpdateListener {
        public void onAssetUpdate(List<AssetStatus> overdue,
                List<AssetStatus> underdue);
    }

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket socket;
    private String user;
    private UserType userType;
    private Gson gson;
    private AssetTrackerServerSide assetTracker;
    private boolean run = true;

    public ClientConnection(ObjectInputStream in, ObjectOutputStream out,
            Socket socket, String user, UserType type) {
        this.gson = new Gson();
        this.input = in;
        this.output = out;
        this.socket = socket;
        this.user = user;
        this.userType = type;
        assetTracker = new AssetTrackerServerSide(new OnAssetUpdateListener() {
            @Override
            public void onAssetUpdate(List<AssetStatus> overdue,
                    List<AssetStatus> underdue) {
                try {
                    output.writeObject(new AssetUpdateMessage(overdue, underdue));
                } catch (IOException e) {
                    System.out.println("no longer connected");
                }
            }
        });
        assetTracker.start();
    }

    public ObjectInputStream getInputStream() {
        return this.input;
    }

    public ObjectOutputStream getOutputStream() {
        return this.output;
    }

    public String getUserName() {
        return this.user;
    }

    public UserType getUserType() {
        return this.userType;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public boolean closeConnection() {
        try {
            run = false;
            assetTracker.run = false;
            Server.allClients.remove(this);
            this.input.close();
            this.output.close();
            this.socket.close();
            return true;
        } catch (Exception e) {
            System.err.println(e.toString());
            return false;
        }
    }

    @Override
    public void run() {
        NetworkMessage message;
        while (run) {
            try {
                message = (NetworkMessage) this.input.readObject();
                switch (message.getType()) {
                case CHAT:
                    handleChat(message);
                    break;
                case GUI:
                    if (userType == UserType.WRITER)
                        handleGuiPush(message);
                    break;
                case NEW_FORM:
                    if (userType == UserType.WRITER)
                        handleNewForm(message);
                    break;
                case LOGIN:
                    handleLogin(message);
                    break;
                case GET:
                    handleGet(message);
                    break;
                case GET_SINGLE:
                    handleGetSingle(message);
                    break;
                case REGISTER_MISSION_NO:
                    handleRegisterMissionNo(message);
                    break;
                case ASSET_COLOR_SET:
                    handleAssetColorSet(message);
                    break;
                case ASSET_UPDATE:
                    System.out
                            .println("Unhandled message type \"ASSET_UPDATE\"");
                    break;
                case ERROR:
                    System.out.println("Unhandled message type \"ERROR\"");
                    break;
                case RESULT:
                    System.out.println("Unhandled message type \"RESULT\"");
                    break;
                }
            } catch (IOException e) {
                closeConnection();
                System.err.println(e.toString());
                return;
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                Logger.getLogger(ClientConnection.class.getName()).log(
                        Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }

    private void handleChat(NetworkMessage message) {
        // echo the message to all authenticated clients....
        for (ClientConnection c : Server.allClients) {
            try {
                c.output.writeObject(message);
            } catch (IOException ex) {
                ex.printStackTrace();
                Logger.getLogger(ClientConnection.class.getName()).log(
                        Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }

    private void handleRegisterMissionNo(NetworkMessage message) {
        assetTracker.setMissionNo(((RegisterMissionNoMessage) message)
                .getMissionNo());
    }

    private void handleGuiPush(NetworkMessage message) {
        // echo the message to all non-radio officer connections...and
        // update db
        DBPushParams pushParams = ((GuiMessage) message).getParams();
        ((GuiMessage) message).setIsUpdate(true);

        switch (pushParams.type) {
        case CL:
            assetTracker.checkForUpdates(pushParams.missionNo);
            database.sqlServer.UpdateCommLog(pushParams.json, pushParams.id,
                    pushParams.missionNo, pushParams.date);
            break;
        case RM:
            database.sqlServer.UpdateRADMESS(pushParams.json, pushParams.id,
                    pushParams.missionNo, pushParams.date);
            break;
        case SAR:
            database.sqlServer.UpdateSAR(pushParams.json, pushParams.id,
                    pushParams.missionNo, pushParams.date);
            break;
        }

        for (ClientConnection c : Server.allClients) {
            try {
                if (c.userType != UserType.WRITER)
                    c.output.writeObject(message);
            } catch (IOException ex) {
                ex.printStackTrace();
                Logger.getLogger(ClientConnection.class.getName()).log(
                        Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }

    private void handleNewForm(NetworkMessage message) {
        NewFormMessage newFormMessage = (NewFormMessage) message;
        DBPushParams params = null;
        int id = sqlServer.RetrieveNextFormId();
        switch (newFormMessage.getFormType()) {
        case CL:
            CommunicationsLog cl;
            cl = new DataContainers.CommunicationsLog("Com Log " + id);
            cl.date = newFormMessage.getDate();
            cl.missionNum = newFormMessage.getMissionNo();
            sqlServer.InsertCommLog(gson.toJson(cl), id,
                    newFormMessage.getMissionNo(), newFormMessage.getDate());
            params = sqlServer.SelectFromCommLogWithID(id);
            break;
        case RM:
            RadioMessage rm;
            rm = new DataContainers.RadioMessage("Radio Message " + id);
            rm.header.dtg = newFormMessage.getDate();
            rm.header.missionNo = newFormMessage.getMissionNo();
            sqlServer.InsertRADIOMESS(gson.toJson(rm), id,
                    newFormMessage.getMissionNo(), newFormMessage.getDate());
            params = sqlServer.SelectFromRADWithID(id);
            break;
        case SAR:
            SearchAndRescue sar;
            sar = new DataContainers.SearchAndRescue("Search And Rescue " + id);
            sar.header.dateTime = newFormMessage.getDate();
            sar.header.missionNumber = newFormMessage.getMissionNo();
            sqlServer.InsertSAR(gson.toJson(sar), id,
                    newFormMessage.getMissionNo(), newFormMessage.getDate());
            params = sqlServer.SelectFromSARWithID(id);
            break;
        }
        if (params != null) {
            try {
                output.writeObject(new GuiMessage(params));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void handleLogin(NetworkMessage message) {
        // TODO this should actually never happen, if it does client is doing
        // something strange..
        // decided to make login the first message and is required to be
        // validated before starting clientConnection
    }

    private void handleGetSingle(NetworkMessage message) {
        int id = ((GetSingleMessage) message).getUID();
        DBPushParams params = null;
        switch (((GetSingleMessage) message).getFormType()) {
        case CL:
            params = database.sqlServer.SelectFromCommLogWithID(id);
            break;
        case RM:
            params = database.sqlServer.SelectFromRADWithID(id);
            break;
        case SAR:
            params = database.sqlServer.SelectFromSARWithID(id);
            break;
        }
        if (params != null) {
            try {
                this.output.writeObject(new GuiMessage(params));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void handleAssetColorSet(NetworkMessage message) {
        AssetColorSetMessage colorSetMessage = ((AssetColorSetMessage) message);
        System.out.println(colorSetMessage.getMissionNo() + colorSetMessage.getName() + colorSetMessage.getColor());
        AssetColorSingletonForServer.getInstance().put(colorSetMessage.getMissionNo(), colorSetMessage.getName(), colorSetMessage.getColor());
        AssetColorSingletonForServer.getInstance().touch();
    }

    private void handleGet(NetworkMessage message) {
        // to handle a request from the db and send back a response
        // can just send it back out on socket...cause it came from this
        // connection =)
        DBRequest request = ((GetMessage) message).getRequest();
        List<DBPushParams> resultsList = new ArrayList<DBPushParams>();

        if (request.COMM) {
            if (request.missionNo.equals("")) {
                resultsList.addAll(database.sqlServer
                        .SelectFromCommLogInitialSearch(request.startDate,
                                request.endDate));
            } else {
                resultsList.addAll(database.sqlServer
                        .SelectFromCommLogInitialSearch(request.startDate,
                                request.endDate, request.missionNo));
            }
        }
        if (request.RAD) {
            if (request.missionNo.equals("")) {
                resultsList.addAll(database.sqlServer
                        .SelectFromRADInitialSearch(request.startDate,
                                request.endDate));
            } else {
                resultsList.addAll(database.sqlServer
                        .SelectFromRADInitialSearch(request.startDate,
                                request.endDate, request.missionNo));
            }
        }
        if (request.SAR) {
            if (request.missionNo.equals("")) {
                resultsList.addAll(database.sqlServer
                        .SelectFromSARInitialSearch(request.startDate,
                                request.endDate));
            } else {
                resultsList.addAll(database.sqlServer
                        .SelectFromSARInitialSearch(request.startDate,
                                request.endDate, request.missionNo));
            }
        }

        ResultMessage result = new ResultMessage(resultsList, false);
        try {
            this.output.writeObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
