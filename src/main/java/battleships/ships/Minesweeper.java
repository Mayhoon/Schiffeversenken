package battleships.ships;

public class Minesweeper extends Ship {
    // Constructor
    public Minesweeper(Field field, boolean horizontal) {
        description = "Minesweeper";
        length = 3;
        score = length;
        width = 1;
        isHorizontal = horizontal;
        setCoordinates(horizontal, field);
    }

    // Is need for Kryo Serialization
    public Minesweeper() {
        
    }
}
