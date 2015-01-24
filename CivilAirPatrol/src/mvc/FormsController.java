package mvc;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/* Placeholder for demonstrating Session MVC */
public class FormsController implements Controller {

    private FormsView view;
    private FormsModel model;

    public FormsController() {
        view = new FormsView();
        model = new FormsModel();

        for (Component c : model.getTabs()) {
            view.addTab(c);
        }

    }

    @Override
    public Component getViewComponent() {
        return view;
    }

    private void addTab(FormComponent c) {

        javax.swing.JScrollPane scrollC = new javax.swing.JScrollPane(c);
        scrollC.setName(c.getName());

        view.addTab(scrollC);

        view.setSelectedIndex(view.getTabCount() - 1);
        int index = view.indexOfTab(scrollC.getName());
        JPanel pnlTab = new JPanel(new GridBagLayout());
        pnlTab.setOpaque(false);
        JLabel lblTitle = new JLabel(scrollC.getName() + "  ");
        JButton btnClose = new JButton("X");
        btnClose.setBorder(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;

        pnlTab.add(lblTitle, gbc);

        gbc.gridx++;
        gbc.weightx = 0;
        pnlTab.add(btnClose, gbc);

        view.setTabComponentAt(index, pnlTab);
        btnClose.addActionListener(new RemoveTabListener(this, c, scrollC));

    }

    public void newComLog() {
        addTab(model.newComLog());
    }

    public void newSearchAndResue() {
        addTab(model.newSearchAndRescue());
    }

    public void newRadioMessage() {
        addTab(model.newRadioMessage());
    }

    public void removeTab(Component c) {
        view.remove(c);
    }

    public class RemoveTabListener implements ActionListener {

        FormComponent component;
        FormsController formsController;
        JScrollPane componentAsScrollPane;

        public RemoveTabListener(FormsController formsController, FormComponent component,
                JScrollPane componentAsScollPane) {
            this.formsController = formsController;
            this.component = component;
            this.componentAsScrollPane = componentAsScollPane;
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
            component.onClose();
            formsController.removeTab(componentAsScrollPane);
        }

    }
}