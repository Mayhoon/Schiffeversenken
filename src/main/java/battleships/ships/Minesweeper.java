package battleships.ships;

public class Minesweeper extends Ship {
    // Constructor
    public Minesweeper(Field field, boolean horizontal) {
        description = "Minesweeper";
        length = 3;
        width = 1;
        isHorizontal = horizontal;
        setCoordinates(horizontal, field);
    }
}
