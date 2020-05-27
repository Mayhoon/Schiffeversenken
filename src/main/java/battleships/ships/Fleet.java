package battleships.ships;

import battleships.enums.HitType;

import java.util.ArrayList;
import java.util.List;

public class Fleet {
    private ArrayList<Ship> fleet;

    public Fleet() {
        init();
    }

    public boolean initCarrier(Field field, boolean isHorizontal) {
        Carrier carrier = new Carrier(field, isHorizontal);

        if (carrier.validatePlacementLocation(fleet)) {
            if (carrier.validateCoordinates(isHorizontal, field)) {
                fleet.add(carrier);
                return true;
            }
        }
        return false;
    }

    public boolean initBattleShip(Field field, boolean isHorizontal) {
        Battleship battleship = new Battleship(field, isHorizontal);

        if (battleship.validatePlacementLocation(fleet)) {
            if (battleship.validateCoordinates(isHorizontal, field)) {
                fleet.add(battleship);
                return true;
            }
        }
        return false;
    }

    public boolean initCruiser(Field field, boolean isHorizontal) {
        Cruiser cruiser = new Cruiser(field, isHorizontal);

        if (cruiser.validatePlacementLocation(fleet)) {
            if (cruiser.validateCoordinates(isHorizontal, field)) {
                fleet.add(cruiser);
                return true;
            }
        }
        return false;
    }

    public boolean initMinesweeper(Field field, boolean isHorizontal) {
        Minesweeper minesweeper = new Minesweeper(field, isHorizontal);

        if (minesweeper.validatePlacementLocation(fleet)) {
            if (minesweeper.validateCoordinates(isHorizontal, field)) {
                fleet.add(minesweeper);
                return true;
            }
        }
        return false;
    }

    public boolean initOilPlatform(Field field, boolean isHorizontal) {
        OilPlatform oilPlatform = new OilPlatform(field, isHorizontal);

        if (oilPlatform.validatePlacementLocation(fleet)) {
            if (oilPlatform.validateOilPlatform(field)) {
                fleet.add(oilPlatform);
                return true;
            }
        }
        return false;
    }

    public void init() {
        fleet = new ArrayList<Ship>();
    }

    // All Coordinates occupied by a ship
    List<Field> missedShots = new ArrayList<>();

    public boolean isOccupied(Field field) {
        boolean isOccupied = false;

        for (Ship ship : fleet) {
            for (int c = 0; c < ship.getOccupiedCoordinates().size(); c++) {
                int posY = ship.getOccupiedCoordinates().get(c).getY();
                int posX = ship.getOccupiedCoordinates().get(c).getX();

                if (posX == field.getX() && posY == field.getY()) {
                    isOccupied = true;
                }
            }
        }
        return isOccupied;
    }

    public boolean allShipsDestroyed() {
        boolean hasLost = false;
        for (Ship ship : fleet) {
            hasLost = ship.isDestroyed();
        }
        return hasLost;
    }

    public ShotInformation isHit(Field field) {
        Ship hitShip = null;
        ShotInformation returnInfo;

        for (Ship ship : fleet) {
            hitShip = ship.getShipAtLocation(field);
        }

        if (hitShip != null) {
            returnInfo = hitShip.isHit(field);
        } else {
            returnInfo = new ShotInformation(HitType.MISS, 0, field, null);
            missedShots.add(field);
        }

        return returnInfo;
    }

    public HitType fleetRenderHelper(Field field) {
        HitType hitType = null;

        if (isOccupied(field)) {
            for (Ship ship : fleet) {
                Ship hitShip = ship.getShipAtLocation(field);
                for (Field hitShot : hitShip.hitCoordinates) {
                    if (hitShot.getX() == field.getX() && hitShot.getY() == field.getY()) {
                        hitType = HitType.SUCCESS;
                    } else {
                        hitType = HitType.NOT_SHOT;
                    }
                }
            }
        } else {
            for (Field shot : missedShots) {
                if (shot.getX() == field.getX() && shot.getY() == field.getY()) {
                    hitType = HitType.MISS;
                } else {
                    hitType = HitType.NOT_SHOT;
                }
            }
        }
        return hitType;
    }
}
