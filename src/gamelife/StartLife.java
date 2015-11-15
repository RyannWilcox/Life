package gamelife;

public class StartLife {

	public static CellWorld gameOfLifeBoard;

	/* We launch the entire application from here...*/
	public static void main(String[] args) {
		System.out.println("Starting Conway's Game of Life");

		gameOfLifeBoard = new CellWorld("Life", 1080, 800);
	}
}
