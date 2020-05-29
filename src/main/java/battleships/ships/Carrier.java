package battleships.ships;

public class Carrier extends Ship {
    // Constructor
    public Carrier(Field field, boolean horizontal) {
        description = "Carrier";
        length = 6;
        score = length;
        width = 1;
        isHorizontal = horizontal;
        setCoordinates(horizontal, field);
    }

    public Carrier () {

    }
}
