import javax.swing.*;
import java.awt.*;
import java.util.Vector;
import java.awt.event.*;
import java.util.*;

public class Player extends JPanel {
    private int roundScore = 0;
    private int gameScore = 0;
    private JPanel panel;
    private String[] colors = {"BLACK", "BLUE", "CYAN", "GRAY", "GREEN", "MAGENTA", "ORANGE", "PINK", "RED", "WHITE", "YELLOW"};
    private JTextField roundWins;
    private JTextField gameWins;
    private JComboBox<String> colorField;
    private JLabel colorLabel;
    private JLabel roundLabel;
    private JLabel gameLabel;
    private String playerOwner;
    private Boolean justAteFood = false;
    private Gameboard gb;
    private Color snakeColor;
    private Point startPoint;
    private Map<String, Color> map = new HashMap<String, Color>(); 


    private Vector<Snakebody> snake = new Vector<Snakebody>();

    public static enum Direction {
        UP, RIGHT, DOWN, LEFT
    };

    private Direction curDir = Direction.DOWN;
    private Direction tempDir = Direction.DOWN;

    public Player(String player, Point startPoint, Gameboard gameboard) {
        playerOwner = player;
        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        map.put(colors[0], Color.BLACK);
        map.put(colors[1], Color.BLUE);
        map.put(colors[2], Color.CYAN);
        map.put(colors[3], Color.GRAY);
        map.put(colors[4], Color.GREEN);
        map.put(colors[5], Color.MAGENTA);
        map.put(colors[6], Color.ORANGE);
        map.put(colors[7], Color.PINK);
        map.put(colors[8], Color.RED);
        map.put(colors[9], Color.WHITE);
        map.put(colors[10], Color.YELLOW);


        colorLabel = new JLabel("COLOR: ");
		colorField = new JComboBox<String>(colors);
		colorField.setFocusable(false);
        colorField.addActionListener(new ComboBoxListener());
        roundLabel = new JLabel("ROUND SCORE: ");
		roundWins = new JTextField(8);
		roundWins.setEditable(false);
		roundWins.setText("0");
        gameLabel = new JLabel("GAME SCORE: ");
        gameWins = new JTextField(8);
		gameWins.setEditable(false);
		gameWins.setText("0");

        this.gb = gameboard;

        this.startPoint = startPoint;

        assignPanel(panel, playerOwner);

	}
	
	public void reset(){
		System.out.println("Resetting...");
		roundScore = 0;
		roundWins.setText("0");

		justAteFood = false;
		curDir = Direction.DOWN;
		tempDir = Direction.DOWN;
		snake = new Vector<Snakebody>();
		Snakebody s = initSnake();
		gb.addToGameGrid(s, s.getX(), s.getY());
	}

    public Snakebody getSnakebodyHead() {
        return snake.get(0);
    }

    public void incrementRoundScore() {
		roundScore++;
		roundWins.setText("" + roundScore);
	}
	
	public void wonRound(){
		gameScore += roundScore;
		roundScore = 0;
		roundWins.setText("" + roundScore);
		gameWins.setText("" + gameScore);
	}

    public void setDirection(Direction newOrient) {
        boolean isSameDir = curDir == newOrient;
        boolean isOppositeDir = curDir.ordinal() == (newOrient.ordinal() + 2) % 4;

        if (!(isSameDir || isOppositeDir)) {
            tempDir = newOrient;
        }
    }

    private class ComboBoxListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
			System.out.println(colorField.getSelectedItem());
			setColor(map.get(colorField.getSelectedItem()));
            colorField.setEnabled(false);
        }
    }

    public void display(Graphics g) {
        //System.out.println("waht is this");
        for (Snakebody t : snake) {
            t.display(g, t.getX(), t.getY());
        }
    }

    public void setColor(Color c) {
        snakeColor = c;
    }

    public String getName() {
        return playerOwner;
    }

    public Snakebody initSnake() {
        Snakebody head = new Snakebody(startPoint, snakeColor);
        snake.add(head);
        return head;
    }

    public Snakebody moveSnakeForward() {
        curDir = tempDir;
        Point nextHeadPos = getNextHeadPosition(snake, curDir);
        Snakebody nextHeadPiece = new Snakebody(nextHeadPos, snakeColor);

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
        //System.out.println(dir);
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
		incrementRoundScore();
        justAteFood = true;
    }

    public void assignPanel(JPanel panel, String playerOwner) {
        setBorder(BorderFactory.createTitledBorder(playerOwner));

        panel.add(colorLabel);
        panel.add(colorField);
        panel.add(roundLabel);
        panel.add(roundWins);
        panel.add(gameLabel);
        panel.add(gameWins);

        add(panel);
    }

}