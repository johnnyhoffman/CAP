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

import java.awt.Color;
import java.awt.Component;

import javax.swing.event.CaretListener;
import javax.swing.table.TableCellRenderer;

import mvcCommon.TableModelWithEditBlocking;

import common.DataContainers.CommunicationsLog.ComLogEntry;
import common.DateTimePicker;
import common.DateTimePicker.DateTimePickerChangeListener;
import common.GlobalConstants;
import common.HourAndMin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * 
 * @author Robert
 */
public class CommLogView extends FormComponent {

    private static final long serialVersionUID = 1633816168566491000L;

    /**
     * Creates new form CommunicationLogView
     */
    public CommLogView() {
        initComponents();
    }

    public void setUneditable() {
        jTextField1.setEditable(false);
        jTextField2.setEditable(false);
        datePicker.setUneditable();
        jTextField4.setEditable(false);
        jTextField5.setEditable(false);
        jTextField6.setEditable(false);
        jTextField7.setEditable(false);
        jTextField8.setEditable(false);
        jTextField9.setEditable(false);
        ((TableModelWithEditBlocking) jTable1.getModel()).setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        datePicker = new DateTimePicker();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        // My oh-so-special table
        jTable1 = new javax.swing.JTable() {
            private static final long serialVersionUID = 1908352458582348120L;

            @Override
            public Component prepareRenderer(TableCellRenderer renderer,
                    int row, int col) {
                Component comp = super.prepareRenderer(renderer, row, col);
                String value = (String) getModel().getValueAt(row, col);
                if (col == 0) {
                    if (value.trim().equals("")) {
                        comp.setBackground(Color.white);
                    } else if (HourAndMin.isValidTimeColumnField(value)) {
                        comp.setBackground(new Color(15136255));
                    } else {
                        comp.setBackground(new Color(16737350));
                    }
                } else {
                    comp.setBackground(Color.white);
                }
                return comp;
            }
        };

        jTable1.getTableHeader().setReorderingAllowed(false);

        jLabel1.setText("COMMUNICATIONS LOG");

        jLabel2.setText("Mission Number");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jLabel3.setText("Station Functional Designator or Location");

        jLabel4.setText("Date");

        jLabel5.setText("DESIGNATOR/SOURCE");

        jLabel6.setText("A");

        jLabel7.setText("B");

        jLabel8.setText("C");

        jLabel9.setText("D");

        jLabel10.setText("E");

        jLabel11.setText("F");

        Object[][] tableModelStartingValues = new Object[GlobalConstants.COMLOG_ENTRY_COUNT][4];

        for (int i = 0; i < GlobalConstants.COMLOG_ENTRY_COUNT; i++) {
            tableModelStartingValues[i] = new Object[] { null, null, null, null };
        }
        jTable1.setModel(new TableModelWithEditBlocking(
                tableModelStartingValues, new String[] { "TIME (HH:mm)",
                        "CALL", "CH REF", "REMARKS" }));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(10);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(
                        layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(
                                        layout.createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(
                                                        layout.createSequentialGroup()
                                                                .addGap(0,
                                                                        0,
                                                                        Short.MAX_VALUE)
                                                                .addGroup(
                                                                        layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.CENTER)
                                                                                .addComponent(
                                                                                        jLabel1)
                                                                                .addComponent(
                                                                                        jLabel5))
                                                                .addContainerGap(
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE))
                                                .addGroup(
                                                        layout.createSequentialGroup()
                                                                .addGroup(
                                                                        layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(
                                                                                        jTextField1,
                                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(
                                                                                        jLabel2,
                                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                        Short.MAX_VALUE))
                                                                .addGap(44, 44,
                                                                        44)
                                                                .addGroup(
                                                                        layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(
                                                                                        jTextField2)
                                                                                .addComponent(
                                                                                        jLabel3))
                                                                .addGap(44, 44,
                                                                        44)
                                                                .addGroup(
                                                                        layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(
                                                                                        jLabel4,
                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                        Short.MAX_VALUE)
                                                                                .addComponent(
                                                                                        datePicker,
                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE))
                                                                .addGap(28, 28,
                                                                        28))
                                                .addGroup(
                                                        layout.createSequentialGroup()
                                                                .addComponent(
                                                                        jLabel6,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        22,
                                                                        Short.MAX_VALUE)
                                                                .addGap(112,
                                                                        112,
                                                                        112)
                                                                .addComponent(
                                                                        jLabel7,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        21,
                                                                        Short.MAX_VALUE)
                                                                .addGap(112,
                                                                        112,
                                                                        112)
                                                                .addComponent(
                                                                        jLabel8,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        22,
                                                                        Short.MAX_VALUE)
                                                                .addGap(112,
                                                                        112,
                                                                        112)
                                                                .addComponent(
                                                                        jLabel9,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        22,
                                                                        Short.MAX_VALUE)
                                                                .addGap(112,
                                                                        112,
                                                                        112)
                                                                .addComponent(
                                                                        jLabel10,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        21,
                                                                        Short.MAX_VALUE)
                                                                .addGap(112,
                                                                        112,
                                                                        112)
                                                                .addComponent(
                                                                        jLabel11,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        22,
                                                                        Short.MAX_VALUE)
                                                                .addGap(96, 96,
                                                                        96))
                                                .addGroup(
                                                        layout.createSequentialGroup()
                                                                .addComponent(
                                                                        jTextField4)
                                                                .addGap(30, 30,
                                                                        30)
                                                                .addComponent(
                                                                        jTextField5)
                                                                .addGap(45, 45,
                                                                        45)
                                                                .addComponent(
                                                                        jTextField6)
                                                                .addGap(51, 51,
                                                                        51)
                                                                .addComponent(
                                                                        jTextField7)
                                                                .addGap(51, 51,
                                                                        51)
                                                                .addComponent(
                                                                        jTextField8)
                                                                .addGap(45, 45,
                                                                        45)
                                                                .addComponent(
                                                                        jTextField9)
                                                                .addContainerGap())
                                                .addGroup(
                                                        javax.swing.GroupLayout.Alignment.TRAILING,
                                                        layout.createSequentialGroup()
                                                                .addGroup(
                                                                        layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(
                                                                                        jScrollPane1,
                                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(
                                                                                        jSeparator3,
                                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(
                                                                                        jSeparator1,
                                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(
                                                                                        jSeparator2))
                                                                .addContainerGap()))));
        layout.setVerticalGroup(layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(
                        layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        12,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(
                                        layout.createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel2)
                                                .addComponent(jLabel3)
                                                .addComponent(jLabel4))
                                .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(
                                        layout.createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(
                                                        jTextField1,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(
                                                        jTextField2,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(
                                                        datePicker,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE))
                                .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator2,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        10,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(
                                        layout.createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel6)
                                                .addComponent(jLabel7)
                                                .addComponent(jLabel8)
                                                .addComponent(jLabel9)
                                                .addComponent(jLabel10)
                                                .addComponent(jLabel11))
                                .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(
                                        layout.createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(
                                                        jTextField4,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(
                                                        jTextField5,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(
                                                        jTextField6,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(
                                                        jTextField7,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(
                                                        jTextField8,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(
                                                        jTextField9,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator3,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        10,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        506,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)));

        layout.linkSize(javax.swing.SwingConstants.VERTICAL,
                new java.awt.Component[] { jTextField4, jTextField5,
                        jTextField6, jTextField7, jTextField8, jTextField9 });

        layout.linkSize(
                javax.swing.SwingConstants.VERTICAL,
                new java.awt.Component[] { jTextField1, jTextField2, datePicker });

        layout.linkSize(javax.swing.SwingConstants.VERTICAL,
                new java.awt.Component[] { jLabel10, jLabel11, jLabel6,
                        jLabel7, jLabel8, jLabel9 });
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField1ActionPerformed
        // Event Code Here
    }// GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_jTextField1KeyTyped
        // Event Code Here
    }// GEN-LAST:event_jTextField1KeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private DateTimePicker datePicker;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;

    // End of variables declaration//GEN-END:variables

    /*
     * The following are functions to be used by the controller to add listeners
     * for changes in fields.
     */

    public void addMissionNumChangeListener(CaretListener l) {
        jTextField1.addCaretListener(l);
    }

    public void addStationFunctionalDesignatorChangeListener(CaretListener l) {
        jTextField2.addCaretListener(l);
    }

    public void addDateChangeListener(DateTimePickerChangeListener l) {
        datePicker.setChangeListener(l);
    }

    public void addAChangeListener(CaretListener l) {
        jTextField4.addCaretListener(l);
    }

    public void addBChangeListener(CaretListener l) {
        jTextField5.addCaretListener(l);
    }

    public void addCChangeListener(CaretListener l) {
        jTextField6.addCaretListener(l);
    }

    public void addDChangeListener(CaretListener l) {
        jTextField7.addCaretListener(l);
    }

    public void addEChangeListener(CaretListener l) {
        jTextField8.addCaretListener(l);
    }

    public void addFChangeListener(CaretListener l) {
        jTextField9.addCaretListener(l);
    }

    public String getMissionNumText() {
        return jTextField1.getText();
    }

    public String getStationFunctionalDesignatorText() {
        return jTextField2.getText();
    }

    public Long getDateLong() {
        return datePicker.getDateLong();
    }

    public String getAText() {
        return jTextField4.getText();
    }

    public String getBText() {
        return jTextField5.getText();
    }

    public String getCText() {
        return jTextField6.getText();
    }

    public String getDText() {
        return jTextField7.getText();
    }

    public String getEText() {
        return jTextField8.getText();
    }

    public String getFText() {
        return jTextField9.getText();
    }

    public ComLogEntry[] getEntries() {
        ComLogEntry[] cles = new ComLogEntry[GlobalConstants.COMLOG_ENTRY_COUNT];
        for (int i = 0; i < GlobalConstants.COMLOG_ENTRY_COUNT; i++) {
            cles[i] = new ComLogEntry();
            jTable1.getCellEditor(i, 0).stopCellEditing(); // need to stop
                                                           // editing to get
                                                           // value

            Object time = jTable1.getValueAt(i, 0);
            cles[i].time = (time == null) ? "" : (String) time;

            jTable1.getCellEditor(i, 1).stopCellEditing(); // need to stop
                                                           // editing to get
                                                           // value
            Object call = jTable1.getValueAt(i, 1);
            cles[i].call = (call == null) ? "" : (String) call;

            jTable1.getCellEditor(i, 2).stopCellEditing(); // need to stop
                                                           // editing to get
                                                           // value
            Object chRef = jTable1.getValueAt(i, 2);
            cles[i].chRef = (chRef == null) ? "" : (String) chRef;

            jTable1.getCellEditor(i, 3).stopCellEditing(); // need to stop
                                                           // editing to get
                                                           // value
            Object remarks = jTable1.getValueAt(i, 3);
            cles[i].remarks = (remarks == null) ? "" : (String) remarks;
        }
        return cles;
    }

    public void setMissionNumText(String s) {
        jTextField1.setText(s);
    }

    public void setStationFunctionalDesignatorText(String s) {
        jTextField2.setText(s);
    }

    public void setDateText(long l) {
        datePicker.setDate(l);
    }

    public void setAText(String s) {
        jTextField4.setText(s);
    }

    public void setBText(String s) {
        jTextField5.setText(s);
    }

    public void setCText(String s) {
        jTextField6.setText(s);
    }

    public void setDText(String s) {
        jTextField7.setText(s);
    }

    public void setEText(String s) {
        jTextField8.setText(s);
    }

    public void setFText(String s) {
        jTextField9.setText(s);
    }

    public void setEntries(ComLogEntry[] cles) {
        for (int i = 0; i < GlobalConstants.COMLOG_ENTRY_COUNT; i++) {
            jTable1.setValueAt(cles[i].time, i, 0);
            jTable1.setValueAt(cles[i].call, i, 1);
            jTable1.setValueAt(cles[i].chRef, i, 2);
            jTable1.setValueAt(cles[i].remarks, i, 3);
        }
    }

}
