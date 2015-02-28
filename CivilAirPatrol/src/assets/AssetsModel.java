package assets;

import java.io.IOException;
import java.util.List;

import common.OnConnectionErrorListener;

import network.ClientSocket;
import network.RegisterMissionNoMessage;

public class AssetsModel {

    public interface IncomingAssetDataListener {
        public void onIncomingAssetData(List<String> overdue,
                List<String> underdue);
    }

    private IncomingAssetDataListener incomingAssetDataListener;
    private String missionNo;
    private OnConnectionErrorListener onConnectionErrorListener;

    public AssetsModel() {
        incomingAssetDataListener = null;
    }

    public void setIncomingAssetDataListener(IncomingAssetDataListener l) {
        incomingAssetDataListener = l;
    }

    public void setLists(List<String> overdue, List<String> underdue) {
        if (incomingAssetDataListener != null) {
            incomingAssetDataListener.onIncomingAssetData(overdue, underdue);
        }
    }

    public void setNewMissionNo(String missionNo) {
        if (this.missionNo == null || !this.missionNo.equals(missionNo)) {
            this.missionNo = missionNo;
            try {
                ClientSocket.getInstance().output
                        .writeObject(new RegisterMissionNoMessage(missionNo));
            } catch (IOException e) {
                onConnectionError();
            }
        }
    }

    public void setConnectionErrorListener(OnConnectionErrorListener l) {
        this.onConnectionErrorListener = l;
    }

    private void onConnectionError() {
        if (onConnectionErrorListener != null) {
            onConnectionErrorListener.onConnectionError();
        }
    }

}
