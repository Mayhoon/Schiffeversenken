package battleships.ships;

import java.util.ArrayList;
import java.util.List;

public class Player {
    int score = 0;

    List<Ship> fleet = new ArrayList<>();

    // Getter/Setter
    // Score
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // Fleet
    public void setFleet(Ship ship) {
        this.fleet.add(ship);
    }

    public List<Ship> getFleet() {
        return fleet;
    }

    // Methods
    private boolean hasLost() {
        boolean hasLost = false;

        for (Ship ship : fleet) {
            if (ship.isDestroyed()) {
                hasLost = true;
            } else return false;
        }

        return hasLost;
    }
}
