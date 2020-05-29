package battleships.ships;

public class Cruiser extends Ship {
    // Constructor
    public Cruiser(Field field, boolean horizontal) {
        description = "Cruiser";
        length = 4;
        score = length;
        width = 1;
        isHorizontal = horizontal;
        setCoordinates(horizontal, field);
    }

    // Is need for Kryo Serialization
    public Cruiser() {
        
    }
}
