package battleships.network;

import battleships.console.Color;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class KryoServer extends NetworkEntity {
    private Server server;
    private Data opponent;

    public KryoServer() {
        server = new Server();
        opponent = new Data();
        Kryo kryo = new ClassRegisterer().addClasses(server.getKryo());
        kryo.setRegistrationRequired(false);
    }

    public void start() {
        try {
            server.start();
            server.bind(54555, 54777);
            server.addListener(new Listener() {
                @Override
                public void connected(Connection connection) {

                }

                @Override
                public void received(Connection connection, Object object) {
                    if (object instanceof Data) {
                        Color.purple("Data received");
                        opponent = (Data) object;
                    }
                }

                @Override
                public void disconnected(Connection connection) {
                    System.exit(0);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendTCP(Data data) {
        server.sendToAllTCP(data);
    }

    public Data opponent() {
        return opponent;
    }

    public void stop() {
        server.stop();
    }
}
