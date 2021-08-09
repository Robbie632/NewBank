package server;

/**
 * Represents customer's bank account
 * 
 * @author UoB, MSc Computer Science, Cohort 6, Software Engineering 2 - Group 1
 */

public class Account {
	/** The account name */
	private String accountName;

	/** The openning balance */
	private double openingBalance;

	/** The current balance */
	private double currentBalance;

/**
 * Instantiates a new customer account with all parts supplied
 * @param accountName, the account name
 * @param openingBalance, the openning balance
 */
	public Account(String accountName, double openingBalance) {
		this.accountName = accountName;
		this.openingBalance = openingBalance;
		this.currentBalance = openingBalance;
	}

	// method called when an amount of money needs to be removed from account
	//@param amount, the amount of money to be removed from account
	public void minusBalance(double amount){
		currentBalance = currentBalance - amount;
	}

	// method called when an amount of money needs to be added to account
	//@param amount, the amount of money to be added to account
	public void addBalance(double amount){
		currentBalance = currentBalance + amount;
	}

	// method called to check if there is enough money in account 
	//@param amount, the amount of money to be tested
	//@return either true or false
	public boolean checkBalance(double amount){
		if (amount>currentBalance){
			//notifies customer of insufficient funds by printing onto console
			System.out.println("Insufficient funds");
			return false;
		}
		return true;
	}

	// method called to get the bank account name 
	// @return account name
	public String getAccountName(){
		return accountName;
	}

	// method called to convert objects to string to display data to console
	// @return account name, opening account balance, current account balance
	public String toString() {
		return (accountName + " opening balance : " + openingBalance + " currentBalance : " + currentBalance + "\n");
	}

}
