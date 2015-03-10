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

package assets;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import network.UserType;

import common.ClientGlobalVariables;

public class AssetsView extends JPanel {

    private static final long serialVersionUID = 1513332921051650164L;
    private JTextField missionNoTF;
    JList list;
    private OnNewAssetColorListener onNewAssetColorListener;
    private AssetStatus selectedAsset;
    private JComboBox colorBox;
    private List<AssetStatus> overdue;
    private ColorComboBoxCellRenderer colorRenderer;
    private Color[] colors;
    private HashMap<Color, Integer> colorToIndex;

    public interface OnNewAssetColorListener {
        public void newAssetColor(String missionNo, String asset, Color color);
    }

    private class AssetColoredListRenderer extends DefaultListCellRenderer {
        private static final long serialVersionUID = 8792708225831488094L;
        int overdueCount;
        List<AssetStatus> overdue;
        List<AssetStatus> underdue;

        public AssetColoredListRenderer(List<AssetStatus> overdue,
                List<AssetStatus> underdue) {
            super();
            this.overdueCount = overdue.size();
            this.overdue = overdue;
            this.underdue = underdue;
        }

        public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected,
                    cellHasFocus);
            if (overdueCount == 0 && underdue.size() == 0) {
                return this;
            }
            if (index < overdueCount) {
                setForeground(overdue.get(index).getColor());
            } else {
                setForeground(underdue.get(index - overdueCount).getColor());
            }
            return this;
        }
    }

    AssetsView() {

        selectedAsset = null;
        colorToIndex = new HashMap<Color, Integer>();
        colors = new Color[] { Color.black, Color.blue, new Color(25600),
                Color.magenta };
        for (int i = 0; i < colors.length; i++) {
            colorToIndex.put(colors[i], i);
        }
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 0, 50, 50, 0 };
        gridBagLayout.rowHeights = new int[] { 15, 0, 0, 0 };
        gridBagLayout.columnWeights = new double[] { 10.0, 1.0, 1.0, 0.0 };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0,
                Double.MIN_VALUE };
        setLayout(gridBagLayout);

        JLabel lblAssetsTracker = new JLabel("Assets Tracker");
        GridBagConstraints gbc_lblAssetsTracker = new GridBagConstraints();
        gbc_lblAssetsTracker.gridwidth = 4;
        gbc_lblAssetsTracker.insets = new Insets(0, 0, 5, 0);
        gbc_lblAssetsTracker.anchor = GridBagConstraints.NORTH;
        gbc_lblAssetsTracker.gridx = 0;
        gbc_lblAssetsTracker.gridy = 0;
        add(lblAssetsTracker, gbc_lblAssetsTracker);

        JLabel lblMissionNumber = new JLabel("Mission Number: ");
        GridBagConstraints gbc_lblMissionNumber = new GridBagConstraints();
        gbc_lblMissionNumber.anchor = GridBagConstraints.EAST;
        gbc_lblMissionNumber.insets = new Insets(0, 0, 5, 5);
        gbc_lblMissionNumber.gridx = 0;
        gbc_lblMissionNumber.gridy = 1;
        add(lblMissionNumber, gbc_lblMissionNumber);

        missionNoTF = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.insets = new Insets(0, 0, 5, 5);
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 1;
        gbc_textField.gridy = 1;
        add(missionNoTF, gbc_textField);
        missionNoTF.setColumns(10);

        DefaultListModel model = new DefaultListModel();
        list = new JList(model); // data has type Object[]
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(10);

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                AssetStatus selected = (AssetStatus) list.getSelectedValue();
                if (selected != null) {
                    if (selected.getColor().equals(Color.red)) {
                        selectedAsset = null;
                    } else {
                        selectedAsset = selected;
                    }
                    setColorUpdateBar();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(list);
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridwidth = 4;
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 2;
        add(scrollPane, gbc_scrollPane);

        colorBox = new JComboBox(colors) {
            @Override
            public void setPopupVisible(boolean v) {
                super.setPopupVisible(v);
                if (!v) {
                    // for when selectedAsset changes during this operation
                    AssetStatus sa = selectedAsset;
                    Color c = (Color) colorBox.getSelectedItem();
                    if (sa != null && !c.equals(sa.getColor())) {
                        sa.setColor(c);
                        if (onNewAssetColorListener != null) {
                            list.clearSelection();
                            selectedAsset = null;
                            setColorUpdateBar();
                            onNewAssetColorListener.newAssetColor(
                                    getMissionNo(), sa.getName(), c);
                            list.updateUI();
                        }
                    }
                }
            }
        };

        colorBox.setMaximumRowCount(5);
        colorRenderer = new ColorComboBoxCellRenderer();
        colorBox.setRenderer(colorRenderer);
        GridBagConstraints gbc_colorBox = new GridBagConstraints();
        gbc_colorBox.anchor = GridBagConstraints.WEST;
        gbc_colorBox.insets = new Insets(0, 0, 5, 5);
        gbc_colorBox.gridx = 0;
        gbc_colorBox.gridwidth = 4;
        gbc_colorBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_colorBox.gridy = 3;
        add(colorBox, gbc_colorBox);

        colorBox.setVisible(false);
    }

    public void setColorUpdateBar() {
        colorBox.setVisible(false);
        if (ClientGlobalVariables.USERTYPE == UserType.WRITER
                && selectedAsset != null && !overdue.contains(selectedAsset)) {
            colorRenderer.setAllText(selectedAsset.toString());
            if (colorToIndex.containsKey(selectedAsset.getColor())) {
                colorBox.setSelectedIndex(colorToIndex.get(selectedAsset
                        .getColor()));
            }
            colorBox.setVisible(true);
        }
    }

    public void setMissionChangedNoListener(CaretListener l) {
        missionNoTF.addCaretListener(l);
    }

    public void setLists(List<AssetStatus> overdue, List<AssetStatus> underdue) {
        this.overdue = overdue;
        list.clearSelection();
        selectedAsset = null;
        setColorUpdateBar();
        DefaultListModel model = new DefaultListModel();
        for (AssetStatus s : overdue) {
            model.addElement(s);
        }
        for (AssetStatus s : underdue) {
            model.addElement(s);
        }
        list.setCellRenderer(new AssetColoredListRenderer(overdue, underdue));
        list.setModel(model);
    }

    public String getMissionNo() {
        return missionNoTF.getText().trim();
    }

    public void setOnNewAssetColorListener(OnNewAssetColorListener l) {
        this.onNewAssetColorListener = l;
    }
}