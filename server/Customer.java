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

	public boolean moveMoney(String amount, String from , String to) {
		//todo implement method

		//try to convert amount to double, if doesnt work return false
		//test from and to to see actual in accounts, if not return false
		//loop through accounts and chek names against input
		boolean fromStatus=false;
		boolean toStatus=false;
		Double numericAmount;

		//if all conditions above don't return false move the money
		try {
			numericAmount = Double.parseDouble(amount);
		} catch(NumberFormatException e) {
			System.out.println("invalid amount entered");
			return false;
		}
		if (from.equals(to)){
			return false;
		}

		for (Account a: accounts) {
			if (a.getAccountName().equals(from)) {
				fromStatus=true;
			}
			if (a.getAccountName().equals(to)) {
				toStatus=true;
			}
		}

		if (!fromStatus | !toStatus) {
			return false;
		}

		//updateBalance
		for (Account a:accounts) {
			if (a.equals(from)){
				a.updateBalance(-numericAmount);
			}
			if (a.equals(to)){
				a.updateBalance(numericAmount);
			}
		}

		return false;
	}

	public void addAccount(Account account) {
		accounts.add(account);		
	}
}
