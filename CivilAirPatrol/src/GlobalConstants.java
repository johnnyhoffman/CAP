import java.util.concurrent.TimeUnit;

public class GlobalConstants {
    // Time between pushing updating information and sending it over the network
    public static final int PUSH_DELAY = 3;
    public static final TimeUnit PUSH_DELAY_UNITS = TimeUnit.SECONDS;

    // Columns and rows for the "Area(s) Searched: Grid/Pod (%)" area in SAR
    // Mission Report"
    public static final int AREASEARCH_COLUMNS = 4;
    public static final int AREASEARCH_ROWS = 5;
    
    //
    public static final int COMLOG_ENTRY_COUNT = 30;
}
