package battleships.console;

import battleships.enums.HitType;
import battleships.etc.Strings;
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
        if (init) {
            System.out.print(Strings.HEADER_SETUP);
            Color.printYellow(Strings.HEADER_SETUP_TAG);
            System.out.println(Strings.HEADER_SETUP);
        } else {
            System.out.println(Strings.HEADER_BATTLE);
        }
        Color.green(Strings.X_COORDINATES);

        for (int y = 0; y < Battlefield_height; y++) {
            Color.printCyan(y + "  ");
            for (int x = 0; x < Battlefield_width; x++) {
                if (init) {
                    if (fleet.initRenderHelper(new Field(x, y))) {
                        Color.printGreen("[X] ");
                    } else {
                        Color.printBlue("[ ] ");
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
                            Color.printBlue("[ ] ");
                            break;
                        default:
                            Color.printRed("[ ] ");
                    }
                }
            }
            //New Line
            System.out.println("");
        }
        System.out.println(Strings.FOOTER);
    }
}
