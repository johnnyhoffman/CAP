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
import common.OnConnectionErrorListener;

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
        btnClose.addActionListener(new RemoveTabListener(this, controller,
                component, scrollC));

    }

    public void newComLog(String missionNo, long date) {
        model.newComLog(missionNo, date);
    }

    public void newSearchAndRescue(String missionNo, long date) {
        model.newSearchAndRescue(missionNo, date);
    }

    public void newRadioMessage(String missionNo, long date) {
        model.newRadioMessage(missionNo, date);
    }

    public void removeTab(Component component, IFormController controller) {
        view.remove(component);
        model.remove(controller);
    }

    public void fromDBPushParams(DBPushParams pushParams, boolean isUpdate) {
        if (model.has(pushParams.id)) {
            IFormController c = model.get(pushParams.id);
            c.updateFromJson((JsonObject) jsonParser.parse(pushParams.json));
        } else {
            if (!isUpdate) {
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
    }

    public class RemoveTabListener implements ActionListener {

        FormComponent component;
        FormsController formsController;
        JScrollPane componentAsScrollPane;
        IFormController innerController;

        public RemoveTabListener(FormsController formsController,
                IFormController innerController, FormComponent component,
                JScrollPane componentAsScollPane) {
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

    public void setOnConnectionErrorListener(OnConnectionErrorListener l) {
        model.setOnConnectionErrorListener(l);
    }

    public void onClose() {
        for (FormComponent c : model.getTabs()) {
            c.onClose();
        }
    }
}