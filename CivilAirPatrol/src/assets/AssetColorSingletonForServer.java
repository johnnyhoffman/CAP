package assets;

import java.awt.Color;
import java.util.HashMap;

public class AssetColorSingletonForServer {
    private static AssetColorSingletonForServer instance = null;
    private HashMap<String, HashMap<String, Color>> missionNoToAssetToColor;
    // used for checking for new updates from incoming messages
    private boolean touched;

    protected AssetColorSingletonForServer() {
        missionNoToAssetToColor = new HashMap<String, HashMap<String, Color>>();
    }

    public synchronized static AssetColorSingletonForServer getInstance() {
        if (instance == null) {
            instance = new AssetColorSingletonForServer();
        }
        return instance;
    }

    public synchronized Color get(String missionNo, String asset) {
        System.out.println(missionNo);
        System.out.println(asset);
        if (missionNoToAssetToColor.containsKey(missionNo)) {
            HashMap<String, Color> assetToColor = missionNoToAssetToColor
                    .get(missionNo);
            if (assetToColor.containsKey(asset)) {
                return assetToColor.get(asset);
            }
        }
        System.out.println();
        return Color.black;
    }

    public synchronized void put(String missionNo, String asset, Color color) {
        HashMap<String, Color> assetToColor;
        if (missionNoToAssetToColor.containsKey(missionNo)) {
            assetToColor = missionNoToAssetToColor.get(missionNo);
        } else {
            assetToColor = new HashMap<String, Color>();
            missionNoToAssetToColor.put(missionNo, assetToColor);
        }
        assetToColor.put(asset, color);
    }

    // check if touch() has been called
    public synchronized boolean wasTouched() {
        if (touched) {
            touched = false;
            return true;
        }
        return false;
    }

    // check if touch has been called
    public synchronized void touch() {
        touched = true;
    }
}