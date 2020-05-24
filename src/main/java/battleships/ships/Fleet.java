package battleships.ships;

import battleships.console.Color;
import battleships.console.Input;
import battleships.etc.Strings;

import java.util.ArrayList;

public class Fleet {
    private ArrayList<Ship> fleet;
    private Input input;

    public Fleet() {
        fleet = new ArrayList<Ship>();
    }

    public Fleet(Input input) {
        this.input = input;
        init();
    }

    public void init() {
        try {
            fleet = new ArrayList<Ship>();

            System.out.println(Strings.SHIP_TO_BE_PLACED + Strings.CARRIER_DESCRIPTION);
            fleet.add(new Carrier(input.getPosition(), input.getDirection()).validatePlacementLocation(fleet));

            System.out.println(Strings.SHIP_TO_BE_PLACED + Strings.BATTLESHIP_DESCRIPTION);
            fleet.add(new Battleship(input.getPosition(), input.getDirection()).validatePlacementLocation(fleet));

            System.out.println(Strings.SHIP_TO_BE_PLACED + Strings.CRUISER_DESCRIPTION);
            fleet.add(new Cruiser(input.getPosition(), input.getDirection()).validatePlacementLocation(fleet));

            System.out.println(Strings.SHIP_TO_BE_PLACED + Strings.MINESWEEPER_DESCRIPTION);
            fleet.add(new Minesweeper(input.getPosition(), input.getDirection()).validatePlacementLocation(fleet));

            System.out.println(Strings.SHIP_TO_BE_PLACED + Strings.OIL_PLATFORM_DESCRIPTION);
            fleet.add(new OilPlatform(input.getPosition(), input.getDirection()).validatePlacementLocation(fleet));
        } catch (Exception e) {
            System.out.println(Color.RED + "Wrong input" + Color.RESET);
            init();
        }
    }

    public ArrayList<Ship> getFleet() {
        return fleet;
    }

    public boolean isOccupied(int x, int y) {
        for (int i = 0; i < fleet.size(); i++) {
            for (int c = 0; c < fleet.get(i).getOccupiedCoordinates().size(); c++) {
                int posY = fleet.get(i).getOccupiedCoordinates().get(c).getY();
                int posX = fleet.get(i).getOccupiedCoordinates().get(c).getX();

                if (posX == x && posY == y) {
                    return true;
                }
            }
        }
        return false;
    }

    // Methods
    public boolean allShipsDestroyed() {
        boolean hasLost = false;

        for (Ship ship : fleet) {
            if (ship.isDestroyed()) {
                hasLost = true;
            } else return false;
        }
        return hasLost;
    }

    public ShotInformation isHit(int x, int y) {
        for (Ship ship : fleet) {
            ship.isHit(new Field(x, y));
        }
    }
}
