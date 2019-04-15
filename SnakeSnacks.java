
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SnakeSnacks extends JFrame {
	public static final int WINDOW_W = 1000;
	public static final int WINDOW_H = 1000;
	private Gameboard gameboard;
	private KeyListenerManager keyMngr = new KeyListenerManager();

	private Timer timer;

	private Player panel1;
	private Player panel2;
	private JPanel gamePanel;
	private JPanel playerPanel;
	private Menu menu;
	private Tile food;
	private Tile wall;

	public SnakeSnacks() {
		setTitle("SnakeSnacks");
		setSize(WINDOW_W, WINDOW_H);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(keyMngr);

		timer = new Timer(200, new timerListener());

		gamePanel = new JPanel();
		playerPanel = new JPanel();
		menu = new Menu();
		food = new Food();
		wall = new Wall();

		gameboard = new Gameboard();
		add(gameboard);

		panel1 = new Player("Player 1", gameboard);

		Snakebody head = panel1.initSnake(new Point(1, 1));
		gameboard.addToGameGrid(head, head.getX(), head.getY());

		// panel2 = new Player("Player 2");

		// add(playerPanel);
		// add(gamePanel);
		assignWASDControls(panel1);
		timer.start();
		setVisible(true);
	}

	private void assignWASDControls(Player p) {
		keyMngr.addKeyCommand("W", () -> p.setDirection(Player.Direction.UP));
		keyMngr.addKeyCommand("A", () -> p.setDirection(Player.Direction.LEFT));
		keyMngr.addKeyCommand("S", () -> p.setDirection(Player.Direction.DOWN));
		keyMngr.addKeyCommand("D", () -> p.setDirection(Player.Direction.RIGHT));
		keyMngr.addKeyCommand("Space", () -> p.eatFood());
	}

	private void assignArrowKeysControls(Player p) {
		keyMngr.addKeyCommand("Up", () -> p.setDirection(Player.Direction.UP));
		keyMngr.addKeyCommand("Left", () -> p.setDirection(Player.Direction.LEFT));
		keyMngr.addKeyCommand("Down", () -> p.setDirection(Player.Direction.DOWN));
		keyMngr.addKeyCommand("Right", () -> p.setDirection(Player.Direction.RIGHT));
	}

	private class timerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Snakebody newBody = panel1.moveSnakeForward();
			gameboard.addToGameGrid(newBody, newBody.getX(), newBody.getY());

			gameboard.repaint();
		}
	}

	public static void main(String[] args) {
		new SnakeSnacks();
	}
}

class Gameboard extends JPanel {
	Tile[][] gameGrid;

	public Gameboard() {
		gameGrid = new Tile[20][20];
	}

	public void addToGameGrid(Tile tile, int x, int y) {
		gameGrid[y][x] = tile;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// For each tile, display it if it exists at all
		Tile curTile;
		for (int r = 0; r < gameGrid.length; r++) {
			for (int c = 0; c < gameGrid[0].length; c++) {
				curTile = gameGrid[r][c];
				if (curTile != null) {
					curTile.display(g, c, r);
				}
			}
		}
	}

	// set the tile at the given location to null
	public void deleteTileAtPoint(int x, int y) {
		gameGrid[y][x] = null;
	}

}