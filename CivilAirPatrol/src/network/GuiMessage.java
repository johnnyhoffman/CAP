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

import common.DBPushParams;

public class GuiMessage extends NetworkMessage {
    private static final long serialVersionUID = 6246753614374221315L;
    private DBPushParams params;
    private boolean isUpdate;

    public GuiMessage(DBPushParams params) {
        super(MessageType.GUI);
        isUpdate = false;
        this.params = params;
    }

    public DBPushParams getParams() {
        return params;
    }

    public void setIsUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public boolean getIsUpdate() {
        return isUpdate;
    }
}
