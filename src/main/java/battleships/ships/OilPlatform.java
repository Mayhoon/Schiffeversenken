package battleships.ships;

import java.util.Collections;
import java.util.List;

public class OilPlatform extends Ship {
    // Constructor
    public OilPlatform(Field field, boolean horizontal) throws Exception {
        description = "Oil Platform";
        length = 2;
        width = 2;
        isHorizontal = horizontal;
        this.setCoordinates(isHorizontal, field);
    }

    public OilPlatform() {

    }

    // Methods
    @Override
    public void setCoordinates(boolean isHorizontal, Field position) throws Exception {
        if ((position.getX() + length) > 9 || (position.getY() + length) > 9) {
            throw new Exception(OUT_OF_BOUND_EXCEPTION_CAUSE);
        } else {
            occupiedCoordinates.add(new Field(position.getX(), position.getY()));
            occupiedCoordinates.add(new Field(position.getX() + 1, position.getY()));
            occupiedCoordinates.add(new Field(position.getX(), position.getY() + 1));
            occupiedCoordinates.add(new Field(position.getX() + 1, position.getY() + 1));
        }
    }
}
