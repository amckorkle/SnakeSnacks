import javax.swing.*;
import java.util.*;
import java.awt.*;

public class Snakebody extends Tile {
	private Point position;

	public Snakebody(Point point) {
		position = point;
	}

	public void paint(Graphics g, int x, int y) {
		g.setColor(Color.GREEN);
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