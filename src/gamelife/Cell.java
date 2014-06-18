package gamelife;


public class Cell implements CellState{
	private boolean state;
	
	/**
	 * A cell can have one of two states
	 * These states are alive or dead
	 */
	public Cell(){
		state = false;
	}
	
	/**
	 *  Changes the cell states to true
	 */
	public void makeAlive(){
		state = true;
	}
	
	/**
	 * Cell state is changed to false
	 */
	public void makeDead(){
		state = false;
	}
	
	/**
	 * Check to see the current state
	 * of the cell.
	 */
	public boolean isAlive(){
		return state;
	}
}
