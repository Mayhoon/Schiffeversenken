package battleships.main;

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

    public GameStateManager() {
        input = new Input();
        network = new Network();
        playerData = new Data();

        //Initialize ships
        connect();
        playerData.fleet = new Fleet(input);

        //Render own ships
        output = new Output();
        output.render(playerData.fleet);

        //DEBUG: render Opponent ships
        startGame();

        while (network.opponent().isDone) {
            shoot();
            break;
        }
    }

    private void shoot() {
       Field field = input.getShootCoordinates();

        //Check Hit - Calculate Score - Check if won
        if (playerData.fleet.isOccupied(field)) {
            ShotInformation hitInfo = playerData.fleet.isHit(field);

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
        System.out.println("DEBUG: Fleet of the opponent");
        output.render(network.opponent().fleet);
    }
}
