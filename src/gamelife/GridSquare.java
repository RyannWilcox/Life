package gamelife;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Creates the squares on the grid
 * @author ryanwilcox
 */
public class GridSquare {

	private  int squareHeight = 10;
	private  int squareWidth = 10;
	private int rowNumber = 0;
	private int columnNumber = 0;
	private int x = 0;
	private int y = 0;
	private int innerWidth = 8;
	private int innerHeight = 8;
	
	public enum colors{GREEN,RED,BLACK};
	private colors clr;

	
	/**
	 * constructor for a square on the grid
	 * @param tempx
	 * @param tempy
	 * @param rowNb
	 * @param colmNb
	 */
	public GridSquare(int tempx, int tempy, int rowNb,int colmNb,colors c){
		clr = c;
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
		g.drawRect(x,y,squareWidth,squareHeight);
	}

	/**
	 * draws a green cell that is considered "alive"
	 * @param g the graphics
	 */
	public void drawGreen(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(x,y, squareWidth, squareHeight);
		
		// Gives it a black border!
		g.setColor(Color.BLACK);
		g.drawRect(x, y, innerWidth, innerHeight);
	} 
	
	/**
	 * draws a red cell that is considered "alive"
	 * @param g the graphics
	 */
	public void drawRed(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(x,y, squareWidth, squareHeight);
		
		// Gives it a black border!
		g.setColor(Color.WHITE);
		g.drawRect(x, y, innerWidth, innerHeight);
	}
	
	/**
	 * draws a black cell that is considered "alive"
	 * @param g the graphics
	 */
	public void drawBlack(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(x,y, squareWidth, squareHeight);
		
		// Gives it a gray border!
		g.setColor(Color.GRAY);
		g.drawRect(x, y, innerWidth, innerHeight);
	} 

	/**
	 * Finds what color is set for
	 * the live cell to be painted
	 * @param g
	 */
	public void findChosenColor(Graphics g){
		switch(getColor()){
		case GREEN:
			drawGreen(g);
			break;
		case RED:
			drawRed(g);
			break;
		case BLACK:
			drawBlack(g);
			break;
		}
	}
	/*
	 * Getters and setters to set the border
	 * around the alive cells
	 */
	public int getInnerWidth(){
		return innerWidth;
	}
	
	public void setInnerWidth(int w){
		innerWidth = w;
	}
	
	public int getInnerHeight(){
		return innerHeight;
	}
	
	public void setInnerHeight(int h){
		innerHeight = h;
	}
	/*Getters and setters to set the border for cells*/
	
	
	/**
	 *  Find what color the square is going
	 *  to be painted
	 */
	public colors getColor(){
		return clr;
	}
	
	/**
	 * Set what color the live
	 * square will be painted
	 * @param color
	 */
	public void setColor(colors color){
		clr = color;
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
	
	public int getSquareHeight(){
		return squareHeight;
	}
	
	public void setSquareHeight(int h){
		squareHeight = h;
	}
	
	public int getSquareWidth(){
		return squareWidth;
	}
	
	public void setSqaureWidth(int w){
		squareWidth = w;
	}
}
