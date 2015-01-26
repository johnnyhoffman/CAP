package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SessionController {

    private SessionView view;
    private SessionModel model;

    private FormsController formsController;
    private ChatController chatController;
    private AssetsController assetsController;

    public SessionController() {
        view = new SessionView();
        model = new SessionModel();

        formsController = new FormsController();
        assetsController = new AssetsController();
        chatController = new ChatController();

        view.setFormsComponent(formsController.getViewComponent());
        view.setAssetsComponent(assetsController.getViewComponent());
        view.setChatComponent(chatController.getViewComponent());

        view.addNewComLogMenuItemActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formsController.newComLog();
            }
        });

        view.addNewSearchAndRescueMenuItemActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formsController.newSearchAndRescue();
            }
        });

        view.addNewRadioMessageMenuItemActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formsController.newRadioMessage();
            }
        });

        // XXX: for testing
        view.addIncomingJsonActionListener(new ActionListener() {
            JsonParser parser = new JsonParser();
            JsonObject json1 = (JsonObject) parser
                    .parse("{\"type\":\"SearchAndRescue\",\"name\":\"SAR form yee\",\"header\":{\"missionNumber\":\"12312\",\"activityForDateOf\":\"23423\",\"reportedBy\":\"khjhjgj\",\"dateTime\":\"hgjhg\"},\"alpha\":{\"nameOfSearchOrg1\":\"jhgjh\",\"nameOfSearchOrg2\":\"gjhg\",\"nameOfSearchOrg3\":\"jh\"},\"bravo\":{\"timeDispatched1\":\"gj\",\"timeDispatched2\":\"hg\",\"timeDispatched3\":\"bvjf\",\"timeELTFirstHeard1\":\"tv\",\"timeELTFirstHeard2\":\"yr\",\"timeELTFirstHeard3\":\"evy\",\"numAircraft1\":\"erdvbnm\",\"numAircraft2\":\"kjng\",\"numAircraft3\":\"iubtv6\",\"numSorties1\":\"4e\",\"numSorties2\":\"vbdrnf\",\"numSorties3\":\" yg\",\"hoursInSearchArea1\":\" yuf\",\"hoursInSearchArea2\":\"8t7f\",\"hoursInSearchArea3\":\"ugyuyfyte6\",\"hoursEnroute1\":\"54ev6\",\"hoursEnroute2\":\"5r\",\"hoursEnroute3\":\"6c546\",\"totalFlightHours1\":\"535\",\"totalFlightHours2\":\"46\",\"totalFlightHours3\":\"78\",\"totalPersonnel1\":\"99\",\"totalPersonnel2\":\"87654\",\"totalPersonnel3\":\"345678\",\"areaSearch\":[[\"7654765\",\"567\",\"6\",\"78\",\"76567\"],[\"5667876\",\"8\",\"7898\",\"76\",\"876\"],[\"789\",\"765\",\"7656\",\"7876\",\"567\"],[\"876\",\"678\",\"7876\",\"78\",\"65678\"]],\"other\":\"dsfdfgdfg\",\"significantWeather\":\"sdfsdf\"},\"charlie\":{\"totalResourcesExpectedACPT\":\"kjhkh\",\"totalResourcesExpectedPersonnel\":\"kjhk\",\"plannedSearchArea\":\"kjhkj\",\"forcastedWeather\":\"dsfsdfsdfssdf\"},\"delta\":{\"nameOfOrg\":\"sdfs\",\"actualLoc\":\"kghjhg\",\"coordinates\":\"jhgjhg\",\"timeObjectiveLocated\":\"jhgjh\",\"ELT\":\"g\",\"BY\":\"mhkhj\",\"terrainAndGroundCover\":\"kjh\",\"numSubjectsInvolved\":\"kjhk\",\"numAlive\":\"jhk\",\"numDeceased\":\"jhk\",\"numMissing\":\"jh\"},\"echo\":{\"orgMakingRecovery\":\"kjh\",\"timeRecoveryBegan\":\"kjh\",\"subjectsDeliveredTo\":\"hk\",\"timeRecoveryCompleted\":\"kj\",\"recoveryMethods\":\"hk\",\"numRecoveredAlive\":\"jh\",\"numRecoveredDeceased\":\"kjh\",\"numSelfRecovered\":\"kj\"},\"foxtrot\":{\"numSubjectsSaved\":\"jhk\",\"numSubjectsAssisted\":\"jh\",\"organizationSavesCreditedTo\":\"kjh\",\"missionClosed\":false,\"missionSuspended\":true,\"closeOrSuspendTime\":\"k\"},\"golf\":{\"additionalRemarks\":\"sdfsdf\"}}");
            JsonObject json2 = (JsonObject) parser
                    .parse("{\"name\":\"COM........LOG!!!\",\"type\":\"CommunicationsLog\",\"missionNum\":\"mhgjhg\",\"stationFunctionalDesignator\":\"jhg\",\"date\":\"jhgjh\",\"A\":\"gjhg\",\"B\":\"jhg\",\"C\":\"jhg\",\"D\":\"jh\",\"E\":\"gjh\",\"F\":\"gj\",\"entries\":[{\"time\":\"sdfgsdf\",\"call\":\"kjh\",\"chRef\":\"kjhkj\",\"remarks\":\"hkjh\"},{\"time\":\"kjh\",\"call\":\"kjh\",\"chRef\":\"kjh\",\"remarks\":\"k\"},{\"time\":\"jhk\",\"call\":\"jh\",\"chRef\":\"k\",\"remarks\":\"hjkj\"},{\"time\":\"hk\",\"call\":\"jh\",\"chRef\":\"kj\",\"remarks\":\"\"},{\"time\":\"kjh\",\"call\":\"kj\",\"chRef\":\"k\",\"remarks\":\"jh\"},{\"time\":\"\",\"call\":\"k\",\"chRef\":\"j\",\"remarks\":\"f\"},{\"time\":\"\",\"call\":\"hjgf\",\"chRef\":\"\",\"remarks\":\"hg\"},{\"time\":\"\",\"call\":\"\",\"chRef\":\"fd\",\"remarks\":\"jhd\"},{\"time\":\"ghfd\",\"call\":\"ghf\",\"chRef\":\"hgf\",\"remarks\":\"dgh\"},{\"time\":\"df\",\"call\":\"ghdf\",\"chRef\":\"hgfd\",\"remarks\":\"ghdf\"},{\"time\":\"hgdf\",\"call\":\"hg\",\"chRef\":\"fdhg\",\"remarks\":\"fdgh\"},{\"time\":\"df\",\"call\":\"ghfd\",\"chRef\":\"ghdf\",\"remarks\":\"hgfd\"},{\"time\":\"\",\"call\":\"ghfd\",\"chRef\":\"ghf\",\"remarks\":\"d\"},{\"time\":\"\",\"call\":\"\",\"chRef\":\"\",\"remarks\":\"\"},{\"time\":\"\",\"call\":\"\",\"chRef\":\"\",\"remarks\":\"\"},{\"time\":\"\",\"call\":\"\",\"chRef\":\"\",\"remarks\":\"\"},{\"time\":\"\",\"call\":\"\",\"chRef\":\"\",\"remarks\":\"\"},{\"time\":\"\",\"call\":\"\",\"chRef\":\"\",\"remarks\":\"\"},{\"time\":\"\",\"call\":\"\",\"chRef\":\"\",\"remarks\":\"\"},{\"time\":\"fdg\",\"call\":\"\",\"chRef\":\"\",\"remarks\":\"\"},{\"time\":\"\",\"call\":\"\",\"chRef\":\"\",\"remarks\":\"\"},{\"time\":\"\",\"call\":\"\",\"chRef\":\"\",\"remarks\":\"\"},{\"time\":\"\",\"call\":\"\",\"chRef\":\"\",\"remarks\":\"\"},{\"time\":\"\",\"call\":\"\",\"chRef\":\"\",\"remarks\":\"\"},{\"time\":\"jgdf\",\"call\":\"\",\"chRef\":\"\",\"remarks\":\"\"},{\"time\":\"\",\"call\":\"\",\"chRef\":\"\",\"remarks\":\"\"},{\"time\":\"\",\"call\":\"\",\"chRef\":\"\",\"remarks\":\"\"},{\"time\":\"\",\"call\":\"\",\"chRef\":\"\",\"remarks\":\"\"},{\"time\":\"\",\"call\":\"jgfd\",\"chRef\":\"\",\"remarks\":\"s\"},{\"time\":\"\",\"call\":\"\",\"chRef\":\"\",\"remarks\":\"sdfasdfasahg\"}]}");
            JsonObject json3 = (JsonObject) parser
                    .parse("{\"type\":\"RadioMessage\",\"name\":\"Radical Massage ;)\",\"header\":{\"msgNum\":\"skdjkjhk\",\"precedence\":\"hj\",\"dtg\":\"kjhk\",\"from\":\"jh\",\"to\":\"kjh\",\"info\":\"kj\thkj\thk\tjh\tkj\thk\tjh\tkj\th\",\"subj\":\"kj\",\"groupCnt\":\"hkj\"},\"message\":\"hkj\thk\tjh\",\"messageRecieved\":{\"from\":\"\",\"dtg\":\"lkj\",\"RecievingOperatorInitials\":\"lkjl\"},\"messageSent\":{\"to\":\"lk\",\"dtg\":\"lkj\",\"sendingOperatorInitials\":\"kjsdf\"}}");

            @Override
            public void actionPerformed(ActionEvent arg0) {
                formsController.fromJson(json1);
                formsController.fromJson(json2);
                formsController.fromJson(json3);
            }
        });
    }

}
