
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class SnakeSnacks extends JFrame {
	public static final int WINDOW_W = 1020;
	public static final int WINDOW_H = 1000;
	private Gameboard gameboard;
	private KeyListenerManager keyMngr;

	private Timer timer;

	private Player panel1;
	private Player panel2;
	private Vector<Player> players;
	private JPanel gamePanel;
	private JPanel playerPanel;
	private Menu menu;
	private Tile food;


	public SnakeSnacks() {
		setTitle("SnakeSnacks");
		setSize(WINDOW_W, WINDOW_H);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		keyMngr = new KeyListenerManager();
		addKeyListener(keyMngr);

		timer = new Timer(200, new timerListener());

		playerPanel = new JPanel();
		menu = new Menu();
		food = new Food();

		gameboard = new Gameboard(keyMngr);
		add(gameboard);

		panel1 = new Player("Player 1", new Point(1, 1), gameboard);
		panel2 = new Player("Player 2", new Point(10, 10), gameboard);

		players = new Vector<Player>();
		players.add(panel1);
		players.add(panel2);

		initPlayerSnakes();
		initWall();

		assignWASDControls(panel1);
		panel1.setColor(Color.GREEN);
		assignArrowKeysControls(panel2);
		panel2.setColor(Color.RED);

		timer.start();

		playerPanel.add(panel1, BorderLayout.EAST);
		playerPanel.add(panel2, BorderLayout.WEST);
		playerPanel.add(menu, BorderLayout.SOUTH);

		add(playerPanel, BorderLayout.SOUTH);

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

	private void initPlayerSnakes() {
		for (Player p : players) {
			Snakebody head = p.initSnake();
			gameboard.addToGameGrid(head, head.getX(), head.getY());
		}
	}

	private void initWall(){
		int height = gameboard.getBoardHeight();
		int width = gameboard.getBoardWidth();

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (i == 0 || i == height-1 || j == 0 || j == width-1) {
					gameboard.gameGrid[i][j] = new Wall();
				}
			}
		}
	}

	private class timerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (Player p : players) {
				Snakebody newBody = p.moveSnakeForward();
				gameboard.addToGameGrid(newBody, newBody.getX(), newBody.getY());
			}
			gameboard.repaint();
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		food.display(g, 9, 9);

	
	}

	public static void main(String[] args) {
		new SnakeSnacks();
	}
}

class Gameboard extends JPanel {
	Tile[][] gameGrid;
	private static int GG_H = 20;
	private static int GG_W = 25;
	

	public Gameboard(KeyListenerManager keyMngr) {
		gameGrid = new Tile[GG_H][GG_W];
		addKeyListener(keyMngr);
		setFocusable(true);
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

	public int getBoardWidth(){
		return GG_W;
	}

	
	public int getBoardHeight(){
		return GG_H;
	}

}