package mvc;

import java.awt.Component;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

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
        //TODO: Add change listeners for "entries" fields
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

                model.beeperHandle.cancel(true);
                model.push();
            }
        });

    }
}