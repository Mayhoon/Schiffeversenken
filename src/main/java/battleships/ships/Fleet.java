package battleships.ships;

import battleships.enums.HitType;

import java.util.ArrayList;

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

    public void init() {
        fleet = new ArrayList<Ship>();
//        boolean valid = false;

//        System.out.println(Strings.SHIP_TO_BE_PLACED + Strings.BATTLESHIP_DESCRIPTION);
//        while (!valid) {
//            Field field = input.getPosition();
//            boolean isHorizontal = input.getDirection();
//
//            Battleship battleship = new Battleship(field, isHorizontal);
//
//            if (battleship.validatePlacementLocation(fleet)) {
//                if (battleship.validateCoordinates(isHorizontal, field)) {
//                    fleet.add(battleship);
//                    valid = true;
//                }
//            }
//        }
//        valid = false;
//
//        System.out.println(Strings.SHIP_TO_BE_PLACED + Strings.CRUISER_DESCRIPTION);
//        while (!valid) {
//            Field field = input.getPosition();
//            boolean isHorizontal = input.getDirection();
//
//            Cruiser cruiser = new Cruiser(field, isHorizontal);
//
//            if (cruiser.validatePlacementLocation(fleet)) {
//                if (cruiser.validateCoordinates(isHorizontal, field)) {
//                    fleet.add(cruiser);
//                    valid = true;
//                }
//            }
//        }
//        valid = false;
//
//        System.out.println(Strings.SHIP_TO_BE_PLACED + Strings.MINESWEEPER_DESCRIPTION);
//        while (!valid) {
//            Field field = input.getPosition();
//            boolean isHorizontal = input.getDirection();
//
//            Minesweeper minesweeper = new Minesweeper(field, isHorizontal);
//
//            if (minesweeper.validatePlacementLocation(fleet)) {
//                if (minesweeper.validateCoordinates(isHorizontal, field)) {
//                    fleet.add(minesweeper);
//                    valid = true;
//                }
//            }
//        }
//        valid = false;
//
//        System.out.println(Strings.SHIP_TO_BE_PLACED + Strings.OIL_PLATFORM_DESCRIPTION);
//        while (!valid) {
//            Field field = input.getPosition();
//            boolean isHorizontal = input.getDirection();
//
//            OilPlatform oilPlatform = new OilPlatform();
//            oilPlatform.setCoordinates(isHorizontal, field);
//
//            if (oilPlatform.validatePlacementLocation(fleet)) {
//                if (oilPlatform.validateOilPlatform(field)) {
//                    fleet.add(oilPlatform);
//                    valid = true;
//                }
//            }
//        }
    }

    public boolean isOccupied(Field field) {
        boolean isOccupied = false;

        for (int i = 0; i < fleet.size(); i++) {
            for (int c = 0; c < fleet.get(i).getOccupiedCoordinates().size(); c++) {
                int posY = fleet.get(i).getOccupiedCoordinates().get(c).getY();
                int posX = fleet.get(i).getOccupiedCoordinates().get(c).getX();

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
            hitShip = ship.findShipOnLocation(field);
        }

        if (hitShip != null) {
            returnInfo = hitShip.isHit(field);
        } else {
            returnInfo = new ShotInformation(HitType.MISS, 0, field, null);
        }

        return returnInfo;
    }
}
