package gamelife;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;




import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CellWorld extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	JFrame myFrame;
	JButton[] button;
	static final String[] BUTTON_STR = { "Go", "Step", "Stop", "Quit" };
	private final int MAX_ROWS = 100;
	private final int MAX_COLUMNS = 100;

	private GridSquare theGrid[][] = new GridSquare[MAX_ROWS][MAX_COLUMNS];
	private Cell cell[][] = new Cell[MAX_ROWS][MAX_COLUMNS];
	private CellUpdate updateCells = new CellUpdate();
	private boolean running = true;
	private JLabel generation = new JLabel("Gen: 1");
	private int genCount = 1;

	/*
	 * private int squareX = 0; 
	 * private int squareY = 0;
	 * private int clickCount = 0;
	 */
	private Updater updates = new Updater();
	private Thread updateThread = new Thread(updates);

	public CellWorld(String title, int width, int height) {
		super();
		setDoubleBuffered(true);
		// Populate cell array
		// Populate Grid array
		// They will all start off dead here..
		for (int i = 0; i < MAX_ROWS; i++) {
			for (int j = 0; j < MAX_COLUMNS; j++) {
				theGrid[i][j] = new GridSquare(i * 10, j * 10, i, j);
				cell[i][j] = new Cell();
			}
		}

		// this is for testing until I get the mouse clicks working
		// This group starts as a box and then turns into a blinker
		cell[20][20].makeAlive();
		cell[20][21].makeAlive();
		cell[20][22].makeAlive();
		cell[23][20].makeAlive();
		cell[23][21].makeAlive();
		cell[23][22].makeAlive();

		cell[21][20].makeAlive();
		cell[22][20].makeAlive();

		cell[22][22].makeAlive();
		cell[21][22].makeAlive();
		//<><><><><><><><><><><><><.
		
		//This one is called the R-Pentomino pattern..
		cell[50][50].makeAlive();
		cell[50][51].makeAlive();
		cell[50][52].makeAlive();
		
		cell[49][51].makeAlive();
		cell[51][50].makeAlive();
		
		
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
		myFrame.add(this, BorderLayout.CENTER);

		JPanel controlPanel = new JPanel(new GridLayout(8, 1));
		button = new JButton[BUTTON_STR.length];
		for (int i = 0; i < BUTTON_STR.length; i++) {
			button[i] = new JButton(BUTTON_STR[i]);
			button[i].addActionListener(this);
			button[i].setFocusable(false);
			controlPanel.add(button[i]);
		}
		controlPanel.add(generation);
		myFrame.add(controlPanel, BorderLayout.EAST);
	}

	/**
	 * Paints the grid
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		for (int i = 0; i < MAX_ROWS; i++) {
			for (int j = 0; j < MAX_COLUMNS; j++) {
				theGrid[i][j].drawSquare(g);// draw grid in graphic panel

				/* checks for living cells */
				if (cell[i][j].isAlive()) {
					theGrid[i][j].drawGreen(g);
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button[0]) {
			System.out.println("Start thread!");
		    setDoubleBuffered(true);
			updateThread.start();
		}
		if (e.getSource() == button[1]) {
			cell = updateCells.updateSquares(cell, MAX_ROWS, MAX_COLUMNS);
			genCount++;
			String genStr= Integer.toString(genCount);
			/*update JLabel*/
			generation.setText("Gen: "+genStr);
			repaint();

		}
		if (e.getSource() == button[2]) {
				running = false;
		}

		if (e.getSource() == button[3]) {
			System.exit(1);
		}
	}

	/**
	 * Nested Thread class This is for a continous update of the cells instead
	 * of constantly pushing the Step button
	 * 
	 * @author ryanwilcox
	 * 
	 */
	public class Updater implements Runnable {
		@Override
		public void run() {
			while (running) {
				cell = updateCells.updateSquares(cell, 100, 100);
				genCount++;
				String genStr= Integer.toString(genCount);
				/*update JLabel*/
				generation.setText("Gen: "+genStr);
				repaint();
				try {
					Thread.sleep(120);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}
}
