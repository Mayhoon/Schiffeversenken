package battleships.ships;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Battleship extends Ship {
    // Constructor
    public Battleship(Field field, boolean horizontal) throws Exception {
        description = "Battleship";
        length = 5;
        width = 1;
        isHorizontal = horizontal;
        setCoordinates(horizontal, field);
    }

    public Battleship() {

    }
}
