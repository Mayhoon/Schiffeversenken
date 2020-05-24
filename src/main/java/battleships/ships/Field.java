package battleships.ships;

import battleships.enums.HitType;

public class Field {
    // X-Coordinate
    private int x;

    // Y-Coordinate
    private int y;

    // Hit?
    private HitType isHit = HitType.NOT_SHOT;

    // Getter/Setter
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Constructor
    public Field(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Field() {

    }
}
