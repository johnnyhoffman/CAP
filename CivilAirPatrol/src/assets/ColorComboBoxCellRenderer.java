package assets;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

class ColorComboBoxCellRenderer extends JButton implements ListCellRenderer {
    private static final long serialVersionUID = -4186811037458042598L;
    private String text;

    public ColorComboBoxCellRenderer() {
        setOpaque(true);
        text = "";
    }

    boolean b = false;

    @Override
    public void setBackground(Color bg) {
        if (!b) {
            return;
        }
        super.setBackground(bg);
    }

    @Override
    public void setForeground(Color bg) {
        if (!b) {
            return;
        }
        super.setForeground(bg);
    }

    public void setAllText(String text) {
        this.text = text;
    }

    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
        b = true;
        setText(text);
        setForeground((Color) value);
        setBackground(Color.white);
        b = false;
        return this;
    }
}