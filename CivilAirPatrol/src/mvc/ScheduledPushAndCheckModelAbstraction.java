package mvc;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import common.GlobalConstants;

public abstract class ScheduledPushAndCheckModelAbstraction extends ScheduledPushModelAbstraction {

    public interface OnIntervalListener {
        public void onInterval();
    }

    private OnIntervalListener onIntervalListener;

    private final ScheduledExecutorService scheduler;
    public final ScheduledFuture<?> checkerHandle;

    protected ScheduledPushAndCheckModelAbstraction() {
        super();
        scheduler = Executors.newScheduledThreadPool(1);

        final Runnable checker = new Runnable() {
            public void run() {
                if (onIntervalListener != null) {
                    onIntervalListener.onInterval();
                }
            }
        };
        checkerHandle = scheduler.scheduleAtFixedRate(checker, GlobalConstants.CHECK_DELAY,
                GlobalConstants.CHECK_DELAY, GlobalConstants.CHECK_DELAY_UNITS);
    }

    public void setOnIntervalListener(OnIntervalListener l) {
        this.onIntervalListener = l;
    }

}
