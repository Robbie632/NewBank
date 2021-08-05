package server;

public class Loan {

    private double amount;
    private double rate;
    private int term;


    public Loan (double amount , double rate , int term){
        this.amount = amount;
        this.rate = rate;
        this.term = term;
    }


    public String toString() {
        return ("Total borrowed : £" + " " + amount + "\t" + " " + "rate: " + rate + "%" + "\n");
    }

}
