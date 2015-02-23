package assets;

import java.awt.Component;
import java.util.List;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import mvcCommon.IController;
import assets.AssetsModel.IncomingAssetDataListener;

/* Placeholder for demonstrating Session MVC */
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

    public static void onClose() {
    }

}