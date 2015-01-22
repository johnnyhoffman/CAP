package mvc;

import java.util.concurrent.ScheduledThreadPoolExecutor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CommLogModel {

    private DataContainers.CommunicationsLog data;
    private Gson gson;
    private final ScheduledThreadPoolExecutor executor;

    public CommLogModel(String name) {
        data = new DataContainers.CommunicationsLog(name);
        // gson = new Gson();
        // XXX: for debugging, revert to above creation method later
        gson = new GsonBuilder().setPrettyPrinting().create();
        executor = new ScheduledThreadPoolExecutor(1);
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

    // TODO: have models extend an abtract, in which this method is defined and
    // jsonSerialize in declared
    /*
     * Schedule the data to be pushed to the server. Only schedules a push if
     * there is not already one scheduled.
     */
    public void schedulePush() {
        // Don't schedule if there are any runnables in the queue
        if (executor.getQueue().size() < 1) {
            executor.schedule(new Runnable() {
                @Override
                public void run() {
                    // TODO: HERE IS WHERE WE HOOK IN DATABASE CONNECTION.
                    // INSTEAD OF PRINTING THE JSON, PUSH IT TO THE DATABASE.
                    System.out.println(jsonSerialize());
                }
            }, GlobalConstants.PUSH_DELAY, GlobalConstants.PUSH_DELAY_UNITS);
        }
    }
}