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

	public void addAccount(Account account) {
		accounts.add(account);		
	}
}
