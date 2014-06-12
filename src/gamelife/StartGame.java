package gamelife;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartGame extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	JFrame myFrame;
	JButton[] button;
	static final String[] BUTTON_STR = {"Go","Step","Stop","Quit"};
	private final int MAX_ROWS = 100;
	private final int MAX_COLUMNS = 100;

	private GridSquare theGrid [][] = new GridSquare [MAX_ROWS] [MAX_COLUMNS];
	private Cell cell[][] = new Cell [MAX_ROWS] [MAX_COLUMNS];
	private int squareX = 0;  
	private int squareY = 0;
	private int clickCount = 0;
	private Updater updates = new Updater();
	private Thread update = new Thread(updates);
	private boolean firstStart = true;
	
	public StartGame(String title,int width,int height){
		super();
		// Populate cell array and nextGen array
		// Populate Grid array
		// They will all start off dead here..
		for(int i = 0; i < MAX_ROWS;i++){
			for(int j = 0; j < MAX_COLUMNS;j++){
				theGrid[i][j] = new GridSquare(i*10,j*10,i,j);
				cell[i][j] = new Cell();
			}
		}
		

		//this is for testing until I get the mouse clicks working
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
		
		layoutSetup(title, width, height);
		myFrame.setVisible(true); 
		button[0].setEnabled(true);
		button[1].setEnabled(true);
		button[2].setEnabled(true);
		button[3].setEnabled(true);
	}
	public void layoutSetup(String theTitle,int theWidth,int theHeight){
		myFrame = new JFrame(theTitle) {
			private static final long serialVersionUID = 1L;
			public void paint(Graphics g) {
				paintComponents(g);
			}
		};
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(theWidth, theHeight);
		myFrame.setLayout(new BorderLayout());
		myFrame.add(this, BorderLayout.CENTER);

		JPanel controlPanel = new JPanel(new GridLayout(8, 1));
		button = new JButton[BUTTON_STR.length];
		for (int i=0; i<BUTTON_STR.length; i++) {
			button[i] = new JButton(BUTTON_STR[i]);
			button[i].addActionListener(this);
			button[i].setFocusable(false);
			controlPanel.add(button[i]);
		}
		myFrame.add(controlPanel, BorderLayout.EAST);
	}
	/**
	 * Paints the grid
	 */
	public void paintComponent(Graphics g){ 
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		for (int i = 0; i < MAX_ROWS; i++) {
			for (int j = 0; j < MAX_COLUMNS; j++) {
				theGrid[i][j].drawSquare(g);//draw grid in graphic panel
			}
		}
		
		if(firstStart){
			for (int i = 0; i < MAX_ROWS; i++) {
				for (int j = 0; j < MAX_COLUMNS; j++) {
					if(cell[i][j].isAlive()){
						theGrid[i][j].drawGreen(g);
					}
				}
			}
		}
		
		if(!firstStart){
			for (int i = 0; i < MAX_ROWS; i++) {
				for (int j = 0; j < MAX_COLUMNS; j++) {
					if(cell[i][j].isAlive()){
						theGrid[i][j].drawGreen(g);
					}
				}
			}
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button[0]){
			firstStart = false;
			System.out.println("Start thread!");
			update.start();
		}
		if(e.getSource() == button[1]){
			firstStart = false;
			updateSquares();
			repaint();
			
		}
		if(e.getSource() == button[2]){
			update.interrupt();
		}
		
		if(e.getSource() == button[3]){
			System.exit(1);
		}
	}
	
	/**
	 * This function will complete one
	 * step through.  This will check for the 4 rules
	 * the squares must follow
	 * 1. If the cell has < 2 alive neighbors it dies
	 * 2. If the cell has 2 or 3 live neighbors it lives
	 * 3. If the cell has 3 > live neighbors it will die
	 * 4. If any dead cell has exactly 3 live neighbors it
	 * will become alive
	 */
	public void updateSquares(){
		int nbrCount = 0;
		//This will become the updated Generation of cells
		Cell nextGen[][] = new Cell[MAX_ROWS][MAX_COLUMNS];
		
		for(int i = 0; i < MAX_ROWS;i++){
			for(int j = 0; j < MAX_COLUMNS;j++){
				nextGen[i][j] = new Cell();
			}
		}
		
		for(int i = 0; i < MAX_ROWS;i++){
			for(int j = 0; j < MAX_COLUMNS;j++){
				nbrCount = countNeighbors(cell,i ,j);
				// If the current cell is dead and it has three neighbors
				// it is now alive
				if((!cell[i][j].isAlive()) && nbrCount == 3){
					nextGen[i][j].makeAlive();
				}
					
				//2 or 3 neighbors.  the cell lives
				if(cell[i][j].isAlive() &&( nbrCount == 2 || nbrCount == 3)){
					nextGen[i][j].makeAlive();
				}
					
				// less than 2 or greater than 3. the cell dies!
				if(cell[i][j].isAlive() && (nbrCount < 2 || nbrCount > 3)){
					nextGen[i][j].makeDead();
				}
			}
		}
		cell = nextGen;
	}
	/**
	 * Will Check the 8 possible neighbors
	 * @param c the cell
	 * @param i X location in the grid
	 * @param j Y location in the grid
	 * @return the count of the neighbors
	 */
	public int countNeighbors(Cell c[][],int i, int j){
		int count = 0;
		//Bounds checks..
		if(i + 1 < 100 && c[i + 1][j].isAlive()){
			count++;
		}
		if(i - 1 >= 0 && c[i - 1][j].isAlive()){
			count++;
		}
		if(j  + 1 < 100 && c[i][j + 1].isAlive()){
			count++;
		}
		if(j - 1 >= 0 && c[i][j - 1].isAlive()){
			count++;
		}
		if((i + 1 < 100 && j + 1 < 100) && c[i + 1][j + 1].isAlive()){
			count++;
		}
		if((i - 1 >= 0 && j - 1 >= 0) && c[i - 1][j - 1].isAlive()){
			count++;
		}
		if((i + 1 < 100 && j - 1 >= 0) && c[i + 1][j - 1].isAlive()){
			count++;
		}
		if((i - 1 >= 0 && j + 1 < 100) && c[i - 1][j + 1].isAlive()){
			count++;
		}
		
		return count;
	}
	
	public static void main(String[] args) {
		StartGame sg = new StartGame("Life",1000,800);	
	}
	
	public class Updater implements Runnable{
		@Override
		public void run() {
			while(true){
				updateSquares();
				repaint();
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
}
