package assets;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import network.AssetColorSetMessage;
import network.ClientSocket;
import network.RegisterMissionNoMessage;
import common.OnConnectionErrorListener;

public class AssetsModel {

    public interface IncomingAssetDataListener {
        public void onIncomingAssetData(List<AssetStatus> overdue,
                List<AssetStatus> underdue);
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

    public void setLists(List<AssetStatus> overdue, List<AssetStatus> underdue) {
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

    public void setAssetColor(String missionNo, String asset, Color color) {
        try {
            ClientSocket.getInstance().output
                    .writeObject(new AssetColorSetMessage(missionNo, asset, color));
        } catch (IOException e) {
            onConnectionError();
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
