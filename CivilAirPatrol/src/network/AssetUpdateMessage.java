package network;

import java.util.List;

import assets.AssetStatus;

public class AssetUpdateMessage extends NetworkMessage {
    private static final long serialVersionUID = 1931886528218842953L;
    private List<AssetStatus> overdue;
    private List<AssetStatus> underdue;

    public AssetUpdateMessage(List<AssetStatus> overdue, List<AssetStatus> underdue) {
        super(MessageType.ASSET_UPDATE);
        this.overdue = overdue;
        this.underdue = underdue;
    }

    public List<AssetStatus> getOverdue() {
        return overdue;
    }

    public List<AssetStatus> getUnderdue() {
        return underdue;
    }
}