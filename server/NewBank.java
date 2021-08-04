package server;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.ArrayList;

public class NewBank {

	private final static Logger LOGGER = Logger.getLogger(NewBank.class.getName());
	private static final NewBank bank = new NewBank();
	private HashMap<String,Customer> customers;
	
	private NewBank() {
		//all levels of message up to and including INFO will be written
		try{
			FileHandler fh = new FileHandler("MyLogFile.log");
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			LOGGER.addHandler(fh);
		} catch(IOException e) {
			System.out.println("Error initialising logfile");
		}

		LOGGER.setLevel(Level.INFO);

		customers = new HashMap<>();
		addTestData();
	}
	
	private void addTestData() {
		Customer bhagy = new Customer();
		//set password
		bhagy.setPassword("1");
		bhagy.addAccount(new Account("Main", 1000.0));
		bhagy.addAccount(new Account("Savings", 1000.0));
		customers.put("b", bhagy);
		
		Customer christina = new Customer();
		//set password
		christina.setPassword("2");
		christina.addAccount(new Account("Savings", 1500.0));
		christina.addAccount(new Account("Main", 100.0));
		customers.put("c", christina);
		
		Customer john = new Customer();
		//set password
		john.setPassword("3");
		john.addAccount(new Account("Main", 250.0));
		john.addAccount(new Account("Savings", 250.0));
		customers.put("j", john);

		/* Create  a new user here by uncommenting the below and setting values  */

//		Customer john = new Customer();
//		john.addAccount(new Account("Checking", 250.0));
//		customers.put("New user", john);

	}
	
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

			//attempt to move money between accounts
			case "MOVE" :
				try {
					status = customers.get(customer.getKey()).moveMoney(request_params[1], request_params[2], request_params[3]);
				//catch exception if incorrect number of parameters are inputted
				} catch(ArrayIndexOutOfBoundsException e) {
					LOGGER.severe("Wrong number of input arguments, exception: " + e);
					status = false;
			}
			break;

			//attempt to pay another customer a certain amount of money
			case "PAY" :
			try {
				status = pay(customer, request_params[1], request_params[2]);
			//catch exception if incorrect number of parameters are inputted
			} catch(ArrayIndexOutOfBoundsException e) {
				LOGGER.severe("Wrong number of input arguments, exception: " + e);
				status = false;
			}
				break;

			case "END" :
				System.out.println("****  Thank you for using NewBank  ****");
				System.exit(0);
				break;

			default :
				LOGGER.warning("No valid operation input found");
				return "FAIL";

			}
		}
		if (status){
			LOGGER.info("Command processed correctly");
			return "SUCCESS";
		} else {
			LOGGER.warning("Processed command failed");
			return "FAIL";
		}
	}
	
	private String showMyAccounts(CustomerID customer) {
		return (customers.get(customer.getKey())).accountsToString();
	}

}
