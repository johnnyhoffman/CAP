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
                    .writeObject(new AssetColorSetMessage(missionNo, asset,
                            color));
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
