package battleships.main;

import battleships.console.Color;
import battleships.etc.Strings;

public class Game {
    public static void main(String[] args) throws Exception {
        new Game();
    }

    private GameStateManager gameStateManager;

    public Game() {
        Color.blue(Strings.BANNER);
        gameStateManager = new GameStateManager();
    }

}