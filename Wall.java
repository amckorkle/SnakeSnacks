import java.awt.Color;
import javax.swing.*;
import java.util.*;
import java.awt.*;

public class Wall extends Tile{
	private int i = 0;
	private int j = 0;
	public Wall(){

	}

	public void paint(Graphics g, int x, int y) {
		g.setColor(Color.GRAY);
		g.fillRect(x, y, Tile.SCALE, Tile.SCALE);
   }
}