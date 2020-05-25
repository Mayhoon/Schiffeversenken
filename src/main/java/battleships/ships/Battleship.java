package battleships.ships;

public class Battleship extends Ship {
    // Constructor
    public Battleship(Field field, boolean horizontal) {
        description = "Battleship";
        length = 5;
        width = 1;
        isHorizontal = horizontal;
        setCoordinates(horizontal, field);
    }

    public Battleship() {

    }
}
