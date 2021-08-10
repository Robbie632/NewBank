package server;

public class LoanAgreement {

    private static int loanId;
    private final double loanAmount;
    private final double rate;
    private final int term;
    private final Customer borrower;
    private final Customer lender;

    public LoanAgreement(double amount , double rate ,int  term , Customer lender , Customer borrower){
        this.loanAmount = amount;
        this.rate = rate;
        this.term = term;
        this.borrower = borrower;
        this.lender = lender;
    }


    /*  Getters and setters */
    public static int getLoanId() {
        return loanId;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public double getRate() {
        return rate;
    }

    public int getTerm() {
        return term;
    }

    public Customer getLender() {
        return lender;
    }

    public Customer getBorrower() {
        return borrower;
    }


}
