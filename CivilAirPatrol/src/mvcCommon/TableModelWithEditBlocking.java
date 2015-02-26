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