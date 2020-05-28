package battleships.ships;

import battleships.console.Color;
import battleships.enums.HitType;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    public Ship() {
    }

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
    // Occupied Coordinates
    public List<Field> getOccupiedCoordinates() {
        return occupiedCoordinates;
    }

    public ShotInformation isHit(Field field) {
        //Check if the ship is already shot at the given position
        for (Field hitCoord : hitCoordinates) {
            if (hitCoord.getX() == field.getX() && hitCoord.getY() == field.getY()) {
                return new ShotInformation(HitType.ALREADY_HIT, 0, field, this);
            }
        }

        System.out.println("Added");
        //Ship has been shot
        hitCoordinates.add(field);
        ShotInformation shotInfo = new ShotInformation(HitType.SUCCESS, 0, field, this);
        if (this.isDestroyed()) {
            shotInfo.score = this.score;
        }
        return shotInfo;
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
        boolean valid = false;

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
                    Color.red("One or more of the designated coordinates are already in use!");
                    return false;
                }
            }
        }
        return true;
    }

    // Ship Destroyed Determination
    public boolean isDestroyed() {
        return this.hitCoordinates.containsAll(this.occupiedCoordinates);
    }

    public boolean occupiesPosition(Field field) {
        for (Field occupiedCord : occupiedCoordinates) {
            if (occupiedCord.getX() == field.getX() && occupiedCord.getY() == field.getY()) {
                return true;
            }
        }
        return false;
    }

    public HitType isVisibleForPlayer(Field field) {
        for (Field hit : hitCoordinates) {
            if (field.getX() == hit.getX() && field.getY() == hit.getY()) {
                return HitType.SUCCESS;
            }
        }
        return HitType.NOT_SHOT;
    }
}
