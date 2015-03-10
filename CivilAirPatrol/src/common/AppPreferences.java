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

import java.util.prefs.Preferences;

public class AppPreferences {
    private AppPreferences() {
    };

    //
    // public static void initPrefs() {
    //
    // }

    public static String getIP() {
        Preferences prefs = Preferences.userRoot().node("CAPFormTracker");
        return prefs.get("IP", GlobalConstants.DEFAULT_ADDRESS);
    }

    public static void setIP(String ip) {
        Preferences prefs = Preferences.userRoot().node("CAPFormTracker");
        prefs.put("IP", ip);
    }

    public static int getPort() {
        Preferences prefs = Preferences.userRoot().node("CAPFormTracker");
        return Integer.parseInt(prefs.get("PORT", GlobalConstants.DEFAULT_PORT
                + ""));
    }

    public static void setPort(int port) {
        Preferences prefs = Preferences.userRoot().node("CAPFormTracker");
        prefs.put("PORT", port + "");
    }
}
