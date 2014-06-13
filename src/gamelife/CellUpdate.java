package gamelife;

public class CellUpdate {
	//For checking the bounds of the grid
	private final int MAX = 100;
	private final int MIN = 0;
	
	
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
	public Cell[][] updateSquares(Cell cells[][],int rows,int cols){
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
				nbrCount = countNeighbors(cells,i ,j,getMax(),getMin());
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

		return cells;
	}
	
	
	/**
	* Will Check the 8 possible neighbors
	* @param c the cell
	* @param i X location in the grid
	* @param j Y location in the grid
	* @return the count of the neighbors
	*/
	public int countNeighbors(Cell c[][],int i,int j,int max,int min){
		int count = 0;
		//Bounds checks..
		if(i + 1 < max && c[i + 1][j].isAlive()){
			count++;
		}
		if(i - 1 >= min && c[i - 1][j].isAlive()){
			count++;
		}
		if(j  + 1 < max && c[i][j + 1].isAlive()){
			count++;
		}
		if(j - 1 >= min && c[i][j - 1].isAlive()){
			count++;
		}
		if((i + 1 < max && j + 1 < max) && c[i + 1][j + 1].isAlive()){
			count++;
		}
		if((i - 1 >= min && j - 1 >= min) && c[i - 1][j - 1].isAlive()){
			count++;
		}
		if((i + 1 < max && j - 1 >= min) && c[i + 1][j - 1].isAlive()){
			count++;
		}
		if((i - 1 >= min && j + 1 < max) && c[i - 1][j + 1].isAlive()){
			count++;
		}
		
		return count;
	}
	/**
	 * Creates a famous formation in the grid
	 * @param c
	 * @return set up for R-Pentomino
	 */
	public Cell[][] createRPentomino(Cell c[][]){
		c[50][50].makeAlive();
		c[50][51].makeAlive();
		c[50][52].makeAlive();
		
		c[49][51].makeAlive();
		c[51][50].makeAlive();
		return c;
	}
	
	/**
	 * The most common period 3 oscillator
	 * 
	 * @param c
	 * @return set up for pulsar
	 */
	public Cell[][] createPulsar(Cell c[][]){
		c[56][10].makeAlive();c[56][11].makeAlive();
		c[56][12].makeAlive();c[56][13].makeAlive();
		c[56][14].makeAlive();c[60][10].makeAlive();
		c[60][12].makeAlive();c[60][11].makeAlive();
		c[60][13].makeAlive();c[60][14].makeAlive();
		c[58][10].makeAlive();c[58][14].makeAlive();
		return c;
	}
	
	/**
	 * An interesting infinite pattern
	 * @param c
	 * @return setup for glider gun
	 */
	public Cell[][] createGliderGun(Cell c[][]){
		c[29][29].makeAlive();c[29][30].makeAlive();
		c[30][30].makeAlive();c[30][29].makeAlive();
		c[39][29].makeAlive();c[39][30].makeAlive();
		c[39][31].makeAlive();c[40][28].makeAlive();
		c[40][32].makeAlive();c[41][33].makeAlive();
		c[42][33].makeAlive();c[41][27].makeAlive();
		c[42][27].makeAlive();c[44][32].makeAlive();
		c[45][31].makeAlive();c[45][30].makeAlive();
		c[45][29].makeAlive();c[46][30].makeAlive();
		c[44][28].makeAlive();c[43][30].makeAlive();
		c[49][29].makeAlive();c[49][28].makeAlive();
		c[49][27].makeAlive();c[50][29].makeAlive();
		c[50][28].makeAlive();c[50][27].makeAlive();
		c[51][26].makeAlive();c[51][30].makeAlive();
		c[53][30].makeAlive();c[53][31].makeAlive();
		c[53][26].makeAlive();c[53][25].makeAlive();
		c[63][27].makeAlive();c[63][28].makeAlive();
		c[63][27].makeAlive();c[64][28].makeAlive();
		c[64][27].makeAlive();
		return c;
	}
	
	public Cell[][] clearCells(Cell c[][],int rows, int cols){
		for(int i = 0; i < rows;i++){
			for(int j = 0; j < cols;j++){
				c[i][j] = new Cell();
			}
		}
		return c;
	}
	
	public int getMax(){
		return MAX;
	}
	
	public int getMin(){
		return MIN;
	}
}
