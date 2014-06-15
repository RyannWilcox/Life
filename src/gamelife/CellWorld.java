package gamelife;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;




import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class CellWorld extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JFrame myFrame;
	private JButton[] button;
	private static final String[] BUTTON_STR = { "Go", "Step", "Stop","Clear","Quit" };
	private static final int MAX_ROWS = 100;
	private static final int MAX_COLUMNS = 100;

	private GridSquare theGrid[][] = new GridSquare[MAX_ROWS][MAX_COLUMNS];
	private Cell cell[][] = new Cell[MAX_ROWS][MAX_COLUMNS];
	private CellUpdate updateCells = new CellUpdate();
	private JLabel generation = new JLabel("Gen: 1");
	private JMenuBar menu = new JMenuBar();
	
	private boolean running = true;
	private int genCount = 1;
	private int newRow = 0; 
	private int newCol = 0;
	private int speed = 120;
	private Updater updates = new Updater();

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
		
		/*
		 * Will allow the user to click and add or
		 * click and remove any cells
		 */
		addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				int count = e.getClickCount();
				
				//Make clicked cell alive
				if(count == 1){
					newRow = e.getX() / (1000/100);
					newCol = e.getY() / (1000/100);
					cell[newRow][newCol].makeAlive();
					System.out.println("Cell comes alive!!! at: ("+ newRow + ","+newCol+")");
					// For testing purposes...
					repaint();
				}
				//Make clicked cell dead
				else if(count == 2){
					newRow = e.getX() / (1000/100);
					newCol = e.getY() / (1000/100);
					cell[newRow][newCol].makeDead();
					// System.out.println("Cell dies at: ("+ newRow + ","+newCol+")");
					// For testing purposes
					repaint();
					
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		
		});
		
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
		/*
		 * Menu for selecting specific patterns
		 */
		myFrame.setJMenuBar(menu);
		JMenu choices = new JMenu("Patterns");
		JMenu speeds = new JMenu("Speeds");
		
		/*Patterns*/
		JMenuItem gliderGun = new JMenuItem("GliderGun");
		JMenuItem pulsar = new JMenuItem("Pulsar");
		JMenuItem Pentomino = new JMenuItem("R-Pentomino");
		JMenuItem spider = new JMenuItem("Spider");
		JMenuItem custom = new JMenuItem("Custom");
		
		/*Speeds*/
		JMenuItem fast = new JMenuItem("Fast");
		JMenuItem med = new JMenuItem("Medium");
		JMenuItem slow = new JMenuItem("Slow");
		menu.add(choices);
		menu.add(speeds);
		choices.add(gliderGun);
		choices.add(pulsar);
		choices.add(Pentomino);
		choices.add(spider);
		choices.add(custom);
		speeds.add(fast);
		speeds.add(med);
		speeds.add(slow);
		
		
		/*
		 * Clears the cells and then prints the glider gun
		 *  pattern to the grid.
		 */
		gliderGun.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cell = updateCells.clearCells(cell, MAX_ROWS, MAX_COLUMNS);
				cell = updateCells.createGliderGun(cell);
				genCount = 1;
				String genStr= Integer.toString(genCount);
				/*update JLabel*/
				generation.setText("Gen: "+genStr);
				repaint();
			}
		});
		
		/* Clears the grid of all live cells. Then
		 * creates the formation pulsar and paints the
		 * grid with those cells.
		 */
		pulsar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cell = updateCells.clearCells(cell, MAX_ROWS, MAX_COLUMNS);
				cell = updateCells.createPulsar(cell);
				genCount = 1;
				String genStr= Integer.toString(genCount);
				/*update JLabel*/
				generation.setText("Gen: "+genStr);
				repaint();
			}
		});
		
		
		/* Clears the grid of all live cells then
		 * creates the formation R-Pentomino and
		 * updates the grid    	
		 */
		Pentomino.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				cell = updateCells.clearCells(cell, MAX_ROWS, MAX_COLUMNS);
				cell = updateCells.createRPentomino(cell);
				genCount = 1;
				String genStr= Integer.toString(genCount);
				/*update JLabel*/
				generation.setText("Gen: "+genStr);
				repaint();
			}
		});
		
		spider.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				cell = updateCells.clearCells(cell, MAX_ROWS, MAX_COLUMNS);
				cell = updateCells.createSpider(cell);
				genCount = 1;
				String genStr= Integer.toString(genCount);
				/*update JLabel*/
				generation.setText("Gen: "+genStr);
				repaint();
			}
		});
		
		custom.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				cell = updateCells.clearCells(cell, MAX_ROWS, MAX_COLUMNS);
				cell = updateCells.createCustom(cell);
				genCount = 1;
				String genStr= Integer.toString(genCount);
				/*update JLabel*/
				generation.setText("Gen: "+genStr);
				repaint();
			}	
		});
		
		/*CHANGES THE SPEED OF THE THREAD!!!*/
		med.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				speed = 120;
			}
		});
		fast.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				speed = 70;
			}
		});
		slow.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				speed = 180;
			}
		});
		
			
		
	}

	/**
	 * Paints the grid with cell updates
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

	/**
	 * Handles the action of pressing the buttons
	 * in the control panel
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button[0]) {
			System.out.println("Start thread!");
		    running = true;
		    //Begin updating the grid
		    Thread updateThread = new Thread(updates);
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
			//Will stop the thread
				running = false;
		}
		if(e.getSource() == button[3]){
			cell = updateCells.clearCells(cell, MAX_ROWS, MAX_COLUMNS);
			genCount = 1;
			running = false;
			repaint();
		}
		if (e.getSource() == button[4]) {
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
				// While the stop button/quit button is not pushed
				// the thread will continue to loop
				cell = updateCells.updateSquares(cell, 100, 100);
				/*update JLabel*/
				genCount++;
				String genStr= Integer.toString(genCount);
				generation.setText("Gen: "+genStr);
				
				repaint();
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
