import javax.swing.*;
import java.awt.Point;

abstract class Tile {
	protected Point gridPoint;
	abstract void display();
}