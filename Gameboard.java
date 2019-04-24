import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Gameboard extends JPanel {
    Tile[][] gameGrid;
    private static int GG_H = 20;
    private static int GG_W = 25;
    private Point foodLocation;
    private Food food;
    private Random rnd;

    public Gameboard(KeyListenerManager keyMngr) {
        gameGrid = new Tile[GG_H][GG_W];
        rnd = new Random();
        foodLocation = new Point();
        food = new Food();

        addKeyListener(keyMngr);
        setFocusable(true);
    }

    public void addToGameGrid(Tile tile, int x, int y) {
        gameGrid[y][x] = tile;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // For each tile, display it if it exists at all
        Tile curTile;
        for (int r = 0; r < gameGrid.length; r++) {
            for (int c = 0; c < gameGrid[0].length; c++) {
                curTile = gameGrid[r][c];
                if (curTile != null) {
                    curTile.display(g, c, r);
                }
            }
        }
    }

    // set the tile at the given location to null
    public void deleteTileAtPoint(int x, int y) {
        gameGrid[y][x] = null;
    }

    public void foodEaten() {
        deleteTileAtPoint((int) foodLocation.getX(), (int) foodLocation.getY());
        placeFood();
    }

    public void placeFood() {
        int x = rnd.nextInt(GG_W);
        int y = rnd.nextInt(GG_H);

        while (gameGrid[y][x] != null) {
            x = rnd.nextInt(GG_W);
            y = rnd.nextInt(GG_H);
        }

        foodLocation.setLocation(x, y);
        System.out.println(x + ", " + y);
        addToGameGrid(food, x, y);
    }

    public int getBoardWidth() {
        return GG_W;
    }

    public int getBoardHeight() {
        return GG_H;
    }

}