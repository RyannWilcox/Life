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
	
	public Cell[][] createSpider(Cell c[][]){
		c[10][12].makeAlive();c[11][13].makeAlive();c[11][14].makeAlive();
		c[10][14].makeAlive();c[9][14].makeAlive();c[22][66].makeAlive();
		c[23][67].makeAlive();c[23][68].makeAlive();c[22][68].makeAlive();
		c[21][68].makeAlive();c[87][7].makeAlive();c[88][8].makeAlive();
		c[69][35].makeAlive();c[89][6].makeAlive();c[89][7].makeAlive();
		c[69][36].makeAlive();c[68][35].makeAlive();c[67][35].makeAlive();
		c[66][34].makeAlive();c[65][34].makeAlive();c[65][35].makeAlive();
		c[65][36].makeAlive();c[65][37].makeAlive();c[64][37].makeAlive();
		c[63][36].makeAlive();c[68][38].makeAlive();c[67][38].makeAlive();
		c[67][39].makeAlive();c[68][39].makeAlive();c[65][39].makeAlive();
		c[64][39].makeAlive();c[64][40].makeAlive();c[63][35].makeAlive();
		c[63][34].makeAlive();c[62][35].makeAlive();c[61][35].makeAlive();
		c[61][34].makeAlive();c[60][33].makeAlive();c[59][34].makeAlive();
		c[58][34].makeAlive();c[57][36].makeAlive();c[57][37].makeAlive();
		c[57][38].makeAlive();c[55][36].makeAlive();c[55][37].makeAlive();
		c[55][38].makeAlive();c[54][34].makeAlive();c[53][34].makeAlive();
		c[52][33].makeAlive();c[51][34].makeAlive();c[51][35].makeAlive();
		c[50][35].makeAlive();c[49][35].makeAlive();c[49][34].makeAlive();
		c[49][36].makeAlive();c[48][37].makeAlive();c[47][37].makeAlive();
		c[47][36].makeAlive();c[47][35].makeAlive();c[47][34].makeAlive();
		c[45][35].makeAlive();c[44][35].makeAlive();c[43][35].makeAlive();
		c[43][36].makeAlive();c[44][39].makeAlive();c[44][38].makeAlive();
		c[45][38].makeAlive();c[45][39].makeAlive();c[47][39].makeAlive();
		c[48][39].makeAlive();c[48][40].makeAlive();c[46][34].makeAlive();
		c[37][1].makeAlive();c[38][2].makeAlive();c[38][3].makeAlive();
		c[37][3].makeAlive();c[36][3].makeAlive();
		
		return c;
	}
	public Cell[][] createCustom(Cell c[][]){
		c[52][25].makeAlive();c[54][25].makeAlive();c[53][25].makeAlive();
		c[55][25].makeAlive();c[56][25].makeAlive();c[57][25].makeAlive();
		c[58][25].makeAlive();c[55][24].makeAlive();c[55][23].makeAlive();
		c[55][22].makeAlive();c[55][21].makeAlive();c[55][27].makeAlive();
		c[55][26].makeAlive();c[55][28].makeAlive();c[55][29].makeAlive();
		c[55][30].makeAlive();c[55][31].makeAlive();c[54][31].makeAlive();
		c[54][30].makeAlive();c[53][29].makeAlive();c[56][31].makeAlive();
		c[56][30].makeAlive();c[57][29].makeAlive();




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
