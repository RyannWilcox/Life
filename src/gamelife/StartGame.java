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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class StartGame extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	JFrame myFrame;
	int lastX = -100, lastY = -100; 
	JButton[] button;
	static final String[] BUTTON_STR = {"Step", "Stop","Quit"};

	private GridSquare theGrid [][] = new GridSquare [50] [50];
	private Cell cell[][] = new Cell [50] [50];
	private int i;
	private int j;  
	private int squareX = 0;  
	private int squareY = 0;
	private int clickCount = 0;
	
	public StartGame(String title,int width,int height){
		super();
		//Populate cell array
		for(int i = 0; i < 50;i++){
			for(int j = 0; j < 50;j++){
				cell[i][j] = new Cell();
			}
		}
		
		//this is for testing until I get the mouse clicks working
		cell[20][20].makeAlive();
		cell[20][21].makeAlive();
		cell[20][22].makeAlive();
		cell[21][23].makeAlive();
		cell[22][23].makeAlive();
		cell[22][24].makeAlive();
		cell[21][19].makeAlive();
		cell[22][19].makeAlive();
		cell[22][20].makeAlive();
		
		layoutSetup(title, width, height);
		myFrame.setVisible(true); 
		button[0].setEnabled(true);
		button[1].setEnabled(true);
		button[2].setEnabled(true);
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
		
		for (i = 0; i < 50; i++) {
			for (j = 0; j < 50; j++) {
				theGrid[i][j] = new GridSquare(i*10,j*10,i,j);	// populate 2d array
				theGrid[i][j].drawSquare(g);//draw grid in graphic panel
				if(cell[i][j].isAlive()){
					theGrid[i][j].drawGreen(g);
				}
			}
		}		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == button[0]){
			System.out.println("Go through one full generation!");
			updateSquares();
			repaint();
			
		}
		if(e.getSource() == button[1]){
			System.out.println("Stop living life!");
		}
		if(e.getSource() == button[2]){
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
		for(int i = 0; i < 50;i++){
			for(int j = 0; j < 50;j++){
				nbrCount = countNeighbors(cell,i ,j);
				// If the current cell is dead and it has three neighbors
				// it is now alive
				if(cell[i][j].isAlive() == false && nbrCount == 3){
					cell[i][j].makeAlive();
				}
				// less than two neighbors. cell dies
				if(cell[i][j].isAlive() && nbrCount < 2){
					cell[i][j].makeDead();
				}
				// 2 or 3 neighbors.  the cell lives
				if(cell[i][j].isAlive() &&( nbrCount == 2 || nbrCount == 3)){
					cell[i][j].makeAlive();
				}
				// 3 or more neighbors. the cell dies!
				if(cell[i][j].isAlive() && nbrCount > 3){
					cell[i][j].makeDead();
				}
			}
			nbrCount = 0;
		}
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
		if(c[i + 1][j].isAlive()){
			count++;
		}
		if(c[i - 1][j].isAlive()){
			count++;
		}
		if(c[i][j + 1].isAlive()){
			count++;
		}
		if(c[i][j - 1].isAlive()){
			count++;
		}
		if(c[i + 1][j + 1].isAlive()){
			count++;
		}
		if(c[i - 1][j - 1].isAlive()){
			count++;
		}
		if(c[i + 1][j - 1].isAlive()){
			count++;
		}
		if(c[i - 1][j + 1].isAlive()){
			count++;
		}
		return count;
	}
	
	public static void main(String[] args) {
		StartGame sg = new StartGame("Life",700,700);	
	}
}
