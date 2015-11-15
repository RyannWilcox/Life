package gamelife;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;

public class ChoiceMenu extends JMenuBar implements RowColumnBounds {
	private static final long serialVersionUID = 1L;

	private GridPanel cellGrid;
	private ControlPanel cPanel;
	private CellsAndGrid cellAndGridSquare;

	/*
	 * Set up our choice menu for the frame
	 */
	public ChoiceMenu(GridPanel aGrid, ControlPanel aPanel, CellsAndGrid data) {
		cellAndGridSquare = data;
		cellGrid = aGrid;
		cPanel = aPanel;
		JMenu choices = new JMenu("Patterns");
		JMenu speeds = new JMenu("Speeds");
		JMenu colors = new JMenu("Colors");
		JMenu zoom = new JMenu("Zoom");
		JMenu rules = new JMenu("Rule Sets");

		/* Patterns */
		ButtonGroup patternGroup = new ButtonGroup();
		JRadioButtonMenuItem gliderGun = new JRadioButtonMenuItem("GliderGun");
		JRadioButtonMenuItem pulsar = new JRadioButtonMenuItem("Pulsar");
		JRadioButtonMenuItem Pentomino = new JRadioButtonMenuItem("R-Pentomino");
		JRadioButtonMenuItem spider = new JRadioButtonMenuItem("Spider");
		JRadioButtonMenuItem custom = new JRadioButtonMenuItem("Custom");
		patternGroup.add(gliderGun);
		patternGroup.add(Pentomino);
		patternGroup.add(spider);
		patternGroup.add(custom);

		/* Speeds */
		ButtonGroup speedGroup = new ButtonGroup();
		JRadioButtonMenuItem fast = new JRadioButtonMenuItem("Fast");
		JRadioButtonMenuItem med = new JRadioButtonMenuItem("Medium");
		JRadioButtonMenuItem slow = new JRadioButtonMenuItem("Slow");
		speedGroup.add(fast);
		speedGroup.add(slow);
		speedGroup.add(med);

		/* Colors */
		ButtonGroup colorGroup = new ButtonGroup();
		JRadioButtonMenuItem green = new JRadioButtonMenuItem("Green");
		JRadioButtonMenuItem red = new JRadioButtonMenuItem("Red");
		JRadioButtonMenuItem black = new JRadioButtonMenuItem("Black");
		colorGroup.add(black);
		colorGroup.add(red);
		colorGroup.add(green);

		/* Zoom! */
		ButtonGroup zoomGroup = new ButtonGroup();
		JRadioButtonMenuItem in = new JRadioButtonMenuItem("In");
		JRadioButtonMenuItem out = new JRadioButtonMenuItem("Out");
		JRadioButtonMenuItem norm = new JRadioButtonMenuItem("Normal");
		zoomGroup.add(in);
		zoomGroup.add(out);
		zoomGroup.add(norm);

		/* Life rule variations */
		ButtonGroup ruleGroup = new ButtonGroup();
		JRadioButtonMenuItem life = new JRadioButtonMenuItem("Life");
		JRadioButtonMenuItem highLife = new JRadioButtonMenuItem("High Life");
		JRadioButtonMenuItem seed = new JRadioButtonMenuItem("Seed");
		ruleGroup.add(life);
		ruleGroup.add(highLife);
		ruleGroup.add(seed);

		/* Add to the JMenuBar */
		add(choices);
		add(speeds);
		add(colors);
		add(zoom);
		add(rules);

		// adds the different MenuItems to the JMenu
		choices.add(gliderGun);
		choices.add(pulsar);
		choices.add(Pentomino);
		choices.add(spider);
		choices.add(custom);
		speeds.add(fast);
		speeds.add(med);
		speeds.add(slow);
		colors.add(green);
		colors.add(red);
		colors.add(black);
		zoom.add(in);
		zoom.add(out);
		zoom.add(norm);
		rules.add(life);
		rules.add(highLife);
		rules.add(seed);

		/*
		 * Clears the cells and then prints the glider gun pattern to the grid.
		 */
		gliderGun.addActionListener(event -> {
			cellAndGridSquare.clearCells(MAX_ROWS, MAX_COLUMNS);
			cellAndGridSquare.createGliderGun();
			cPanel.setGenCount(1);
			cPanel.updateGenLabel();
			cellGrid.repaint();
		});

		/*
		 * Clears the grid of all live cells. Then creates the formation pulsar
		 * and paints the grid with those cells.
		 */
		pulsar.addActionListener(event -> {
			cellAndGridSquare.clearCells(MAX_ROWS, MAX_COLUMNS);
			cellAndGridSquare.createPulsar();
			cPanel.setGenCount(1);
			cPanel.updateGenLabel();
			cellGrid.repaint();
		});
		/*
		 * Clears the grid of all live cells then creates the formation
		 * R-Pentomino and updates the grid
		 */
		Pentomino.addActionListener(event -> {
			cellAndGridSquare.clearCells(MAX_ROWS, MAX_COLUMNS);
			cellAndGridSquare.createRPentomino();
			cPanel.setGenCount(1);
			cPanel.updateGenLabel();
			cellGrid.repaint();
		});
		spider.addActionListener(event -> {
			cellAndGridSquare.clearCells(MAX_ROWS, MAX_COLUMNS);
			cellAndGridSquare.createSpider();
			cPanel.setGenCount(1);
			cPanel.updateGenLabel();
			cellGrid.repaint();
		});
		custom.addActionListener(event -> {
			cellAndGridSquare.clearCells(MAX_ROWS, MAX_COLUMNS);
			cellAndGridSquare.createCustom();
			cPanel.setGenCount(1);
			cPanel.updateGenLabel();
			cellGrid.repaint();
		});

		/* CHANGES THE SPEED OF THE THREAD!!! */
		med.addActionListener(event -> cPanel.getUpdater().setSpeed(120));
		fast.addActionListener(event -> cPanel.getUpdater().setSpeed(70));
		slow.addActionListener(event -> cPanel.getUpdater().setSpeed(180));

		/* CHANGES THE COLOR OF THE CELLS!!! */
		red.addActionListener(event -> {
			cellAndGridSquare.changeColors(GridSquare.colors.RED);
			cellGrid.repaint();
		});
		green.addActionListener(event -> {
			cellAndGridSquare.changeColors(GridSquare.colors.GREEN);
			cellGrid.repaint();
		});
		black.addActionListener(event -> {
			cellAndGridSquare.changeColors(GridSquare.colors.BLACK);
			cellGrid.repaint();
		});

		/*
		 * Makes all the squares larger This zooms in the grid
		 */
		in.addActionListener(event -> {
			cellAndGridSquare.zoomGrid(CellsAndGrid.zooms.IN);
			cellGrid.repaint();
		});

		/*
		 * Makes all the squares smaller This zooms out the grid
		 */
		out.addActionListener(event -> {
			cellAndGridSquare.zoomGrid(CellsAndGrid.zooms.OUT);
			cellGrid.repaint();
		});
		/*
		 * This will return the grid to normal starting size.
		 */
		norm.addActionListener(event -> {
			cellAndGridSquare.zoomGrid(CellsAndGrid.zooms.NORMAL);
			cellGrid.repaint();
		});

		/*
		 * Normal game of life rules
		 */
		life.addActionListener(event -> {
			cellAndGridSquare.setHighLifeRule(false);
		});

		/*
		 * Adds one new rule to the game of life
		 */
		highLife.addActionListener(event -> {
			cellAndGridSquare.setHighLifeRule(true);
		});

		/*
		 * Uses special set of rules for game of life
		 */
		seed.addActionListener(event -> {
			cellAndGridSquare.setSeedRule(true);
		});
	}
}