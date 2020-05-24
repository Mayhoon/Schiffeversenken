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
    public ShotInformation isHit(Field position) {
        ShotInformation returnInfo = null;

        for (Field f : occupiedCoordinates) {
            if (f == position) {
                if (!hitCoordinates.contains(f)) {
                    hitCoordinates.add(f);
                    returnInfo = new ShotInformation(HitType.SUCCESS, 0, f, this);
                    if (this.isDestroyed()) {
                        returnInfo.awardedScore = this.score;
                    }
                } else {
                    returnInfo = new ShotInformation(HitType.ALREADY_HIT, 0, f, this);
                }
            }
        }

        return returnInfo;
    }

    // Set Coordinates
    public void setCoordinates(boolean isHorizontal, Field position) {
        if (isHorizontal) {
            for (int l = 0; l < length; l++) {
                this.occupiedCoordinates.add(new Field(position.getX() + l, position.getY()));
            }
        } else {
            for (int l = 0; l < length; l++) {
                this.occupiedCoordinates.add(new Field(position.getX(), position.getY() - l));
            }
        }
    }

    public boolean validateCoordinates(boolean isHorizontal, Field position) {
        boolean valid = true;

        if (isHorizontal) {
            for (int l = 0; l < length; l++) {
                valid = (position.getX() + length - 1) <= 9 && (position.getY()) <= 9;
            }
        } else {
            for (int l = 0; l < length; l++) {
                valid = (position.getX()) >= 0 && (position.getY() - (length - 1)) >= 0;
            }
        }

        return valid;
    }

    // Placement Validation - Checks if other ships already use the coordinates
    public Boolean validatePlacementLocation(ArrayList<Ship> fleet) {
        ArrayList<Field> allShipsCoordinates = new ArrayList<>();

        for (Ship ship : fleet) {
            allShipsCoordinates.addAll(ship.occupiedCoordinates);
        }

        for (Field field : allShipsCoordinates) {
            for (Field occ : occupiedCoordinates) {
                if (field.getX() == occ.getX() && field.getY() == occ.getY()) {
                    System.out.println("One or more of the designated coordinates are already in use!");
                    return false;
                }
            }

        }
        return true;
    }

    public Ship() {
    }

    // Ship Destroyed Determination
    public boolean isDestroyed() {
        return this.hitCoordinates.containsAll(this.occupiedCoordinates);
    }

    public Ship findShipOnLocation(Field position) {
        Ship hitShip = null;

        for (Field p : occupiedCoordinates) {
            if (p == position) {
                if (hitCoordinates.contains(p)) {
                    hitShip = this;
                }
            }
        }

        return hitShip;
    }
}
