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
	
	public int getMax(){
		return MAX;
	}
	
	public int getMin(){
		return MIN;
	}
}
