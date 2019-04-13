import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Player extends JPanel{
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

    public Player(String player){
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

        assignPanel(panel, playerOwner);

    }

    public void assignPanel(JPanel panel, String playerOwner){
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