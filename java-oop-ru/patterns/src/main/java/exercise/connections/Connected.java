package exercise.connections;

import exercise.TcpConnection;

import java.util.ArrayList;
import java.util.List;

// BEGIN
public class Connected implements Connection {
    private TcpConnection connection;
    private static List<String> data = new ArrayList<>();

    public Connected(TcpConnection connection) {
        this.connection = connection;
    }

    public static List<String> getData() {
        return data;
    }

    @Override
    public String getCurrentState() {
        return "connected";
    }

    @Override
    public void connect() {
        System.out.println("Error! Connection already exist");
    }

    @Override
    public void disconnect() {
        this.connection.setConnection(new Disconnected(connection));
    }

    @Override
    public void write(String smth) {
        data.add(smth);
    }
}
// END
