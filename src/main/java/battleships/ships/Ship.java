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

    // Methods
    // Determine Hit
    public ShotInformation isHit(Field field) {
        ShotInformation returnInfo = null;
        boolean alreadyHit = false;

        for (Field occ : occupiedCoordinates) {
            if (occ.getX() == field.getX() && occ.getY() == field.getY()) {

                for (Field hitCoord : hitCoordinates) {
                    if (hitCoord.getX() == occ.getX() && hitCoord.getY() == occ.getY()) {
                        alreadyHit = true;
                        returnInfo = new ShotInformation(HitType.ALREADY_HIT, 0, occ, this);
                    }
//                    else {
//                        hitCoordinates.add(occ);
//                        returnInfo = new ShotInformation(HitType.SUCCESS, 0, occ, this);
//                        if (this.isDestroyed()) {
//                            returnInfo.score = this.score;
//                        }
//                    }
                }
                if (!alreadyHit) {
                    hitCoordinates.add(occ);
                    returnInfo = new ShotInformation(HitType.SUCCESS, 0, occ, this);
                    if (this.isDestroyed()) {
                        returnInfo.score = this.score;
                    }
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

    public Ship getShipAtLocation(Field field) {
        Ship hitShip = null;
        System.out.println("Occupied coords length: " + occupiedCoordinates.size());
        for (Field position : occupiedCoordinates) {
            if (position.getX() == field.getX() && position.getY() == field.getY()) {
                return this;
            }
        }
        return hitShip;
    }
}
