import javax.swing.*;
import java.awt.*;

public class Player {
    private int roundScore = 0;
    private int gameScore = 0;
	private JPanel panel;
    private String color;
    private JTextField roundWins;
    private JTextField gameWins;
    private JLabel colorLabel;
    private JLabel roundLabel;
    private JLabel gameLabel;
	private String playerOwner;

	private Snakebody snake[] = new Snakebody[0];
	public static enum Direction {UP, RIGHT, DOWN, LEFT};
	private Direction curDir = Direction.UP;

    public Player(String player){
        playerOwner = player;
        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        colorLabel = new JLabel("COLOR: ");
        roundLabel = new JLabel("ROUND SCORE: ") ;


	
    }

    public void incrementRoundWins(String playerOwner) {
	    roundScore++;
	}
	
	public void setDirection(Direction newOrient){
		boolean isSameDir = curDir == newOrient;
		boolean isOppositeDir = curDir.ordinal() == (newOrient.ordinal() + 2) % 4;
		if(isSameDir || isOppositeDir) // don't want to go directly backwards
			System.out.println("nah");
		else {
			curDir = newOrient;
			System.out.println(curDir);
		}
	}

	

}