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
	/*
	* Changes balance by parameter amount
	* @param amount, the amount to change account by,
	* note if positive will increase balance and if negative will decrease balance
	* */
	public void updateBalance(double amount) {
		currentBalance = currentBalance + amount;
	}

	public String getAccountName(){
		return accountName;
	}

	public String toString() {
		return (accountName + " opening balance : " + openingBalance + " currentBalance : " + currentBalance);
	}

}
