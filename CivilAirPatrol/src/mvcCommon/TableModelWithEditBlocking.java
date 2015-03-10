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

package mvcCommon;

import javax.swing.table.DefaultTableModel;

public class TableModelWithEditBlocking extends DefaultTableModel {
    // Needed to make override isCellEditable so that we can make the
    // entire table uneditable if the user is a reader
    private static final long serialVersionUID = 1L;
    private boolean isEditable = true;

    public TableModelWithEditBlocking(Object[][] objects, String[] strings) {
        super(objects, strings);
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        // all cells false
        return isEditable;
    }
}