import javax.swing.*;
import java.util.*;
import java.awt.*;

public class Snakebody extends Tile {
	private Point position;
	private Color color;

	public Snakebody(Point point, Color c) {
		position = point;
		color = c;
	}

	public void paint(Graphics g, int x, int y) {
		g.setColor(color);
		g.fillRect(x, y, Tile.SCALE, Tile.SCALE);
	}

	public Point getPosition() {
		return position;
	}

	public int getX() {
		return (int) position.getX();
	}

	public int getY() {
		return (int) position.getY();
	}

}