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

public class ErrorMessage extends NetworkMessage {
    private static final long serialVersionUID = -1638515020975443836L;
    private String message;

    public ErrorMessage(String m) {
        super(MessageType.ERROR);
        this.message = m;
    }

    public String getMessage() {
        return this.message;
    }
}
