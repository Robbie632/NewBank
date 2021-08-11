package server;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.ArrayList;
/**
 * This represent the logic of NewBank app
 * 
 * @author UoB, MSc Computer Science, Cohort 6, Software Engineering 2 - Group 1
 */
public class NewBank {
	// the logger for tracing applications runtime errors
	private final static Logger LOGGER = Logger.getLogger(NewBank.class.getName());
	// the instance of the bank app
	private static final NewBank bank = new NewBank();
	// for storing and linking the customers details
	private HashMap<String,Customer> customers;
	
	private NewBank() {
		//all levels of message up to and including INFO will be written
		try{
			//creates a new log file for log records 
			FileHandler fh = new FileHandler("MyLogFile.log");
			//creates specific format for logging records
			SimpleFormatter formatter = new SimpleFormatter();
			//assignes format to the created log file
			fh.setFormatter(formatter);
			//register log file for logging messages
			LOGGER.addHandler(fh);
		} catch(IOException e) {
			//inform user by printing onto console if there was a problem initialising log file
			System.out.println("Error initialising logfile");
		}
		//setting confuguration for log level
		LOGGER.setLevel(Level.INFO);
		//creates empty hashmap
		customers = new HashMap<>();
		//initilizes method to test app and its features
		addTestData();
	}
	/**
	 * The method has a hardcoded set of users that it checks when you log in. 
	 * This is to test the app
	 */
	private void addTestData() {
		//first customer
		Customer bhagy = new Customer();
		//set password
		bhagy.setPassword("1");
		//add money onto the main account
		bhagy.addAccount(new Account("Main", 1000.0));
		//add money to the Savings account
		bhagy.addAccount(new Account("Savings", 1000.0));

		customers.put("b", bhagy);
		
		//second customer
		Customer christina = new Customer();
		christina.setPassword("2");
		christina.addAccount(new Account("Savings", 1500.0));
		christina.addAccount(new Account("Main", 100.0));
//		christina.addLoan(new Loan(75 , 25.5 , 24));
		customers.put("c", christina);
		
		//third customer
		Customer john = new Customer();
		john.setPassword("3");
		john.addAccount(new Account("Main", 250.0));
		john.addAccount(new Account("Savings", 250.0));
//		john.addLoan(new Loan(150, 10 , 24));
		customers.put("j", john);

// Create test loans

		Loan.issueLoan(new LoanAgreement(100 , 25.5 , 24 , bhagy, christina));


		/* Create  a new user here by uncommenting the below and setting values  */

//		Customer john = new Customer();
//		john.addAccount(new Account("Checking", 250.0));
//		customers.put("New user", john);

	}
	/**
	 * 
	 * @return bank, initialising the NewBank app
	 */
	public static NewBank getBank() {
		return bank;
	}

	/*
	* checks if username exists and if password correct
	* @param userName, username to check
	* @param password, password to check
	* @return object that contains the log in status, any messages and
	* the information returned from a successful log in
	* */
	public synchronized Output checkLogInDetails(String userName, String password) {

		Output out = new Output();

		if(customers.containsKey(userName)) {
			Customer relevantCustomer = customers.get(userName);
			if (relevantCustomer.checkPassword(password)) {
				out.setStatus(true);
				out.addMessage("Log In Successful. What do you want to do?");
				out.setInformation(new CustomerID(userName));
				return out;
			} else {

				LOGGER.warning("Password entered is incorrect.");
				out.setStatus(false);
				out.addMessage("Username entered is valid.");
				out.addMessage("Password entered is incorrect.");
				return out;
			}
		} else {
			out.setStatus(false);
			out.addMessage("Incorrect username inputted");
		}
		return out;
	}

	public synchronized boolean isCustomer(String userName , String password){
		if(customers.containsKey(userName)){
			return true;
		} else {
			return false;
		}
	}

	/*
	*  pay other person or company in NewBank a certain amount of money
	* @param customer, customer who wishes to make the payment
	* @param to, customer to which the amount of money is to be paid to
	* @param amount, amount which will be paid
	* */
	public boolean pay(CustomerID customer, String to, String amount) {

		Double numericAmount;
		CustomerID recipient = new CustomerID(to);
		Customer current = customers.get(customer.getKey());

		//try to convert amount to double
		try {
			numericAmount = Double.parseDouble(amount);
		} catch(NumberFormatException e) {
			LOGGER.warning("Invalid amount entered");
			return false;
		}

		// checks if the customer being paid is the same as the customer instantiating the payment
		if(customer.getKey().equals(recipient.getKey())){
			return false;
		}

		//Provided there is sufficient funds, removes the amount of money chosen by the customer from the first  account listed under their name and adds it to the the first account  listed under the name of the customer receiving the money
		if(customers.get(customer.getKey()).getAccount().checkBalance(numericAmount) == true){
			customers.get(customer.getKey()).getAccount().minusBalance(numericAmount);
			customers.get(recipient.getKey()).getAccount().addBalance(numericAmount);
		}
			else {
				return false;
			}
		
		//store details of transaction
		ArrayList<String> parties = new ArrayList<>();
		parties.add(customer.getKey());
		parties.add(to);
		current.addTransaction(new Transaction("payment", parties, numericAmount));
		// if code reaches here payment has been successful
		return true;
	}

	/*
	* commands from the NewBank customer are processed in this method
	* @param customer, the id of the customer processing the request
	* @param request, the input at the command line from the user
	* @return object depending on the customer command choice
	* */
	public synchronized String processRequest(CustomerID customer, String request) {
		boolean status=false;
		if(customers.containsKey(customer.getKey())) {
			String[] request_params = request.split("\\s+");

			switch(request_params[0]) {

			case "SHOWMYACCOUNTS" : return showMyAccounts(customer);


			//add new account (only works if account does not already exist for customer)
			case "NEWACCOUNT" :
			Customer current = customers.get(customer.getKey());
				try{
					status = customers.get(customer.getKey()).newAccount(current, request_params[1]);
				//catch exception if incorrect number of parameters are inputted	
				} catch(ArrayIndexOutOfBoundsException e) {
					LOGGER.severe("Wrong number of input arguments, exception: " + e);
					status = false;
			} 
			break;
			
			//if user asks to see transactions
			case "SHOWMYTRANSACTIONS" :
				customers.get(customer.getKey()).printTransactions();
				status = true;
			break;


			//if user asks to see loans
			case "SHOWMYLOANS" :
				customers.get(customer.getKey()).printLoans();
				status = true;

			break;

			//attempt to move money between accounts
			case "MOVE" :
			if(NewBankClientHandler.confirmDetails() == true){
				try {
					status = customers.get(customer.getKey()).moveMoney(request_params[1], request_params[2], request_params[3]);
				//catch exception if incorrect number of parameters are inputted
				} catch(ArrayIndexOutOfBoundsException e) {
					LOGGER.severe("Wrong number of input arguments, exception: " + e);
					status = false;
				}
			}
			break;

			//attempt to pay another customer a certain amount of money
			case "PAY" :
			if(NewBankClientHandler.confirmDetails() == true){
			try {
				status = pay(customer, request_params[1], request_params[2]);
			//catch exception if incorrect number of parameters are inputted
			} catch(ArrayIndexOutOfBoundsException e) {
				LOGGER.severe("Wrong number of input arguments, exception: " + e);
				status = false;
				}
			}
				break;
			//terminates bank application session
			case "END" :
				System.out.println("****  Thank you for using NewBank  ****");
				System.exit(0);
				break;
			//the log file configuration messages
			default :
				LOGGER.warning("No valid operation input found");
				return "FAIL";

			}
		}
		//the log file configuration messages
		if (status){
			LOGGER.info("Command processed correctly");
			return "SUCCESS";
		} else {
			LOGGER.warning("Processed command failed");
			return "FAIL";
		}
	}
	
	/**
	 * 
	 * @param customer, creates 
	 * @return prints out onto console the customer account details
	 */
	private String showMyAccounts(CustomerID customer) {
		return (customers.get(customer.getKey())).accountsToString();
	}

}
