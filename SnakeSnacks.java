

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SnakeSnacks extends JFrame{
	private final int WINDOW_W = 1000;
	private final int WINDOW_H = 1000;
	private Tile[][] gameGrid;
	private Player panel1;
	private Player panel2;
	private JPanel playerPanel;
	private Menu menu;
	private Tile food;
	private Tile snakeBody;
	private Tile wall;
	private int i = 0;
	private int j = 0;

	
	public SnakeSnacks(){
		//setTitle("SnakeSnacks");
		setSize(WINDOW_W, WINDOW_H);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		playerPanel = new JPanel();
		menu = new Menu();
		food = new Food();
		wall = new Wall();
		snakeBody  = new Snakebody();
		gameGrid = new Tile[WINDOW_W][WINDOW_H];
		panel1 = new Player("Player 1");
		panel2 = new Player("Player 2");
		playerPanel.add(panel1, BorderLayout.EAST);
        playerPanel.add(panel2, BorderLayout.WEST);
		playerPanel.add(menu, BorderLayout.SOUTH);

		add(playerPanel, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	public void redrawGameGrid(){
		
	}

	public void paint(Graphics g){
		super.paint(g);
		food.display(g, 9, 9);
		snakeBody.display(g, 7, 6);
		for(i = 0; i < (WINDOW_W / 40); i++){
			for(j = 0; j < ((WINDOW_H / 40) - 4); j++){
				if(i == 0 || i == ((WINDOW_W / 40) - 1) || j == 0 || j == ((WINDOW_H / 40) - 5)){
					gameGrid[i][j] = wall;
					wall.display(g, i, j);
				}
			}
		}


	}
	
	public static void main(String[] args){
		new SnakeSnacks();
	}
}