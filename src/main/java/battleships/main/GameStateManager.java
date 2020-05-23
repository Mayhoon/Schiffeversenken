package battleships.main;

import battleships.console.Input;
import battleships.console.Output;
import battleships.enums.NetworkType;
import battleships.network.Data;
import battleships.network.Network;
import battleships.ships.Fleet;
import com.sun.tools.classfile.ConstantPool;

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

        while (! network.opponent().isDone) {
            shoot();
        }
    }

    private void shoot() {
        //Input coord
        if( playerData.fleet.isOccupied(0, 0)) {
            //erhoehe score
        }
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
