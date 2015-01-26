package mvc;

import java.text.SimpleDateFormat;
import java.util.Date;

import mvc.DataContainers.CommunicationsLog.ComLogEntry;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class CommLogModel extends ScheduledPushAndCheckModelAbstraction {

    private DataContainers.CommunicationsLog data;
    private Gson gson;
    private int id;

    public CommLogModel(int id, String name) {
        super();
        this.id = id;
        data = new DataContainers.CommunicationsLog(name);
        gson = new Gson();
        // for debugging revert to creation method below
        // gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /* methods for updating fields */

    public CommLogModel(int id, JsonObject json) {
        super();
        this.id = id;
        gson = new Gson();
        // for debugging revert to creation method below
        // gson = new GsonBuilder().setPrettyPrinting().create();
        jsonDeserialize(json);
    }

    public int getID() {
        return id;
    }

    public void updateName(String s) {
        data.name = s;
        schedulePush();
    }

    public void updateMissionNum(String s) {
        data.missionNum = s;
        schedulePush();
    }

    public void updateStationFunctionalDesignator(String s) {
        data.stationFunctionalDesignator = s;
        schedulePush();
    }

    public void updateDate(String s) {
        data.date = s;
        schedulePush();
    }

    public void updateA(String s) {
        data.A = s;
        schedulePush();
    }

    public void updateB(String s) {
        data.B = s;
        schedulePush();
    }

    public void updateC(String s) {
        data.C = s;
        schedulePush();
    }

    public void updateD(String s) {
        data.D = s;
        schedulePush();
    }

    public void updateE(String s) {
        data.E = s;
        schedulePush();
    }

    public void updateF(String s) {
        data.F = s;
        schedulePush();
    }

    public void updateEntries(ComLogEntry[] cles) {
        data.entries = cles;
        schedulePush();
    }

    public String getName() {
        return data.name;
    }

    public String getMissionNum() {
        return data.missionNum;
    }

    public String getStationFunctionalDesignator() {
        return data.stationFunctionalDesignator;
    }

    public String getDate() {
        return data.date;
    }

    public String getA() {
        return data.A;
    }

    public String getB() {
        return data.B;
    }

    public String getC() {
        return data.C;
    }

    public String getD() {
        return data.D;
    }

    public String getE() {
        return data.E;
    }

    public String getF() {
        return data.F;
    }

    public ComLogEntry[] getEntries() {
        return data.entries;
    }

    @Override
    public DBPushParams prepareForPush() {
        String json = gson.toJson(data);
        int missionNo = -1;
        try {
            missionNo = Integer.parseInt(data.missionNum);
        } catch (NumberFormatException e) {
            e.printStackTrace(); // TODO : Handle error more appropriately
        }
        String date = new SimpleDateFormat(GlobalConstants.DATE_FORMAT)
                .format(new Date());// TODO: Eventually use date input in form
        return new DBPushParams(FormType.CL, json, id, missionNo, date);
    }

    public void jsonDeserialize(JsonObject json) {
        data = gson.fromJson(json, DataContainers.CommunicationsLog.class);
        modelLoaded();
    }

}