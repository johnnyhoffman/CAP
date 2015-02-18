package forms;

import java.util.concurrent.ScheduledThreadPoolExecutor;


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
        // TODO: HERE IS WHERE WE HOOK IN DATABASE CONNECTION.
        // INSTEAD OF PRINTING THE JSON, PUSH IT TO THE DATABASE.
        DBPushParams pushParams = prepareForPush();
        
        //This will be the hook for sending the network push...i believe? check with johnny
        //create a  new GuiMessage(pushParams); and send the guiMessage to server
        
        System.out.println(pushParams.json + "\n" + pushParams.id + "\n"
                + pushParams.missionNo + "\n" + pushParams.date);

        switch (pushParams.type) {
        case CL:
            database.sqlServer.UpdateCommLog(pushParams.json, pushParams.id,
                    pushParams.missionNo, pushParams.date);
            break;
        case RM:
            database.sqlServer.UpdateRADMESS(pushParams.json, pushParams.id,
                    pushParams.missionNo, pushParams.date);
            break;
        case SAR:
            database.sqlServer.UpdateSAR(pushParams.json, pushParams.id,
                    pushParams.missionNo, pushParams.date);
            break;
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