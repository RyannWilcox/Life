package gamelife;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlPanel extends JPanel implements ActionListener, Bounds {
	private static final long serialVersionUID = 1L;
	private JButton[] button;
	private static final String[] BUTTON_STR = { "Go", "Step", "Stop", "Clear", "Quit" };
	private JLabel generation = new JLabel("Gen: 1");
	private Updater updates;
	private GridPanel cellGrid;
	private int genCount = 1;
	private CellsAndGrid cellAndGridSquare;

	public ControlPanel(GridPanel aGrid, CellsAndGrid data) {
		cellGrid = aGrid;
		cellAndGridSquare = data;
		updates = new Updater(true, cellAndGridSquare, cellGrid, this);
		setLayout(new GridLayout(8, 1));
		button = new JButton[BUTTON_STR.length];

		for (int i = 0; i < BUTTON_STR.length; i++) {
			button[i] = new JButton(BUTTON_STR[i]);
			button[i].addActionListener(this);
			button[i].setFocusable(false);
			add(button[i]);
		}
		add(generation);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// creates a thread to cycle through generations
		if (e.getSource() == button[0]) {
			updates.startRunning();
			// Begin updating the grid
			// A new Thread is created...
			Thread updateThread = new Thread(updates);
			updateThread.start();
		}
		// ALlows you to step through generations
		if (e.getSource() == button[1]) {
			if (cellAndGridSquare.getSeedRuleStatus()) {
				cellAndGridSquare.seedUpdateCells(MAX_ROWS, MAX_COLUMNS);
			} else {
				cellAndGridSquare.updateCells(MAX_ROWS, MAX_COLUMNS);
			}
			updateGenLabel();
			cellGrid.repaint();
		}
		// stops the automatic generation
		if (e.getSource() == button[2]) {
			updates.stopRunning();
		}

		// Clears all alive cells from the board
		if (e.getSource() == button[3]) {
			// Make all cells dead on the grid
			cellAndGridSquare.clearCells(MAX_ROWS, MAX_COLUMNS);
			setGenCount(1);
			updates.stopRunning();
			cellGrid.repaint();
		}
		// Quits the game of life
		if (e.getSource() == button[4]) {
			System.exit(1);
		}
	}

	/**
	 * Getter
	 * 
	 * @return generation count
	 */
	public int getGenCount() {
		return genCount;
	}

	/**
	 * set a new generation count
	 * 
	 * @param gc
	 *            the new number
	 */
	public void setGenCount(int gc) {
		genCount = gc;
	}

	/**
	 * Gets the label responsible for showing the generation
	 * 
	 * @return the JLabel
	 */
	public JLabel getGenLabel() {
		return generation;
	}

	/**
	 * updates the generation label it increments the generation by 1
	 */
	public void updateGenLabel() {
		genCount++;
		String genStr = Integer.toString(genCount);
		generation.setText("Gen: " + genStr);
	}

	/**
	 * Gets the updater object
	 * 
	 * @return the updater object
	 */
	public Updater getUpdater() {
		return updates;
	}
}
