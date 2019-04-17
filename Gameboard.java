import javax.swing.*;
import java.awt.*;

public class Gameboard extends JPanel {
    Tile[][] gameGrid;
    private static int GG_H = 20;
    private static int GG_W = 25;

    public Gameboard(KeyListenerManager keyMngr) {
        gameGrid = new Tile[GG_H][GG_W];
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

    public int getBoardWidth() {
        return GG_W;
    }

    public int getBoardHeight() {
        return GG_H;
    }

}