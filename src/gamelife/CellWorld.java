package gamelife;

import gamelife.GridSquare.colors; 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

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
	
	/*possible colors to paint the live cells*/
	private colors grn = colors.GREEN;
	private colors rd = colors.RED;
	private colors blck = colors.BLACK;
	
	public CellWorld(String title, int width, int height) {
		super();
		setDoubleBuffered(true);
		// Populate cell array
		// Populate Grid array
		// They will all start off dead here..
		for (int i = 0; i < MAX_ROWS; i++) {
			for (int j = 0; j < MAX_COLUMNS; j++) {
				theGrid[i][j] = new GridSquare(i * 10, j * 10, i, j, grn);
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
					//System.out.println("Cell comes alive!!! at: ("+ newRow + ","+newCol+")");
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
		JMenu colors = new JMenu("Colors");
		JMenu zoom = new JMenu("Zoom");
		
		/*Patterns*/
		ButtonGroup patternGroup = new ButtonGroup();
		JRadioButtonMenuItem gliderGun = new JRadioButtonMenuItem("GliderGun");
		JRadioButtonMenuItem pulsar = new JRadioButtonMenuItem("Pulsar");
		JRadioButtonMenuItem Pentomino = new JRadioButtonMenuItem("R-Pentomino");
		JRadioButtonMenuItem spider = new JRadioButtonMenuItem("Spider");
		JRadioButtonMenuItem custom = new JRadioButtonMenuItem("Custom");
		patternGroup.add(gliderGun);patternGroup.add(Pentomino);patternGroup.add(spider);patternGroup.add(custom);
		
		/*Speeds*/
		ButtonGroup speedGroup = new ButtonGroup();
		JRadioButtonMenuItem fast = new JRadioButtonMenuItem("Fast");
		JRadioButtonMenuItem med = new JRadioButtonMenuItem("Medium");
		JRadioButtonMenuItem slow = new JRadioButtonMenuItem("Slow");
		speedGroup.add(fast);speedGroup.add(slow);speedGroup.add(med);
		
		/*Colors*/
		ButtonGroup colorGroup = new ButtonGroup();
		JRadioButtonMenuItem green = new JRadioButtonMenuItem("Green");
		JRadioButtonMenuItem red = new JRadioButtonMenuItem("Red");
		JRadioButtonMenuItem black = new JRadioButtonMenuItem("Black");
		colorGroup.add(black);colorGroup.add(red);colorGroup.add(green);
		
		/*Zoom!*/
		ButtonGroup zoomGroup = new ButtonGroup();
		JRadioButtonMenuItem in = new JRadioButtonMenuItem("In");
		JRadioButtonMenuItem out = new JRadioButtonMenuItem("Out");
		JRadioButtonMenuItem norm = new JRadioButtonMenuItem("Normal");
		zoomGroup.add(in);zoomGroup.add(out);zoomGroup.add(norm);
		
		menu.add(choices);
		menu.add(speeds);
		menu.add(colors);
		menu.add(zoom);
		
		//adds the different MenuItems to the JMenu 
		choices.add(gliderGun);choices.add(pulsar);choices.add(Pentomino);
		choices.add(spider);choices.add(custom);
		speeds.add(fast); speeds.add(med); speeds.add(slow);
		colors.add(green); colors.add(red); colors.add(black);
		zoom.add(in); zoom.add(out); zoom.add(norm);
		
		
		/*
		 * Clears the cells and then prints the glider gun
		 *  pattern to the grid.
		 */
		gliderGun.addActionListener(event -> {
			cell = updateCells.clearCells(cell, MAX_ROWS, MAX_COLUMNS);
			cell = updateCells.createGliderGun(cell);
			genCount = 1;
			String genStr= Integer.toString(genCount);
			/*update JLabel*/
			generation.setText("Gen: "+genStr);
			repaint();
		});
		
		/* Clears the grid of all live cells. Then
		 * creates the formation pulsar and paints the
		 * grid with those cells.
		 */
		pulsar.addActionListener(event -> {
			cell = updateCells.clearCells(cell, MAX_ROWS, MAX_COLUMNS);
			cell = updateCells.createPulsar(cell);
			genCount = 1;
			String genStr= Integer.toString(genCount);
			/*update JLabel*/
			generation.setText("Gen: "+genStr);
			repaint();
		});
		/* Clears the grid of all live cells then
		 * creates the formation R-Pentomino and
		 * updates the grid    	
		 */
		Pentomino.addActionListener(event -> {
			cell = updateCells.clearCells(cell, MAX_ROWS, MAX_COLUMNS);
			cell = updateCells.createRPentomino(cell);
			genCount = 1;
			String genStr= Integer.toString(genCount);
			/*update JLabel*/
			generation.setText("Gen: "+genStr);
			repaint();
		});
		spider.addActionListener(event -> {
			cell = updateCells.clearCells(cell, MAX_ROWS, MAX_COLUMNS);
			cell = updateCells.createSpider(cell);
			genCount = 1;
			String genStr= Integer.toString(genCount);
			/*update JLabel*/
			generation.setText("Gen: "+genStr);
			repaint();
		});
		custom.addActionListener(event -> {
			cell = updateCells.clearCells(cell, MAX_ROWS, MAX_COLUMNS);
			cell = updateCells.createCustom(cell);
			genCount = 1;
			String genStr= Integer.toString(genCount);
			/*update JLabel*/
			generation.setText("Gen: "+genStr);
			repaint();
		});
		
		/*CHANGES THE SPEED OF THE THREAD!!!*/
		med.addActionListener(event -> speed = 120);
		fast.addActionListener(event -> speed = 70);
		slow.addActionListener(event -> speed = 180);

		/*CHANGES THE COLOR OF THE CELLS!!!*/
		red.addActionListener(event ->{
			for (int i = 0; i < MAX_ROWS; i++) {
				for (int j = 0; j < MAX_COLUMNS; j++) {
					theGrid[i][j].setColor(rd);
				}
			}
				repaint();
		});
		green.addActionListener(event ->{
			for (int i = 0; i < MAX_ROWS; i++) {
				for (int j = 0; j < MAX_COLUMNS; j++) {
					theGrid[i][j].setColor(grn);
				}
			}
			repaint();
		});
		black.addActionListener(event ->{
			for (int i = 0; i < MAX_ROWS; i++) {
				for (int j = 0; j < MAX_COLUMNS; j++) {
					theGrid[i][j].setColor(blck);
				}
			}
			repaint();
		});
		
		in.addActionListener(event ->{
			int newBorderNums = 13;
			int heightwidth = 15;
			for (int i = 0; i < MAX_ROWS; i++) {
				for (int j = 0; j < MAX_COLUMNS; j++) {
					theGrid[i][j] = new GridSquare(i * 15, j * 15, i, j, theGrid[i][j].getColor());
					theGrid[i][j].setInnerHeight(newBorderNums);
					theGrid[i][j].setInnerWidth(newBorderNums);
					theGrid[i][j].setSquareHeight(heightwidth);
					theGrid[i][j].setSqaureWidth(heightwidth);
				}
			}
			repaint();
		
		});
		out.addActionListener(event ->{
			int newBorderNums = 4;
			int heightwidth = 5;
			for (int i = 0; i < MAX_ROWS; i++) {
				for (int j = 0; j < MAX_COLUMNS; j++) {
					theGrid[i][j] = new GridSquare(i * 5, j * 5, i, j, theGrid[i][j].getColor());
					theGrid[i][j].setInnerHeight(newBorderNums);
					theGrid[i][j].setInnerWidth(newBorderNums);
					theGrid[i][j].setSquareHeight(heightwidth);
					theGrid[i][j].setSqaureWidth(heightwidth);
				}
			}
			repaint();
		});
		norm.addActionListener(event ->{
			int newBorderNums = 8;
			int heightwidth = 10;
			for (int i = 0; i < MAX_ROWS; i++) {
				for (int j = 0; j < MAX_COLUMNS; j++) {
					theGrid[i][j] = new GridSquare(i * 10, j * 10, i, j, theGrid[i][j].getColor());
					theGrid[i][j].setInnerHeight(newBorderNums);
					theGrid[i][j].setInnerWidth(newBorderNums);
					theGrid[i][j].setSquareHeight(heightwidth);
					theGrid[i][j].setSqaureWidth(heightwidth);
				}
			}
			repaint();
		});
	}

	/**
	 * Simple paint component
	 * Paints the grid with cell updates
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		for (int i = 0; i < MAX_ROWS; i++) {
			for (int j = 0; j < MAX_COLUMNS; j++) {
				theGrid[i][j].drawSquare(g);// draw grid in graphic panel
				if (cell[i][j].isAlive()) { // paint if cell is alive
					theGrid[i][j].findChosenColor(g);
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
		    running = true;
		    // Begin updating the grid
		    // A new Thread is created...
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
			
			/* Will cause the thread to exit
			 * its loop ending the infinite
			 * iteration of the cells
			 */
				running = false;
		}
		if(e.getSource() == button[3]){
			
			// Make all cells dead on the grid
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
