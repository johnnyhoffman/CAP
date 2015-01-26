/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

/**
 *
 * @author Robert
 */
    public class DBPushParams {
        public ScheduledPushModelAbstraction.FormType type;
        public String json;
        public int id;
        public int missionNo;
        public String date;

        public DBPushParams(ScheduledPushModelAbstraction.FormType type, String json, int id, int missionNo,
                String date) {
            this.type = type;
            this.json = json;
            this.id = id;
            this.missionNo = missionNo;
            this.date = date;
        }
    }
