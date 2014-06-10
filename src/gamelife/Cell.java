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
	
	public void makeAlive(){
		state = true;
	}
	
	public void makeDead(){
		state = false;
	}
	
	public boolean isAlive(){
		return state;
	}
}
