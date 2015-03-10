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

package userInterface;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TextDocumentForLimitedTextFields extends PlainDocument {

    public static final int NUMBERS_ONLY = 0;
    public static final int NUMBERS_LETTERS_AND_UNDERSCORES = 1;
    public static final int ALL_CHARS = 2;
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private int max;
    private int flags;

    public TextDocumentForLimitedTextFields(int max, int flags) {
        super();
        this.max = max;
        this.flags = flags;
    }

    public void insertString(int offset, String str, AttributeSet attr)
            throws BadLocationException {
        if (str == null) {
            return;
        } else if ((getLength() + str.length()) <= max) {
            String newStr = "";
            int len = str.length();
            for (int i = 0; i < len; i++) {
                char c = str.charAt(i);
                if (c >= '0' && c <= '9') {
                    newStr += c;
                } else if (flags == ALL_CHARS) {
                    newStr += c;
                } else if (flags == NUMBERS_LETTERS_AND_UNDERSCORES) {
                    if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')
                            || (c == '_')) {
                        newStr += c;
                    }
                }
            }
            super.insertString(offset, newStr, attr);
        }
    }
}
