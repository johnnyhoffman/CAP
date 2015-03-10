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
