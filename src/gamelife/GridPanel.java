package gamelife;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class GridPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private int newRow = 0; 
	private int newCol = 0;
	private CellsAndGrid cellAndGridSquare;
	
	public GridPanel(CellsAndGrid allData){
		cellAndGridSquare = allData;
		setDoubleBuffered(true);
				/*
				 * Will allow the user to click and add or
				 * click and remove any cells
				 */
				addMouseListener(new MouseListener(){
					@Override
					public void mouseClicked(MouseEvent e) {
						int count = e.getClickCount();
						
						//Make clicked cell alive
						if(count == 1){
							/* Will find the correct
							 * box to make alive 
							 */
							if(cellAndGridSquare.getNormZoom()){
								newRow = e.getX() / (1000/100);
								newCol = e.getY() / (1000/100);
							}
							if(cellAndGridSquare.getZoom()){
								newRow = e.getX() / (1500/100);
								newCol = e.getY() / (1500/100);
							}
							if(!cellAndGridSquare.getZoom()){
								newRow = e.getX() / (500/100);
								newCol = e.getY() / (500/100);
								
							}
							//System.out.println("Cell comes alive!!! at: ("+ newRow + ","+newCol+")");
							cellAndGridSquare.setCellAlive(newRow, newCol);
							repaint();
						}
						//Make clicked cell dead
						else if(count == 2){
							if(cellAndGridSquare.getNormZoom()){
								newRow = e.getX() / (1000/100);
								newCol = e.getY() / (1000/100);
							}
							if(cellAndGridSquare.getZoom()){
								newRow = e.getX() / (1500/100);
								newCol = e.getY() / (1500/100);
							}
							if(!cellAndGridSquare.getZoom()){
								newRow = e.getX() / (500/100);
								newCol = e.getY() / (500/100);
								
							}				
							// System.out.println("Cell dies at: ("+ newRow + ","+newCol+")");
							cellAndGridSquare.setCellDead(newRow,newCol);
							repaint();
							
						}
					}
					@Override
					public void mousePressed(MouseEvent e) {}
					@Override
					public void mouseReleased(MouseEvent e) {}
					@Override
					public void mouseEntered(MouseEvent e) {}
					@Override
					public void mouseExited(MouseEvent e) {}
				
				});
	}
		
	/**
	 * Simple paint component
	 * Paints the grid with cell updates
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		cellAndGridSquare.paintAll(g);
	}
}
