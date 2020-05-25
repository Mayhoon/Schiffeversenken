package battleships.ships;

import battleships.enums.HitType;

public class ShotInformation {
    public HitType hitType;
    public int score;
    public Field field;
    public Ship ship;

    public ShotInformation(HitType hitType, int score, Field field, Ship ship) {
        this.hitType = hitType;
        this.score = score;
        this.field = field;
        this.ship = ship;
    }
}
