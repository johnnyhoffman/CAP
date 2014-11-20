import java.util.concurrent.ScheduledThreadPoolExecutor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LocatingDataModel {
    private String name;
    private Delta delta;
    private Echo echo;
    private Foxtrot foxtrot;
    private Golf golf;
    private Gson gson;
    private final ScheduledThreadPoolExecutor executor;

    public LocatingDataModel(String name) {
        this.name = name;
        delta = new Delta();
        echo = new Echo();
        foxtrot = new Foxtrot();
        golf = new Golf();
        // gson = new Gson();
        // XXX: for debugging, revert to above creation method later
        gson = new GsonBuilder().setPrettyPrinting().create();
        executor = new ScheduledThreadPoolExecutor(1);
    }

    /* These classes represent the sections of the form */

    private class Delta {
        // These all are used by gson.toJson, but eclipse does not recognize
        // that because the class is private.
        public String nameOfOrg;
        public String actualLoc;
        public String coordinates;
        public String timeObjectiveLocated;
        public String ELT;
        public String BY;
        public String terrainAndGroundCover;
        public String numSubjectsInvolved;
        public String numAlive;
        public String numDeceased;
        public String numMissing;

        public Delta() {
            nameOfOrg = "";
            actualLoc = "";
            coordinates = "";
            timeObjectiveLocated = "";
            ELT = "";
            BY = "";
            terrainAndGroundCover = "";
            numSubjectsInvolved = "";
            numAlive = "";
            numDeceased = "";
            numMissing = "";
        }
    }

    private class Echo {
        // These all are used by gson.toJson, but eclipse does not recognize
        // that because the class is private.
        public String orgMakingRecovery;
        public String timeRecoveryBegan;
        public String subjectsDeliveredTo;
        public String timeRecoveryCompleted;
        public String recoveryMethods;
        public String numRecoveredAlive;
        public String numRecoveredDeceased;
        public String numSelfRecovered;

        public Echo() {
            orgMakingRecovery = "";
            timeRecoveryBegan = "";
            subjectsDeliveredTo = "";
            timeRecoveryCompleted = "";
            recoveryMethods = "";
            numRecoveredAlive = "";
            numRecoveredDeceased = "";
            numSelfRecovered = "";
        }
    }

    private class Foxtrot {
        // These all are used by gson.toJson, but eclipse does not recognize
        // that because the class is private.
        public String numSubjectsSaved;
        public String numSubjectsAssisted;
        public String organizationSavesCreditedTo;
        public boolean missionClosed;
        public boolean missionSuspended;
        public String closeOrSuspendTime;

        public Foxtrot() {
            numSubjectsSaved = "";
            numSubjectsAssisted = "";
            organizationSavesCreditedTo = "";
            missionClosed = false;
            missionSuspended = false;
            closeOrSuspendTime = "";
        }
    }

    private class Golf {
        public String additionalRemarks;

        public Golf() {
            additionalRemarks = "";
        }
    }

    /* methods for updating fields */

    public void updateDeltaNameOfOrg(String s) {
        delta.nameOfOrg = s;
        schedulePush();
    }

    public void updateDeltaActualLoc(String s) {
        delta.actualLoc = s;
        schedulePush();
    }

    public void updateDeltaCoordinates(String s) {
        delta.coordinates = s;
        schedulePush();
    }

    public void updateDeltaTimeObjectiveLocated(String s) {
        delta.timeObjectiveLocated = s;
        schedulePush();
    }

    public void updateDeltaELT(String s) {
        delta.ELT = s;
        schedulePush();
    }

    public void updateDeltaBY(String s) {
        delta.BY = s;
        schedulePush();
    }

    public void updateDeltaTerrainAndGroundCover(String s) {
        delta.terrainAndGroundCover = s;
        schedulePush();
    }

    public void updateDeltaNumSubjectsInvolved(String s) {
        delta.numSubjectsInvolved = s;
        schedulePush();
    }

    public void updateDeltaNumAlive(String s) {
        delta.numAlive = s;
        schedulePush();
    }

    public void updateDeltaNumDeceased(String s) {
        delta.numDeceased = s;
        schedulePush();
    }

    public void updateDeltaNumMissing(String s) {
        delta.numMissing = s;
        schedulePush();
    }

    public void updateEchoOrgMakingRecovery(String s) {
        echo.orgMakingRecovery = s;
        schedulePush();
    }

    public void updateEchoTimeRecoveryBegan(String s) {
        echo.timeRecoveryBegan = s;
        schedulePush();
    }

    public void updateEchoSubjectsDeliveredTo(String s) {
        echo.subjectsDeliveredTo = s;
        schedulePush();
    }

    public void updateEchoTimeRecoveryCompleted(String s) {
        echo.timeRecoveryCompleted = s;
        schedulePush();
    }

    public void updateEchoRecoveryMethods(String s) {
        echo.recoveryMethods = s;
        schedulePush();
    }

    public void updateEchoNumRecoveredAlive(String s) {
        echo.numRecoveredAlive = s;
        schedulePush();
    }

    public void updateEchoNumRecoveredDeceased(String s) {
        echo.numRecoveredDeceased = s;
        schedulePush();
    }

    public void updateEchoNumSelfRecovered(String s) {
        echo.numSelfRecovered = s;
        schedulePush();
    }

    public void updateFoxtrotNumSubjectsSaved(String s) {
        foxtrot.numSubjectsSaved = s;
        schedulePush();
    }

    public void updateFoxtrotNumSubjectsAssisted(String s) {
        foxtrot.numSubjectsAssisted = s;
        schedulePush();
    }

    public void updateFoxtrotOrganizationSavesCreditedTo(String s) {
        foxtrot.organizationSavesCreditedTo = s;
        schedulePush();
    }

    public void updateFoxtrotMissionClosedOrSuspended(boolean closed, boolean suspended) {
        foxtrot.missionClosed = closed;
        foxtrot.missionSuspended = suspended;
        schedulePush();
    }

    public void updateFoxtrotCloseOrSuspendTime(String s) {
        foxtrot.closeOrSuspendTime = s;
        schedulePush();
    }

    public void updateGolfAdditionalRemarks(String s) {
        golf.additionalRemarks = s;
        schedulePush();
    }

    // These all are used by gson.toJson, but
    // eclipse does not recognize that because the class is private.

    /*
     * This class encapsulates all data that should be json serialized and
     * nothing else
     */
    private class SerializableDataEncapsulator {
        // These all are used by gson.toJson, but
        // eclipse does not recognize that because the class is private.
        private String name;
        private Delta delta;
        private Echo echo;
        private Foxtrot foxtrot;
        private Golf golf;

        public SerializableDataEncapsulator(String name, Delta delta, Echo echo, Foxtrot foxtrot, Golf golf) {
            this.name = name;
            this.delta = delta;
            this.echo = echo;
            this.foxtrot = foxtrot;
            this.golf = golf;
        }
    }

    public String jsonSerialize() {
        return gson.toJson(new SerializableDataEncapsulator(name, delta, echo, foxtrot, golf));
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
