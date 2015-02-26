package network;

import java.util.List;

public class AssetUpdateMessage extends NetworkMessage {
    private static final long serialVersionUID = 1931886528218842953L;
    private List<String> overdue;
    private List<String> underdue;

    public AssetUpdateMessage(List<String> overdue, List<String> underdue) {
        super(MessageType.ASSET_UPDATE);
        this.overdue = overdue;
        this.underdue = underdue;
    }

    public List<String> getOverdue() {
        return overdue;
    }
    
    public List<String> getUnderdue() {
        return underdue;
    }
}