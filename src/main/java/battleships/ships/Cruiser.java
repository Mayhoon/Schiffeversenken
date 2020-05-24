package battleships.ships;

public class Cruiser extends Ship {
    // Constructor
    public Cruiser(Field field, boolean horizontal) {
        description = "Cruiser";
        length = 4;
        width = 1;
        isHorizontal = horizontal;
        setCoordinates(horizontal, field);
    }

    public Cruiser() {

    }
}
