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
import java.awt.Component;
import java.util.List;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import mvcCommon.IController;
import assets.AssetsModel.IncomingAssetDataListener;
import assets.AssetsView.OnNewAssetColorListener;

import common.OnConnectionErrorListener;

public class AssetsController implements IController {
    private AssetsView view;
    private AssetsModel model;

    public AssetsController() {
        this.view = new AssetsView();
        this.model = new AssetsModel();

        model.setIncomingAssetDataListener(new IncomingAssetDataListener() {
            @Override
            public void onIncomingAssetData(List<AssetStatus> overdue,
                    List<AssetStatus> underdue) {
                view.setLists(overdue, underdue);
            }
        });

        view.setMissionChangedNoListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.setNewMissionNo(view.getMissionNo());
            }
        });

        view.setOnNewAssetColorListener(new OnNewAssetColorListener() {
            @Override
            public void newAssetColor(String missionNo, String asset,
                    Color color) {
                model.setAssetColor(missionNo, asset, color);
            }
        });
    }

    @Override
    public Component getViewComponent() {
        return view;
    }

    public void onClose() {
    }

    public void setOnConnectionErrorListener(OnConnectionErrorListener l) {
        model.setConnectionErrorListener(l);
    }

    public void setLists(List<AssetStatus> overdue, List<AssetStatus> underdue) {
        model.setLists(overdue, underdue);
    }

}