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

import java.util.List;

import assets.AssetStatus;

public class AssetUpdateMessage extends NetworkMessage {
    private static final long serialVersionUID = 1931886528218842953L;
    private List<AssetStatus> overdue;
    private List<AssetStatus> underdue;

    public AssetUpdateMessage(List<AssetStatus> overdue,
            List<AssetStatus> underdue) {
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