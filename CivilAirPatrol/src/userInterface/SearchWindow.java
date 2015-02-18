package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import database.sqlServer;

import common.DBPushParams;
import common.DateTimePicker;
import forms.FormsController;

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
	private static final int SEARCH_WIDTH = 480; //was 480
	private static final int SEARCH_HEIGHT = 240;
	private static final int RESULTS_WIDTH = 480;
	private static final int RESULTS_HEIGHT = 520;
	private static final int X_DISPLACE = 60;
	private static final int Y_DISPLACE = 40;

	private static final String FORM_TYPE_COMM_LOG = "Comm. Log";
	private static final String FORM_TYPE_RADIO_MSG = "Radio Message";
	private static final String FORM_TYPE_SAR = "S.A.R.";

	private static final int FORM_TYPE_ID_COMM_LOG = 0;
	private static final int FORM_TYPE_ID_RADIO_MSG = 1;
	private static final int FORM_TYPE_ID_SAR = 2;
	
	
	/* Dana, I left what you had but commented it out.
	 * to get the min and max date values just use 
	 * min/maxDateTime.getDate() or .getDateString().
	 * I also change SEARCH_WIDTH to 580 to fit it */
	//	private JTextField minDay;
	//	private JTextField minMonth;
	//	private JTextField minYear;
	//	private JTextField maxDay;
	//	private JTextField maxMonth;
	//	private JTextField maxYear;
    private DateTimePicker minDateTime;
    private DateTimePicker maxDateTime;

	private JTextField tMissionNo;

	private JCheckBox commLogCheckbox;
	private JCheckBox radioMessageCheckbox;
	private JCheckBox searchAndRescueCheckbox;

	private JButton cancelButton;
	private JButton goButton;
	
	private SearchWindowJFrame mainFrame;
	
	private FormsController formsController;
	
	// variables for displaying the results of a search
	JList resultsList;
	JScrollPane resultsListScroller;
	
	private boolean dead;
	
	public SearchWindow(int x, int y, FormsController formsController) {
		dead = true;
		setSearchWindow();
		this.formsController = formsController;
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
		JPanel dateRangePanel1 = new JPanel();
		dateRangePanel1.setLayout(new FlowLayout());
		dateRangePanel1.setBackground(new Color(200,200,220));
		JPanel dateRangePanel2 = new JPanel();
		dateRangePanel2.setLayout(new FlowLayout());
		dateRangePanel2.setBackground(new Color(200,200,220));
		JLabel dateHeader = new JLabel("Created between ",JLabel.CENTER);
		JLabel date1to2label = new JLabel("  and  ",JLabel.CENTER);
        minDateTime = new DateTimePicker();
        maxDateTime = new DateTimePicker();
        dateRangePanel1.add(dateHeader);
        dateRangePanel1.add(minDateTime);
        dateRangePanel2.add(date1to2label);
        dateRangePanel2.add(maxDateTime);
		
		mainFrame.add(tooltip);
		mainFrame.add(dateRangePanel1);
		mainFrame.add(dateRangePanel2);
		
		// second row
		
		// form id no
		JPanel missionNoPanel = new JPanel();
		missionNoPanel.setLayout(new FlowLayout());
		missionNoPanel.setBackground(new Color(230,230,230));
		JLabel formIDHeader = new JLabel("Mission Number: ",JLabel.CENTER);
		tMissionNo = new javax.swing.JTextField();
		tMissionNo.setDocument(new TextDocumentForLimitedTextFields(18,false));
		tMissionNo.setPreferredSize(new Dimension(96,24));
		missionNoPanel.add(formIDHeader);
		missionNoPanel.add(tMissionNo);
		
		mainFrame.add(missionNoPanel);
		
		// checkboxes
		JPanel formTypePanel = new JPanel();
		formTypePanel.setLayout(new FlowLayout());
		formTypePanel.setBackground(new Color(220,200,200));
		commLogCheckbox = new JCheckBox(FORM_TYPE_COMM_LOG,true);
		radioMessageCheckbox = new JCheckBox(FORM_TYPE_RADIO_MSG,true);
		searchAndRescueCheckbox = new JCheckBox(FORM_TYPE_SAR,true);
		formTypePanel.add(commLogCheckbox);
		formTypePanel.add(radioMessageCheckbox);
		formTypePanel.add(searchAndRescueCheckbox);
		mainFrame.add(formTypePanel);
		
		
		// create buttons
		JPanel buttonPanel = new JPanel();
		cancelButton = new JButton("Cancel");
		goButton = new JButton("Search");
		buttonPanel.add(cancelButton);
		buttonPanel.add(goButton);
		cancelButton.addActionListener(new CancelSearchListener());
		goButton.addActionListener(new StartSearchListener());
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
	
	/**
	 * This shows the results of a search.
	 */
	public void setResultsWindow(List<DBPushParams> formPushParams) {
		// removes existing frame
		mainFrame.dispose();
		dead = false;
		mainFrame = new SearchWindowJFrame();
		
		// new frame is added, in place of the old one
		mainFrame = new SearchWindowJFrame();
		
		mainFrame.setSize(RESULTS_WIDTH, RESULTS_HEIGHT);
		mainFrame.setLayout(new GridLayout(2,1));
		mainFrame.setBackground(new Color(230,230,230));
		
		DefaultListModel resultsListModel = new DefaultListModel();

		for (int i = 0; i < formPushParams.size(); i++) {
			DBPushParams p = formPushParams.get(i);
			String type = "";
			switch (p.type) {
		        case CL:
		            type = FORM_TYPE_COMM_LOG;
		            break;
		        case RM:
		        	type = FORM_TYPE_RADIO_MSG;
		            break;
		        case SAR:
		        	type = FORM_TYPE_SAR;
		            break;
	        }
			resultsListModel.addElement(p.missionNo +" ; "+p.date+" ; "+type);
			
		}
		
		
		resultsList = new JList(resultsListModel);
		resultsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		resultsList.setLayoutOrientation(JList.VERTICAL);
		resultsList.setVisibleRowCount(-1);
		resultsListScroller = new JScrollPane(resultsList);
		resultsListScroller.setPreferredSize(new Dimension(250, 80));
		mainFrame.add(resultsListScroller);

		// create buttons
		JPanel buttonPanel = new JPanel();
		cancelButton = new JButton("Cancel");
		goButton = new JButton("Open Selected Forms");
		buttonPanel.add(cancelButton);
		buttonPanel.add(goButton);
		cancelButton.addActionListener(new CancelSearchListener());
		goButton.addActionListener(new LoadFormsFromSearchListener(formPushParams));
		mainFrame.add(buttonPanel);
		
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
		removeListeners(goButton);
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

			String missionNo = tMissionNo.getText();
			
			List<DBPushParams> formPushParams = new ArrayList<DBPushParams>();
			if (commLogCheckbox.isSelected()) {
				System.out.println("CHECK 1");
				formPushParams.addAll(sqlServer.SelectFromCommLogWithMissionNum(missionNo));
			}
			if (radioMessageCheckbox.isSelected()) {
				System.out.println("CHECK 2");
				formPushParams.addAll(sqlServer
	                    .SelectFromRadMessWithMissionNum(missionNo));
			}
			if (searchAndRescueCheckbox.isSelected()) {
				System.out.println("CHECK 3");
				formPushParams.addAll(sqlServer.SelectFromSARWithMissionNum(missionNo));
			}
			
			setResultsWindow(formPushParams);
		}		
	}
	
	/**
	 * Listener for when you hit the "load forms" button, from the search results window.
	 * 
	 * @author danavold
	 *
	 */
	private class LoadFormsFromSearchListener implements ActionListener{
		private List<DBPushParams> formPushParams;
		
		public LoadFormsFromSearchListener(List<DBPushParams> formPushParams) {
			super();
			this.formPushParams = formPushParams;
			
		}
		
		public void actionPerformed(ActionEvent e) {
			// load forms that were selected
			int[] indices = resultsList.getSelectedIndices();
			
			for (int i = 0; i < indices.length; i++) {
				DBPushParams currentPushParams = formPushParams.get(indices[i]);
				formsController.fromDBPushParams(currentPushParams);
			}
			
			dispose();
		}	
	}
	
	private class SearchWindowJFrame extends JFrame {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 5188445703046822084L;

		public void dispose() {
			super.dispose();
			removeAllListeners();
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


