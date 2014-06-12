package gamelife;

public class StartLife {
	
	public static CellWorld gameOfLifeBoard;

	public static void main(String[] args) {
		System.out.println("Starting Conway's Game of Life");
		
		gameOfLifeBoard = new CellWorld("Life", 1000, 800);
	}
}
