/**
 *  Copyright 2015 Dana Vold, Johnny Hoffman, Robert Wassenaar
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

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
        if (missionNoToAssetToColor.containsKey(missionNo)) {
            HashMap<String, Color> assetToColor = missionNoToAssetToColor
                    .get(missionNo);
            if (assetToColor.containsKey(asset)) {
                return assetToColor.get(asset);
            }
        }
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