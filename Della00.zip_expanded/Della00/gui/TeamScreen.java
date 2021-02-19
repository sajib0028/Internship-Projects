package gui;

import java.awt.*;
import javax.swing.*;

/**
 * <p>
 * Title: TeamScreen
 * </p>
 *
 * <p>
  * Description: The Della Team Screen code
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
public class TeamScreen extends JPanel {
	//---------------------------------------------------------------------------------------------------------------------
	// Team Screen constants

	//---------------------------------------------------------------------------------------------------------------------
	// Team Screen attributes

	//---------------------------------------------------------------------------------------------------------------------
	// Team Screen GUI elements
	JLabel teamLabel = new JLabel();

	public TeamScreen() {
		// Set up all of the Graphical User Interface elements and place them on the screen
		guiInit();
	}

	/**
	 * Initialize each graphic element, position it on the screen, and add it to the layout.
	 * 
	 */
	private void guiInit() {
		// Set all of the graphical elements in this screen by adding them to the layout
		this.setLayout(null);

		teamLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
		teamLabel.setBorder(BorderFactory.createEtchedBorder());
		teamLabel.setHorizontalAlignment(SwingConstants.CENTER);
		teamLabel.setText("Teams");
		teamLabel.setBounds(new Rectangle(0, 0, 657, 20));

		//----------------------------------------------------------------------------
		// Add the objects to the layout
		this.add(teamLabel);
	}
}
