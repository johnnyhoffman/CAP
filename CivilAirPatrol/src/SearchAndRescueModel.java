import java.util.concurrent.ScheduledThreadPoolExecutor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SearchAndRescueModel {

    private DataContainers.SearchAndRescue data;
    private Gson gson;
    private final ScheduledThreadPoolExecutor executor;

    public SearchAndRescueModel(String name) {
        data = new DataContainers.SearchAndRescue(name);
        // gson = new Gson();
        // XXX: for debugging, revert to above creation method later
        gson = new GsonBuilder().setPrettyPrinting().create();
        executor = new ScheduledThreadPoolExecutor(1);
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
