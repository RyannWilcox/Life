package gamelife;

import java.awt.Graphics;

import gamelife.GridSquare.colors;
/**
 * Class that will take care of all
 * changes done to the Grid and      the Cells
 * @author ryanwilcox
 *
 */
public class CellsAndGrid implements RowColumnBounds {
	private Cell cells[][] = new Cell[100][100];
	private GridSquare grid[][] = new GridSquare[100][100];
	
	//zoom variations..
	private boolean zoomed = false;
	private boolean normZoom = true;
	//For checking the bounds of the grid
	private final int MAX = 100;
	private final int MIN = 0;
	
	/**
	 * Constructor will populate
	 * the grid and the cell arrays
	 */
	public CellsAndGrid(){
		for (int i = 0; i < MAX_ROWS; i++) {
			for (int j = 0; j < MAX_COLUMNS; j++) {
				grid[i][j] = new GridSquare(i * 10, j * 10, i, j, colors.GREEN);
				cells[i][j] = new Cell();
			}
		}
	}
	
	/**
	 * When we need to update our grid and
	 * cells this checks to see what is 
	 * now alive or dead
	 * @param g
	 */
	public void paintAll(Graphics g){
		for (int i = 0; i < MAX_ROWS; i++) {
			for (int j = 0; j < MAX_COLUMNS; j++) {
				grid[i][j].drawSquare(g);// draw grid in graphic panel
				if (cells[i][j].isAlive()) { // paint if cell is alive
					grid[i][j].findChosenColor(g);
				}
			}
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
	public void updateCells(int rows,int cols){
		int nbrCount = 0;
		//This will become the updated Generation of cells
		Cell nextGen[][] = new Cell[rows][cols];
		
		/* 
		 * Populates the next generation with
		 * new empty cells
		 */
		for(int i = 0; i < rows;i++){ 
			for(int j = 0; j < cols;j++){
				nextGen[i][j] = new Cell();
			}
		}
		
		for(int i = 0; i < rows;i++){
			for(int j = 0; j < cols;j++){
				nbrCount = countNeighbors(i ,j,getMax(),getMin());
				// If the current cell is dead and it has three neighbors
				// it is now alive
				if((!cells[i][j].isAlive()) && nbrCount == 3){
					nextGen[i][j].makeAlive();
				}
					
				//2 or 3 neighbors.  the cell lives
				if(cells[i][j].isAlive() &&( nbrCount == 2 || nbrCount == 3)){
					nextGen[i][j].makeAlive();
				}
					
				// less than 2 or greater than 3. the cell dies!
				if(cells[i][j].isAlive() && (nbrCount < 2 || nbrCount > 3)){
					nextGen[i][j].makeDead();
				}
			}
		}
		cells = nextGen;
	}
	
	
	/**
	* Will Check the 8 possible neighbors
	* @param c the cell
	* @param i X location in the grid
	* @param j Y location in the grid
	* @return the count of the neighbors
	*/
	public int countNeighbors(int i,int j,int max,int min){
		int count = 0;
		//Bounds checks..
		if(i + 1 < max && cells[i + 1][j].isAlive()){
			count++;
		}
		if(i - 1 >= min && cells[i - 1][j].isAlive()){
			count++;
		}
		if(j  + 1 < max && cells[i][j + 1].isAlive()){
			count++;
		}
		if(j - 1 >= min && cells[i][j - 1].isAlive()){
			count++;
		}
		if((i + 1 < max && j + 1 < max) && cells[i + 1][j + 1].isAlive()){
			count++;
		}
		if((i - 1 >= min && j - 1 >= min) && cells[i - 1][j - 1].isAlive()){
			count++;
		}
		if((i + 1 < max && j - 1 >= min) && cells[i + 1][j - 1].isAlive()){
			count++;
		}
		if((i - 1 >= min && j + 1 < max) && cells[i - 1][j + 1].isAlive()){
			count++;
		}
		
		return count;
	}

	/**
	 * This will create a new set of cells.
	 * A cleared group of cells means they are
	 * all set as "dead"
	 * @param c
	 * @param rows
	 * @param cols
	 * @return a cleared group of cells
	 */
	public void clearCells(int rows, int cols){
		for(int i = 0; i < rows;i++){
			for(int j = 0; j < cols;j++){
				cells[i][j] = new Cell();
			}
		}
		
	}
	
	/**
	 * Changes color of all the grid squares
	 * @param clr the color choice
	 */
	public void changeColors(colors clr){
		for (int i = 0; i < MAX_ROWS; i++) {
			for (int j = 0; j < MAX_COLUMNS; j++) {
				grid[i][j].setColor(clr);
			}
		}
	}
	
	
	/**
	 * Makes the grid squares either larger
	 * or smaller.  This zooms them in or out.
	 * You can also return the size of the squares
	 * to the normal size.  
	 * @param choice between 1 and 3
	 */
	public void zoomGrid(int choice){
		int newBorderNums = 8;
		int heightwidth = 10;
		int newValue = 10;
		
		if(choice == 1){
			setZoomed(true);
			setNormZoom(false);
			newBorderNums = 13;
			heightwidth = 15;
			newValue = 15;
		}
		if(choice == 2){
			setZoomed(false);
			setNormZoom(false);
			newBorderNums = 4;
			heightwidth = 5;
			newValue = 5;
		}
		if(choice == 3){
			setZoomed(false);
			setNormZoom(true);
			newBorderNums = 8;
			heightwidth= 10;
			newValue= 10;
		}
		
		for (int i = 0; i < MAX_ROWS; i++) {
			for (int j = 0; j < MAX_COLUMNS; j++) {
				grid[i][j] = new GridSquare(i * newValue, j * newValue, i, j, grid[i][j].getColor());
				grid[i][j].setInnerHeight(newBorderNums);
				grid[i][j].setInnerWidth(newBorderNums);
				grid[i][j].setSquareHeight(heightwidth);
				grid[i][j].setSqaureWidth(heightwidth);
			}
		}
	}
	
	/**
	 * Makes a cell alive depending
	 * on the location of in the 
	 * 2d array
	 * @param i row
	 * @param j column
	 */
	public void setCellAlive(int i , int j){
		cells[i][j].makeAlive();
	}
	public void setCellDead(int i, int j){
		cells[i][j].makeDead();
	}
	
	/**
	 * Creates a famous formation in the grid
	 * @param c
	 * @return set up for R-Pentomino
	 */
	public void createRPentomino(){
		cells[50][50].makeAlive();
		cells[50][51].makeAlive();
		cells[50][52].makeAlive();
		
		cells[49][51].makeAlive();
		cells[51][50].makeAlive();
	}
	
	/**
	 * The most common period 3 oscillator
	 * 
	 * @param c
	 * @return set up for pulsar
	 */
	public void createPulsar(){
		cells[56][10].makeAlive();cells[56][11].makeAlive();
		cells[56][12].makeAlive();cells[56][13].makeAlive();
		cells[56][14].makeAlive();cells[60][10].makeAlive();
		cells[60][12].makeAlive();cells[60][11].makeAlive();
		cells[60][13].makeAlive();cells[60][14].makeAlive();
		cells[58][10].makeAlive();cells[58][14].makeAlive();
	}
	
	/**
	 * An interesting infinite pattern
	 * @param c
	 * @return setup for glider gun
	 */
	public void createGliderGun(){
		cells[29][29].makeAlive();cells[29][30].makeAlive();
		cells[30][30].makeAlive();cells[30][29].makeAlive();
		cells[39][29].makeAlive();cells[39][30].makeAlive();
		cells[39][31].makeAlive();cells[40][28].makeAlive();
		cells[40][32].makeAlive();cells[41][33].makeAlive();
		cells[42][33].makeAlive();cells[41][27].makeAlive();
		cells[42][27].makeAlive();cells[44][32].makeAlive();
		cells[45][31].makeAlive();cells[45][30].makeAlive();
		cells[45][29].makeAlive();cells[46][30].makeAlive();
		cells[44][28].makeAlive();cells[43][30].makeAlive();
		cells[49][29].makeAlive();cells[49][28].makeAlive();
		cells[49][27].makeAlive();cells[50][29].makeAlive();
		cells[50][28].makeAlive();cells[50][27].makeAlive();
		cells[51][26].makeAlive();cells[51][30].makeAlive();
		cells[53][30].makeAlive();cells[53][31].makeAlive();
		cells[53][26].makeAlive();cells[53][25].makeAlive();
		cells[63][27].makeAlive();cells[63][28].makeAlive();
		cells[63][27].makeAlive();cells[64][28].makeAlive();
		cells[64][27].makeAlive();
	}
	
	public void createSpider(){
		cells[10][12].makeAlive();cells[11][13].makeAlive();cells[11][14].makeAlive();
		cells[10][14].makeAlive();cells[9][14].makeAlive();cells[22][66].makeAlive();
		cells[23][67].makeAlive();cells[23][68].makeAlive();cells[22][68].makeAlive();
		cells[21][68].makeAlive();cells[87][7].makeAlive();cells[88][8].makeAlive();
		cells[69][35].makeAlive();cells[89][6].makeAlive();cells[89][7].makeAlive();
		cells[69][36].makeAlive();cells[68][35].makeAlive();cells[67][35].makeAlive();
		cells[66][34].makeAlive();cells[65][34].makeAlive();cells[65][35].makeAlive();
		cells[65][36].makeAlive();cells[65][37].makeAlive();cells[64][37].makeAlive();
		cells[63][36].makeAlive();cells[68][38].makeAlive();cells[67][38].makeAlive();
		cells[67][39].makeAlive();cells[68][39].makeAlive();cells[65][39].makeAlive();
		cells[64][39].makeAlive();cells[64][40].makeAlive();cells[63][35].makeAlive();
		cells[63][34].makeAlive();cells[62][35].makeAlive();cells[61][35].makeAlive();
		cells[61][34].makeAlive();cells[60][33].makeAlive();cells[59][34].makeAlive();
		cells[58][34].makeAlive();cells[57][36].makeAlive();cells[57][37].makeAlive();
		cells[57][38].makeAlive();cells[55][36].makeAlive();cells[55][37].makeAlive();
		cells[55][38].makeAlive();cells[54][34].makeAlive();cells[53][34].makeAlive();
		cells[52][33].makeAlive();cells[51][34].makeAlive();cells[51][35].makeAlive();
		cells[50][35].makeAlive();cells[49][35].makeAlive();cells[49][34].makeAlive();
		cells[49][36].makeAlive();cells[48][37].makeAlive();cells[47][37].makeAlive();
		cells[47][36].makeAlive();cells[47][35].makeAlive();cells[47][34].makeAlive();
		cells[45][35].makeAlive();cells[44][35].makeAlive();cells[43][35].makeAlive();
		cells[43][36].makeAlive();cells[44][39].makeAlive();cells[44][38].makeAlive();
		cells[45][38].makeAlive();cells[45][39].makeAlive();cells[47][39].makeAlive();
		cells[48][39].makeAlive();cells[48][40].makeAlive();cells[46][34].makeAlive();
		cells[37][1].makeAlive();cells[38][2].makeAlive();cells[38][3].makeAlive();
		cells[37][3].makeAlive();cells[36][3].makeAlive();
	}
	public void createCustom(){
		cells[52][25].makeAlive();cells[54][25].makeAlive();cells[53][25].makeAlive();
		cells[55][25].makeAlive();cells[56][25].makeAlive();cells[57][25].makeAlive();
		cells[58][25].makeAlive();cells[55][24].makeAlive();cells[55][23].makeAlive();
		cells[55][22].makeAlive();cells[55][21].makeAlive();cells[55][27].makeAlive();
		cells[55][26].makeAlive();cells[55][28].makeAlive();cells[55][29].makeAlive();
		cells[55][30].makeAlive();cells[55][31].makeAlive();cells[54][31].makeAlive();
		cells[54][30].makeAlive();cells[53][29].makeAlive();cells[56][31].makeAlive();
		cells[56][30].makeAlive();cells[57][29].makeAlive();
	}

	public int getMax(){
		return MAX;
	}
	
	public int getMin(){
		return MIN;
	}
	
	
	
	
	public boolean getZoom(){
		return zoomed;
	}
	public void setZoomed(boolean z){
		zoomed = z;
	}
	
	public boolean getNormZoom(){
		return normZoom;
	}
	
	public void setNormZoom(boolean z){
		normZoom = z;
	}
	
}
