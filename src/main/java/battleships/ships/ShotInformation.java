package battleships.ships;

import battleships.enums.HitType;

public class ShotInformation {
    public HitType hitType;
    public int awardedScore;
    public Field field;
    public Ship ship;

    public ShotInformation(HitType hitType, int score, Field field, Ship ship) {
    }
}
