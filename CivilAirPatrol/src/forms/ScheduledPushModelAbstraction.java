package forms;

import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import network.ClientSocket;
import network.GuiMessage;
import common.DBPushParams;
import common.GlobalConstants;

public abstract class ScheduledPushModelAbstraction {

    public enum FormType {
        SAR, // Search and Rescue
        CL, // Communication Log
        RM // Radio Message
    }

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
        DBPushParams pushParams = prepareForPush();
        try {
            ClientSocket.getInstance().output
                    .writeObject(new GuiMessage(pushParams));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void setOnModelLoadListener(OnModelLoadListener l) {
        onModelLoadListener = l;
    }

    protected void modelLoaded() {
        if (onModelLoadListener != null) {
            onModelLoadListener.onModelLoad();
        }
    }

    public abstract DBPushParams prepareForPush();
}
