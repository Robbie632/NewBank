package server;
/**
 * This represents loans
 * 
 * @author UoB, MSc Computer Science, Cohort 6, Software Engineering 2 - Group 1
 */
public class Loan {
    // the loan amount
    private double amount;
    // the loan rate
    private double rate;
    // the loan terms
    private int term;

    /**
     * 
     * @param amount, the loan amount barrowed
     * @param rate, the loan rate
     * @param term, the loand terms
     */
    public Loan (double amount , double rate , int term){
        this.amount = amount;
        this.rate = rate;
        this.term = term;
    }

    //@return prints on console the barrowed loan details
    public String toString() {
        return ("Total borrowed : £" + " " + amount + "\t" + " " + "rate: " + rate + "%" + "\n");
    }

}
