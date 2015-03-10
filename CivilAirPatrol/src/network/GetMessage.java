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

/**
 * GetMessage will be used to send a request to the server for data
 * 
 * @author Robert
 */
public class GetMessage extends NetworkMessage {

    private static final long serialVersionUID = -3641656218757595654L;
    private DBRequest request;

    public GetMessage(DBRequest request) {
        super(MessageType.GET);
        this.request = request;
    }

    public DBRequest getRequest() {
        return this.request;
    }

    @Override
    public String toString() {
        return request.toString();
    }
}
