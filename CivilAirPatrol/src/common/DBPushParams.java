/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import forms.ScheduledPushModelAbstraction;

/**
 *
 * @author Robert
 */
    public class DBPushParams {
        public ScheduledPushModelAbstraction.FormType type;
        public String json;
        public int id;
        public String missionNo;
        public String date;

        public DBPushParams(ScheduledPushModelAbstraction.FormType type, String json, int id, String missionNo,
                String date) {
            this.type = type;
            this.json = json;
            this.id = id;
            this.missionNo = missionNo;
            this.date = date;
        }
    }
