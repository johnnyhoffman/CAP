package assets;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

class ColorComboBoxCellRenderer extends JButton implements ListCellRenderer {
    private static final long serialVersionUID = -4186811037458042598L;

    public ColorComboBoxCellRenderer() {
        setOpaque(true);

    }

    boolean b = false;

    @Override
    public void setBackground(Color bg) {
        if (!b) {
            return;
        }
        super.setBackground(bg);
    }

    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
        b = true;
        setText(" ");
        setBackground((Color) value);
        b = false;
        return this;
    }
}