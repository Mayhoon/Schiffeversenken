package battleships.network;

import battleships.enums.HitType;
import battleships.ships.*;
import com.esotericsoftware.kryo.Kryo;

import java.util.ArrayList;

//Classes that the server and client have to serialize.
public class ClassRegisterer {
    public Kryo addClasses(Kryo kryo) {
        kryo.register(Data.class);
        kryo.register(Ship.class);
        kryo.register(ArrayList.class);
        kryo.register(Fleet.class);
        kryo.register(Carrier.class);
        kryo.register(Battleship.class);
        kryo.register(Cruiser.class);
        kryo.register(Field.class);
        kryo.register(Minesweeper.class);
        kryo.register(OilPlatform.class);
        kryo.register(HitType.class);
        return kryo;
    }
}
