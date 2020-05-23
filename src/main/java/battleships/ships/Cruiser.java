package battleships.ships;

import java.util.Collections;
import java.util.List;

public class Cruiser extends Ship {
    // Constructor
    public Cruiser(Field field, boolean horizontal) throws Exception {
        description = "Cruiser";
        length = 4;
        width = 1;
        isHorizontal = horizontal;
        setCoordinates(horizontal, field);
    }

    public Cruiser() {

    }
}
