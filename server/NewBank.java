package server;

import java.util.HashMap;

public class NewBank {
	
	private static final NewBank bank = new NewBank();
	private HashMap<String,Customer> customers;
	
	private NewBank() {
		customers = new HashMap<>();
		addTestData();
	}
	
	private void addTestData() {
		Customer bhagy = new Customer();
		//set password
		bhagy.setPassword("randomPassword1");
		bhagy.addAccount(new Account("Main", 1000.0));
		customers.put("Bhagy", bhagy);
		
		Customer christina = new Customer();
		//set password
		christina.setPassword("randomPassword2");
		christina.addAccount(new Account("Savings", 1500.0));
		customers.put("Christina", christina);
		
		Customer john = new Customer();
		//set password
		john.setPassword("randomPassword3");
		john.addAccount(new Account("Checking", 250.0));
		customers.put("John", john);

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
				System.out.println("incorrect password");
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

	// commands from the NewBank customer are processed in this method
	public synchronized String processRequest(CustomerID customer, String request) {
		if(customers.containsKey(customer.getKey())) {
			//check for MOVE command here
			String[] request_params = request.split("\\s+");
			request = request_params[0];
			switch(request) {
			case "SHOWMYACCOUNTS" : return showMyAccounts(customer);
			case "MOVE" : customers.get(customer).moveMoney(request_params[1], request_params[2], request_params[3]);
			default : return "FAIL";
			}
		}
		return "FAIL";
	}
	
	private String showMyAccounts(CustomerID customer) {
		return (customers.get(customer.getKey())).accountsToString();
	}

}
