  package forms;

import javax.swing.JPanel;

public class FormComponent extends JPanel {

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
