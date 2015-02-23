package assets;

import java.util.List;

/* Placeholder for demonstrating Session MVC */
public class AssetsModel {

    public interface IncomingAssetDataListener {
        public void onIncomingAssetData(List<String> overdue,
                List<String> underdue);
    }

    private IncomingAssetDataListener incomingAssetDataListener;
    private String missionNo;

    public AssetsModel() {
        incomingAssetDataListener = null;
    }

    public void setIncomingAssetDataListener(IncomingAssetDataListener l) {
        incomingAssetDataListener = l;
    }

    public void setNewMissionNo(String missionNo) {
        if (this.missionNo == null || ! this.missionNo.equals(missionNo)) {
            this.missionNo = missionNo;
            //TODO: Register missionNo with server.
        }
    }

}
