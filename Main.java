import client.*;
import server.*;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * This to run the client and server as one application
 * 
 * @author UoB, MSc Computer Science, Cohort 6, Software Engineering 2 - Group 1
 */
public class Main {
    /**
     * 
     * @param args, command line argument 
     * @throws UnknownHostException, host error handler
     * @throws IOException, input and output error handler
     * @throws InterruptedException, thread opearation error handler
     */
    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
        // Run the client and server together as an application
        var app = new Main();
        app.startServer(14002);
        app.startClient("localhost" , 14002);
    }

    /**
     * Initialise NewBank client
	 * @param ip, the internet protocol to indentify local network
	 * @param port the application port
     * @throws UnknownHostException
     * @throws IOException
     * @throws InterruptedException
     */
    public void startClient(String ip, int port) throws UnknownHostException, IOException, InterruptedException {
        new ExampleClient(ip,port).start();
    }

    /**
     * Initialise NewBank server
     * @param port
     * @throws IOException
     */
    public void startServer(int port) throws IOException {
        // starts a new NewBankServer thread on a specified port number
        new NewBankServer(port).start();
    }
}
