package gamelife;

public class Updater implements Runnable, RowColumnBounds {
	private boolean running = true;
	private CellsAndGrid cellAndGridSquare;
	private GridPanel cellGrid;
	private int speed = 120;
	private ControlPanel cPanel;
	
	public Updater(boolean run,CellsAndGrid cellAndGrid,GridPanel gp, ControlPanel cp){
		running = run;
		cellAndGridSquare = cellAndGrid;
		cellGrid = gp;
		cPanel = cp;
	}
	
	@Override
	public void run() {
		while (running) {
			// While the stop button/quit button is not pushed
			// the thread will continue to loop
			if(cellAndGridSquare.getSeedRuleStatus()){
				cellAndGridSquare.seedUpdateCells(MAX_ROWS, MAX_COLUMNS);
			}
			else{
				cellAndGridSquare.updateCells(MAX_ROWS, MAX_COLUMNS);
			}
			/*update JLabel*/
			cPanel.updateGenLabel();
			cellGrid.repaint();
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	/**
	 * Time that the thread will sleep
	 * @return sleep time
	 */
	public int getSpeed(){
		return speed;
	}
	/**
	 * Set the time the thread will sleep
	 * @param s
	 */
	public void setSpeed(int s){
		speed = s;
	}
	/**
	 * Will stop the thread by
	 * exiting the loop
	 */
	public void stopRunning(){
		running = false;
	}
	/**
	 * Will make it possible to start
	 *  the thread again
	 */
	public void startRunning(){
		running = true;
	}
}
