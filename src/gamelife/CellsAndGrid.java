package gamelife;

import java.awt.Graphics;

import gamelife.GridSquare.colors;

/**
 * Class that will take care of all changes done to the Grid and the Cells
 * 
 * @author RyanWilcox
 *
 */
public class CellsAndGrid implements RowColumnBounds, MaxMinBounds {
	private Cell cells[][] = new Cell[MAX_ROWS][MAX_COLUMNS];
	private GridSquare grid[][] = new GridSquare[MAX_ROWS][MAX_COLUMNS];

	public enum zooms {
		IN, OUT, NORMAL
	};

	private zooms choice;
	private boolean useHighLife = false;
	private boolean useSeed = false;

	/**
	 * Constructor will populate the grid and the cell arrays
	 */
	public CellsAndGrid() {
		choice = zooms.NORMAL;
		for (int i = 0; i < MAX_ROWS; i++) {
			for (int j = 0; j < MAX_COLUMNS; j++) {
				grid[i][j] = new GridSquare(i * 10, j * 10, i, j, colors.GREEN);
				cells[i][j] = new Cell();
			}
		}
	}

	/**
	 * When we need to update our grid and cells this checks to see what is now
	 * alive or dead
	 * 
	 * @param g
	 */
	public void paintAll(Graphics g) {
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
	 * This function will complete one step through. This will check for the 4
	 * rules the squares must follow 1. If the cell has < 2 alive neighbors it
	 * dies 2. If the cell has 2 or 3 live neighbors it lives 3. If the cell has
	 * 3 > live neighbors it will die 4. If any dead cell has exactly 3 live
	 * neighbors it will come alive EXTRA RULE: if cell is dead and has exactly
	 * 6 neighbors it will become alive
	 */
	public void updateCells(int rows, int cols) {
		int nbrCount = 0;
		// This will become the updated Generation of cells
		Cell nextGen[][] = new Cell[rows][cols];

		/*
		 * Populates the next generation with new empty(dead) cells
		 */
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				nextGen[i][j] = new Cell();
			}
		}
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				nbrCount = countNeighbors(i, j, MAX_BOUND, MIN_BOUND);
				// If the current cell is dead and it has three neighbors
				// it is now alive
				if ((!cells[i][j].isAlive()) && nbrCount == 3) {
					nextGen[i][j].makeAlive();
				}
				// 2 or 3 neighbors. the cell lives
				if (cells[i][j].isAlive() && (nbrCount == 2 || nbrCount == 3)) {
					nextGen[i][j].makeAlive();
				}
				// less than 2 or greater than 3. the cell dies!
				if (cells[i][j].isAlive() && (nbrCount < 2 || nbrCount > 3)) {
					nextGen[i][j].makeDead();
				}
				/*
				 * Adds an extra rule to the game If cell is dead and has
				 * exactly 6 neighbors the dead cell will come alive Its called
				 * 'High Life'
				 */
				if (useHighLife) {
					if (!cells[i][j].isAlive() && nbrCount == 6) {
						nextGen[i][j].makeAlive();
					}
				}
			}
		}
		cells = nextGen;
	}

	/**
	 * Uses a completely different rule set for the game of life 1. If a square
	 * was on, it will turn off 2. If a square was off, it will turn on only if
	 * exactly 2 of its neighbors were on
	 * 
	 * @param rows
	 * @param cols
	 */
	public void seedUpdateCells(int rows, int cols) {
		int nbrCount = 0;
		// This will become the updated Generation seed of cells
		Cell nextGen[][] = new Cell[rows][cols];

		/*
		 * Populates the next generation with new empty(dead) cells
		 */
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				nextGen[i][j] = new Cell();
			}
		}
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				nbrCount = countNeighbors(i, j, MAX_BOUND, MIN_BOUND);

				if (!cells[i][j].isAlive() && nbrCount == 2) {
					nextGen[i][j].makeAlive();
				}

				if (cells[i][j].isAlive()) {
					nextGen[i][j].makeDead();
				}
			}
		}
		cells = nextGen;
	}

	/**
	 * Will Check the 8 possible neighbors
	 * 
	 * @param c
	 *            the cell
	 * @param i
	 *            X location in the grid
	 * @param j
	 *            Y location in the grid
	 * @return the count of the neighbors
	 */
	public int countNeighbors(int i, int j, int max, int min) {
		int count = 0;
		// Bounds checks..
		if (i + 1 < max && cells[i + 1][j].isAlive()) {
			count++;
		}
		if (i - 1 >= min && cells[i - 1][j].isAlive()) {
			count++;
		}
		if (j + 1 < max && cells[i][j + 1].isAlive()) {
			count++;
		}
		if (j - 1 >= min && cells[i][j - 1].isAlive()) {
			count++;
		}
		if ((i + 1 < max && j + 1 < max) && cells[i + 1][j + 1].isAlive()) {
			count++;
		}
		if ((i - 1 >= min && j - 1 >= min) && cells[i - 1][j - 1].isAlive()) {
			count++;
		}
		if ((i + 1 < max && j - 1 >= min) && cells[i + 1][j - 1].isAlive()) {
			count++;
		}
		if ((i - 1 >= min && j + 1 < max) && cells[i - 1][j + 1].isAlive()) {
			count++;
		}

		return count;
	}

	/**
	 * This will create a new set of cells. A cleared group of cells means they
	 * are all set as "dead"
	 * 
	 * @param rows
	 * @param cols
	 * @return a cleared group of cells
	 */
	public void clearCells(int rows, int cols) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				cells[i][j] = new Cell();
			}
		}

	}

	/**
	 * Changes color of all the grid squares
	 * 
	 * @param clr
	 *            the color choice
	 */
	public void changeColors(colors clr) {
		for (int i = 0; i < MAX_ROWS; i++) {
			for (int j = 0; j < MAX_COLUMNS; j++) {
				grid[i][j].setColor(clr);
			}
		}
	}

	/**
	 * Makes the grid squares either larger or smaller. This zooms them in or
	 * out. You can also return the size of the squares to the normal size.
	 * 
	 * @param choice
	 *            between 1 and 3
	 */
	public void zoomGrid(zooms z) {
		int newBorderNums = 8;
		int heightwidth = 10;
		int newValue = 10;
		switch (z) {
		case IN:
			setChoice(z);
			newBorderNums = 13;
			heightwidth = 15;
			newValue = 15;
			break;
		case NORMAL:
			setChoice(z);
			break;
		case OUT:
			setChoice(z);
			newBorderNums = 4;
			heightwidth = 5;
			newValue = 5;
			break;
		}
		for (int i = 0; i < MAX_ROWS; i++) {
			for (int j = 0; j < MAX_COLUMNS; j++) {
				grid[i][j] = new GridSquare(i * newValue, j * newValue, i, j, grid[i][j].getColor());
				grid[i][j].setInnerHeight(newBorderNums);
				grid[i][j].setInnerWidth(newBorderNums);
				grid[i][j].setSquareHeight(heightwidth);
				grid[i][j].setSquareWidth(heightwidth);
			}
		}
	}

	/**
	 * Makes a cell alive based on the location of in the 2d array
	 * 
	 * @param i
	 *            row
	 * @param j
	 *            column
	 */
	public void setCellAlive(int i, int j) {
		cells[i][j].makeAlive();
	}

	public void setCellDead(int i, int j) {
		cells[i][j].makeDead();
	}
	
	/**
	 * Will set the current cells to a new formation
	 * @param newCellFormation
	 */
	public void setNewCellFormation(Cell newCellFormation [][]){
		cells = newCellFormation;
	}

	/**
	 * The choice of zoom
	 * 
	 * @return either in,out or normal
	 */
	public zooms getChoice() {
		return choice;
	}

	/**
	 * Set the choice of zoom
	 * 
	 * @param c
	 *            the choice
	 */
	public void setChoice(zooms c) {
		choice = c;
	}

	/**
	 * Adds a rule to the game of life
	 * 
	 * @param hl
	 *            either true or false
	 */
	public void setHighLifeRule(boolean hl) {
		useSeed = false;
		useHighLife = hl;
	}

	/**
	 * To decide whether to use the seed rule set for the game of life
	 * 
	 * @param sr
	 */
	public void setSeedRule(boolean sr) {
		useHighLife = false;
		useSeed = sr;
	}

	/*
	 * Get status of the rule seed rule set
	 */
	public boolean getSeedRuleStatus() {
		return useSeed;
	}

	/*
	 * Get the status of the high life rule set
	 */
	public boolean getHighLifeStatus() {
		return useHighLife;
	}
}