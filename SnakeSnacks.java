
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Set;
import java.util.Vector;

public class SnakeSnacks extends JFrame {
	public static final int WINDOW_W = Gameboard.GG_W * Tile.SCALE + 5; // 25 tiles
	public static final int WINDOW_H = Gameboard.GG_H * Tile.SCALE + 200; // 20 tiles
	private Gameboard gameboard;
	private KeyListenerManager keyMngr;

	public static Timer timer;

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
		setResizable(false);
		keyMngr = new KeyListenerManager();
		addKeyListener(keyMngr);
		timer = new Timer(250, new timerListener());
		

		playerPanel = new JPanel();

		gameboard = new Gameboard(keyMngr);
		add(gameboard);

		panel1 = new Player("Player 1", new Point(1, 10), gameboard, Player.Direction.RIGHT);
		panel2 = new Player("Player 2", new Point(23, 10), gameboard, Player.Direction.LEFT);

		players = new Vector<Player>();
		players.add(panel1);
		players.add(panel2);
		menu = new Menu(timer, this);


		initPlayerSnakes();
		initWall();
		gameboard.placeFood();

		assignWASDControls(panel1);
		panel1.setColor(Color.BLACK);
		assignArrowKeysControls(panel2);
		panel2.setColor(Color.BLACK);

		//timer.start();

		playerPanel.add(panel1, BorderLayout.EAST);
		playerPanel.add(panel2, BorderLayout.WEST);
		playerPanel.add(menu, BorderLayout.SOUTH);

		add(playerPanel, BorderLayout.SOUTH);

		setVisible(true);

		requestFocus();
	}

	private void assignWASDControls(Player p) {
		keyMngr.addKeyCommand("W", () -> p.setDirection(Player.Direction.UP));
		keyMngr.addKeyCommand("A", () -> p.setDirection(Player.Direction.LEFT));
		keyMngr.addKeyCommand("S", () -> p.setDirection(Player.Direction.DOWN));
		keyMngr.addKeyCommand("D", () -> p.setDirection(Player.Direction.RIGHT));
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

	public void reset(){
		gameboard.reset();
		for(Player p: players){
			p.reset();
		}
	}

	private void initWall() {
		int height = gameboard.getBoardHeight();
		int width = gameboard.getBoardWidth();

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
					gameboard.gameGrid[i][j] = new Wall();
				}
			}
		}
	}

	private class timerListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {

			CollisionManager collMngr = new CollisionManager(gameboard);
			Vector<Snakebody> newSnakebodies = new Vector<Snakebody>();

			for (Player p : players) {
				Snakebody newBody = p.moveSnakeForward();
				collMngr.registerSnakeMovement(p);
				newSnakebodies.add(newBody);
			}

			Set<Player> collidedPlayers = collMngr.resolveCollisions();

			if (collidedPlayers.isEmpty()) {

				for (Snakebody body : newSnakebodies) {
					gameboard.addToGameGrid(body, body.getX(), body.getY());
				}

				// Should probably be at the end.
				// Here for sake of demo
				gameboard.repaint();

			} else {
				Vector<Player> winningPlayer = new Vector<Player>(players);

				for(Player collidedP : collidedPlayers){
					for(Player p: players){
						if(collidedP == p){
							winningPlayer.remove(p);
						}
					}
				}

				if(!winningPlayer.isEmpty()){
					winningPlayer.get(0).wonRound();
				}
				timer.stop();
				// then reset everthing
			}

		}
	}

	public void paint(Graphics g) {
		super.paint(g);

	}

	public static void main(String[] args) {
		new SnakeSnacks();
	}
}