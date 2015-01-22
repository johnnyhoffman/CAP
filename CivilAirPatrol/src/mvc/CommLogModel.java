package mvc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CommLogModel extends ScheduledPushModelAbstraction {

    private DataContainers.CommunicationsLog data;
    private Gson gson;

    public CommLogModel(String name) {
        data = new DataContainers.CommunicationsLog(name);
        // gson = new Gson();
        // XXX: for debugging, revert to above creation method later
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /* methods for updating fields */

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

    public String jsonSerialize() {
        return gson.toJson(data);
    }

}