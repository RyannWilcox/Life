package gamelife;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class StartGame extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	JFrame myFrame;
	int lastX = -100, lastY = -100; 
	JButton[] button;
	static final String[] BUTTON_STR = {"Obstacle Creation", "Path Generation","QUIT"};

	private GridSquare theGrid [][] = new GridSquare [50] [50];
	private Cell cell[][] = new Cell [50] [50];
	private int i;
	private int j;  
	private int squareX = 0;  
	private int squareY = 0;	
	public StartGame(String title,int width,int height){
		super();
		//Populate cell array
		for(int i = 0; i < 50;i++){
			for(int j = 0; j < 50;j++){
				cell[i][j] = new Cell();
			}
		}
		layoutSetup(title, width, height);
		myFrame.setVisible(true); 
		button[0].setEnabled(true);
		button[1].setEnabled(false);
		button[2].setEnabled(true);
	}
	public void layoutSetup(String theTitle,int theWidth,int theHeight){
		myFrame = new JFrame(theTitle) {
			private static final long serialVersionUID = 1L;
			public void paint(Graphics g) {
				paintComponents(g);
			}
		};
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(theWidth, theHeight);
		myFrame.setLayout(new BorderLayout());
		myFrame.add(this, BorderLayout.CENTER);

		JPanel controlPanel = new JPanel(new GridLayout(8, 1));
		button = new JButton[BUTTON_STR.length];
		for (int i=0; i<BUTTON_STR.length; i++) {
			button[i] = new JButton(BUTTON_STR[i]);
			button[i].addActionListener(this);
			button[i].setFocusable(false);
			controlPanel.add(button[i]);
		}
		myFrame.add(controlPanel, BorderLayout.EAST);
	}
	/**
	 * Paints the grid
	 */
	public void paintComponent(Graphics g){ 
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		for (i = 0; i < 50; i++) {
			for (j = 0; j < 50; j++) {
				theGrid[i][j] = new GridSquare(i*10,j*10,i,j);	// populate 2d array
				theGrid[i][j].drawSquare(g);//draw grid in graphic panel
			}
		}  
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button[2]){
			System.exit(1);
		}
	}
	
	public static void main(String[] args) {
		StartGame sg = new StartGame("Life",700,700);	
	}

}
