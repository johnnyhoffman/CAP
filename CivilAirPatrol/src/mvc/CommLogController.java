package mvc;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import com.google.gson.JsonObject;

import mvc.DataContainers.CommunicationsLog.ComLogEntry;
import mvc.ScheduledPushModelAbstraction.OnModelLoadListener;

/* Placeholder for demonstrating Session MVC */
public class CommLogController implements IFormController {

    private CommLogView view;
    private CommLogModel model;

    public CommLogController(int id, String name) {
        view = new CommLogView();
        view.setName(name);
        model = new CommLogModel(id, name);
        setListeners();
    }

    public CommLogController(int id, String name, String missionNo, String date) {
        view = new CommLogView();
        view.setName(name);
        model = new CommLogModel(id, name, missionNo, date);
        setListeners();
        refreshViewFromModel();
    }

    public CommLogController(int id, JsonObject json) {
        view = new CommLogView();
        view.setName(json.get("name").getAsString());
        model = new CommLogModel(id, json);
        setListeners();
        refreshViewFromModel();
    }

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
                model.updateStationFunctionalDesignator(view
                        .getStationFunctionalDesignatorText());
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

        model.setOnModelLoadListener(new OnModelLoadListener() {
            @Override
            public void onModelLoad() {
                refreshViewFromModel();
            }
        });

    }

    private void checkEntries() {
        ComLogEntry[] viewEntries = view.getEntries();
        ComLogEntry[] modelEntries = model.getEntries();

        for (int i = 0; i < GlobalConstants.COMLOG_ENTRY_COUNT; i++) {
            if (!viewEntries[i].time.equals(modelEntries[i].time)
                    || !viewEntries[i].call.equals(modelEntries[i].call)
                    || !viewEntries[i].chRef.equals(modelEntries[i].chRef)
                    || !viewEntries[i].remarks.equals(modelEntries[i].remarks)) {
                model.updateEntries(viewEntries);
                return;
            }
        }
    }

    public void refreshViewFromModel() {
        view.setName(model.getName());
        view.setMissionNumText(model.getMissionNum());
        view.setStationFunctionalDesignatorText(model
                .getStationFunctionalDesignator());
        view.setDateText(model.getDate());
        view.setAText(model.getA());
        view.setBText(model.getB());
        view.setCText(model.getC());
        view.setDText(model.getD());
        view.setEText(model.getE());
        view.setFText(model.getF());
        view.setEntries(model.getEntries());
    }

    @Override
    public int getID() {
        return model.getID();
    }

    @Override
    public void updateFromJson(JsonObject json) {
        model.jsonDeserialize(json);
    }
}