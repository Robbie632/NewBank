package server;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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
			System.out.println("error initialising logfile");
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
	* @return either null or the customer id
	* */
	public synchronized CustomerID checkLogInDetails(String userName, String password) {

		if(customers.containsKey(userName)) {
			Customer relevantCustomer = customers.get(userName);
			if (relevantCustomer.checkPassword(password)) {
				return new CustomerID(userName);
			} else {
				LOGGER.warning("Password entered is incorrect.");
				return null;
			}
		}
		return null;
	}

	public synchronized boolean isCustomer(String userName , String password){
		if(customers.containsKey(userName)){
			return true;
		} else {
			return false;
		}
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
					status = false;
			} 
			break;

			//attempt to move money between accounts
			case "MOVE" :
				try {
					status = customers.get(customer.getKey()).moveMoney(request_params[1], request_params[2], request_params[3]);
				//catch exception if incorrect number of parameters are inputted
				} catch(ArrayIndexOutOfBoundsException e) {
					LOGGER.severe("wrong number of input arguments, exception: " + e);
					status = false;
			}

			break;

			default :
				LOGGER.warning("no valid operation input found");
				return "FAIL";
			}
		}
		if (status){
			LOGGER.info("command processed correctly");
			return "SUCCESS";
		} else {
			LOGGER.warning("processed command failed");
			return "FAIL";
		}
	}
	
	private String showMyAccounts(CustomerID customer) {
		return (customers.get(customer.getKey())).accountsToString();
	}

}
