package battleships.console;

import battleships.enums.NetworkType;
import battleships.ships.Field;

import java.util.Scanner;

public class Input {
    final String INVALID_PLACEMENT_RANGE_EXCEPTION_CAUSE = "Your Ship must be placed within the Battlefield!";
    private Scanner scanner;

    public Input() {
        scanner = new Scanner(System.in);
    }

    public boolean getDirection() {
        System.out.println("Direction of the ship:");
        System.out.println("horizontal (h) / vertical (v)");

        Boolean horizontal = false;
        String input = scanner.next();

        if (input.equals("h")) {
            horizontal = true;
        } else if (input.equals("v")) {
            horizontal = false;
        }
        return horizontal;
    }

    //Todo do not end game if failed
    public Field getPosition() throws Exception {
        System.out.println("X position of the ship:");
        int x = scanner.nextInt();
        System.out.println("Y position of the ship:");
        int y = scanner.nextInt();

        return validateInput(new Field(x, y));
    }

    //Todo: exception handling
    public NetworkType networkType() {
        System.out.println("Are you hosting the game?");
        System.out.println("yes / no");
        String input = scanner.next();

        switch (input) {
            case "yes":
                return NetworkType.HOST;
            case "no":
                return NetworkType.CLIENT;
            default:
                System.out.println("Wrong input.");
                System.exit(1);
        }
        return null;
    }

    public boolean startGame() {
        System.out.println("Type start if your opponent is ready.");
        String input = scanner.next();
        if (input.equals("yes")) {
            return true;
        }else{
            return false;
        }
    }

    public void connectWhenHostReady() {
        System.out.println("Type start when the host has opened the connection.");
        String input = scanner.next();
        while (!input.equals("start")) {
            input = scanner.next();
        }

    }

    private Field validateInput(Field field) throws Exception {
        if (field.getX() > 9 || field.getX() < 0 || field.getY() > 9 || field.getY() < 0) {
            throw new Exception(INVALID_PLACEMENT_RANGE_EXCEPTION_CAUSE);
        } else {
            return field;
        }
    }
}
