package battleships.network;

import battleships.ships.Fleet;

public class Data {
    public Fleet fleet;
    public boolean turn = false;
    public int score = 0;
    public boolean hasWon = false;

    public Data() {
    }
}
