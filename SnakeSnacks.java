

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
	private JPanel playerPanel;
	private Menu menu;
	private Food food;

	
	public SnakeSnacks(){
		setTitle("SnakeSnacks");
		setSize(WINDOW_W, WINDOW_H);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gamePanel = new JPanel();
		playerPanel = new JPanel();
		menu = new Menu();
		food = new Food();
		panel1 = new Player("Player 1");
		panel2 = new Player("Player 2");
		playerPanel.add(panel1, BorderLayout.SOUTH);
        playerPanel.add(panel2, BorderLayout.NORTH);
		playerPanel.add(menu, BorderLayout.CENTER);

		//add(playerPanel);
		//add(gamePanel);
		
		setVisible(true);
	}
	
	public void redrawGameGrid(){
		
	}

	public void paint(Graphics g){
		super.paint(g);
		food.paintTEST(g);
		
	}
	
	public static void main(String[] args){
		new SnakeSnacks();
	}
}