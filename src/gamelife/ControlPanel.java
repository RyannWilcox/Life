package gamelife;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlPanel extends JPanel implements ActionListener,RowColumnBounds{
	private static final long serialVersionUID = 1L;
	private JButton[] button;
	private static final String[] BUTTON_STR = { "Go", "Step", "Stop","Clear","Quit" };
	private JLabel generation = new JLabel("Gen: 1");
	private boolean running = true;
	private Updater updates = new Updater();
	private GridPanel cellGrid;
	private int genCount = 1;
	private int speed = 120;
	private CellsAndGrid cellAndGridSquare;

	public ControlPanel(GridPanel aGrid, CellsAndGrid data){
		cellGrid = aGrid;
		cellAndGridSquare = data;
		
		setLayout(new GridLayout(8,1));
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
		if (e.getSource() == button[0]) {
		    running = true;
		    // Begin updating the grid
		    // A new Thread is created...
		    Thread updateThread = new Thread(updates);
			updateThread.start();
		

		}
		if (e.getSource() == button[1]) {
			cellAndGridSquare.updateCells(MAX_ROWS, MAX_COLUMNS);
			updateGenLabel();
			cellGrid.repaint();
			

		}
		if (e.getSource() == button[2]) {
			/* Will cause the thread to exit */
				running = false;
		}
		if(e.getSource() == button[3]){
			// Make all cells dead on the grid
			cellAndGridSquare.clearCells(MAX_ROWS, MAX_COLUMNS);
			setGenCount(1);
			running = false;
			cellGrid.repaint();
		}
		if (e.getSource() == button[4]) {
			System.exit(1);
		}
		
	}
	
	/**
	 * Getter
	 * @return generation count
	 */
	public int getGenCount(){
		return genCount;
	}
	
	/**
	 * set a new generation count
	 * @param gc the new number
	 */
	public void setGenCount(int gc){
		genCount = gc;
	}

	
	/**
	 * Gets the label responsible
	 * for showing the generation
	 * @return the JLabel
	 */
	public JLabel getGenLabel(){
		return generation;
	}
	
	/**
	 * updates the generation label
	 * it increments the generation by 1
	 */
	public void updateGenLabel(){
		genCount++;
		String genStr= Integer.toString(genCount);
		generation.setText("Gen: "+genStr);
	}
	
	public int getSpeed(){
		return speed;
	}
	public void setSpeed(int newSpeed){
		speed = newSpeed;
	}
	
	/**
	 * inner Thread class This is for a continous update of the cells instead
	 * of constantly pushing the Step button
	 * 
	 * @author ryanwilcox
	 * 
	 */
	private class Updater implements Runnable {
		@Override
		public void run() {
			while (running) {
				// While the stop button/quit button is not pushed
				// the thread will continue to loop
				cellAndGridSquare.updateCells(MAX_ROWS, MAX_COLUMNS);
				/*update JLabel*/
				updateGenLabel();
				cellGrid.repaint();
				try {
					Thread.sleep(speed);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}
}
