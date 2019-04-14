import javax.swing.*;
import java.awt.*;

abstract class Tile {
	//protected Point gridPoint;
	protected static final int SCALE = 40;

	public void display(Graphics g, int grid_x, int grid_y){
		paint(g, SCALE * grid_x, SCALE * grid_y);
	}

	abstract void paint(Graphics g, int x, int y);
}