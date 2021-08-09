package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This extends the main thread to handle front end customer input
 * 
 * @author UoB, MSc Computer Science faculty
 */
public class ExampleClient extends Thread{
	//endpoint connection between customer machine and the client thread
	private Socket server;
	//prints output text
	private PrintWriter bankServerOut;
	//for reading text of the customer input	
	private BufferedReader userInput;
	//the bank server response thread
	private Thread bankServerResponceThread;
	
	/**
	 * Initilise Socket connection, Bufferreader and PrintWriter 
	 * @param ip, internet protocol to indentify local network
	 * @param port the application port
	 * @throws UnknownHostException, host error handler
	 * @throws IOException, input and output error handler
	 */
	public ExampleClient(String ip, int port) throws UnknownHostException, IOException {
		server = new Socket(ip,port);
		userInput = new BufferedReader(new InputStreamReader(System.in)); 
		bankServerOut = new PrintWriter(server.getOutputStream(), true); 
		
		//Initilise the bank server responce thread
		bankServerResponceThread = new Thread() {
			private BufferedReader bankServerIn = new BufferedReader(new InputStreamReader(server.getInputStream())); 
			public void run() {
				try {
					while(true) {
						String responce = bankServerIn.readLine();
						System.out.println(responce);
					}
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
			}
		};
		//begin execution of the thread
		bankServerResponceThread.start();
	}
	
	//
	public void run() {
		while(true) {
			try {
				while(true) {
					String command = userInput.readLine();
					bankServerOut.println(command);
				}				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * Executes client application
     * @param args, command line argument 
     * @throws UnknownHostException, host error handler
     * @throws IOException, input and output error handler
     * @throws InterruptedException, thread opearation error handler
	 */
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		new ExampleClient("localhost",14002).start();
	}
}
