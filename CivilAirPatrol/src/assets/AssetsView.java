package assets;

/* Placeholder for demonstrating Session MVC */
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
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
    JList<String> list;

    private class MyListRenderer extends DefaultListCellRenderer {
        private int overdueCount;
        private int underdueCount;

        public MyListRenderer(int overdueCount) {
            super();
            this.overdueCount = overdueCount;
        }

        public Component getListCellRendererComponent(JList<?> list,
                Object value, int index, boolean isSelected,
                boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected,
                    cellHasFocus);
            if (index < overdueCount) {
                setForeground(Color.red);
            } else {
                setForeground(Color.green);
            }

            return (this);
        }
    }

    AssetsView() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 172, 0, 105, 0 };
        gridBagLayout.rowHeights = new int[] { 15, 0, 0, 0 };
        gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 1.0,
                Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0,
                Double.MIN_VALUE };
        setLayout(gridBagLayout);

        JLabel lblAssetsTracker = new JLabel("Assets Tracker");
        GridBagConstraints gbc_lblAssetsTracker = new GridBagConstraints();
        gbc_lblAssetsTracker.gridwidth = 3;
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

        DefaultListModel<String> model = new DefaultListModel<String>();
        list = new JList<String>(model); // data has type Object[]
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(10);

        JScrollPane scrollPane = new JScrollPane(list);
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridwidth = 3;
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 2;

        add(scrollPane, gbc_scrollPane);
    }

    public void setMissionChangedNoListener(CaretListener l) {
        missionNoTF.addCaretListener(l);
    }

    public void setLists(List<String> overdue, List<String> underdue) {
        List<String> overdueTEMP = new ArrayList<String>();
        List<String> underdueTEMP = new ArrayList<String>();
        overdueTEMP.add("OD1");
        overdueTEMP.add("OD2");
        overdueTEMP.add("OD3");
        overdueTEMP.add("OD4");
        overdueTEMP.add("OD5");
        underdueTEMP.add("ud1");
        underdueTEMP.add("ud2");
        underdueTEMP.add("ud3");
        underdueTEMP.add("ud4");
        underdueTEMP.add("ud5");
        DefaultListModel<String> model = new DefaultListModel<String>();
        for (String s : overdueTEMP) {
            model.addElement(s);
        }
        for (String s : underdueTEMP) {
            model.addElement(s);
        }
        list.setModel(model);
        list.setCellRenderer(new MyListRenderer(overdueTEMP.size()));
    }
    
    public String getMissionNo() {
        return missionNoTF.getText();
    }
}