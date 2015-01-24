import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// The Controller coordinates interactions
// between the View and Model

public class FormController {

    private FormView theView;
    private FormModel theModel;

    public FormController(FormView theView, FormModel theModel) {
        this.theView = theView;
        this.theModel = theModel;

        // Tell the View that when ever the calculate button
        // is clicked to execute the actionPerformed method
        // in the CalculateListener inner class

        this.theView.addChangeListener(new SaveListener());
    }

    class SaveListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            String one, two, three;

            // Surround interactions with the view with
            // a try block in case numbers weren't
            // properly entered

            try {

                one = theView.get1();
                two = theView.get2();
                three = theView.get3();

                theModel.save(one, two, three);

                theView.set4(theModel.get1());
                theView.set5(theModel.get2());
                theView.set6(theModel.get3());

            }

            catch (NumberFormatException ex) {

                System.out.println(ex);

                theView.displayErrorMessage("You Need to Enter 2 Integers");

            }

        }

    }

}