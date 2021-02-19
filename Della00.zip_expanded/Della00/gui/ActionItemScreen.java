package gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import control.*;

import model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * Title: ActionItemScreen
 * </p>
 * 
 * <p>
 * Description: A manually generated Action Item Screen for Della
 * </p>
 * 
 * <p>
 * Copyright: Copyright © 2007
 * </p>
 * 
 * @author Lynn Robert Carter
 * @version 1.00
 * Many thanks to Harry Sameshima for his original work.
 */
public class ActionItemScreen extends JPanel {
	//---------------------------------------------------------------------------------------------------------------------
	// Action Item Screen constants

	public static final int noItemSelected = -1;

	//---------------------------------------------------------------------------------------------------------------------
	// Action Item Screen attributes

	private Boolean updatingGUI = false;
	private Controller theController = null;
	private ActionItemManager aiM = null;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	//---------------------------------------------------------------------------------------------------------------------
	// Action Item Screen GUI elements
	JLabel actionItemLabel = new JLabel();

	JLabel selectedLabel = new JLabel();
	JLabel nameLabel = new JLabel();
	JTextField nameTextField = new JTextField();
	JLabel descriptionLabel = new JLabel();
	JScrollPane descriptionScrollPane = new JScrollPane();
	JTextArea descriptionTextArea = new JTextArea();
	JLabel resolutionLabel = new JLabel();
	JScrollPane resolutionScrollPane = new JScrollPane();
	JTextArea resolutionTextArea = new JTextArea();

	// Unsaved updated fields
	DocumentListener aiChangeListener = new DocumentListener() {
		public void changedUpdate(DocumentEvent de){ checkForUnsavedUpdates(); }
		public void insertUpdate(DocumentEvent de){ checkForUnsavedUpdates(); }
		public void removeUpdate(DocumentEvent de){ checkForUnsavedUpdates(); }
	};
	JLabel unsavedChangesLabel = new JLabel();

	JLabel datesLabel = new JLabel();
	JLabel creationLabel = new JLabel();
	JLabel creationValueLabel = new JLabel();
	JLabel dueDateLabel = new JLabel();
	JTextField dueDateTextField = new JTextField();
	JLabel formatLabel = new JLabel();
	JLabel actionItemLabel2 = new JLabel();
	JLabel statusLabel = new JLabel();
	JComboBox statusComboBox = new JComboBox(ActionItemManager.statusStrings);
	ActionListener statusSelectorActionListner = new ActionListener() {
		public void actionPerformed(ActionEvent ae) { checkForUnsavedUpdates(); }
	};

	// Action Buttons
	JButton updateButton = new JButton();
	ActionListener updateButtonActionListner = new ActionListener() {
		public void actionPerformed(ActionEvent ae) { updateActionItem(ae); }
	};
	JButton clearButton = new JButton();
	ActionListener clearButtonActionListner = new ActionListener() {
		public void actionPerformed(ActionEvent ae) { clearActionItemForm(ae); }
	};
	//---------------------------------------------------------------------------------------------------------------------


	/**
	 * The ActionItemScreen class constructor.
	 * 
	 */
	public ActionItemScreen() {
		// Use a modified singleton pattern to access the application's state
		theController = Controller.getInstance();
		aiM = theController.getActionItemManager();

		// Set up all of the Graphical User Interface elements and place them on the screen
		guiInit();

		// Initialize the screen with the current action item
		loadScreen();
	}

	/**
	 * Initialize each graphic element, position it on the screen, and add it to the loayout.
	 * 
	 */
	private void guiInit() {
		// Updating the GUI
		updatingGUI = true;
		
		// Set all of the graphical elements in this screen by adding them to the layout
		this.setLayout(null);

		actionItemLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
		actionItemLabel.setBorder(BorderFactory.createEtchedBorder());
		actionItemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		actionItemLabel.setText("Action Items");
		actionItemLabel.setBounds(new Rectangle(0, 0, 657, 20));

		selectedLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		selectedLabel.setText("Selected Action Item:");
		selectedLabel.setBounds(new Rectangle(6, 145, 123, 15));
		nameLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		nameLabel.setText("Name:");
		nameLabel.setBounds(new Rectangle(7, 165, 42, 15));
		nameTextField.setText("");
		nameTextField.setBounds(new Rectangle(46, 165, 390, 22));
		nameTextField.getDocument().addDocumentListener(aiChangeListener);

		descriptionLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		descriptionLabel.setText("Description:");
		descriptionLabel.setBounds(new Rectangle(6, 190, 69, 15));
		descriptionScrollPane.setBounds(new Rectangle(7, 210, 430, 75));
		descriptionScrollPane.getViewport().add(descriptionTextArea);
		descriptionTextArea.setText("");
		descriptionTextArea.getDocument().addDocumentListener(aiChangeListener);

		resolutionLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		resolutionLabel.setText("Resolution:");
		resolutionLabel.setBounds(new Rectangle(6, 295, 73, 15));
		resolutionScrollPane.setBounds(new Rectangle(7, 315, 430, 75));
		resolutionScrollPane.getViewport().add(resolutionTextArea);
		resolutionTextArea.setToolTipText("");
		resolutionTextArea.setText("");
		resolutionTextArea.getDocument().addDocumentListener(aiChangeListener);

		datesLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		datesLabel.setText("Dates");
		datesLabel.setBounds(new Rectangle(450, 175, 34, 16));

		creationLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		creationLabel.setText("Creation:");
		creationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		creationLabel.setBounds(new Rectangle(469, 195, 51, 16));
		creationValueLabel.setText("");
		creationValueLabel.setBounds(new Rectangle(528, 195, 85, 16));

		dueDateLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		dueDateLabel.setText("Due:");
		dueDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dueDateLabel.setBounds(new Rectangle(469, 217, 51, 16));
		dueDateTextField.setBounds(new Rectangle(524, 215, 90, 20));
		dueDateTextField.getDocument().addDocumentListener(aiChangeListener);
		formatLabel.setFont(new java.awt.Font("Dialog", Font.PLAIN, 10));
		formatLabel.setText("Use yyyy-mm-dd format");
		formatLabel.setBounds(new Rectangle(495, 238, 125, 11));

		actionItemLabel2.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		actionItemLabel2.setText("Action Item");
		actionItemLabel2.setBounds(new Rectangle(450, 260, 67, 15));

		statusLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		statusLabel.setText("Status:");
		statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		statusLabel.setBounds(new Rectangle(469, 277, 51, 16));
		statusComboBox.setBounds(new Rectangle(524, 275, 90, 25));
		statusComboBox.addActionListener(statusSelectorActionListner);

		updateButton.setFont(new Font("Dialog", Font.BOLD, 11));
		updateButton.setBounds(new Rectangle(3, 395, 170, 30));
		updateButton.setText("Update This Action Item");
		updateButton.addActionListener(updateButtonActionListner);

		clearButton.setFont(new Font("Dialog", Font.BOLD, 11));
		clearButton.setBounds(new Rectangle(173, 395, 126, 30));
		clearButton.setText("Clear This Form");
		clearButton.addActionListener(clearButtonActionListner);

		unsavedChangesLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		unsavedChangesLabel.setBounds(new Rectangle(250, 430, 200, 15));
		unsavedChangesLabel.setText("");
		unsavedChangesLabel.setForeground(Color.red);		
		
		//----------------------------------------------------------------------------
		// Add the objects to the layout
		this.add(actionItemLabel);

		this.add(selectedLabel);
		this.add(nameLabel);
		this.add(nameTextField);
		this.add(descriptionLabel);
		this.add(descriptionScrollPane);
		this.add(resolutionLabel);
		this.add(resolutionScrollPane);

		this.add(datesLabel);
		this.add(creationLabel);
		this.add(creationValueLabel);
		this.add(dueDateLabel);
		this.add(dueDateTextField);
		this.add(formatLabel);
		this.add(actionItemLabel2);
		this.add(statusLabel);
		this.add(statusComboBox);

		this.add(updateButton);
		this.add(clearButton);

		this.add(unsavedChangesLabel);

		// Done updating the GUI
		updatingGUI = false;
	}

	/**
	 * Clear the current action item and the attribute related combo boxes
	 * 
	 */
	private void clearAI() {
		updatingGUI = true;
		aiM.clearCurrentActionItem();
		nameTextField.setText("");
		descriptionTextArea.setText("");
		resolutionTextArea.setText("");
		creationValueLabel.setText("");
		dueDateTextField.setText("");

		// Select the Open status
		statusComboBox.setSelectedIndex(ActionItemManager.statusOpen);
		updatingGUI = false;
	}

	/**
	 * Process a "Clear This Form" button click request
	 * Clear out the current action item and inform the user if this results in unsaved changes
	 * 
	 * @param e ActionEvent
	 */
	private void clearActionItemForm(ActionEvent e) {
		// Reset the current Action Item Fields
		clearAI();
		
		theController.setDirtyFlag(true);
		checkForUnsavedUpdates();
	}

	/**
	 * Update the current action item in memory
	 * 
	 * @param e ActionEvent
	 */
	private void updateActionItem(ActionEvent e) {
		// Tell the ActionItemManager to save the update
		try {
			aiM.updateActionItem(nameTextField.getText(),
					descriptionTextArea.getText(),
					resolutionTextArea.getText(),
					statusComboBox.getSelectedItem().toString(),
					dueDateTextField.getText());
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(),
					"Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// This code is just for Della00 to simulate the effect of the "create" button being pressed
		// remove this for Della01
		creationValueLabel.setText(dateFormat.format(new Date()));

		theController.setDirtyFlag(true);
		checkForUnsavedUpdates();
	}

	/**
	 * Fill the screen with the values of the current action item, if we have one, and display it.
	 */
	public void loadScreen() {
		updatingGUI = true;
		// Fetch the current action item.  If there isn't one, leave now
		ActionItem ai = aiM.getCurrentActionItem();
		if (ai == null) {
			clearAI();
			updatingGUI = true;
			statusComboBox.setSelectedIndex(ActionItemManager.statusOpen);
			creationValueLabel.setText("");
			dueDateTextField.setText("");
		}
		else {
			// Define the text fields
			updatingGUI = true;
			nameTextField.setText(ai.getActionItemName());
			descriptionTextArea.setText(ai.getDescription());
			descriptionTextArea.setCaretPosition(0);
			resolutionTextArea.setText(ai.getResolution());
			resolutionTextArea.setCaretPosition(0);
		}
		// Define the status combobox value
		for (int i = 0; i < ActionItemManager.statusStrings.length; ++i)
			if (ai.getStatus().compareTo(ActionItemManager.statusStrings[i]) == 0) {
				statusComboBox.setSelectedIndex(i);
				break;
			}

		// Define the creation and due dates
		if (ai.getCreatedDate() != null)
			creationValueLabel.setText(dateFormat.format(ai.getCreatedDate()));
		else
			creationValueLabel.setText("");
		if (ai.getDueDate() != null)
			dueDateTextField.setText(dateFormat.format(ai.getDueDate()));
		else
			dueDateTextField.setText("");
		
		updatingGUI = false;
	}

	/**
	 * Any number of events has occurred that could change the display.  See if the current edit values still
	 * match the current action item.  If so, then no warning is needed.  If not, then given a warning (red
	 * text) that informs the user that there are edits to the action item that have not been saved.
	 * 
	 */
	private void checkForUnsavedUpdates(){
		if (updatingGUI) return;
		if (nameTextField.getText().equals(aiM.getCurrentActionItem().getActionItemName()) &&
				descriptionTextArea.getText().equals(aiM.getCurrentActionItem().getDescription()) &&
				resolutionTextArea.getText().equals(aiM.getCurrentActionItem().getResolution()) && 
				dueDateTextField.getText().equals(aiM.getCurrentActionItem().getDueDate()!=null?dateFormat.format(aiM.getCurrentActionItem().getDueDate()):"") &&
				(	(statusComboBox.getSelectedIndex() == 0 && aiM.getCurrentActionItem().getStatus().equals("")) ||
						(statusComboBox.getSelectedIndex() == 0 && aiM.getCurrentActionItem().getStatus().equals("Open")) ||
						(statusComboBox.getSelectedIndex() == 1 && aiM.getCurrentActionItem().getStatus().equals("Closed"))
				)
		){
			unsavedChangesLabel.setText("");
			aiM.setEditChangesPending(false);
		}
		else {
			unsavedChangesLabel.setText("There are unsaved changes!");
			aiM.setEditChangesPending(true);
		}	
	}
}
