import java.util.concurrent.ScheduledThreadPoolExecutor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LocatingDataModel {
    private DataContainers.LocatingData data;
    private Gson gson;
    private final ScheduledThreadPoolExecutor executor;

    public LocatingDataModel(String name) {
        data = new DataContainers.LocatingData(name);
        // gson = new Gson();
        // XXX: for debugging, revert to above creation method later
        gson = new GsonBuilder().setPrettyPrinting().create();
        executor = new ScheduledThreadPoolExecutor(1);
    }

    /* These classes represent the sections of the form */

    /* methods for updating fields */

    public void updateDeltaNameOfOrg(String s) {
        data.delta.nameOfOrg = s;
        schedulePush();
    }

    public void updateDeltaActualLoc(String s) {
        data.delta.actualLoc = s; 
        schedulePush();
    }

    public void updateDeltaCoordinates(String s) {
        data.delta.coordinates = s;
        schedulePush();
    }

    public void updateDeltaTimeObjectiveLocated(String s) {
        data.delta.timeObjectiveLocated = s;
        schedulePush();
    }

    public void updateDeltaELT(String s) {
        data.delta.ELT = s;
        schedulePush();
    }

    public void updateDeltaBY(String s) {
        data.delta.BY = s;
        schedulePush();
    }

    public void updateDeltaTerrainAndGroundCover(String s) {
        data.delta.terrainAndGroundCover = s;
        schedulePush();
    }

    public void updateDeltaNumSubjectsInvolved(String s) {
        data.delta.numSubjectsInvolved = s;
        schedulePush();
    }

    public void updateDeltaNumAlive(String s) {
        data.delta.numAlive = s;
        schedulePush();
    }

    public void updateDeltaNumDeceased(String s) {
        data.delta.numDeceased = s;
        schedulePush();
    }

    public void updateDeltaNumMissing(String s) {
        data.delta.numMissing = s;
        schedulePush();
    }

    public void updateEchoOrgMakingRecovery(String s) {
        data.echo.orgMakingRecovery = s;
        schedulePush();
    }

    public void updateEchoTimeRecoveryBegan(String s) {
        data.echo.timeRecoveryBegan = s;
        schedulePush();
    }

    public void updateEchoSubjectsDeliveredTo(String s) {
        data.echo.subjectsDeliveredTo = s;
        schedulePush();
    }

    public void updateEchoTimeRecoveryCompleted(String s) {
        data.echo.timeRecoveryCompleted = s;
        schedulePush();
    }

    public void updateEchoRecoveryMethods(String s) {
        data.echo.recoveryMethods = s;
        schedulePush();
    }

    public void updateEchoNumRecoveredAlive(String s) {
        data.echo.numRecoveredAlive = s;
        schedulePush();
    }

    public void updateEchoNumRecoveredDeceased(String s) {
        data.echo.numRecoveredDeceased = s;
        schedulePush();
    }

    public void updateEchoNumSelfRecovered(String s) {
        data.echo.numSelfRecovered = s;
        schedulePush();
    }

    public void updateFoxtrotNumSubjectsSaved(String s) {
        data.foxtrot.numSubjectsSaved = s;
        schedulePush();
    }

    public void updateFoxtrotNumSubjectsAssisted(String s) {
        data.foxtrot.numSubjectsAssisted = s;
        schedulePush();
    }

    public void updateFoxtrotOrganizationSavesCreditedTo(String s) {
        data.foxtrot.organizationSavesCreditedTo = s;
        schedulePush();
    }

    public void updateFoxtrotMissionClosedOrSuspended(boolean closed, boolean suspended) {
        data.foxtrot.missionClosed = closed;
        data.foxtrot.missionSuspended = suspended;
        schedulePush();
    }

    public void updateFoxtrotCloseOrSuspendTime(String s) {
        data.foxtrot.closeOrSuspendTime = s;
        schedulePush();
    }

    public void updateGolfAdditionalRemarks(String s) {
        data.golf.additionalRemarks = s;
        schedulePush();
    }

    // These all are used by gson.toJson, but
    // eclipse does not recognize that because the class is private.

    /*
     * This class encapsulates all data that should be json serialized and
     * nothing else
     */

    public String jsonSerialize() {
        return gson.toJson(data);
    }

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
