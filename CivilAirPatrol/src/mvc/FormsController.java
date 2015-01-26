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

import com.google.gson.JsonObject;

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

    public void newSearchAndRescue() {
        addTab(model.newSearchAndRescue());
    }

    public void newRadioMessage() {
        addTab(model.newRadioMessage());
    }

    public void removeTab(Component c) {
        // TODO: Remove controller from model as well
        view.remove(c);
    }

    public void fromJson(JsonObject json) {
        // TODO: Need to check if tab exists already, in which case we just update it.
        if (json.has("type")) {
            String type = json.get("type").getAsString();
            if (type.equals(GlobalConstants.RADIO_MESSAGE_TYPE)) {
                addTab(model.radioMessageFromJson(json));
            } else if (type.equals(GlobalConstants.COMMUNICATIONS_LOG_TYPE)) {
                addTab(model.comLogFromJson(json));
            } else if (type.equals(GlobalConstants.SEARCH_AND_RESCUE_TYPE)) {
                addTab(model.searchAndRescueFromJson(json));
            } else {
                // TODO: Exception instead maybe
                System.out.println("Problem, unknown type");
            }
        } else {
            // TODO: Exception instead maybe
            System.out.println("Problem, json missing type");
        }
        // GlobalConstants.COMMUNICATIONS_LOG_TYPE
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