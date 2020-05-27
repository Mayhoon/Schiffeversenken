package battleships.main;

import battleships.console.Color;
import battleships.console.Input;
import battleships.console.Output;
import battleships.enums.HitType;
import battleships.enums.NetworkType;
import battleships.etc.Strings;
import battleships.network.Data;
import battleships.network.Network;
import battleships.ships.Field;
import battleships.ships.Fleet;
import battleships.ships.ShotInformation;

import java.util.Scanner;

public class GameStateManager {
    private Input input;
    private Data playerData;
    private Network network;
    private Output output;
    private Scanner scanner;
    private NetworkType networkType;
    private Fleet fleet;

    public GameStateManager() {
        input = new Input();
        network = new Network();
        playerData = new Data();

        connect();
        initializeFleet();

        //Send ships to opponent
        network.sendData(playerData);

        //Render fleet from opponent
        System.out.println("Did your opponent finish his turn?");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();


        if (input.equals("yes")) {
            output.render(network.opponent().fleet);
            gameLoop();
        }
    }

    private void gameLoop() {
        System.out.println("Waiting for thy enemy...");
        while (network.opponent().turn) {

        }
        if (network.opponent().hasWon) {
            Color.yellow("Your Opponent has won with a score of: " + network.opponent().score);
            Color.green("Your score: " + playerData.score);
            System.exit(1);
        } else {
            shoot();
            output.render(network.opponent().fleet);
            endTurn();
            gameLoop();
        }
    }

    private void endTurn() {
        network.sendData(playerData);
        playerData.turn = false;
    }

    private void shoot() {
        Field field = input.getShootCoordinates();
        Fleet opponent = network.opponent().fleet;

        //Check Hit - Calculate Score - Check if won
        ShotInformation hitInfo = opponent.isHit(field);
        if (hitInfo.hitType == HitType.ALREADY_HIT) {
            Color.yellow("[" + hitInfo.field.getX() + "][" + hitInfo.field.getY() + "]" + " is already hit!");
            shoot();
        } else if (hitInfo.hitType == HitType.MISS) {
            Color.red("Miss at [" + hitInfo.field.getX() + "][" + hitInfo.field.getY() + "]");
        } else {
            Color.green("Hit at [" + hitInfo.field.getX() + "][" + hitInfo.field.getY() + "]");
            playerData.score += hitInfo.score;

            //Win condition
            if (opponent.allShipsDestroyed()) {
                Color.green("Won with score: " + hitInfo.score);
                Color.yellow("Enemy score: " + network.opponent().score);
                System.out.println(Strings.CREDITS);

                //Sending the final results to the opponent
                playerData.hasWon = true;
                playerData.score = hitInfo.score;
                network.sendData(playerData);
                System.exit(0);
            }
        }
    }

    private void connect() {
        networkType = input.networkType();
        if (networkType.equals(NetworkType.CLIENT)) {
            input.connectWhenHostReady();
        }
        network.connect(networkType);
    }

    private void startGame() {
        boolean startGame = input.startGame();
        while (!startGame) {
            startGame = input.startGame();
        }
        System.out.println("DEBUG: Fleet of the opponent");
        output.render(network.opponent().fleet);
    }

    private void initializeFleet() {
        //Initialize ships
        boolean carrierValid = false;
        boolean battleShipValid = false;
        boolean cruiserValid = false;
        boolean minesweeperValid = false;
        boolean oilPlatformValid = false;

        output = new Output();
        fleet = new Fleet();

        Color.purple(Strings.SHIP_TO_BE_PLACED + Strings.CARRIER_DESCRIPTION);
        while (!carrierValid) {
            carrierValid = fleet.initCarrier(input.getPosition(), input.getDirection());
        }
        output.render(fleet);

//        Color.purple(Strings.SHIP_TO_BE_PLACED + Strings.BATTLESHIP_DESCRIPTION);
//        while (!battleShipValid) {
//            battleShipValid = fleet.initBattleShip(input.getPosition(), input.getDirection());
//        }
//        output.render(fleet);
//
//        Color.purple(Strings.SHIP_TO_BE_PLACED + Strings.CRUISER_DESCRIPTION);
//        while (!cruiserValid) {
//            cruiserValid = fleet.initCruiser(input.getPosition(), input.getDirection());
//        }
//        output.render(fleet);
//
//        Color.purple(Strings.SHIP_TO_BE_PLACED + Strings.MINESWEEPER_DESCRIPTION);
//        while (!minesweeperValid) {
//            minesweeperValid = fleet.initMinesweeper(input.getPosition(), input.getDirection());
//        }
//        output.render(fleet);
//
//        Color.purple(Strings.SHIP_TO_BE_PLACED + Strings.OIL_PLATFORM_DESCRIPTION);
//        while (!oilPlatformValid) {
//            oilPlatformValid = fleet.initOilPlatform(input.getPosition(), input.getDirection());
//        }
//        output.render(fleet);

        playerData.fleet = fleet;
    }
}
