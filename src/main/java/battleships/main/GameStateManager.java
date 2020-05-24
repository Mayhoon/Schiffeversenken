package battleships.main;

import battleships.console.Input;
import battleships.console.Output;
import battleships.enums.HitType;
import battleships.enums.NetworkType;
import battleships.etc.Strings;
import battleships.network.Data;
import battleships.network.Network;
import battleships.ships.Fleet;
import battleships.ships.ShotInformation;

import java.util.Scanner;

public class GameStateManager {
    private Input input;
    private Data playerData;
    private Network network;
    private Output output;
    private Scanner scanner;

    public GameStateManager() {
        input = new Input();
        network = new Network();
        connect();

        //Initialize ships
        playerData = new Data();
        playerData.fleet = new Fleet(input);

        //Render own ships
        output = new Output();
        output.render(playerData.fleet);

        //Connect to opponent and send fleet data
        startGame();

        while (!network.opponent().isDone) {
            shoot();
        }
    }

    private void shoot() {
        //Input coordinates
        System.out.println("Select x position:");
        int x = scanner.nextInt();
        System.out.println("Select y position");
        int y = scanner.nextInt();

        final ShotInformation hitInfo;

        //Check Hit - Calculate Score - Check if won
        if (playerData.fleet.isOccupied(x, y)) {
            hitInfo = playerData.fleet.isHit(x,y);

            if (hitInfo.hitType == HitType.ALREADY_HIT) {
                System.out.println("That position is already hit! (" + hitInfo.field + ")");
                shoot();
                return;
            } else if (hitInfo.hitType == HitType.MISS) {
                System.out.println("Miss at " + hitInfo.field);
            } else {
                System.out.println("Hit at" + hitInfo.field);

                playerData.score += hitInfo.awardedScore;

                if (playerData.fleet.allShipsDestroyed()) {
                    System.out.println("Won with score: " + hitInfo.awardedScore);
                    System.out.println("Enemy score: " + network.opponent().score);
                    System.out.println(Strings.CREDITS);
                    System.exit(0);
                }
            }
        }

        //End turn
        playerData.isDone = true;
        network.sendData(playerData);
        playerData.isDone = false;
    }

    private void connect() {
        NetworkType networkType = input.networkType();

        if (networkType.equals(NetworkType.CLIENT)) {
            input.connectWhenHostReady();
        }
        network.connect(networkType);
        network.sendData(playerData);
    }

    private void startGame() {
        boolean startGame = input.startGame();
        while (!startGame) {
            startGame = input.startGame();
        }
        output.render(network.opponent().fleet);
    }
}
