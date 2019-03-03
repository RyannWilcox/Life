package gamelife;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class GridPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int newRow = 0;
	private int newCol = 0;
	private CellsAndGrid cellAndGridSquare;

	public GridPanel(CellsAndGrid allData) {
		cellAndGridSquare = allData;
		setDoubleBuffered(true);
		/*
		 * Will allow the user to click and add or click and remove any cells
		 */
		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int count = e.getClickCount();

				switch (cellAndGridSquare.getChoice()) {
				case IN:
					newRow = e.getX() / (1500 / 100);
					newCol = e.getY() / (1500 / 100);
					break;
				case NORMAL:
					newRow = e.getX() / (1000 / 100);
					newCol = e.getY() / (1000 / 100);
					break;
				case OUT:
					newRow = e.getX() / (500 / 100);
					newCol = e.getY() / (500 / 100);
					break;
				default:
					break;
				}
				// Make clicked cell alive
				if (count == 1) {
					cellAndGridSquare.setCellAlive(newRow, newCol);
				}
				// Make double-clicked cell dead
				else if (count == 2) {
					cellAndGridSquare.setCellDead(newRow, newCol);
				}
				repaint();
			}

			// These have to stay...
			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

		});
	}

	/**
	 * Simple paint component Paints the grid with cell updates
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		cellAndGridSquare.paintAll(g);
	}
}
