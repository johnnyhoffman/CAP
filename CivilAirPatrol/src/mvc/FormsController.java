package mvc;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

    private void addTab(Component c) {
        view.addTab(c);

        view.setSelectedIndex(view.getTabCount() - 1);
        int index = view.indexOfTab(c.getName());
        JPanel pnlTab = new JPanel(new GridBagLayout());
        pnlTab.setOpaque(false);
        JLabel lblTitle = new JLabel(c.getName() + "  ");
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
        btnClose.addActionListener(new RemoveTabListener(this, c));

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

        Component component;
        FormsController formsController;

        public RemoveTabListener(FormsController formsController, Component component) {
            this.formsController = formsController;
            this.component = component;
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
            formsController.removeTab(component);
        }

    }
}