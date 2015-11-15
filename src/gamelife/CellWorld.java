package gamelife;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class CellWorld {
	private JFrame myFrame = new JFrame();
	private CellsAndGrid cellAndGridSquare = new CellsAndGrid();
	private GridPanel gridpanel = new GridPanel(cellAndGridSquare);
	private ControlPanel controlpanel = new ControlPanel(gridpanel, cellAndGridSquare);
	private ChoiceMenu menuChoices = new ChoiceMenu(gridpanel, controlpanel, cellAndGridSquare);

	public CellWorld(String title, int width, int height) {
		super();
		layoutSetup(title, width, height);
		myFrame.setVisible(true);
	}

	/**
	 * Sets up the structure of the Frame
	 * 
	 * @param theTitle
	 * @param theWidth
	 * @param theHeight
	 */
	public void layoutSetup(String theTitle, int theWidth, int theHeight) {
		myFrame.setTitle(theTitle);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(theWidth, theHeight);
		myFrame.setLayout(new BorderLayout());

		// panel to store the grid squares
		myFrame.add(gridpanel);

		// panel for the buttons
		myFrame.add(controlpanel, BorderLayout.EAST);

		// JMenu for game customization
		myFrame.setJMenuBar(menuChoices);
	}
}
