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

			if (bank.isCustomer(userName, password)){

				out.println("Checking Details...");
				// authenticate user and get customer ID token from bank for use in subsequent requests
				CustomerID customer = bank.checkLogInDetails(userName, password);
				// if the user is authenticated then get requests from the user and process them
				if(customer != null) {
					out.println("Log In Successful. What do you want to do?");
					out.println();
					out.println("SHOWMYACCOUNTS - to view a list of your accounts and their balance");
					out.println("NEWACCOUNT <Name> - to open a new account with the name you provide");
					out.println("MOVE <Amount> <From> <To> - to move an amount of money from one of your accounts to another");
					out.println("PAY <Person/Company> <Amount> - to pay an amount of money to a person or company of your choosing");
					out.println("END - To exit NewBank.");
					out.println();
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
				}
				else {
					out.println("Log In Failed");
					// ask for username and password again
					run();
				}

			} else {
				System.out.println("User not found - please create account");
				System.out.println("\n");
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
