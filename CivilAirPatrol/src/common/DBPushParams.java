/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.Serializable;

import forms.ScheduledPushModelAbstraction;

/**
 *
 * @author Robert
 */
public class DBPushParams implements Serializable {
    private static final long serialVersionUID = 1318721442843405476L;
    public ScheduledPushModelAbstraction.FormType type;
    public String json;
    public int id;
    public String missionNo;
    public long date;

    public DBPushParams(ScheduledPushModelAbstraction.FormType type,
            String json, int id, String missionNo, long date) {
        this.type = type;
        this.json = json;
        this.id = id;
        this.missionNo = missionNo;
        this.date = date;
    }
}
