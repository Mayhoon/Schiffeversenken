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
        System.out.println("(v) vertical: from bottom to top");
        System.out.println("(h) horizontal: from left to right ");

        Boolean horizontal = false;
        String input = scanner.next();

        if (input.equals("h")) {
            horizontal = true;
        } else if (input.equals("v")) {
            horizontal = false;
        }
        return horizontal;
    }

    public Field getPosition() {
        Field field = new Field();
        boolean valid = false;

        while (!valid) {
            try {
                System.out.println("X position of the ship:");
                field.setX(scanner.nextInt());

                System.out.println("Y position of the ship:");
                field.setY(scanner.nextInt());

                if (validateInput(field)) {
                    valid = true;
                }
            } catch (Exception e) {
                Color.red("Wrong input");
                break;
            }
        }
        return field;
    }

    public NetworkType networkType() {
        boolean valid = false;
        NetworkType networkType = null;

        System.out.println("Are you hosting the game?");
        System.out.println("yes / no");

        while (!valid) {
            String input = scanner.next();

            if (input.equals("yes") || input.equals("no")) {
                switch (input) {
                    case "yes":
                        networkType = NetworkType.HOST;
                        break;
                    case "no":
                        networkType = NetworkType.CLIENT;
                        break;
                }
                valid = true;
            }
        }
        return networkType;
    }

    public void connectWhenHostReady() {
        System.out.println("Type start when the host has opened the connection.");
        String input = scanner.next();
        while (!input.equals("start")) {
            input = scanner.next();
        }

    }

    private boolean validateInput(Field field) {
        if (field.getX() > 9 || field.getX() < 0 || field.getY() > 9 || field.getY() < 0) {
            Color.red("Coordinate is not contained in battlefield! Try again:");
            return false;
        } else {
            return true;
        }
    }

    public Field getShootCoordinates() {
        Field field = null;
        boolean valid = false;

        while (!valid) {
            System.out.println("X position of your shot:");
            int x = scanner.nextInt();
            System.out.println("Y position of your shot:");
            int y = scanner.nextInt();

            field = new Field(x, y);
            valid = validateInput(field);
        }
        return field;
    }
}
