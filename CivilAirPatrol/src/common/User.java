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

import network.UserType;

/**
 *
 * @author Robert
 */
public class User {
    private String userName;
    private String password;
    private UserType type;

    public User(String user, String password, UserType userType) {
        this.password = password;
        this.userName = user;
        this.type = userType;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getUser() {
        return this.userName;
    }

    public String getPass() {
        return this.password;
    }

    public UserType getType() {
        return this.type;
    }
}
