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
import java.util.List;

public class ResultMessage extends NetworkMessage {
    private static final long serialVersionUID = -7754252435400212783L;
    private List<DBPushParams> results;
    boolean hasJSON;

    public ResultMessage(List<DBPushParams> results, boolean hasJSON) {
        super(MessageType.RESULT);
        this.results = results;
        this.hasJSON = hasJSON;
    };

    public List<DBPushParams> getResults() {
        return this.results;
    }
}
