public class MVCForms {

    public static void main(String[] args) {

        FormView theView = new FormView();

        FormModel theModel = new FormModel();

        new FormController(theView, theModel);

        theView.setVisible(true);

    }
}