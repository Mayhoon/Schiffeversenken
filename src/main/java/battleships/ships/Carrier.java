package battleships.ships;

public class Carrier extends Ship {
    public Carrier() {

    }

    // Constructor
    public Carrier(Field field, boolean horizontal) throws Exception {
        description = "Carrier";
        length = 6;
        width = 1;
        isHorizontal = horizontal;
        setCoordinates(horizontal, field);
    }
}
