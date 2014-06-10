package gamelife;

/**
 * This interface has some functions
 * regarding the state of a cell
 * @author ryanwilcox
 *
 */
public interface CellState {
	
	/*
	 * Creates an alive state
	 */
	public void makeAlive();
	
	/*
	 * Creates a dead state
	 */
	public void makeDead();
	
	/*
	 * Checks the current state of the cell
	 */
	public boolean isAlive();
}
