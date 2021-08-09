package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/*
* This represent customer
*
* @author UoB, MSc Computer Science, Cohort 6, Software Engineering 2 - Group 1
*/
public class Customer {
	// the logger for tracing app errors
	private final static Logger LOGGER = Logger.getLogger(NewBank.class.getName());
	// the accounts to hold account data
	private ArrayList<Account> accounts;
	// the transactions to hold transaction data
	private ArrayList<Transaction> transactions;
	// the loans to hold loan data
	private ArrayList<Loan> loans = new ArrayList<Loan>();
	// the password
	private String password;
	
	/**
	 * Instantiates new customer and a log file that helps developers to tace out errors
	 */
	public Customer() {
		accounts = new ArrayList<>();
		transactions = new ArrayList<>();

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
		
	}

	/*
	* sets password
	* @param password sets password for the instance of customer
	* */
	public void setPassword(String password) {
		this.password = password;
	}

	/*
	* Checks if password is correct
	* @param passwordInput, password entered by user
	* @return boolean indicating if password correct or not
	*/
	public boolean checkPassword(String passwordInput) {
		if (passwordInput.equals(password)) {
			return true;
		} else {
			return false;
		}
	}

	// @return accounts details in a string format
	public String accountsToString() {
		String s = "";
		for(Account a : accounts) {
			s += a.toString();
		}
		return s;
	}

	// converts loan objects to string for printing
	public void printLoans(){

		for (var loan : loans){
			System.out.println(loan.toString());
		}
	}

	// Add Loans
	public void addLoan(Loan loan){
		loans.add(loan);
	}

	/*
	* add new account for customer
	* @param current, customer who is requesting new account
	* @param name, name of new account
	* */
	public boolean newAccount(Customer current, String name){
		boolean accountExists = false;

		//checks if the account exists
		for(Account a: accounts){
			if(a.getAccountName().equals(name)){
				accountExists = true;
			}
		}

		//if account exists return false
		if(accountExists){
			LOGGER.warning("Account already exists.");
			return false;
		}

		//add new account for current customer
		current.addAccount(new Account(name, 0.0));
	
		//if code reaches here new account has been added
		return true;
	}

	/*
	* move money between accounts
	* @param amount, amount to be moved
	* @param from, account name money is to be moved from
	* @param to, account name money is to be moved to
	* */
	public boolean moveMoney(String amount, String from , String to) {

		boolean fromStatus=false;
		boolean toStatus=false;
		Double numericAmount;

		//try to convert amount to double
		try {
			numericAmount = Double.parseDouble(amount);
		} catch(NumberFormatException e) {
			LOGGER.warning("invalid amount entered");
			return false;
		}
		// check if the savings from and to are the same
		if (from.equals(to)){
			return false;
		}
		//check the accounts exist
		for (Account a: accounts) {
			if (a.getAccountName().equals(from)) {
				fromStatus=true;
			}
			if (a.getAccountName().equals(to)) {
				toStatus=true;
			}
		}
		//if at least one account doesn't exist, return false
		if (!fromStatus | !toStatus) {
			LOGGER.warning("At least one account in MOVE command doesn't exist");
			return false;
		}

		//checks if the 'from' account has sufficient funds
		//if there are sufficient money is removed from the 'from' account and added to the 'to' account
		for (Account a: accounts){
			if(a.getAccountName().equals(from)){
				if(a.checkBalance(numericAmount) == true){
				a.minusBalance(numericAmount);
				}
				else{
				return false;
				}
			}
		
			if(a.getAccountName().equals(from)){
				if(a.checkBalance(numericAmount) == true){
					if(a.getAccountName().equals(to)){
						a.addBalance(numericAmount);
					}
				}
			}

}
		// if code reaches here money transfer would have been successful
		//store details of transaction
		ArrayList<String> parties = new ArrayList<>();
		parties.add(from);
		parties.add(to);
		addTransaction(new Transaction("move", parties, numericAmount));
		return true;
	}

	/**
	 * 
	 * @param account, adds new account
	 */
	public void addAccount(Account account) {
		accounts.add(account);		
	}

	/**
	 * 
	 * @return first account listed for the customer
	 */
	public Account getAccount(){
		return accounts.get(0);
	}

	public void addTransaction(Transaction transaction) { transactions.add(transaction); };

	/**
	 * Method for printing stored transactions associated with the customer
	 */
	public void printTransactions() {

		for (Transaction tr: transactions) {
			System.out.println("Transaction type was : ");
			System.out.println(tr.getTransactionType());
			System.out.println("Involved parties were: ");
			for (String party:tr.getInvolvedParties()) {
				System.out.println(party);
			}
			System.out.println("Amount was: ");
			System.out.println(""+tr.getAmount());
			System.out.println();
		}
	}
}
