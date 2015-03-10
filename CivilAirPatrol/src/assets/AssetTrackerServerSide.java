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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import network.ClientConnection.OnAssetUpdateListener;

import com.google.gson.Gson;

import common.DBPushParams;
import common.DataContainers;
import common.DataContainers.CommunicationsLog;
import common.GlobalConstants;
import common.HourAndMin;
import database.sqlServer;

public class AssetTrackerServerSide extends Thread {
    private OnAssetUpdateListener onAssetUpdateListener;
    private String missionNo;
    private boolean newUpdates;
    private Gson gson;
    public boolean run = true;

    public AssetTrackerServerSide(OnAssetUpdateListener onAssetUpdateListener) {
        gson = new Gson();
        this.missionNo = "";
        this.newUpdates = true;
        this.onAssetUpdateListener = onAssetUpdateListener;
    }

    // MissionNo and loop that checks for updates to that assets with that
    // mission no must not execute at the same time
    public synchronized void setMissionNo(String missionNo) {
        this.missionNo = missionNo;
        this.newUpdates = true;
    }

    public synchronized void checkForUpdates(String no) {
        if (missionNo.equals(no)) {
            newUpdates = true;
        }
    }

    public static long getTimeUntilUpdate(long lastUpdate) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        return lastUpdate + GlobalConstants.ASSET_TRACKER_AUTOCHECK_PERIOD
                - currentTime;
    }

    @Override
    public void run() {
        long lastUpdate = 0;
        while (run) {
            // missionNo can't be changed while this block executes
            synchronized (this) {
                long timeUntilUpdate = getTimeUntilUpdate(lastUpdate);
                if (AssetColorSingletonForServer.getInstance().wasTouched()
                        || newUpdates || timeUntilUpdate < 0) {
                    lastUpdate = Calendar.getInstance().getTimeInMillis();
                    List<AssetStatus> overdue = new ArrayList<AssetStatus>();
                    List<AssetStatus> underdue = new ArrayList<AssetStatus>();
                    newUpdates = false;
                    if (missionNo.isEmpty()) {
                        if (onAssetUpdateListener != null) {
                            overdue.add(new AssetStatus(
                                    "Empty Mission Number Field", null,
                                    Color.red));
                            onAssetUpdateListener.onAssetUpdate(overdue,
                                    underdue);
                        }
                    } else {
                        List<DBPushParams> clPushParams = sqlServer
                                .SelectFromCommLogWithMissionNum(missionNo);
                        HashMap<String, String> assetTimes = new HashMap<String, String>();
                        if (clPushParams.size() == 0) {
                            overdue.add(new AssetStatus(
                                    "No Communication Logs with Mission number '"
                                            + missionNo + "'", null, Color.red));
                            onAssetUpdateListener.onAssetUpdate(overdue,
                                    underdue);
                        } else {
                            for (DBPushParams clPushParam : clPushParams) {
                                CommunicationsLog cl = gson.fromJson(
                                        clPushParam.json,
                                        DataContainers.CommunicationsLog.class);
                                for (int i = 0; i < cl.entries.length; i++) {
                                    String call = cl.entries[i].call.trim();
                                    HourAndMin time = HourAndMin
                                            .sanitizeTimeColumnFieldToInts(cl.entries[i].time
                                                    .trim());
                                    // If we have valid time field and
                                    // nonempty
                                    // call
                                    if (time != null && !call.isEmpty()) {
                                        if (assetTimes.containsKey(call)) {
                                            String oldtime = assetTimes
                                                    .get(call);
                                            if (oldtime.compareTo(time
                                                    .toString()) < 0) {
                                                assetTimes.put(call,
                                                        time.toString());
                                            }
                                        } else {
                                            assetTimes.put(call,
                                                    time.toString());
                                        }
                                    }
                                }
                            }
                            // Sort entries of the hashMap
                            List<Entry<String, String>> entries = new ArrayList<Entry<String, String>>(
                                    assetTimes.entrySet());
                            Collections.sort(entries,
                                    new Comparator<Entry<String, String>>() {

                                        @Override
                                        public int compare(
                                                Entry<String, String> arg0,
                                                Entry<String, String> arg1) {
                                            // Just compare time
                                            // values
                                            return arg0.getValue().compareTo(
                                                    arg1.getValue());
                                        }
                                    });
                            List<AssetStatus> listToAddTo = overdue;
                            for (Entry<String, String> e : entries) {
                                HourAndMin time = HourAndMin
                                        .sanitizeTimeColumnFieldToInts(e
                                                .getValue());
                                // TODO Might want
                                // GlobalConstants.ASSET_TRACKER_EXPIRATION_TIME
                                // to be replaced by a configurable value
                                if (time.IsPastByXMinutes(GlobalConstants.ASSET_TRACKER_EXPIRATION_TIME)) {
                                    listToAddTo = underdue;
                                    // no longer timed out should be set back to
                                    // black
                                    if (AssetColorSingletonForServer
                                            .getInstance()
                                            .get(missionNo, e.getKey())
                                            .equals(Color.red)) {
                                        AssetColorSingletonForServer
                                                .getInstance()
                                                .put(missionNo, e.getKey(),
                                                        Color.black);
                                    }
                                } else { // set timed out to red
                                    AssetColorSingletonForServer.getInstance()
                                            .put(missionNo, e.getKey(),
                                                    Color.red);
                                }
                                listToAddTo.add(new AssetStatus(e.getKey(), e
                                        .getValue(),
                                        AssetColorSingletonForServer
                                                .getInstance().get(missionNo,
                                                        e.getKey())));
                            }
                            // Final send the lists
                            onAssetUpdateListener.onAssetUpdate(overdue,
                                    underdue);
                        }
                    }
                }
            }
            try {
                sleep(GlobalConstants.ASSET_TRACKER_SLEEP);
            } catch (InterruptedException e) {
                System.out.println("Asset Tracker sleep interupted.");
            }
        }
    }
}