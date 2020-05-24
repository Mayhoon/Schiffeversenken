package battleships.console;

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
        System.out.println("-----------------Battleships-----------------");
        System.out.println(Color.GREEN + "    A   B   C   D   E   F   G   H   I   J" + Color.RESET);

        for (int y = 0; y < Battlefield_height; y++) {
            System.out.print(Color.CYAN + y + "  " + Color.RESET);
            for (int x = 0; x < Battlefield_width; x++) {

                Boolean occupied = fleet.isOccupied(new Field(x, y));
                if (occupied == true) {
                    System.out.print("[X] ");
                } else if (occupied == false) {
                    System.out.print("[ ] ");
                }
            }
            //New Line
            System.out.println("");
        }
    }


}
