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

import java.util.concurrent.TimeUnit;

public class GlobalConstants {
    // Time between pushing updating information and sending it over the network
    public static final int PUSH_DELAY = 3;
    public static final TimeUnit PUSH_DELAY_UNITS = TimeUnit.SECONDS;

    // Time between checks for new information in fields that don't have
    // listeners
    public static final int CHECK_DELAY = 2;
    public static final TimeUnit CHECK_DELAY_UNITS = TimeUnit.SECONDS;

    // Columns and rows for the "Area(s) Searched: Grid/Pod (%)" area in SAR
    // Mission Report"
    public static final int AREASEARCH_COLUMNS = 4;
    public static final int AREASEARCH_ROWS = 5;

    // number of entries in the communication log table
    public static final int COMLOG_ENTRY_COUNT = 30;
    public static final String COMMUNICATIONS_LOG_TYPE = "CommunicationsLog";
    public static final String RADIO_MESSAGE_TYPE = "RadioMessage";
    public static final String SEARCH_AND_RESCUE_TYPE = "SearchAndRescue";

    public static final String DAY_FORMAT = "dd";
    public static final String MONTH_FORMAT = "MMM";
    public static final String YEAR_FORMAT = "yyyy";
    public static final String TIME_FORMAT = "HH:mm";
    public static final String DATETIME_FORMAT = DAY_FORMAT + " "
            + MONTH_FORMAT + ". " + YEAR_FORMAT + ", " + TIME_FORMAT;

    // network
    public static final int DEFAULT_PORT = 8117;
    public static final String DEFAULT_ADDRESS = "localhost";// "224.6.3.33";
    public static final long ASSET_TRACKER_AUTOCHECK_PERIOD = 30000; // 30
                                                                     // seconds
    public static final long ASSET_TRACKER_SLEEP = 3000; // 3 seconds
    public static final int ASSET_TRACKER_EXPIRATION_TIME = 30; // 10 minutes
    public static final int USERNAME_MAX_LEN = 16;
    public static final int PASSWORD_MAX_LEN = 8;
    public static String USERNAME = "DEFAULT USER";
}
