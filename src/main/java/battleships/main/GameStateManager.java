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
import java.util.concurrent.TimeUnit;

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
        firstTurn();

        gameLoop();
    }

    private void gameLoop() {
        System.out.println("Waiting for thy enemy");
        while (network.opponent().turn) {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(".");
        }
        if (network.opponent().hasWon) {
            output.render(network.opponent().fleet, false);
            Color.yellow("Your Opponent has won with a score of: " + network.opponent().score);
            Color.green("Your score: " + playerData.score);
            System.out.println(Strings.CREDITS);
            System.exit(1);
        } else {
            shoot();
            output.render(network.opponent().fleet, false);
            endTurn();
            gameLoop();
        }
    }

    private void firstTurn() {
        System.out.println("Waiting for your opponent");
        while (network.opponent().fleet == null) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(".");
        }

        output.render(network.opponent().fleet, false);

        if (networkType.equals(NetworkType.HOST)) {
            playerData.turn = true;
            network.sendData(playerData);
            shoot();
            output.render(network.opponent().fleet, false);
            endTurn();
        } else {
            network.opponent().turn = true;
        }
    }

    private void endTurn() {
        playerData.turn = false;
        network.opponent().turn = true;
        network.sendData(playerData);
    }

    private void shoot() {
        Field field = input.getShootCoordinates();
        Fleet opponent = network.opponent().fleet;

        //Check Hit - Calculate Score - Check if won
        ShotInformation hitInfo = opponent.isHit(field);
        if (hitInfo.hitType == HitType.ALREADY_HIT) {
            Color.yellow("[" + hitInfo.field.getX() + "][" + hitInfo.field.getY() + "]" + " is already hit! Try again!");
            shoot();
        } else if (hitInfo.hitType == HitType.MISS) {
            Color.red("Miss at [" + hitInfo.field.getX() + "][" + hitInfo.field.getY() + "]");
        } else {
            Color.green("Hit at [" + hitInfo.field.getX() + "][" + hitInfo.field.getY() + "]");
            playerData.score += hitInfo.score;

            //Win condition
            if (opponent.allShipsDestroyed()) {
                output.render(network.opponent().fleet, false);
                Color.green("Won with score: " + hitInfo.score);
                Color.yellow("Enemy score: " + network.opponent().score);
                System.out.println(Strings.CREDITS);

                //Sending the final results to the opponent
                playerData.hasWon = true;
                playerData.score = hitInfo.score;
                endTurn();

                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        }
    }

    private void connect() {
        networkType = input.networkType();
        if(networkType.equals(NetworkType.CLIENT)) {
            network.connect(networkType, input.getIp());
        }else {
            network.connect(networkType, "");
        }
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
        output.render(fleet, true);

        Color.purple(Strings.SHIP_TO_BE_PLACED + Strings.CARRIER_DESCRIPTION);
        Color.green(Strings.CARRIER_FORM);
        while (!carrierValid) {
            carrierValid = fleet.initCarrier(input.getPosition(), input.getDirection());
        }
        output.render(fleet, true);

        Color.purple(Strings.SHIP_TO_BE_PLACED + Strings.BATTLESHIP_DESCRIPTION);
        Color.green(Strings.BATTLESHIP_FORM);
        while (!battleShipValid) {
            battleShipValid = fleet.initBattleShip(input.getPosition(), input.getDirection());
        }
        output.render(fleet, true);

        Color.purple(Strings.SHIP_TO_BE_PLACED + Strings.CRUISER_DESCRIPTION);
        Color.green(Strings.CRUISER_FORM);
        while (!cruiserValid) {
            cruiserValid = fleet.initCruiser(input.getPosition(), input.getDirection());
        }
        output.render(fleet, true);

        Color.purple(Strings.SHIP_TO_BE_PLACED + Strings.MINESWEEPER_DESCRIPTION);
        Color.green(Strings.MINESWEEPER_FORM);
        while (!minesweeperValid) {
            minesweeperValid = fleet.initMinesweeper(input.getPosition(), input.getDirection());
        }
        output.render(fleet, true);

        Color.purple(Strings.SHIP_TO_BE_PLACED + Strings.OIL_PLATFORM_DESCRIPTION);
        Color.green(Strings.OIL_PLATFORM_FORM);
        while (!oilPlatformValid) {
            oilPlatformValid = fleet.initOilPlatform(input.getPosition(), input.getDirection());
        }
        output.render(fleet, true);

        playerData.fleet = fleet;
    }
}
