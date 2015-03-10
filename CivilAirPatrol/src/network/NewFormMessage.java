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

import forms.ScheduledPushModelAbstraction.FormType;

public class NewFormMessage extends NetworkMessage {
    private static final long serialVersionUID = 6246753614374221315L;
    private FormType formType;
    private String missionNo;
    private long date;

    public NewFormMessage(FormType formType, String missionNo, long date) {
        super(MessageType.NEW_FORM);
        this.missionNo = missionNo;
        this.date = date;
        this.formType = formType;
    }

    public FormType getFormType() {
        return formType;
    }

    public String getMissionNo() {
        return missionNo;
    }

    public long getDate() {
        return date;
    }
}