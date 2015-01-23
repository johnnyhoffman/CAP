package mvc;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public abstract class ScheduledPushAndCheckModelAbstraction extends ScheduledPushModelAbstraction {

    private final ScheduledExecutorService scheduler;
    public final ScheduledFuture<?> beeperHandle;
    protected ScheduledPushAndCheckModelAbstraction() {
        super();
        scheduler = Executors.newScheduledThreadPool(1);

        final Runnable beeper = new Runnable() {
            public void run() {
                System.out.println("beep");
            }
        };
        beeperHandle = scheduler.scheduleAtFixedRate(beeper, 1, 1, TimeUnit.SECONDS);
        scheduler.schedule(new Runnable() {
            public void run() {
                beeperHandle.cancel(true);
            }
        }, 60 * 60, TimeUnit.SECONDS);
    }
    // GlobalConstants.CHECK_DELAY, GlobalConstants.CHECK_DELAY_UNITS);

}
