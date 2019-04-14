import javax.swing.*;
import java.util.*;
import java.awt.*;

public class Snakebody extends Tile{
	public Snakebody(){
		
	}

	public void paint(Graphics g, int x, int y) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, Tile.SCALE, Tile.SCALE);
   }
}