package server;

public class Account {
	
	private String accountName;
	private double openingBalance;
	private double currentBalance;

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
	public boolean checkBalance(double amount){
		if (amount>currentBalance){
			System.out.println("Insufficient funds");
			return false;
		}
		return true;
	}

	public String getAccountName(){
		return accountName;
	}

	public String toString() {
		return (accountName + " opening balance : " + openingBalance + " currentBalance : " + currentBalance + "\n");
	}

}
