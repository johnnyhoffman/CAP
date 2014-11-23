import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LocatingDataModel {
    private String name;
    private Delta delta;
    private Echo echo;
    private Foxtrot foxtrot;
    private Golf golf;
    private Gson gson;

    public LocatingDataModel(String name) {
        this.name = name;
        delta = new Delta();
        echo = new Echo();
        foxtrot = new Foxtrot();
        golf = new Golf();
        // gson = new Gson();
        // XXX: for debugging, revert to above creation method later
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    private class Delta {
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

    public void updateDeltaNameOfOrg(String s) {
        delta.nameOfOrg = s;
    }

    public void updateDeltaActualLoc(String s) {
        delta.actualLoc = s;
    }

    public void updateDeltaCoordinates(String s) {
        delta.coordinates = s;
    }

    public void updateDeltaTimeObjectiveLocated(String s) {
        delta.timeObjectiveLocated = s;
    }

    public void updateDeltaELT(String s) {
        delta.ELT = s;
    }

    public void updateDeltaBY(String s) {
        delta.BY = s;
    }

    public void updateDeltaTerrainAndGroundCover(String s) {
        delta.terrainAndGroundCover = s;
    }

    public void updateDeltaNumSubjectsInvolved(String s) {
        delta.numSubjectsInvolved = s;
    }

    public void updateDeltaNumAlive(String s) {
        delta.numAlive = s;
    }

    public void updateDeltaNumDeceased(String s) {
        delta.numDeceased = s;
    }

    public void updateDeltaNumMissing(String s) {
        delta.numMissing = s;
    }

    public void updateEchoOrgMakingRecovery(String s) {
        echo.orgMakingRecovery = s;
    }

    public void updateEchoTimeRecoveryBegan(String s) {
        echo.timeRecoveryBegan = s;
    }

    public void updateEchoSubjectsDeliveredTo(String s) {
        echo.subjectsDeliveredTo = s;
    }

    public void updateEchoTimeRecoveryCompleted(String s) {
        echo.timeRecoveryCompleted = s;
    }

    public void updateEchoRecoveryMethods(String s) {
        echo.recoveryMethods = s;
    }

    public void updateEchoNumRecoveredAlive(String s) {
        echo.numRecoveredAlive = s;
    }

    public void updateEchoNumRecoveredDeceased(String s) {
        echo.numRecoveredDeceased = s;
    }

    public void updateEchoNumSelfRecovered(String s) {
        echo.numSelfRecovered = s;
    }

    public void updateFoxtrotNumSubjectsSaved(String s) {
        foxtrot.numSubjectsSaved = s;
    }

    public void updateFoxtrotNumSubjectsAssisted(String s) {
        foxtrot.numSubjectsAssisted = s;
    }

    public void updateFoxtrotOrganizationSavesCreditedTo(String s) {
        foxtrot.organizationSavesCreditedTo = s;
    }

    public void updateFoxtrotMissionClosedOrSuspended(boolean closed, boolean suspended) {
        foxtrot.missionClosed = closed;
        foxtrot.missionSuspended = suspended;
        System.out.println(jsonSerialize());
    }

    public void updateFoxtrotCloseOrSuspendTime(String s) {
        foxtrot.closeOrSuspendTime = s;
    }

    public void updateGolfAdditionalRemarks(String s) {
        golf.additionalRemarks = s;
    }

    /*
     * This class encapsulates all data that should be json serialized and
     * nothing else
     */
    private class SerializableDataEncapsulator {
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
        }
    }

    public String jsonSerialize() {
        return gson.toJson(new SerializableDataEncapsulator(name, delta, echo, foxtrot, golf));
    }
}
