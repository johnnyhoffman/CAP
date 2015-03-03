package assets;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.CaretListener;

public class AssetsView extends JPanel {

    private static final long serialVersionUID = 1513332921051650164L;
    private JTextField missionNoTF;
    JList list;
    private OnNewAssetColorListener onNewAssetColorListener;

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
            if (index < overdueCount) {
                setForeground(overdue.get(index).getColor());
            } else {
                setForeground(underdue.get(index - overdueCount).getColor());
            }
            return (this);
        }
    }

    AssetsView() {
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

        JScrollPane scrollPane = new JScrollPane(list);
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridwidth = 4;
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 2;
        add(scrollPane, gbc_scrollPane);

        JLabel lblUpdateAsset = new JLabel("Asset: ");
        GridBagConstraints gbc_lblUpdateAsset = new GridBagConstraints();
        gbc_lblUpdateAsset.anchor = GridBagConstraints.WEST;
        gbc_lblUpdateAsset.insets = new Insets(0, 0, 5, 5);
        gbc_lblUpdateAsset.gridx = 0;
        gbc_lblUpdateAsset.gridy = 3;
        add(lblUpdateAsset, gbc_lblUpdateAsset);

        final JTextField assetTF = new JTextField(8);
        GridBagConstraints gbc_assetTF = new GridBagConstraints();
        gbc_assetTF.anchor = GridBagConstraints.WEST;
        gbc_assetTF.insets = new Insets(0, 0, 5, 5);
        gbc_assetTF.gridx = 1;
        gbc_assetTF.gridy = 3;
        add(assetTF, gbc_assetTF);

        Color[] colors = { Color.white, Color.red, Color.blue, Color.green };
        final JComboBox colorBox = new JComboBox(colors);
        colorBox.setMaximumRowCount(5);
        colorBox.setPreferredSize(new Dimension(50, 20));
        colorBox.setRenderer(new ColorComboBoxCellRenderer());
        GridBagConstraints gbc_colorBox = new GridBagConstraints();
        gbc_colorBox.anchor = GridBagConstraints.WEST;
        gbc_colorBox.insets = new Insets(0, 0, 5, 5);
        gbc_colorBox.gridx = 2;
        gbc_colorBox.gridy = 3;
        add(colorBox, gbc_colorBox);

        JButton updateColorButton = new JButton("Update Color");
        GridBagConstraints gbc_button = new GridBagConstraints();
        gbc_button.anchor = GridBagConstraints.WEST;
        gbc_button.insets = new Insets(0, 0, 5, 5);
        gbc_button.gridx = 3;
        gbc_button.gridy = 3;
        updateColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (onNewAssetColorListener != null) {
                    onNewAssetColorListener.newAssetColor(getMissionNo(),
                            assetTF.getText().trim(),
                            (Color) colorBox.getSelectedItem());
                }
            }
        });
        add(updateColorButton, gbc_button);

    }

    public void setMissionChangedNoListener(CaretListener l) {
        missionNoTF.addCaretListener(l);
    }

    public void setLists(List<AssetStatus> overdue, List<AssetStatus> underdue) {
        DefaultListModel model = new DefaultListModel();
        for (AssetStatus s : overdue) {
            model.addElement(s.toString());
        }
        for (AssetStatus s : underdue) {
            model.addElement(s.toString());
        }
        list.setModel(model);
        list.setCellRenderer(new AssetColoredListRenderer(overdue, underdue));
    }

    public String getMissionNo() {
        return missionNoTF.getText().trim();
    }

    public void setOnNewAssetColorListener(OnNewAssetColorListener l) {
        this.onNewAssetColorListener = l;
    }
}