package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NewBankClientHandler extends Thread{
	
	private NewBank bank;
	private BufferedReader in;
	private PrintWriter out;
	
	
	public NewBankClientHandler(Socket s) throws IOException {
		bank = NewBank.getBank();
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		out = new PrintWriter(s.getOutputStream(), true);
	}
	
	public void run() {
		// keep getting requests from the client and processing them
		try {
			// ask for user name
			out.println("Enter Username");
			String userName = "";
			while(userName.isEmpty()){
				userName = in.readLine();
				if(userName.isEmpty()){
					System.out.println("Please enter a username!");
				}
			}

			// ask for password
			out.println("Enter Password");
			String password = "";
			while(password.isEmpty()){
				password = in.readLine();
				if(password.isEmpty()){
					System.out.println("Please enter a password");
				}
			}

			out.println("Checking Details...");
			// authenticate user and get customer ID token from bank for use in subsequent requests
			Output loginFeedback = bank.checkLogInDetails(userName, password);
			//check for successful log in
			if(loginFeedback.getStatus()) {
				//extract customer ID
				Object customerObject = loginFeedback.getInformation();
				CustomerID customer = (CustomerID) customerObject;
				//loop through messages and print
				for (String message:loginFeedback.getMessages()) {
					System.out.println(message + "\n");
				}
					out.println();
					out.println("SHOWMYACCOUNTS - to view a list of your accounts and their balance");
					out.println("NEWACCOUNT <Name> - to open a new account with the name you provide");
					out.println("MOVE <Amount> <From> <To> - to move an amount of money from one of your accounts to another");
					out.println("PAY <Person/Company> <Amount> - to pay an amount of money to a person or company of your choosing");
					out.println("END - To exit NewBank.");
					out.println();
				//listen for commands from user
				while(true) {
					String request = in.readLine();
					System.out.println("Request from " + customer.getKey());
					String responce = bank.processRequest(customer, request);
					out.println(responce);
					out.println("What else would you like to do?");
						out.println();
						out.println("SHOWMYACCOUNTS - To view a list of your accounts and their balance.");
						out.println("NEWACCOUNT <Name> - To open a new account with the name you provide.");
						out.println("MOVE <Amount> <From> <To> - To move an amount of money from one of your accounts to another.");
						out.println("PAY <Person/Company> <Amount> - To pay an amount of money to a person or company of your choosing.");
						out.println("END - To exit NewBank.");
						out.println();
				}
				// start accepting input by using while true{ blah blah
			}
			//login has been unsuccessful
			else {
				//print messages for why login was unsuccessful
				for (String message:loginFeedback.getMessages()) {
					System.out.println(message + "\n");
				}
				run();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
	}

}
