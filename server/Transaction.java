package server;

import java.util.ArrayList;

/**
 * Class for storing information about a transaction
 */
public class Transaction {

    private String transactionType;
    private ArrayList<String> involvedParties;
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
     * Getter method ofr transaction type
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
