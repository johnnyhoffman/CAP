/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import forms.ScheduledPushModelAbstraction;

/**
 *
 * @author Robert
 */
public class DBRequest {
        public boolean SAR;
        public boolean RAD;
        public boolean COMM;
        public String missionNo;
        public long startDate;
        public long endDate;
    
        public DBRequest(boolean sar, boolean rad, boolean comm, String no, long start, long end){
            this.SAR = sar;
            this.COMM = comm;
            this.RAD = rad;
            this.missionNo = no;
            this.startDate = start;
            this.endDate = end;
        }
}
