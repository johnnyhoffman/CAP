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
