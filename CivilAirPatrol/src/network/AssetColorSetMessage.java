package network;

import java.awt.Color;

public class AssetColorSetMessage extends NetworkMessage {
    private static final long serialVersionUID = 1511803160878828604L;
    private String missionNo;
    private String name;
    private Color color;

    public AssetColorSetMessage(String missionNo, String name, Color color) {
        super(MessageType.ASSET_COLOR_SET);
        this.missionNo = missionNo;
        this.name = name;
        this.color = color;
    }

    public String getMissionNo() {
        return missionNo;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}
