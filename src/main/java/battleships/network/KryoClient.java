package battleships.network;

import battleships.console.Color;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

public class KryoClient extends NetworkEntity {
    private Client client;
    private Data opponent;
    private Boolean ignoreFleet = false;
    private String ip;

    public KryoClient(String ip) {
        this.ip = ip;
        client = new Client();
        opponent = new Data();
        Kryo kryo = new ClassRegisterer().addClasses(client.getKryo());
        // kryo.setRegistrationRequired(false);
    }

    public void start() {
        try {
            client.start();
            client.connect(5000, ip, 54555, 54777);
            client.addListener(new Listener.ThreadedListener(new Listener() {

                @Override
                public void connected(Connection connection) {
                }

                @Override
                public void received(Connection connection, Object object) {
                    if (object instanceof Data) {
                        if (ignoreFleet) {
                            opponent.score = ((Data) object).score;
                            opponent.hasWon = ((Data) object).hasWon;
                            opponent.turn = ((Data) object).turn;
                        } else {
                            opponent = (Data) object;
                        }
                        ignoreFleet = true;
                    }
                }

                @Override
                public void disconnected(Connection connection) {
                    System.exit(0);
                }
            }));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendTCP(Data data) {
        client.sendTCP(data);
    }

    public Data opponent() {
        return opponent;
    }

    public void stop() {
        client.stop();
    }
}
