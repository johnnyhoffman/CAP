public class DataContainers {

    public static class CommunicationsLog {

        String name;
        String missionNum;
        String date;
        String A;
        String B;
        String C;
        String D;
        String E;
        String F;
        ComLogEntry[] entries;

        public CommunicationsLog(String name) {
            this.name = name;
            missionNum = "";
            date = "";
            A = "";
            B = "";
            C = "";
            D = "";
            E = "";
            F = "";
            entries = new ComLogEntry[GlobalConstants.COMLOG_ENTRY_COUNT];
            for (int i = 0; i < GlobalConstants.COMLOG_ENTRY_COUNT; i++) {
                entries[i] = new ComLogEntry();
            }
        }

        public static class ComLogEntry {
            String time;
            String call;
            String chRef;
            String remarks;

            public ComLogEntry() {
                time = "";
                call = "";
                chRef = "";
                remarks = "";
            }
        }
    }

    public static class RadioMessage {
        String name;
        Header header;
        String message;
        MessageRecieved messageRecieved;
        MessageSent messageSent;

        public RadioMessage(String name) {
            this.name = name;
            header = new Header();
            message = "";
            messageRecieved = new MessageRecieved();
            messageSent = new MessageSent();
        }

        public static class Header {
            String MsgNum;
            String precedence;
            String dtg;
            String from;
            String to;
            String info;
            String subj;
            String groupCnt;

            public Header() {
                MsgNum = "";
                precedence = "";
                dtg = "";
                from = "";
                to = "";
                info = "";
                subj = "";
                groupCnt = "";
            }
        }

        public static class MessageRecieved {
            String from;
            String dtg;
            String RecievingOperatorInitials;

            public MessageRecieved() {
                from = "";
                dtg = "";
                RecievingOperatorInitials = "";
            }
        }

        public static class MessageSent {
            String to;
            String dtg;
            String sendingOperatorInitials;

            public MessageSent() {
                to = "";
                dtg = "";
                sendingOperatorInitials = "";
            }
        }
    }

    public static class SearchAndRescue {

        public String name;
        public Header header;
        public Alpha alpha;
        public Bravo bravo;
        public Charlie charlie;

        public SearchAndRescue(String name) {
            this.name = name;
            header = new Header();
            alpha = new Alpha();
            bravo = new Bravo();
            charlie = new Charlie();
        }

        static public class Header {
            public String missionNumber;
            public String activityForDateOf;
            public String reportedBy;
            public String dateTime;

            public Header() {
                missionNumber = "";
                activityForDateOf = "";
                reportedBy = "";
                dateTime = "";
            }
        }

        static public class Alpha {
            String NameOfSearchOrg;

            public Alpha() {
                NameOfSearchOrg = "";
            }
        }

        static public class Bravo {
            public String timeDispatched;
            public String timeELTFirstHeard;
            public String numAircraft;
            public String numSorties;
            public String hoursInSearchArea;
            public String hoursEnroute;
            public String totalFlightHours;
            public String totalPersonnel;
            public String[][] areaSearch;
            public String other;
            public String significantWeather;

            public Bravo() {
                timeDispatched = "";
                timeELTFirstHeard = "";
                numAircraft = "";
                numSorties = "";
                hoursInSearchArea = "";
                hoursEnroute = "";
                totalFlightHours = "";
                totalPersonnel = "";
                areaSearch = new String[GlobalConstants.AREASEARCH_COLUMNS][GlobalConstants.AREASEARCH_ROWS];
                for (int c = 0; c < GlobalConstants.AREASEARCH_COLUMNS; c++) {
                    for (int r = 0; r < GlobalConstants.AREASEARCH_ROWS; r++) {
                        areaSearch[c][r] = "";
                    }
                }
                other = "";
                significantWeather = "";
            }
        }

        static public class Charlie {
            public String totalResourcesExpectedACPT;
            public String totalResourcesExpectedPersonnel;
            public String plannedSearchArea;
            public String forcastedWeather;

            public Charlie() {
                totalResourcesExpectedACPT = "";
                totalResourcesExpectedPersonnel = "";
                plannedSearchArea = "";
                forcastedWeather = "";
            }
        }
    }

    public static class LocatingData {

        public String name;
        public Delta delta;
        public Echo echo;
        public Foxtrot foxtrot;
        public Golf golf;

        public LocatingData(String name) {
            this.name = name;
            delta = new Delta();
            echo = new Echo();
            foxtrot = new Foxtrot();
            golf = new Golf();
        }

        static public class Delta {
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

        static public class Echo {
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

        static public class Foxtrot {
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

        static public class Golf {
            public String additionalRemarks;

            public Golf() {
                additionalRemarks = "";
            }
        }
    }
}
