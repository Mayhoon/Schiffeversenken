package battleships.network;

import battleships.enums.NetworkType;

public class Network {
    private NetworkEntity networkEntity;

    public Network() {
    }

    public void connect(NetworkType networkType) {
        if (networkType.equals(NetworkType.CLIENT)) {
            networkEntity = new KryoClient();
            networkEntity.start();
        } else if (networkType.equals(NetworkType.HOST)) {
            networkEntity = new KryoServer();
            networkEntity.start();
        }
    }

    public void sendData(Data data) {
        networkEntity.sendTCP(data);
    }

    public Data opponent() {
        return networkEntity.opponent();
    }
}
