package server;

import java.util.ArrayList;

//todo add method here to add password and to check password
public class Customer {
	
	private ArrayList<Account> accounts;
	private String password;
	
	public Customer() {
		accounts = new ArrayList<>();
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
		return true;
	}

	public void addAccount(Account account) {
		accounts.add(account);		
	}
}
