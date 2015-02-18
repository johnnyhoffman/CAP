package forms;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import mvcCommon.IController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import common.DBPushParams;

public class FormsController implements IController {

    private FormsView view;
    private FormsModel model;
    private JsonParser jsonParser;

    public FormsController() {
        jsonParser = new JsonParser();
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

    private void addTab(IFormController controller) {

        FormComponent component = controller.getViewComponent();
        javax.swing.JScrollPane scrollC = new javax.swing.JScrollPane(component);
        scrollC.setName(component.getName());

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
        btnClose.addActionListener(new RemoveTabListener(this, controller, component, scrollC));

    }

    public void newComLog() {
        addTab(model.newComLog());
    }

    public void newComLog(String missionNo, String date) {
        addTab(model.newComLog(missionNo, date));
    }

    public void newSearchAndRescue() {
        addTab(model.newSearchAndRescue());
    }

    public void newSearchAndRescue(String missionNo, String date) {
        addTab(model.newSearchAndRescue(missionNo, date));
    }

    public void newRadioMessage() {
        addTab(model.newRadioMessage());
    }

    public void newRadioMessage(String missionNo, String date) {
        addTab(model.newRadioMessage(missionNo, date));
    }

    public void removeTab(Component component, IFormController controller) {
        view.remove(component);
        model.remove(controller);
    }

    public void fromDBPushParams(DBPushParams pushParams) {
        if (model.has(pushParams.id)) {
            IFormController c = model.get(pushParams.id);
            if (c == null) {
                System.out.println("null");
            }
            System.out.println("[[[[[" + pushParams.json + "]]]]]");
            c.updateFromJson((JsonObject) jsonParser.parse(pushParams.json));
        } else {
            switch (pushParams.type) {
            case CL:
                addTab(model.comLogFromJson(pushParams));
                break;
            case RM:
                addTab(model.radioMessageFromJson(pushParams));
                break;
            case SAR:
                addTab(model.searchAndRescueFromJson(pushParams));
                break;
            }
        }
    }

    public class RemoveTabListener implements ActionListener {

        FormComponent component;
        FormsController formsController;
        JScrollPane componentAsScrollPane;
        IFormController innerController;

        public RemoveTabListener(FormsController formsController, IFormController innerController,
                FormComponent component, JScrollPane componentAsScollPane) {
            this.formsController = formsController;
            this.innerController = innerController;
            this.component = component;
            this.componentAsScrollPane = componentAsScollPane;
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
            component.onClose();
            formsController.removeTab(componentAsScrollPane, innerController);
        }

    }

    public void onClose() {
        for (FormComponent c : model.getTabs()) {
            c.onClose();
        }
    }
}