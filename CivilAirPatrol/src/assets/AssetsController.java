package assets;

import java.awt.Component;
import java.util.List;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import common.OnConnectionErrorListener;

import mvcCommon.IController;
import assets.AssetsModel.IncomingAssetDataListener;

public class AssetsController implements IController {

    private AssetsView view;
    private AssetsModel model;

    public AssetsController() {
        this.view = new AssetsView();
        this.model = new AssetsModel();

        model.setIncomingAssetDataListener(new IncomingAssetDataListener() {
            @Override
            public void onIncomingAssetData(List<String> overdue,
                    List<String> underdue) {
                view.setLists(overdue, underdue);
            }
        });

        view.setMissionChangedNoListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.setNewMissionNo(view.getMissionNo());
            }
        });
    }

    @Override
    public Component getViewComponent() {
        return view;
    }

    public void onClose() {
    }

    public void setOnConnectionErrorListener(OnConnectionErrorListener l) {
        model.setConnectionErrorListener(l);
    }

    public void setLists(List<String> overdue, List<String> underdue) {
        model.setLists(overdue, underdue);
    }

}