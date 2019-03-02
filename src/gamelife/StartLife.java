package gamelife;

public class StartLife {

	/* We launch the entire application from here...*/
	public static void main(String[] args) {
		System.out.println("Starting Conway's Game of Life");

		CellWorld gameOfLifeBoard = new CellWorld("Life", 1080, 800);
	}
}
