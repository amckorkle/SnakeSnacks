

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SnakeSnacks extends JFrame{
	private final int WINDOW_W = 500;
	private final int WINDOW_H = 500;
	private Tile[][] gameGrid;
	
	public SnakeSnacks(){
		setTitle("SnakeSnacks");
		setSize(WINDOW_W, WINDOW_H);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		
		setVisible(true);
	}
	
	
	public static void main(String[] args){
		new SnakeSnacks();
	}
}