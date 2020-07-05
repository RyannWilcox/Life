package gamelife;

public class SpecialFormation implements Bounds {
	
	private Cell cellFormation [][];
	
	public SpecialFormation(){
		
		cellFormation = new Cell[MAX_ROWS][MAX_COLUMNS];
		
		// Populate the array with empty cells for now
		for (int i = 0; i < MAX_ROWS; i++) {
			for (int j = 0; j < MAX_COLUMNS; j++) {
				cellFormation[i][j] = new Cell();
			}
		}
	}
	
	/**
	 * Creates a famous formation in the grid
	 * 
	 * @param c
	 * @return set up for R-Pentomino
	 */
	public Cell[][] createRPentomino() {
		cellFormation[50][50].makeAlive();
		cellFormation[50][51].makeAlive();
		cellFormation[50][52].makeAlive();

		cellFormation[49][51].makeAlive();
		cellFormation[51][50].makeAlive();
		
		return cellFormation;
	}
	
	
	/**
	 * The most common period 3 oscillator
	 * 
	 * @param c
	 * @return set up for pulsar
	 */
	public Cell [][] createPulsar() {
		cellFormation[56][10].makeAlive();
		cellFormation[56][11].makeAlive();
		cellFormation[56][12].makeAlive();
		cellFormation[56][13].makeAlive();
		cellFormation[56][14].makeAlive();
		cellFormation[60][10].makeAlive();
		cellFormation[60][12].makeAlive();
		cellFormation[60][11].makeAlive();
		cellFormation[60][13].makeAlive();
		cellFormation[60][14].makeAlive();
		cellFormation[58][10].makeAlive();
		cellFormation[58][14].makeAlive();
		
		return cellFormation;
	}

	/**
	 * An interesting infinite pattern
	 * 
	 * @param c
	 * @return setup for glider gun
	 */
	public Cell[][] createGliderGun() {
		cellFormation[29][29].makeAlive();
		cellFormation[29][30].makeAlive();
		cellFormation[30][30].makeAlive();
		cellFormation[30][29].makeAlive();
		cellFormation[39][29].makeAlive();
		cellFormation[39][30].makeAlive();
		cellFormation[39][31].makeAlive();
		cellFormation[40][28].makeAlive();
		cellFormation[40][32].makeAlive();
		cellFormation[41][33].makeAlive();
		cellFormation[42][33].makeAlive();
		cellFormation[41][27].makeAlive();
		cellFormation[42][27].makeAlive();
		cellFormation[44][32].makeAlive();
		cellFormation[45][31].makeAlive();
		cellFormation[45][30].makeAlive();
		cellFormation[45][29].makeAlive();
		cellFormation[46][30].makeAlive();
		cellFormation[44][28].makeAlive();
		cellFormation[43][30].makeAlive();
		cellFormation[49][29].makeAlive();
		cellFormation[49][28].makeAlive();
		cellFormation[49][27].makeAlive();
		cellFormation[50][29].makeAlive();
		cellFormation[50][28].makeAlive();
		cellFormation[50][27].makeAlive();
		cellFormation[51][26].makeAlive();
		cellFormation[51][30].makeAlive();
		cellFormation[53][30].makeAlive();
		cellFormation[53][31].makeAlive();
		cellFormation[53][26].makeAlive();
		cellFormation[53][25].makeAlive();
		cellFormation[63][27].makeAlive();
		cellFormation[63][28].makeAlive();
		cellFormation[63][27].makeAlive();
		cellFormation[64][28].makeAlive();
		cellFormation[64][27].makeAlive();
		
		return cellFormation;
	}

	
	/**
	 * Creates a spider-like formation
	 * @return cellFormation - The Alive cells required to
	 * create the spider.
	 */
	public Cell[][] createSpider() {
		cellFormation[10][12].makeAlive();
		cellFormation[11][13].makeAlive();
		cellFormation[11][14].makeAlive();
		cellFormation[10][14].makeAlive();
		cellFormation[9][14].makeAlive();
		cellFormation[22][66].makeAlive();
		cellFormation[23][67].makeAlive();
		cellFormation[23][68].makeAlive();
		cellFormation[22][68].makeAlive();
		cellFormation[21][68].makeAlive();
		cellFormation[87][7].makeAlive();
		cellFormation[88][8].makeAlive();
		cellFormation[69][35].makeAlive();
		cellFormation[89][6].makeAlive();
		cellFormation[89][7].makeAlive();
		cellFormation[69][36].makeAlive();
		cellFormation[68][35].makeAlive();
		cellFormation[67][35].makeAlive();
		cellFormation[66][34].makeAlive();
		cellFormation[65][34].makeAlive();
		cellFormation[65][35].makeAlive();
		cellFormation[65][36].makeAlive();
		cellFormation[65][37].makeAlive();
		cellFormation[64][37].makeAlive();
		cellFormation[63][36].makeAlive();
		cellFormation[68][38].makeAlive();
		cellFormation[67][38].makeAlive();
		cellFormation[67][39].makeAlive();
		cellFormation[68][39].makeAlive();
		cellFormation[65][39].makeAlive();
		cellFormation[64][39].makeAlive();
		cellFormation[64][40].makeAlive();
		cellFormation[63][35].makeAlive();
		cellFormation[63][34].makeAlive();
		cellFormation[62][35].makeAlive();
		cellFormation[61][35].makeAlive();
		cellFormation[61][34].makeAlive();
		cellFormation[60][33].makeAlive();
		cellFormation[59][34].makeAlive();
		cellFormation[58][34].makeAlive();
		cellFormation[57][36].makeAlive();
		cellFormation[57][37].makeAlive();
		cellFormation[57][38].makeAlive();
		cellFormation[55][36].makeAlive();
		cellFormation[55][37].makeAlive();
		cellFormation[55][38].makeAlive();
		cellFormation[54][34].makeAlive();
		cellFormation[53][34].makeAlive();
		cellFormation[52][33].makeAlive();
		cellFormation[51][34].makeAlive();
		cellFormation[51][35].makeAlive();
		cellFormation[50][35].makeAlive();
		cellFormation[49][35].makeAlive();
		cellFormation[49][34].makeAlive();
		cellFormation[49][36].makeAlive();
		cellFormation[48][37].makeAlive();
		cellFormation[47][37].makeAlive();
		cellFormation[47][36].makeAlive();
		cellFormation[47][35].makeAlive();
		cellFormation[47][34].makeAlive();
		cellFormation[45][35].makeAlive();
		cellFormation[44][35].makeAlive();
		cellFormation[43][35].makeAlive();
		cellFormation[43][36].makeAlive();
		cellFormation[44][39].makeAlive();
		cellFormation[44][38].makeAlive();
		cellFormation[45][38].makeAlive();
		cellFormation[45][39].makeAlive();
		cellFormation[47][39].makeAlive();
		cellFormation[48][39].makeAlive();
		cellFormation[48][40].makeAlive();
		cellFormation[46][34].makeAlive();
		cellFormation[37][1].makeAlive();
		cellFormation[38][2].makeAlive();
		cellFormation[38][3].makeAlive();
		cellFormation[37][3].makeAlive();
		cellFormation[36][3].makeAlive();
		
		return cellFormation;
	}

	/**
	 *  Just a random formation I made up
	 * @return cellFormation
	 */
	public Cell[][]  createCustom() {
		cellFormation[52][25].makeAlive();
		cellFormation[54][25].makeAlive();
		cellFormation[53][25].makeAlive();
		cellFormation[55][25].makeAlive();
		cellFormation[56][25].makeAlive();
		cellFormation[57][25].makeAlive();
		cellFormation[58][25].makeAlive();
		cellFormation[55][24].makeAlive();
		cellFormation[55][23].makeAlive();
		cellFormation[55][22].makeAlive();
		cellFormation[55][21].makeAlive();
		cellFormation[55][27].makeAlive();
		cellFormation[55][26].makeAlive();
		cellFormation[55][28].makeAlive();
		cellFormation[55][29].makeAlive();
		cellFormation[55][30].makeAlive();
		cellFormation[55][31].makeAlive();
		cellFormation[54][31].makeAlive();
		cellFormation[54][30].makeAlive();
		cellFormation[53][29].makeAlive();
		cellFormation[56][31].makeAlive();
		cellFormation[56][30].makeAlive();
		cellFormation[57][29].makeAlive();
		
		return cellFormation;
	}
}
