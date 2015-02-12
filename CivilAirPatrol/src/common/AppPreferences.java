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
        return Integer.parseInt(prefs.get("PORT", GlobalConstants.DEFAULT_PORT + ""));
    }

    public static void setPort(int port) {
        Preferences prefs = Preferences.userRoot().node("CAPFormTracker");
        prefs.put("PORT", port + "");
    }
}
