import newbank.client.ExampleClient;
import newbank.server.NewBankServer;

import java.io.IOException;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
        // Run the client and server together as an application
        var app = new Main();
        app.startServer(14002);
        app.startClient("localhost" , 14002);
    }
    public void startClient(String ip, int port) throws UnknownHostException, IOException, InterruptedException {
        new ExampleClient(ip,port).start();
    }
    public void startServer(int port) throws IOException {
        // starts a new NewBankServer thread on a specified port number
        new NewBankServer(port).start();
    }
}
