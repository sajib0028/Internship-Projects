package model;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * <p>
 * Title: ActionItemManager
 * </p>
 *
 * <p>
 * Description: A class to organize and manage all known action items
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
public class ActionItemManager {

	//---------------------------------------------------------------------------------------------------------------------
	// Constants

	private transient SimpleDateFormat dateFormat = null;

	public static final int statusOpen = 0;			// The constants for Open and Close
	public static final int statusClosed = 1;
	public static final String[] statusStrings = {"Open", "Closed"};

	private boolean editChangesPending;

	//---------------------------------------------------------------------------------------------------------------------
	// Attributes

	private ActionItem currentActionItem = null;	// Currently displayed action item
	private ActionItem emptyActionItem = null;		// The standard empty action item
	
	//---------------------------------------------------------------------------------------------------------------------

	/**
	 * The ActionItemManager class constructor.
	 * 
	 */
	public ActionItemManager() {
		currentActionItem = new ActionItem();
		emptyActionItem = new ActionItem();
		emptyActionItem.setCreatedDate(null);
	}

	/**
	 * Update an existing action item based on the parameters pass to the routine
	 * @param name String
	 * @param description String
	 * @param resolution String
	 * @param status String
	 * @param dueDateStr String
	 * @return ActionItem
	 */
	public ActionItem updateActionItem(String name, String description,
			String resolution, String status,
			String dueDateStr) 
	throws Exception {
		// Just allocate a new action item and save it.  The inner workings of
		// this method will change drastically in Task 01 so I don't care about
		// being wasteful now.
		ActionItem ai = new ActionItem(name, description, resolution, status);

		// Check if there are problems with the modifications.
		validateActionItem(ai, name, dueDateStr);

		// We passed the tests so it's ok to set the new current action item
		setCurrentActionItem(ai);
		return ai;
	}

	/**
	 * Check the parameters to see if the action item can be added to the list of action items.
	 * @param name String
	 * @param dueDateStr String
	 * @return boolean
	 * @throws an exception if there are any problems with the input.
	 */
	private void validateActionItem(ActionItem ai, String name, String dueDateStr) 
	throws Exception {
		if (name.trim().length() == 0) {
			throw new Exception("The Action Item Name must not be empty!   ");
		}

		Date dueDate = null;
		if (dueDateStr.length() != 0) {
			try {
				dueDate = dateFormat.parse(dueDateStr);
			}
			catch (ParseException ex) {
				throw new Exception("Please use the requested date format!   ");
			}
		}
		ai.setDueDate(dueDate);
	}

	// The usual getters and setters

	/**
	 * Get the current action item 
	 * @return	- The current action item
	 */
	public ActionItem getCurrentActionItem() {
		if (currentActionItem == null) 
			return emptyActionItem;
		return currentActionItem;
	}

	public void setCurrentActionItem(ActionItem x) { currentActionItem = x; }

	public void clearCurrentActionItem() { currentActionItem = emptyActionItem; }

	public void setDateFormatChecker() { dateFormat = new SimpleDateFormat("yyyy-MM-dd"); }

	public void setEditChangesPending(boolean flag){ editChangesPending = flag; }

	public boolean getEditChangesPending(){ return editChangesPending; }
}
