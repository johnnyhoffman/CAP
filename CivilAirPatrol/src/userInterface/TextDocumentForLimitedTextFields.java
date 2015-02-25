package userInterface;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TextDocumentForLimitedTextFields extends PlainDocument {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int max;
    private boolean numbersOnly;

    public TextDocumentForLimitedTextFields(int max, boolean numbersOnly) {
        super();
        this.max = max;
        this.numbersOnly = numbersOnly;
    }

    public void insertString(int offset, String str, AttributeSet attr)
            throws BadLocationException {
        if (str == null) {
            return;
        } else if ((getLength() + str.length()) <= max) {
            String newStr = "";
            if (numbersOnly) {
                int len = str.length();
                for (int i = 0; i < len; i++) {
                    char c = str.charAt(i);
                    if (c >= '0' && c <= '9') {
                        newStr += c;
                    }
                }
            } else {
                newStr = str;
            }
            super.insertString(offset, newStr, attr);
        }
    }
}
