

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SnakeSnacks extends JFrame{
	private final int WINDOW_W = 500;
	private final int WINDOW_H = 500;
	private Tile[][] gameGrid;
	private Player panel1;
	private Player panel2;
	private JPanel gamePanel;
	private Menu menu;

	
	public SnakeSnacks(){
		setTitle("SnakeSnacks");
		setSize(WINDOW_W, WINDOW_H);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gamePanel = new JPanel();
		menu = new Menu();
		panel1 = new Player("Player 1");
		panel2 = new Player("Player 2");
		gamePanel.add(panel1, BorderLayout.SOUTH);
        gamePanel.add(panel2, BorderLayout.NORTH);
		gamePanel.add(menu, BorderLayout.CENTER);

		add(gamePanel);
		
		setVisible(true);
	}
	
	public void redrawGameGrid(){
		
	}
	
	public static void main(String[] args){
		new SnakeSnacks();
	}
}