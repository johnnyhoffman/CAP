package common;

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
        // Must have a colon
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
        }
        return null;
    }
    
    @Override
    public String toString() {
        return String.format("%02d:%02d", hour, min);
    }
}