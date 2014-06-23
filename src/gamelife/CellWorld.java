package gamelife;

import java.awt.BorderLayout;
import javax.swing.JFrame;


public class CellWorld {
	private JFrame myFrame;
	private CellsAndGrid cellAndGridSquare = new CellsAndGrid();
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
		myFrame = new JFrame(theTitle);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(theWidth, theHeight);
		myFrame.setLayout(new BorderLayout());
		GridPanel gridpanel = new GridPanel(cellAndGridSquare);
		myFrame.add(gridpanel);
		
		ControlPanel cp = new ControlPanel(gridpanel,cellAndGridSquare);
		myFrame.add(cp,BorderLayout.EAST);
		
		
		/* Menu for selecting specific patterns */
		ChoiceMenu menuChoices = new ChoiceMenu(gridpanel,cp,cellAndGridSquare);
		myFrame.setJMenuBar(menuChoices);
	}
}
