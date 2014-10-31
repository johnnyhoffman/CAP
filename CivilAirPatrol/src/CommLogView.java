import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class CommLogView extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CommLogView(){
		GroupLayout layout = new GroupLayout(this);
		//this.setLayout(layout);
		
		JTextField arg0 = new JTextField("num 1");
		JTextField arg1 = new JTextField("num 2");
		
		this.add(arg0);
		this.add(arg1);
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addComponent(arg0)
				.addComponent(arg1));
	}
	
	
	
}
