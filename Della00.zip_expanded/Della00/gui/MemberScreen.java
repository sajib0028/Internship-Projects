package gui;

import java.awt.*;
import javax.swing.*;

/**
 * <p>
 * Title: MemberScreen
 * </p>
 *
 * <p>
 * Description: The Della Member Screen code
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
public class MemberScreen extends JPanel {
	//---------------------------------------------------------------------------------------------------------------------
	// Member Screen constants


	//---------------------------------------------------------------------------------------------------------------------
	// Member Screen attributes


	//---------------------------------------------------------------------------------------------------------------------
	// Member Screen GUI elements
	JLabel memberLabel = new JLabel();

	//---------------------------------------------------------------------------------------------------------------------

	/**
	 * The MemberScreen class constructor.
	 * 
	 */
	public MemberScreen() {
		// Set up all of the Graphical User Interface elements and place them on the screen
		guiInit();
	}

	/**
	 * Initialize each graphic element, position it on the screen, and add it to the layout.
	 * 
	 */
	private void guiInit() {
		this.setLayout(null);
		memberLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
		memberLabel.setBorder(BorderFactory.createEtchedBorder());
		memberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		memberLabel.setText("Members");
		memberLabel.setBounds(new Rectangle(0, 0, 657, 20));
		
		//----------------------------------------------------------------------------
		// Add the objects to the layout
		this.add(memberLabel);
	}
}

