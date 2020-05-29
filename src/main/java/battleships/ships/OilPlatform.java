package battleships.ships;

public class OilPlatform extends Ship {
    public OilPlatform(Field field, boolean horizontal) {
        description = "Oil Platform";
        length = 2;
        score = length * 2;
        width = 2;
        isHorizontal = horizontal;
        this.setCoordinates(isHorizontal, field);
    }

    public OilPlatform () {

    }

    // Methods
    @Override
    public void setCoordinates(boolean isHorizontal, Field position) {
        occupiedCoordinates.add(new Field(position.getX(), position.getY()));
        occupiedCoordinates.add(new Field(position.getX() + 1, position.getY()));
        occupiedCoordinates.add(new Field(position.getX(), position.getY() + 1));
        occupiedCoordinates.add(new Field(position.getX() + 1, position.getY() + 1));
    }

    public boolean validateOilPlatform(Field field) {
        return (field.getX() + length) <= 9 || (field.getY() + length) <= 9;
    }
}
