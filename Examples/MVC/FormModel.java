// The Model performs all the calculations needed
// and that is it. It doesn't know the View 
// exists

public class FormModel {

    // Holds the value of the sum of the numbers
    // entered in the view

    private String one;
    private String two;
    private String three;

    public void save(String one, String two, String three) {

        this.one = new StringBuilder(one).reverse().toString();
        this.two = new StringBuilder(two).reverse().toString();
        this.three = new StringBuilder(three).reverse().toString();

    }

    public String get1() {
        return one;
    }

    public String get2() {
        return two;
    }

    public String get3() {
        return three;
    }

}