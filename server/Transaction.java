package server;

import java.util.ArrayList;

/**
 * This represent customers transactions
 * 
 * @author UoB, MSc Computer Science, Cohort 6, Software Engineering 2 - Group 1
 */
public class Transaction {
    //the transaction type
    private String transactionType;
    //the involved parties
    private ArrayList<String> involvedParties;
    //the transaction amount
    private Double amount;

    /**
     * Constructor, used to input information about the transaction
     * @param transactionType type of transaction, for example move, loan etc
     * @param involvedParties who was involved in the transaction
     * @param amount amount associated with the transaction
     */
    public Transaction(String transactionType,
                       ArrayList<String> involvedParties,
                       Double amount){
        this.transactionType = transactionType;
        this.involvedParties = involvedParties;
        this.amount = amount;
    }

    /**
     * Getter method for transaction type
     * @return
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Getter method for involve parties
     * @return
     */
    public ArrayList<String> getInvolvedParties() {
        return this.involvedParties;
    }

    /**
     * Getter method for amount
     * @return
     */
    public Double getAmount() {
        return this.amount;
    }
}
