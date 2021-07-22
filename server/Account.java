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
	public void updateBalance(double amount) {
		currentBalance = currentBalance + amount;
	}

	public String getAccountName(){
		return accountName;
	}

	public String toString() {
		return (accountName + ": " + openingBalance);
	}

}
