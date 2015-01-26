package mvc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class SearchAndRescueModel extends ScheduledPushAndCheckModelAbstraction {

    private DataContainers.SearchAndRescue data;
    private Gson gson;

    public SearchAndRescueModel(String name) {
        super();
        data = new DataContainers.SearchAndRescue(name);
        gson = new Gson();
        // for debugging revert to above creation method later
        // gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public SearchAndRescueModel(JsonObject json) {
        super();
        gson = new Gson();
        // for debugging revert to creation method below
        // gson = new GsonBuilder().setPrettyPrinting().create();
        jsonDeserialize(json);
    }

    /* methods for updating fields */
    public void updateHeaderMissionNumber(String s) {
        data.header.missionNumber = s;
        schedulePush();
    }

    public void updateHeaderActivityForDateOf(String s) {
        data.header.activityForDateOf = s;
        schedulePush();
    }

    public void updateHeaderReportedBy(String s) {
        data.header.reportedBy = s;
        schedulePush();
    }

    public void updateHeaderDateTime(String s) {
        data.header.dateTime = s;
        schedulePush();
    }

    public void updateAlphaNameOfSearchOrg1(String s) {
        data.alpha.nameOfSearchOrg1 = s;
        schedulePush();
    }

    public void updateAlphaNameOfSearchOrg2(String s) {
        data.alpha.nameOfSearchOrg2 = s;
        schedulePush();
    }

    public void updateAlphaNameOfSearchOrg3(String s) {
        data.alpha.nameOfSearchOrg3 = s;
        schedulePush();
    }

    public void updateBravoTimeDispatched1(String s) {
        data.bravo.timeDispatched1 = s;
        schedulePush();
    }

    public void updateBravoTimeDispatched2(String s) {
        data.bravo.timeDispatched2 = s;
        schedulePush();
    }

    public void updateBravoTimeDispatched3(String s) {
        data.bravo.timeDispatched3 = s;
        schedulePush();
    }

    public void updateBravoTimeELTFirstHeard1(String s) {
        data.bravo.timeELTFirstHeard1 = s;
        schedulePush();
    }

    public void updateBravoTimeELTFirstHeard2(String s) {
        data.bravo.timeELTFirstHeard2 = s;
        schedulePush();
    }

    public void updateBravoTimeELTFirstHeard3(String s) {
        data.bravo.timeELTFirstHeard3 = s;
        schedulePush();
    }

    public void updateBravoNumAircraft1(String s) {
        data.bravo.numAircraft1 = s;
        schedulePush();
    }

    public void updateBravoNumAircraft2(String s) {
        data.bravo.numAircraft2 = s;
        schedulePush();
    }

    public void updateBravoNumAircraft3(String s) {
        data.bravo.numAircraft3 = s;
        schedulePush();
    }

    public void updateBravoNumSorties1(String s) {
        data.bravo.numSorties1 = s;
        schedulePush();
    }

    public void updateBravoNumSorties2(String s) {
        data.bravo.numSorties2 = s;
        schedulePush();
    }

    public void updateBravoNumSorties3(String s) {
        data.bravo.numSorties3 = s;
        schedulePush();
    }

    public void updateBravoHoursInSearchArea1(String s) {
        data.bravo.hoursInSearchArea1 = s;
        schedulePush();
    }

    public void updateBravoHoursInSearchArea2(String s) {
        data.bravo.hoursInSearchArea2 = s;
        schedulePush();
    }

    public void updateBravoHoursInSearchArea3(String s) {
        data.bravo.hoursInSearchArea3 = s;
        schedulePush();
    }

    public void updateBravoHoursEnroute1(String s) {
        data.bravo.hoursEnroute1 = s;
        schedulePush();
    }

    public void updateBravoHoursEnroute2(String s) {
        data.bravo.hoursEnroute2 = s;
        schedulePush();
    }

    public void updateBravoHoursEnroute3(String s) {
        data.bravo.hoursEnroute3 = s;
        schedulePush();
    }

    public void updateBravoTotalFlightHours1(String s) {
        data.bravo.totalFlightHours1 = s;
        schedulePush();
    }

    public void updateBravoTotalFlightHours2(String s) {
        data.bravo.totalFlightHours2 = s;
        schedulePush();
    }

    public void updateBravoTotalFlightHours3(String s) {
        data.bravo.totalFlightHours3 = s;
        schedulePush();
    }

    public void updateBravoTotalPersonnel1(String s) {
        data.bravo.totalPersonnel1 = s;
        schedulePush();
    }

    public void updateBravoTotalPersonnel2(String s) {
        data.bravo.totalPersonnel2 = s;
        schedulePush();
    }

    public void updateBravoTotalPersonnel3(String s) {
        data.bravo.totalPersonnel3 = s;
        schedulePush();
    }

    public void updateBravoAreaSearched(String[][] sss) {
        data.bravo.areaSearch = sss;
        schedulePush();
    }

    public void updateBravoOther(String s) {
        data.bravo.other = s;
        schedulePush();
    }

    public void updateBravoSignificantWeather(String s) {
        data.bravo.significantWeather = s;
        schedulePush();
    }

    public void updateCharlieTotalResourcesExpectedACPT(String s) {
        data.charlie.totalResourcesExpectedACPT = s;
        schedulePush();
    }

    public void updateCharlieTotalResourcesExpectedPersonnel(String s) {
        data.charlie.totalResourcesExpectedPersonnel = s;
        schedulePush();
    }

    public void updateCharliePlannedSearchArea(String s) {
        data.charlie.plannedSearchArea = s;
        schedulePush();
    }

    public void updateCharlieForcastedWeather(String s) {
        data.charlie.forcastedWeather = s;
        schedulePush();
    }

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

    public String getHeaderMissionNumber() {
        return data.header.missionNumber;
    }

    public String getHeaderActivityForDateOf() {
        return data.header.activityForDateOf;
    }

    public String getHeaderReportedBy() {
        return data.header.reportedBy;
    }

    public String getHeaderDateTime() {
        return data.header.dateTime;
    }

    public String getAlphaNameOfSearchOrg1() {
        return data.alpha.nameOfSearchOrg1;
    }

    public String getAlphaNameOfSearchOrg2() {
        return data.alpha.nameOfSearchOrg2;
    }

    public String getAlphaNameOfSearchOrg3() {
        return data.alpha.nameOfSearchOrg3;
    }

    public String getBravoTimeDispatched1() {
        return data.bravo.timeDispatched1;
    }

    public String getBravoTimeDispatched2() {
        return data.bravo.timeDispatched2;
    }

    public String getBravoTimeDispatched3() {
        return data.bravo.timeDispatched3;
    }

    public String getBravoTimeELTFirstHeard1() {
        return data.bravo.timeELTFirstHeard1;
    }

    public String getBravoTimeELTFirstHeard2() {
        return data.bravo.timeELTFirstHeard2;
    }

    public String getBravoTimeELTFirstHeard3() {
        return data.bravo.timeELTFirstHeard3;
    }

    public String getBravoNumAircraft1() {
        return data.bravo.numAircraft1;
    }

    public String getBravoNumAircraft2() {
        return data.bravo.numAircraft2;
    }

    public String getBravoNumAircraft3() {
        return data.bravo.numAircraft3;
    }

    public String getBravoNumSorties1() {
        return data.bravo.numSorties1;
    }

    public String getBravoNumSorties2() {
        return data.bravo.numSorties2;
    }

    public String getBravoNumSorties3() {
        return data.bravo.numSorties3;
    }

    public String getBravoHoursInSearchArea1() {
        return data.bravo.hoursInSearchArea1;
    }

    public String getBravoHoursInSearchArea2() {
        return data.bravo.hoursInSearchArea2;
    }

    public String getBravoHoursInSearchArea3() {
        return data.bravo.hoursInSearchArea3;
    }

    public String getBravoHoursEnroute1() {
        return data.bravo.hoursEnroute1;
    }

    public String getBravoHoursEnroute2() {
        return data.bravo.hoursEnroute2;
    }

    public String getBravoHoursEnroute3() {
        return data.bravo.hoursEnroute3;
    }

    public String getBravoTotalFlightHours1() {
        return data.bravo.totalFlightHours1;
    }

    public String getBravoTotalFlightHours2() {
        return data.bravo.totalFlightHours2;
    }

    public String getBravoTotalFlightHours3() {
        return data.bravo.totalFlightHours3;
    }

    public String getBravoTotalPersonnel1() {
        return data.bravo.totalPersonnel1;
    }

    public String getBravoTotalPersonnel2() {
        return data.bravo.totalPersonnel2;
    }

    public String getBravoTotalPersonnel3() {
        return data.bravo.totalPersonnel3;
    }

    public String getBravoOther() {
        return data.bravo.other;
    }

    public String getBravoSignificantWeather() {
        return data.bravo.significantWeather;
    }

    public String getCharlieTotalResourcesExpectedACPT() {
        return data.charlie.totalResourcesExpectedACPT;
    }

    public String getCharlieTotalResourcesExpectedPersonnel() {
        return data.charlie.totalResourcesExpectedPersonnel;
    }

    public String getCharliePlannedSearchArea() {
        return data.charlie.plannedSearchArea;
    }

    public String getCharlieForcastedWeather() {
        return data.charlie.forcastedWeather;
    }

    public String getDeltaNameOfOrg() {
        return data.delta.nameOfOrg;
    }

    public String getDeltaActualLoc() {
        return data.delta.actualLoc;
    }

    public String getDeltaCoordinates() {
        return data.delta.coordinates;
    }

    public String getDeltaTimeObjectiveLocated() {
        return data.delta.timeObjectiveLocated;
    }

    public String getDeltaELT() {
        return data.delta.ELT;
    }

    public String getDeltaBY() {
        return data.delta.BY;
    }

    public String getDeltaTerrainAndGroundCover() {
        return data.delta.terrainAndGroundCover;
    }

    public String getDeltaNumSubjectsInvolved() {
        return data.delta.numSubjectsInvolved;
    }

    public String getDeltaNumAlive() {
        return data.delta.numAlive;
    }

    public String getDeltaNumDeceased() {
        return data.delta.numDeceased;
    }

    public String getDeltaNumMissing() {
        return data.delta.numMissing;
    }

    public String getEchoOrgMakingRecovery() {
        return data.echo.orgMakingRecovery;
    }

    public String getEchoTimeRecoveryBegan() {
        return data.echo.timeRecoveryBegan;
    }

    public String getEchoSubjectsDeliveredTo() {
        return data.echo.subjectsDeliveredTo;
    }

    public String getEchoTimeRecoveryCompleted() {
        return data.echo.timeRecoveryCompleted;
    }

    public String getEchoRecoveryMethods() {
        return data.echo.recoveryMethods;
    }

    public String getEchoNumRecoveredAlive() {
        return data.echo.numRecoveredAlive;
    }

    public String getEchoNumRecoveredDeceased() {
        return data.echo.numRecoveredDeceased;
    }

    public String getEchoNumSelfRecovered() {
        return data.echo.numSelfRecovered;
    }

    public String getFoxtrotNumSubjectsSaved() {
        return data.foxtrot.numSubjectsSaved;
    }

    public String getFoxtrotNumSubjectsAssisted() {
        return data.foxtrot.numSubjectsAssisted;
    }

    public String getFoxtrotOrganizationSavesCreditedTo() {
        return data.foxtrot.organizationSavesCreditedTo;
    }

    public boolean getFoxtrotMissionClosed() {
        return data.foxtrot.missionClosed;
    }

    public boolean getFoxtrotMissionSuspended() {
        return data.foxtrot.missionSuspended;
    }

    public String getFoxtrotCloseOrSuspendTime() {
        return data.foxtrot.closeOrSuspendTime;
    }

    public String getGolfAdditionalRemarks() {
        return data.golf.additionalRemarks;
    }

    public String jsonSerialize() {
        return gson.toJson(data);
    }

    public void jsonDeserialize(JsonObject json) {
        data = gson.fromJson(json, DataContainers.SearchAndRescue.class);
        modelLoaded();
    }

    public String[][] getBravoAreaSearched() {
        return data.bravo.areaSearch;
    }
}
