package mvc;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import mvc.DataContainers.CommunicationsLog.ComLogEntry;

/* Placeholder for demonstrating Session MVC */
public class CommLogController implements Controller {

    private CommLogView view;
    private CommLogModel model;

    public CommLogController(String name) {
        view = new CommLogView();
        view.setName(name);
        model = new CommLogModel(name);
        setListeners();
    }

    @Override
    public FormComponent getViewComponent() {
        return view;
    }

    /*
     * Set the listeners for all of the actions we want to keep track of in the
     * view.
     */
    public void setListeners() {
        view.addMissionNumChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateMissionNum(view.getMissionNumText());
            }
        });
        view.addStationFunctionalDesignatorChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateStationFunctionalDesignator(view.getStationFunctionalDesignatorText());
            }
        });
        view.addDateChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateDate(view.getDateText());
            }
        });
        view.addAChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateA(view.getAText());
            }
        });
        view.addBChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateB(view.getBText());
            }
        });
        view.addCChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateC(view.getCText());
            }
        });
        view.addDChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateD(view.getDText());
            }
        });
        view.addEChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateE(view.getEText());
            }
        });
        view.addFChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateF(view.getFText());
            }
        });

        view.setOnCloseListener(new FormComponent.OnCloseListener() {

            @Override
            public void onClose() {
                checkEntries();
                model.checkerHandle.cancel(true);
                model.push();
            }
        });

        model.setOnIntervalListener(new ScheduledPushAndCheckModelAbstraction.OnIntervalListener() {
            @Override
            public void onInterval() {
                checkEntries();
            }
        });

    }

    private void checkEntries() {
        ComLogEntry[] viewEntries = view.getEntries();
        ComLogEntry[] modelEntries = model.getEntries();

//        for (int i = 0; i < GlobalConstants.COMLOG_ENTRY_COUNT; i++) {
//            if (!viewEntries[i].time.equals(modelEntries[i].time) || !viewEntries[i].call.equals(modelEntries[i].call)
//                    || !viewEntries[i].chRef.equals(modelEntries[i].chRef)
//                    || !viewEntries[i].remarks.equals(modelEntries[i].remarks)) {
//                model.updateEntries(viewEntries);
//                return;
//            }
//        }
    }
}