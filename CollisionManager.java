import java.util.Vector;
import java.util.HashSet;
import java.awt.*;

public class CollisionManager {
    private Vector<Player> registeredPlayers;
    private Gameboard gameboard;

    public CollisionManager(Gameboard gameboard) {
        this.gameboard = gameboard;
        registeredPlayers = new Vector<Player>();
    }

    public HashSet<Player> resolveCollisions() {
        HashSet<Player> fatalCollisions = new HashSet<Player>();

        for (Player p1 : registeredPlayers) {

            // Check for collisions with heads
            for (Player p2 : registeredPlayers) {
                Point p1Point = p1.getSnakebodyHead().getPosition();
                Point p2Point = p2.getSnakebodyHead().getPosition();

                if (p1 != p2 && p1Point.equals(p2Point)) {
                    // System.out.println("snake head to head collision");
                    fatalCollisions.add(p1);
                }
            }

            Snakebody body = p1.getSnakebodyHead();
            Tile inGrid = gameboard.gameGrid[body.getY()][body.getX()];
            if (inGrid != null) {

                // collides with a Wall, Snakebody, Food, etc
                if (inGrid instanceof Wall) {
                    fatalCollisions.add(p1);

                } else if (inGrid instanceof Snakebody) {
                    fatalCollisions.add(p1);

                } else if (inGrid instanceof Food) {
                    p1.eatFood();
                    gameboard.foodEaten();
                }
            }
        }

        return fatalCollisions;
    }

    public void registerSnakeMovement(Player p) {
        registeredPlayers.add(p);
    }

}