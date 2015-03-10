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

package forms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import network.ClientSocket;
import network.NewFormMessage;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import common.DBPushParams;
import common.OnConnectionErrorListener;

import forms.ScheduledPushModelAbstraction.FormType;

/* Placeholder for demonstrating Session MVC */
public class FormsModel {

    private List<IFormController> formControllers;
    private JsonParser jsonParser;
    private OnConnectionErrorListener onConnectionErrorListener;

    FormsModel() {
        jsonParser = new JsonParser();
        formControllers = new ArrayList<IFormController>();
    }

    public boolean has(int id) {
        for (IFormController c : formControllers) {
            if (c.getID() == id) {
                return true;
            }
        }
        return false;
    }

    public IFormController get(int id) {
        for (IFormController c : formControllers) {
            if (c.getID() == id) {
                return c;
            }
        }
        return null;
    }

    public List<FormComponent> getTabs() {
        List<FormComponent> tabs = new ArrayList<FormComponent>();
        for (IFormController c : formControllers) {
            tabs.add(c.getViewComponent());
        }
        return tabs;
    }

    public void newComLog(String missionNo, long date) {
        try {
            ClientSocket.getInstance().output.writeObject(new NewFormMessage(
                    FormType.CL, missionNo, date));
        } catch (IOException e) {
            onConnectionError();
        }
    }

    public void newSearchAndRescue(String missionNo, long date) {
        try {
            ClientSocket.getInstance().output.writeObject(new NewFormMessage(
                    FormType.SAR, missionNo, date));
        } catch (IOException e) {
            onConnectionError();
        }
    }

    public void newRadioMessage(String missionNo, long date) {
        try {
            ClientSocket.getInstance().output.writeObject(new NewFormMessage(
                    FormType.RM, missionNo, date));
        } catch (IOException e) {
            onConnectionError();
        }
    }

    public RadioMessageController radioMessageFromJson(DBPushParams pushParams) {
        RadioMessageController radMesCont = new RadioMessageController(
                pushParams.id, (JsonObject) jsonParser.parse(pushParams.json));
        radMesCont.setOnConnectionErrorListener(onConnectionErrorListener);
        formControllers.add(radMesCont);
        return radMesCont;
    }

    public CommLogController comLogFromJson(DBPushParams pushParams) {
        CommLogController comCont = new CommLogController(pushParams.id,
                (JsonObject) jsonParser.parse(pushParams.json));
        comCont.setOnConnectionErrorListener(onConnectionErrorListener);
        formControllers.add(comCont);
        return comCont;
    }

    public SearchAndRescueController searchAndRescueFromJson(
            DBPushParams pushParams) {
        SearchAndRescueController searchRescCont = new SearchAndRescueController(
                pushParams.id, (JsonObject) jsonParser.parse(pushParams.json));
        searchRescCont.setOnConnectionErrorListener(onConnectionErrorListener);
        formControllers.add(searchRescCont);
        return searchRescCont;
    }

    public void remove(IFormController controller) {
        formControllers.remove(controller);
    }

    public void setOnConnectionErrorListener(OnConnectionErrorListener l) {
        this.onConnectionErrorListener = l;
        for (IFormController c : formControllers) {
            c.setOnConnectionErrorListener(l);
        }
    }

    private void onConnectionError() {
        if (onConnectionErrorListener != null) {
            onConnectionErrorListener.onConnectionError();
        }
    }

}