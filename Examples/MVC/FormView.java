// This is the View
// Its only job is to display what the user sees
// It performs no calculations, but instead passes
// information entered by the user to whomever needs
// it. 

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormView extends JFrame {

    private static final long serialVersionUID = 9131304647063274186L;
    private JTextField entry1 = new JTextField(10);
    private JTextField entry2 = new JTextField(10);
    private JTextField entry3 = new JTextField(10);
    private JLabel entry4 = new JLabel("  ");
    private JLabel entry5 = new JLabel("  ");
    private JLabel entry6 = new JLabel("  ");

    FormView() {

        // Sets up the view and adds the components

        JPanel panel1 = new JPanel();
        //JPanel panel2 = new JPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);

        panel1.add(entry1);
        panel1.add(entry2);
        panel1.add(entry3);
        panel1.add(entry4);
        panel1.add(entry5);
        panel1.add(entry6);

        this.add(panel1);
        //this.add(panel2);

        // End of setting up the components --------

    }

    public String get1() {

        return entry1.getText();

    }

    public String get2() {

        return entry2.getText();

    }

    public String get3() {

        return entry3.getText();

    }

    public void set4(String s) {

        entry4.setText(s);

    }

    public void set5(String s) {

        entry5.setText(s);

    }

    public void set6(String s) {

        entry6.setText(s);

    }

    // If the calculateButton is clicked execute a method
    // in the Controller named actionPerformed

    void addChangeListener(ActionListener listener) {

        entry1.addActionListener(listener);
        entry2.addActionListener(listener);
        entry3.addActionListener(listener);

    }

    // Open a popup that contains the error message passed

    void displayErrorMessage(String errorMessage) {

        JOptionPane.showMessageDialog(this, errorMessage);

    }

}