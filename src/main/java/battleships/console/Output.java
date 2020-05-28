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

    public void render(Fleet fleet, boolean init) {
        System.out.println("----------------Battleships----------------");
        Color.green("    0   1   2   3   4   5   6   7   8   9");

        for (int y = 0; y < Battlefield_height; y++) {
            Color.printCyan(y + "  ");
            for (int x = 0; x < Battlefield_width; x++) {
                if (init) {
                    if (fleet.initRenderHelper(new Field(x, y))) {
                        Color.printGreen("[X] ");
                    } else  {
                        Color.printBlue("[҈] ");
                    }
                } else {
                    HitType hitType = fleet.fleetRenderHelper(new Field(x, y));
                    switch (hitType) {
                        case MISS:
                            Color.printYellow("[-] ");
                            break;
                        case SUCCESS:
                            Color.printRed("[X] ");
                            break;
                        case NOT_SHOT:
                            Color.printBlue("[҈] ");
                            break;
                        default:
                            Color.printRed("[ ] ");
                    }
                }
            }
            //New Line
            System.out.println("");
        }
        System.out.println("-------------------------------------------");
    }


}
