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

import javax.swing.JPanel;

public class FormComponent extends JPanel {

    private static final long serialVersionUID = -7817979188924602071L;

    public interface OnCloseListener {
        public void onClose();
    }

    private OnCloseListener onCloseListener;

    public void onClose() {
        if (onCloseListener != null) {
            onCloseListener.onClose();
        }
    }

    public void setOnCloseListener(OnCloseListener l) {
        onCloseListener = l;
    }

}
