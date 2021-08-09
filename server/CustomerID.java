package server;

/**
 * This represents customer's ID
 * 
 * @author UoB, MSc Computer Science, Cohort 6, Software Engineering 2 - Group 1
 */
public class CustomerID {
	//the customer key
	private String key;
	
	//Instantiates customer key
	public CustomerID(String key) {
		this.key = key;
	}
	
	/**
	 * Getter method that returns the key
	 * @return object with customer key
	 */
	public String getKey() {
		return key;
	}
}
