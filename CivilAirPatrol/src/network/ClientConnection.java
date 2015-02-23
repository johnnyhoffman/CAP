/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket socket;
    private String user;
    private UserType userType;
    private Gson gson;

    public ClientConnection(ObjectInputStream in, ObjectOutputStream out,
            Socket socket, String user, UserType type) {
        this.gson = new Gson();
        this.input = in;
        this.output = out;
        this.socket = socket;
        this.user = user;
        this.userType = type;
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

    public Socket getSocket() {
        return this.socket;
    }

    public boolean closeConnection() {
        try {
            this.input.close();
            this.output.close();
            this.socket.close();
            Server.allClients.remove(this);
            return true;
        } catch (Exception e) {
            System.err.println(e.toString());
            return false;
        }
    }

    @Override
    public void run() {
        // TODO this will be where i handle the input coming from the clients
        NetworkMessage message;
        boolean run = true;
        while (run) {
            try {
                message = (NetworkMessage) this.input.readObject();
                switch (message.getType()) {
                case CHAT:
                    handleChat(message);
                    break;
                case GUI:
                    handleGuiPush(message);
                    break;
                case NEW_FORM:
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
                default:
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
        // TODO echo the message to all authenticated clients....
        for (ClientConnection c : Server.allClients) {
            try {
                System.out.println("sending message to " + c.getUserName()); // For
                // debug
                // purposes
                System.out.println(message);
                c.output.writeObject(message);
            } catch (IOException ex) {
                ex.printStackTrace();
                Logger.getLogger(ClientConnection.class.getName()).log(
                        Level.SEVERE, ex.getMessage(), ex);
            }
        }

    }

    private void handleGuiPush(NetworkMessage message) {
        // TODO echo the message to all non-radio officer connections...and
        // update db
        // will start with just pushing to the db
        // TODO TEST THIS?
        DBPushParams pushParams = ((GuiMessage) message).getParams();
        ((GuiMessage) message).setIsUpdate(true);
        System.out.println(pushParams.json + "\n" + pushParams.id + "\n"
                + pushParams.missionNo + "\n" + pushParams.date);

        switch (pushParams.type) {
        case CL:
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

        // System.out.println(message);
        for (ClientConnection c : Server.allClients) {
            try {
                System.out.println("sending message to " + c.getUserName()); // For
                                                                             // debug
                                                                             // purposes
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
        System.out.println("NEWFORMMESSSSSSSAGE:::::: " + newFormMessage);
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
        System.out.println("AQUI");
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
        System.out.println("IN CLIENT CONNECTION, params = " + params);
        if (params != null) {
            try {
                this.output.writeObject(new GuiMessage(params));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void handleGet(NetworkMessage message) {
        // TODO this is where i will need to handle a request from the db and
        // send back a response
        // can just send it back out on socket...cause it came from this
        // connection =)
        DBRequest request = ((GetMessage) message).getRequest();
        List<DBPushParams> resultsList = new ArrayList<DBPushParams>();

        if (request.COMM) {
            if (request.missionNo == null) {
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
            if (request.missionNo == null) {
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
            if (request.missionNo == null) {
                resultsList.addAll(database.sqlServer
                        .SelectFromSARInitialSearch(request.startDate,
                                request.endDate));
            } else {
                resultsList.addAll(database.sqlServer
                        .SelectFromSARInitialSearch(request.startDate,
                                request.endDate, request.missionNo));
            }
        }

        // send the results back to the client
        // TODO RESULTS MESSAGE TO BUNDLE THE RESULTS BACK UP, distinguish
        // between a search result and actual forms coming back, 1 has json 1
        // doesnt
        ResultMessage result = new ResultMessage(resultsList, false);
        try {
            this.output.writeObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
