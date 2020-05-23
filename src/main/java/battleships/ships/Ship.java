package battleships.ships;

import battleships.enums.HitType;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    final String OUT_OF_BOUND_EXCEPTION_CAUSE = "The Ship cannot be placed out of bounds";
    final String FIELD_ALREADY_IN_USE_EXCEPTION_CAUSE
            = "The coordinates of your new Ship are already used by another one";

    // Description
    String description = "";

    // Ships length
    int length;

    // Ships width
    int width = 1;

    // horizontal?
    boolean isHorizontal = false;

    // Score
    int score = length;

    // All Coordinates occupied by a ship
    List<Field> occupiedCoordinates = new ArrayList<>();

    // Hit Coordinates
    List<Field> hitCoordinates = new ArrayList<>();

    // Getter Setter
    // Length
    public int getLength() {
        return length;
    }

    public void setLength(int l) {
        length = l;
    }

    // Width
    public int getWidth() {
        return width;
    }

    // Alignment
    public void setHorizontal(boolean horizontal) {
        isHorizontal = horizontal;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    // Score
    public int getScore() {
        return score;
    }

    // Occupied Coordinates
    public List<Field> getOccupiedCoordinates() {
        return occupiedCoordinates;
    }

    public void setOccupiedCoordinates(List<Field> occupiedCoordinates) {
        this.occupiedCoordinates = occupiedCoordinates;
    }

    // Hit Coordinates
    public List<Field> getHitCoordinates() {
        return hitCoordinates;
    }

    public void setHitCoordinates(List<Field> hitCoordinates) {
        this.hitCoordinates = hitCoordinates;
    }

    // Methods
    // Determine Hit
    public HitType isHit(Field position) {
        for (Field f : occupiedCoordinates) {
            if (f == position) {
                if (!hitCoordinates.contains(f)) {
                    hitCoordinates.add(f);
                    this.isDestroyed();
                    return HitType.SUCCESS;
                } else {
                    return HitType.ALREADY_HIT;
                }
            }
        }
        return HitType.MISS;
    }

    // Set Coordinates
    public void setCoordinates(boolean isHorizontal, Field position) throws Exception {
        if (isHorizontal) {
            for (int l = 0; l < length; l++) {
                if ((position.getX() + length - 1) > 9 || (position.getY()) > 9) {
                    throw new Exception(OUT_OF_BOUND_EXCEPTION_CAUSE);
                } else {
                    this.occupiedCoordinates.add(new Field(position.getX() + l, position.getY()));
                }
            }
        } else {
            for (int l = 0; l < length; l++) {
                if ((position.getX()) < 0 || (position.getY() - (length - 1)) < 0) {
                    throw new Exception(OUT_OF_BOUND_EXCEPTION_CAUSE);
                } else {
                    this.occupiedCoordinates.add(new Field(position.getX(), position.getY() - l));
                }
            }
        }
    }

    // Placement Validation - Checks if other ships already use the coordinates
    public Ship validatePlacementLocation(List<Ship> fleet) throws Exception {
        List<Field> allShipsCoordinates = new ArrayList<>();

        for (Ship ship : fleet) {
            allShipsCoordinates.addAll(ship.occupiedCoordinates);
        }

        for (Field coordinate : occupiedCoordinates) {
            if (allShipsCoordinates.contains(coordinate)) {
                throw new Exception(FIELD_ALREADY_IN_USE_EXCEPTION_CAUSE);
            }
        }

        return this;
    }

    public Ship() {

    }

    // Rotate
    public boolean rotate(boolean isHorizontal) {
        return !isHorizontal;
    }

    // Ship Score Determination
    public int grantScore() {
        if (this.hitCoordinates.containsAll(this.occupiedCoordinates)) {
            return this.score;
        } else return 0;
    }

    // Ship Destroyed Determination
    public boolean isDestroyed() {
        return this.hitCoordinates.containsAll(this.occupiedCoordinates);
    }
}
