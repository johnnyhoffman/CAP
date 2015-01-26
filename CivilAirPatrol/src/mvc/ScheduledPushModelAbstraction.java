package mvc;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public abstract class ScheduledPushModelAbstraction {
    
    public interface OnModelLoadListener {
        public void onModelLoad();
    }

    private final ScheduledThreadPoolExecutor pushExecutor;
    private OnModelLoadListener onModelLoadListener;

    protected ScheduledPushModelAbstraction() {
        pushExecutor = new ScheduledThreadPoolExecutor(1);
    }

    /*
     * Schedule the data to be pushed to the server. Only schedules a push if
     * there is not already one scheduled.
     */
    public void schedulePush() {
        // Don't schedule if there are any runnables in the queue
        if (pushExecutor.getQueue().size() < 1) {
            pushExecutor.schedule(new Runnable() {
                @Override
                public void run() {
                    push();
                }
            }, GlobalConstants.PUSH_DELAY, GlobalConstants.PUSH_DELAY_UNITS);
        }
    }

    public void push() {
        // TODO: HERE IS WHERE WE HOOK IN DATABASE CONNECTION.
        // INSTEAD OF PRINTING THE JSON, PUSH IT TO THE DATABASE.
        System.out.println(jsonSerialize());
    }
    
    public void setOnModelLoadListener(OnModelLoadListener l) {
        onModelLoadListener = l;
    }
    
    protected void modelLoaded() {
        if (onModelLoadListener != null) {
            onModelLoadListener.onModelLoad();
        }
    }

    public abstract String jsonSerialize();
}
