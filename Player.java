import javax.swing.*;
import java.awt.*;
import java.util.Vector;
import java.awt.event.*;

public class Player {
	private int roundScore = 0;
	private int gameScore = 0;
	private JPanel panel;
	private String color;
	private JTextField roundWins;
	private JTextField gameWins;
	private JTextField colorField;
	private JLabel colorLabel;
	private JLabel roundLabel;
	private JLabel gameLabel;
	private String playerOwner;
	private Boolean justAteFood = false;
	private Gameboard gb;

	private Vector<Snakebody> snake = new Vector<Snakebody>();

	public static enum Direction {
		UP, RIGHT, DOWN, LEFT
	};

	private Direction curDir = Direction.DOWN;

	public Player(String player, Gameboard gameboard) {
		playerOwner = player;
		panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1));

		colorLabel = new JLabel("COLOR: ");
		colorField = new JTextField(8);
		roundLabel = new JLabel("ROUND SCORE: ");
		roundWins = new JTextField(8);
		roundWins.setEditable(false);
		gameLabel = new JLabel("GAME SCORE: ");
		gameWins = new JTextField(8);
		gameWins.setEditable(false);

		this.gb = gameboard;

		assignPanel(panel, playerOwner);

	}

	public void incrementRoundWins(String playerOwner) {
		roundScore++;
	}

	public void setDirection(Direction newOrient) {
		boolean isSameDir = curDir == newOrient;
		boolean isOppositeDir = curDir.ordinal() == (newOrient.ordinal() + 2) % 4;

		if (!(isSameDir || isOppositeDir)) {
			curDir = newOrient;
			System.out.println(curDir);
		}
	}

	public void display(Graphics g) {
		for (Snakebody t : snake) {
			t.display(g, t.getX(), t.getY());
		}
	}

	public Snakebody initSnake(Point startPoint) {
		Snakebody head = new Snakebody(startPoint);
		snake.add(head);
		return head;
	}

	public Snakebody moveSnakeForward() {
		Point nextHeadPos = getNextHeadPosition(snake, curDir);
		Snakebody nextHeadPiece = new Snakebody(nextHeadPos);

		// A new body piece in the front
		// and remove the old one
		// idx 0 is the head of the snake
		snake.insertElementAt(nextHeadPiece, 0);

		// if the snake just ate food, you don't need to shrink
		if (justAteFood) {
			justAteFood = false;

		} else {

			Snakebody removed = snake.remove(snake.size() - 1);
			gb.deleteTileAtPoint(removed.getX(), removed.getY());
		}

		return nextHeadPiece;
	}

	private static Point getNextHeadPosition(Vector<Snakebody> snake, Direction dir) {
		Point nextPosition = new Point(snake.get(0).getPosition());

		switch (dir) {
		case UP:
			nextPosition.translate(0, -1);
			break;
		case RIGHT:
			nextPosition.translate(1, 0);
			break;
		case DOWN:
			nextPosition.translate(0, 1);
			break;
		case LEFT:
			nextPosition.translate(-1, 0);
			break;
		}
		return nextPosition;
	}

	public void eatFood() {
		justAteFood = true;
	}

	public void assignPanel(JPanel panel, String playerOwner) {
		// setBorder(BorderFactory.createTitledBorder(playerOwner));

		panel.add(colorLabel);
		panel.add(colorField);
		panel.add(roundLabel);
		panel.add(roundWins);
		panel.add(gameLabel);
		panel.add(gameWins);

		// add(panel);
	}

}