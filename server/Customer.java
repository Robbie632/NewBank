package server;

import java.util.ArrayList;

//todo add method here to add password and to check password
public class Customer {
	
	private ArrayList<Account> accounts;
	private ArrayList<Transaction> transactions;
	private String password;
	
	public Customer() {

		accounts = new ArrayList<>();
		transactions = new ArrayList<>();
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
	
	public String accountsToString() {
		String s = "";
		for(Account a : accounts) {
			s += a.toString();
		}
		return s;
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
			System.out.println("invalid amount entered");
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
		//if at least one account doesnt exist, return false
		if (!fromStatus | !toStatus) {
			return false;
		}

		//updateBalance
		for (Account a:accounts) {
			if (a.getAccountName().equals(from)){
				a.updateBalance(-numericAmount);

			}
			if (a.getAccountName().equals(to)){
				a.updateBalance(numericAmount);

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

	public void addAccount(Account account) {
		accounts.add(account);		
	}

	public void addTransaction(Transaction transaction) { transactions.add(transaction); };

	/**
	 * Method for printing stored transactions associated with the customer
	 */
	public void printTransactions() {

		for (Transaction tr: transactions) {
			System.out.println("transaction type was : ");
			System.out.println(tr.getTransactionType());
			System.out.println("Involved parties were: ");
			for (String party:tr.getInvolvedParties()) {
				System.out.println(party);
			}
			System.out.println("amount was: ");
			System.out.println(""+tr.getAmount());
		}
	}


}
