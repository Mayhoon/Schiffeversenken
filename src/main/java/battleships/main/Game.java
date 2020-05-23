package battleships.main;

public class Game {
    public static void main(String[] args) throws Exception {
        new Game();
    }

    private GameStateManager gameStateManager;

    public Game() throws Exception {
        gameStateManager = new GameStateManager();
    }

}