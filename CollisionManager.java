import java.util.Vector;
import java.util.HashMap;

public class CollisionManager {
    private HashMap<Player, Boolean> snakesColliding;
    private Gameboard gameboard;

    public CollisionManager(Gameboard gameboard) {
        this.gameboard = gameboard;
        snakesColliding = new HashMap<Player, Boolean>();
    }

    public Vector<Player> resolveCollisions() {
        return null;
    }

    public void registerSnakeMovement(Player p) {
        Snakebody body = p.getSnakebodyHead();
        Tile inGrid = gameboard.gameGrid[body.getY()][body.getX()];
        if (inGrid != null) {
            System.out.println("C O L L I S I O N");
            if (inGrid instanceof Wall) {
                System.out.println("wall");
            } else if (inGrid instanceof Snakebody) {
                System.out.println("snakebody");
            }
        }
    }

}