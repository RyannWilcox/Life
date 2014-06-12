package gamelife;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Creates the squares on the grid
 * @author ryanwilcox
 */
public class GridSquare {

	private final int SQUARE_HEIGHT = 10;
	private final int SQUARE_WIDTH = 10;
	private int rowNumber;
	private int columnNumber;
	private int x;
	private int y;
	
	/**
	 * constructor for a square on the grid
	 * @param tempx
	 * @param tempy
	 * @param rowNb
	 * @param colmNb
	 */
	public GridSquare(int tempx, int tempy, int rowNb,int colmNb){
		rowNumber = rowNb;
		columnNumber = colmNb;
		x = tempx;
		y = tempy;

	}
	/**
	 * draws the square for the grid
	 * @param g
	 */
	public void drawSquare(Graphics g){
		g.setColor(Color.GRAY);
		g.drawRect(x,y,SQUARE_WIDTH,SQUARE_HEIGHT);
	}

	/**
	 * draws the starting point on the grid
	 * @param g the graphics
	 */
	public void drawGreen(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(x,y, SQUARE_WIDTH, SQUARE_HEIGHT);
		
		// Gives it a black border!
		g.setColor(Color.BLACK);
		g.drawRect(x, y, 10 - 2, 10 - 2);
	} 
	
	/**
	 * gets the row number
	 * @return the row number of the square
	 */
	public int rowNumber() {
		return rowNumber;
	}
	
	/**
	 *  gets the column number 
	 * @return the column number of the square
	 */
	public int columnNumber(){
		return columnNumber;
	}
}
