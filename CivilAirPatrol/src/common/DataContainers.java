/**
 *  Copyright 2015 Dana Vold, Johnny Hoffman, Robert Wassenaar
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package common;

public class DataContainers {

    public static class CommunicationsLog {
        public String name;
        public String type = GlobalConstants.COMMUNICATIONS_LOG_TYPE;
        public String missionNum;
        public String stationFunctionalDesignator;
        public long date;
        public String A;
        public String B;
        public String C;
        public String D;
        public String E;
        public String F;
        public ComLogEntry[] entries;

        public CommunicationsLog(String name) {
            this.name = name;
            missionNum = "";
            stationFunctionalDesignator = "";
            date = 0;
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
            public String time;
            public String call;
            public String chRef;
            public String remarks;

            public ComLogEntry() {
                time = "";
                call = "";
                chRef = "";
                remarks = "";
            }
        }
    }

    public static class RadioMessage {
        public String type = GlobalConstants.RADIO_MESSAGE_TYPE;
        public String name;
        public Header header;
        public String message;
        public MessageRecieved messageRecieved;
        public MessageSent messageSent;

        public RadioMessage(String name) {
            this.name = name;
            header = new Header();
            message = "";
            messageRecieved = new MessageRecieved();
            messageSent = new MessageSent();
        }

        public static class Header {
            public String missionNo;
            public String msgNum;
            public String precedence;
            public long dtg; // XXX: Is this the date
            public String from;
            public String to;
            public String info;
            public String subj;
            public String groupCnt;

            public Header() {
                missionNo = "";
                msgNum = "";
                precedence = "";
                dtg = 0;
                from = "";
                to = "";
                info = "";
                subj = "";
                groupCnt = "";
            }
        }

        public static class MessageRecieved {
            public String from;
            public String dtg;
            public String RecievingOperatorInitials;

            public MessageRecieved() {
                from = "";
                dtg = "";
                RecievingOperatorInitials = "";
            }
        }

        public static class MessageSent {
            public String to;
            public String dtg;
            public String sendingOperatorInitials;

            public MessageSent() {
                to = "";
                dtg = "";
                sendingOperatorInitials = "";
            }
        }
    }

    public static class SearchAndRescue {
        public String type = GlobalConstants.SEARCH_AND_RESCUE_TYPE;
        public String name;
        public Header header;
        public Alpha alpha;
        public Bravo bravo;
        public Charlie charlie;
        public Delta delta;
        public Echo echo;
        public Foxtrot foxtrot;
        public Golf golf;

        public SearchAndRescue(String name) {
            this.name = name;
            header = new Header();
            alpha = new Alpha();
            bravo = new Bravo();
            charlie = new Charlie();
            delta = new Delta();
            echo = new Echo();
            foxtrot = new Foxtrot();
            golf = new Golf();
        }

        static public class Header {
            public String missionNumber;
            public String activityForDateOf;
            public String reportedBy;
            public long dateTime;

            public Header() {
                missionNumber = "";
                activityForDateOf = "";
                reportedBy = "";
                dateTime = 0;
            }
        }

        static public class Alpha {
            public String nameOfSearchOrg1;
            public String nameOfSearchOrg2;
            public String nameOfSearchOrg3;

            public Alpha() {
                nameOfSearchOrg1 = "";
                nameOfSearchOrg2 = "";
                nameOfSearchOrg3 = "";
            }
        }

        static public class Bravo {
            public String timeDispatched1;
            public String timeDispatched2;
            public String timeDispatched3;
            public String timeELTFirstHeard1;
            public String timeELTFirstHeard2;
            public String timeELTFirstHeard3;
            public String numAircraft1;
            public String numAircraft2;
            public String numAircraft3;
            public String numSorties1;
            public String numSorties2;
            public String numSorties3;
            public String hoursInSearchArea1;
            public String hoursInSearchArea2;
            public String hoursInSearchArea3;
            public String hoursEnroute1;
            public String hoursEnroute2;
            public String hoursEnroute3;
            public String totalFlightHours1;
            public String totalFlightHours2;
            public String totalFlightHours3;
            public String totalPersonnel1;
            public String totalPersonnel2;
            public String totalPersonnel3;
            public String[][] areaSearch;
            public String other;
            public String significantWeather;

            public Bravo() {
                timeDispatched1 = "";
                timeDispatched2 = "";
                timeDispatched3 = "";
                timeELTFirstHeard1 = "";
                timeELTFirstHeard2 = "";
                timeELTFirstHeard3 = "";
                numAircraft1 = "";
                numAircraft2 = "";
                numAircraft3 = "";
                numSorties1 = "";
                numSorties2 = "";
                numSorties3 = "";
                hoursInSearchArea1 = "";
                hoursInSearchArea2 = "";
                hoursInSearchArea3 = "";
                hoursEnroute1 = "";
                hoursEnroute2 = "";
                hoursEnroute3 = "";
                totalFlightHours1 = "";
                totalFlightHours2 = "";
                totalFlightHours3 = "";
                totalPersonnel1 = "";
                totalPersonnel2 = "";
                totalPersonnel3 = "";
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
