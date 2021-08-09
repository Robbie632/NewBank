package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This extends the main thread and represents the customer input/output handler
 * 
 * @author UoB, MSc Computer Science, Cohort 6, Software Engineering 2 - Group 1
 */
public class NewBankClientHandler extends Thread{
	//the instance of the bank app
	private NewBank bank;
	//for reading text of the customer input
	private static BufferedReader in;
	//prints output text 
	private PrintWriter out;
	
	/**
	 * 
	 * @param s, coomunication endpoint between the server and the customer
	 * @throws IOException, the input and output interrruptions
	 */
	public NewBankClientHandler(Socket s) throws IOException {
		bank = NewBank.getBank();
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		out = new PrintWriter(s.getOutputStream(), true);
	}
	
	public void run() {
		// keep getting requests from the client and processing them
		try {
			// ask customer for user name
			out.println("Enter Username");
			String userName = "";
			while(userName.isEmpty()){
				userName = in.readLine();
				if(userName.isEmpty()){
					System.out.println("Please enter a username!");
				}
			}

			// ask customer for password
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
					out.println("SHOWMYLOANS - to view a list of your loans");
					out.println("NEWACCOUNT <Name> - to open a new account with the name you provide");
					out.println("MOVE <Amount> <From> <To> - to move an amount of money from one of your accounts to another");
					out.println("PAY <Person/Company> <Amount> - to pay an amount of money to a person or company of your choosing");
					out.println("SHOWMYTRANSACTIONS - to return information on all transactions done during the session");
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
						out.println("SHOWMYTRANSACTIONS - to return information on all transactions done during the session");
						out.println("END - To exit NewBank.");
						out.println();
				}
				// start accepting input by using while true
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
		//to close input/output session
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

		// asks user to confirm if the details they entered for either a MOVE or PAY command are correct before proceeding
		public static boolean confirmDetails(){
			System.out.println("Please confirm the above details are correct (Y/N)");
			try{
				String choice = in.readLine();
				switch(choice){
					case "Y":
					return true;
		
					case "N":
					return false;
		
					default :
					System.out.println("Wrong input, please enter 'Y' for yes or 'N' for no");
					return false;
				}
			//informs of invaild input 
			} catch(IOException e){
					System.out.println("Wrong input argument, exception: " + e);
				}
			return false;
				
		}

}
