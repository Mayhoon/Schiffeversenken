package battleships.console;

import battleships.enums.HitType;
import battleships.ships.Field;
import battleships.ships.Fleet;

public class Output {
    private int Battlefield_width;
    private int Battlefield_height;

    public Output() {
        Battlefield_height = 10;
        Battlefield_width = 10;
    }

    public void render(Fleet fleet) {
        System.out.println("----------------Battleships----------------");
        Color.green("    0   1   2   3   4   5   6   7   8   9");

        for (int y = 0; y < Battlefield_height; y++) {
            Color.printCyan(y + "  ");
            for (int x = 0; x < Battlefield_width; x++) {

                HitType hitType = fleet.fleetRenderHelper(new Field(x, y));
                switch (hitType) {
                    case MISS:
                        Color.printCyan("[-] ");
                        break;
                    case SUCCESS:
                        Color.printRed("[X] ");
                        break;
                    case NOT_SHOT:
                        Color.printBlue("[ ] ");
                        break;//BLUE
                    default:
                }

//                Boolean occupied = fleet.isOccupied(new Field(x, y));
//                if (occupied == true) {
//                    System.out.print("[X] ");
//                } else if (occupied == false) {
//                    System.out.print("[ ] ");
//                }
            }
            //New Line
            System.out.println("");
        }
        System.out.println("-------------------------------------------");
    }


}
