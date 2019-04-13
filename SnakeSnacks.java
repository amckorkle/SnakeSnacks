

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SnakeSnacks extends JFrame{
	private final int WINDOW_W = 500;
	private final int WINDOW_H = 500;
	private Tile[][] gameGrid;
	private Player panel1;
	private Player panel2;
	
	public SnakeSnacks(){
		setTitle("SnakeSnacks");
		setSize(WINDOW_W, WINDOW_H);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel1 = new Player("Player 1");
		panel2 = new Player("Player 2");
		
		add(panel1, BorderLayout.SOUTH);
        add(panel2, BorderLayout.NORTH);
		
		
		
		setVisible(true);
	}
	
	public void redrawGameGrid(){
		
	}
	
	public static void main(String[] args){
		new SnakeSnacks();
	}
}