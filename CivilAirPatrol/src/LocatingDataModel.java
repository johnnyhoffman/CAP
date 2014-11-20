public class LocatingDataModel {
    private Delta delta;
    private Echo echo;
    private Foxtrot foxtrot;
    private String additionalRemarks;
    public LocatingDataModel(){
        delta = new Delta();
        echo = new Echo();
        foxtrot = new Foxtrot();
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
    }

    private class Foxtrot {
        public String numSubjectsSaved;
        public String numSubjectsAssisted;
        public String organizationSavesCreditedTo;
        public boolean missionClosed = false;
        public boolean missionSuspended = false;
        public String closeOrSuspendTime;
    }

    public void updateDeltaNameOfOrg              (String s) {delta.nameOfOrg             = s;}
    public void updateDeltaActualLoc              (String s) {delta.actualLoc             = s;}
    public void updateDeltaCoordinates            (String s) {delta.coordinates           = s;}
    public void updateDeltaTimeObjectiveLocated   (String s) {delta.timeObjectiveLocated  = s;}
    public void updateDeltaELT                    (String s) {delta.ELT                   = s;}
    public void updateDeltaBY                     (String s) {delta.BY                    = s;}
    public void updateDeltaTerrainAndGroundCover  (String s) {delta.terrainAndGroundCover = s;}
    public void updateDeltaNumSubjectsInvolved    (String s) {delta.numSubjectsInvolved   = s;}
    public void updateDeltaNumAlive               (String s) {delta.numAlive              = s;}
    public void updateDeltaNumDeceased            (String s) {delta.numDeceased           = s;}
    public void updateDeltaNumMissing             (String s) {delta.numMissing            = s;}

    public void updateEchoOrgMakingRecovery           (String s) { echo.orgMakingRecovery        = s;}
    public void updateEchoTimeRecoveryBegan           (String s) { echo.timeRecoveryBegan        = s;}
    public void updateEchoSubjectsDeliveredTo         (String s) { echo.subjectsDeliveredTo      = s;}
    public void updateEchoTimeRecoveryCompleted       (String s) { echo.timeRecoveryCompleted    = s;}
    public void updateEchoRecoveryMethods             (String s) { echo.recoveryMethods          = s;}
    public void updateEchoNumRecoveredAlive           (String s) { echo.numRecoveredAlive        = s;}
    public void updateEchoNumRecoveredDeceased        (String s) { echo.numRecoveredDeceased     = s;}
    public void updateEchoNumSelfRecovered            (String s) { echo.numSelfRecovered         = s;}
    

    public void updateFoxtrotNumSubjectsSaved                   (String s) {foxtrot.numSubjectsSaved                = s;}
    public void updateFoxtrotNumSubjectsAssisted                (String s) {foxtrot.numSubjectsAssisted             = s;}
    public void updateFoxtrotOrganizationSavesCreditedTo        (String s) {foxtrot.organizationSavesCreditedTo     = s;}
    public void updateFoxtrotMissionClosed                      (boolean b) {foxtrot.missionClosed                   = b; foxtrot.missionSuspended = !b;}
    public void updateFoxtrotMissionSuspended                   (boolean b) {foxtrot.missionSuspended                = b; foxtrot.missionClosed = !b;}
    public void updateFoxtrotCloseOrSuspendTime                 (String s) {foxtrot.closeOrSuspendTime              = s;}
    public void updateAdditionalRemarks                            (String s) {additionalRemarks = s;}
    
    
    public void jsonSerialize() {
        
    }
}
