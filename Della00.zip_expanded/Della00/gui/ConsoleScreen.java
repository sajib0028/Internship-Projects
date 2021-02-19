package gui;

import java.awt.*;
import javax.swing.*;

/**
 * <p>
 * Title: ConsoleScreen
 * </p>
 *
 * <p>
 * Description:  A manually generated action item screen for Della
 * </p>
 *
 * <p>
 * Copyright: Copyright � 2007
 * </p>
 *
 * @author Lynn Robert Carter
 * Many thanks to Harry Sameshima for his original work.
 * @version 1.00
 */
public class ConsoleScreen extends JPanel {
	//---------------------------------------------------------------------------------------------------------------------
	// Console Screen constants

	//---------------------------------------------------------------------------------------------------------------------
	// Console Screen attributes

	//---------------------------------------------------------------------------------------------------------------------
	// Console Screen GUI elements
	JLabel consoleLabel = new JLabel();

	JLabel copyrightLabel = new JLabel();
	//---------------------------------------------------------------------------------------------------------------------

	/**
	 * The ConsoleScreen class constructor.
	 * 
	 */
	public ConsoleScreen() {
		// Set up all of the Graphical User Interface elements and position them on the screen
		guiInit();
	}

	/**
	 * Initialize each graphic element, position it on the screen, and add it to the loayout.
	 * 
	 */
	private void guiInit() {
		this.setLayout(null);
		consoleLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
		consoleLabel.setBorder(BorderFactory.createEtchedBorder());
		consoleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		consoleLabel.setText("Console");
		consoleLabel.setBounds(new Rectangle(0, 0, 657, 20));

		copyrightLabel.setFont(new java.awt.Font("Dialog", Font.BOLD, 10));
		copyrightLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		copyrightLabel.setText("Copyright � 2021 Sajib Biswas");
		copyrightLabel.setBounds(new Rectangle(0, 400, 620, 15));

		this.add(consoleLabel);
		this.add(copyrightLabel);
	}
}
