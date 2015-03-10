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

import java.util.Calendar;

public class HourAndMin {
    public int hour;
    public int min;

    public HourAndMin(int hour, int min) {
        this.hour = hour;
        this.min = min;
    }

    // Matches valid times of format "00:00", "0:00", "00:0", "0:0"
    public static boolean isValidTimeColumnField(String time) {
        return (sanitizeTimeColumnFieldToInts(time) != null);
    }

    // Fixes times of format "0:00", "00:0", "0:0" to format "00:00" or returns
    // null if they are invalid
    public static HourAndMin sanitizeTimeColumnFieldToInts(String time) {
        // Remove surrounding whitespace
        String trimtime = time.trim();
        // Must have either a colon or no
        if (trimtime.contains(":")) {
            String[] hm = trimtime.split(":");
            // split must result in only two strings
            if (hm.length == 2) {
                try {
                    int h = Integer.parseInt(hm[0]);
                    int m = Integer.parseInt(hm[1]);
                    if ((h >= 0 && h <= 23) && (m >= 0 && m <= 60)) {
                        return new HourAndMin(h, m);
                    }
                } catch (NumberFormatException e) {
                    // Do nothing, function end will return null
                }
            }
        } else if (trimtime.length() == 4) {
            String hours = trimtime.substring(0, 2);
            String minutes = trimtime.substring(2, 4);
            try {
                int h = Integer.parseInt(hours);
                int m = Integer.parseInt(minutes);
                if ((h >= 0 && h <= 23) && (m >= 0 && m <= 60)) {
                    return new HourAndMin(h, m);
                }
            } catch (NumberFormatException e) {
                // Do nothing, function end will return null
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hour, min);
    }

    public boolean IsPastByXMinutes(int x) {
        Calendar currentTime = Calendar.getInstance();
        int currentHour = currentTime.get(Calendar.HOUR_OF_DAY);
        int currentMin = currentTime.get(Calendar.MINUTE);
        int currentTotal = 60 * currentHour + currentMin;
        int thisTotal = 60 * hour + min;
        return currentTotal < (thisTotal + x);
    }
}
