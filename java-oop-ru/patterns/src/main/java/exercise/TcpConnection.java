package exercise;
import exercise.connections.Connected;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

import java.util.List;
import java.util.ArrayList;

// BEGIN
public class TcpConnection {
    private Connection connection;
    private String ipAddress;
    private int port;
    public List<String> data = new ArrayList<>();

    public TcpConnection(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
        setConnection(new Disconnected(this));
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getCurrentState() {
        return connection.getCurrentState();
    }

    public void connect() {
        connection.connect();
    }

    public void disconnect() {
        connection.disconnect();
    }

    public void write(String data) {
        connection.write(data);
        this.data = Connected.getData();
    }
}
// END
