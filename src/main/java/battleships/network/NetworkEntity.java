package battleships.network;

public abstract class NetworkEntity {
    public abstract void start();

    public abstract void stop();

    public abstract Data opponent();

    public abstract void sendTCP(Data data);
}
