package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Here we have a class that lets us search the database for forms
 * 
 * @author danavold
 *
 */
public class SearchWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7658371200547895945L;
	private static final int SEARCH_WIDTH = 480;
	private static final int SEARCH_HEIGHT = 240;
	private static final int RESULTS_WIDTH = 420;
	private static final int RESULTS_HEIGHT = 480;
	private static final int X_DISPLACE = 60;
	private static final int Y_DISPLACE = 40;
	
	private JTextField minDay;
	private JTextField minMonth;
	private JTextField minYear;
	private JTextField maxDay;
	private JTextField maxMonth;
	private JTextField maxYear;

	private JTextField formID;

	private JTextField missionName;

	private JCheckBox commLogCheckbox;
	private JCheckBox radioMessageCheckbox;
	private JCheckBox searchAndRescueCheckbox;

	private JButton cancelButton;
	private JButton searchButton;
	
	private SearchWindowJFrame mainFrame;
	
	private boolean dead;
	
	private JTable searchResultsTable;
	
	public SearchWindow(int x, int y) {
		dead = true;
		setSearchWindow();
	}
	
	private void setSearchWindow() {
		if (!dead) {
			dispose();
		}
		dead = false;
		
		mainFrame = new SearchWindowJFrame();
		
		mainFrame.setSize(SEARCH_WIDTH, SEARCH_HEIGHT);
		mainFrame.setLayout(new GridLayout(6,1));
		mainFrame.setBackground(new Color(230,230,230));
		JLabel tooltip = new JLabel("Please apply one or more of the following filters",JLabel.CENTER);
		tooltip.setSize(100,60);
		
		// dates
		JPanel dateRangePanel = new JPanel();
		dateRangePanel.setLayout(new FlowLayout());
		dateRangePanel.setBackground(new Color(200,200,220));
		JLabel dateHeader = new JLabel("Created between ",JLabel.CENTER);
		JLabel date1slash1 = new JLabel("/",JLabel.CENTER);
		JLabel date1slash2 = new JLabel("/",JLabel.CENTER);
		JLabel date1to2label = new JLabel("  and  ",JLabel.CENTER);
		JLabel date2slash1 = new JLabel("/",JLabel.CENTER);
		JLabel date2slash2 = new JLabel("/",JLabel.CENTER);
		minDay = new javax.swing.JTextField();
		minDay.setDocument(new TextDocumentForLimitedTextFields(2,true));
		minDay.setPreferredSize(new Dimension(32,24));
		maxDay = new javax.swing.JTextField();
		maxDay.setDocument(new TextDocumentForLimitedTextFields(2,true));
		maxDay.setPreferredSize(new Dimension(32,24));
		minMonth = new javax.swing.JTextField();
		minMonth.setDocument(new TextDocumentForLimitedTextFields(2,true));
		minMonth.setPreferredSize(new Dimension(32,24));
		maxMonth = new javax.swing.JTextField();
		maxMonth.setDocument(new TextDocumentForLimitedTextFields(2,true));
		maxMonth.setPreferredSize(new Dimension(32,24));
		minYear = new javax.swing.JTextField();
		minYear.setDocument(new TextDocumentForLimitedTextFields(4,true));
		minYear.setPreferredSize(new Dimension(48,24));
		maxYear = new javax.swing.JTextField();
		maxYear.setDocument(new TextDocumentForLimitedTextFields(4,true));
		maxYear.setPreferredSize(new Dimension(48,24));
		dateRangePanel.add(dateHeader);
		dateRangePanel.add(minDay);
		dateRangePanel.add(date1slash1);
		dateRangePanel.add(minMonth);
		dateRangePanel.add(date1slash2);
		dateRangePanel.add(minYear);
		dateRangePanel.add(date1to2label);
		dateRangePanel.add(maxDay);
		dateRangePanel.add(date2slash1);
		dateRangePanel.add(maxMonth);
		dateRangePanel.add(date2slash2);
		dateRangePanel.add(maxYear);
		mainFrame.add(tooltip);
		mainFrame.add(dateRangePanel);
		
		// second row
		JPanel formIDandMissionName = new JPanel();
		formIDandMissionName.setLayout(new GridLayout(1,2));
		
		// form id no
		JPanel formIDPanel = new JPanel();
		formIDPanel.setLayout(new FlowLayout());
		formIDPanel.setBackground(new Color(230,230,230));
		JLabel formIDHeader = new JLabel("Form ID: ",JLabel.CENTER);
		formID = new javax.swing.JTextField();
		formID.setDocument(new TextDocumentForLimitedTextFields(18,true));
		formID.setPreferredSize(new Dimension(64,24));
		formIDPanel.add(formIDHeader);
		formIDPanel.add(formID);
		formIDandMissionName.add(formIDPanel);
		
		// mission name
		JPanel missionNamePanel = new JPanel();
		missionNamePanel.setLayout(new FlowLayout());
		missionNamePanel.setBackground(new Color(200,220,200));
		JLabel missionNameHeader = new JLabel("Mission name: ",JLabel.CENTER);
		missionName = new javax.swing.JTextField();
		missionName.setDocument(new TextDocumentForLimitedTextFields(60,false));
		missionName.setPreferredSize(new Dimension(128,24));
		missionNamePanel.add(missionNameHeader);
		missionNamePanel.add(missionName);
		formIDandMissionName.add(missionNamePanel);
		
		mainFrame.add(formIDandMissionName);
		
		// checkboxes
		JPanel formTypePanel = new JPanel();
		formTypePanel.setLayout(new FlowLayout());
		formTypePanel.setBackground(new Color(220,200,200));
		commLogCheckbox = new JCheckBox("Comm. Log",true);
		radioMessageCheckbox = new JCheckBox("Radio Message",true);
		searchAndRescueCheckbox = new JCheckBox("S.A.R.",true);
		formTypePanel.add(commLogCheckbox);
		formTypePanel.add(radioMessageCheckbox);
		formTypePanel.add(searchAndRescueCheckbox);
		mainFrame.add(formTypePanel);
		
		
		// create buttons
		JPanel buttonPanel = new JPanel();
		cancelButton = new JButton("Cancel");
		searchButton = new JButton("Search");
		buttonPanel.add(cancelButton);
		buttonPanel.add(searchButton);
		cancelButton.addActionListener(new CancelSearchListener());
		searchButton.addActionListener(new StartSearchListener());
		mainFrame.add(buttonPanel);
		
		
		// finishing up
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setTitle("Retrieve Forms");
		mainFrame.setPreferredSize(new Dimension(SEARCH_WIDTH,SEARCH_HEIGHT));
		
		mainFrame.setLocation(mainFrame.getX()+X_DISPLACE, mainFrame.getY()+Y_DISPLACE);
		mainFrame.pack();
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		
	}
	
	
	public void setResultsWindow() {
		mainFrame.dispose();
		dead = false;
		mainFrame = new SearchWindowJFrame();

		mainFrame = new SearchWindowJFrame();
		
		mainFrame.setSize(RESULTS_WIDTH, RESULTS_HEIGHT);
		mainFrame.setLayout(new GridLayout(6,1));
		mainFrame.setBackground(new Color(230,230,230));

		searchResultsTable = new JTable();
		TableColumn IDs = new TableColumn(10);
		TableColumn missionNames = new TableColumn(10);
		TableColumn formTypes = new TableColumn(10);
		TableColumn dates = new TableColumn(10);
		searchResultsTable.addColumn(IDs);
		searchResultsTable.addColumn(missionNames);
		searchResultsTable.addColumn(formTypes);
		searchResultsTable.addColumn(dates);
		mainFrame.add(searchResultsTable);
		
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setTitle("Form Retrieval Results");
		mainFrame.setPreferredSize(new Dimension(RESULTS_WIDTH,RESULTS_HEIGHT));
		
		mainFrame.setLocation(mainFrame.getX()+X_DISPLACE, mainFrame.getY()+Y_DISPLACE);
		mainFrame.pack();
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
	}
	
	public boolean isDead() {
		return dead;
	}
	
	private void dispose() {
		mainFrame.dispose();
	}
	
	private void removeAllListeners() {
		removeListeners(cancelButton);
		removeListeners(searchButton);
		dead = true;
	}
	
	public void moveToTop() {
		mainFrame.setAlwaysOnTop(true);
		mainFrame.setAlwaysOnTop(false);
	}
	
	private void removeListeners(AbstractButton c) {
		ActionListener[] listenersToRemove = c.getActionListeners();
		for(ActionListener l : listenersToRemove) {
	    	c.removeActionListener(l);
	    }
	}
	
	private class CancelSearchListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			dispose();	
		}		
	}
	private class StartSearchListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			setResultsWindow();
		}		
	}
	
	private class SearchWindowJFrame extends JFrame {
		
		public void dispose() {
			super.dispose();
			removeAllListeners();
		}
		
		public void quietDispose() {
			super.dispose();
		}
	}
}

class TextDocumentForLimitedTextFields extends PlainDocument {
	
    private int max;
    private boolean numbersOnly;
    
    TextDocumentForLimitedTextFields(int max, boolean numbersOnly) {
        super();
        this.max = max;
        this.numbersOnly = numbersOnly;
    }
    
    public void insertString(int offset, String  str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
        	return;
        } else if ((getLength() + str.length()) <= max) {
    		String newStr = "";
        	if (numbersOnly) {
        		int len = str.length();
        		for (int i = 0; i < len; i++) {
        			char c = str.charAt(i);
        			if (c >= '0' && c <= '9') {
        				newStr += c;
        			}
        		}
        	} else {
        		newStr = str;
        	}
            super.insertString(offset, newStr, attr);
        }
    }
}


