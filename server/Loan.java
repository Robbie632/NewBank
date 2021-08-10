package server;

public class Loan {

    private LoanAgreement agreement;
    private double balance;

    public Loan (LoanAgreement loanAgreement){
        this.agreement = loanAgreement;
    }

    public String toString() {

        String lenderOrBorrower = "";
        if (this.balance < 0){
            lenderOrBorrower = "owed";
        } else {
            lenderOrBorrower = "borrowed";
        }


        return ("Total " + lenderOrBorrower + " : " +  "£" + " " + agreement.getLoanAmount() + "\t" + " " + "rate: " + agreement.getRate() + "%" + "\n");
    }

    public void decreaseBalance(double amount){
        balance = balance - amount;
    }

    public void increaseBalance(double amount){
        balance = balance + amount;
    }


    public static void issueLoan(LoanAgreement agreement){

        // Create a new loan based on agreement and set initial balance
        Loan lenderLoan = new Loan(agreement);
        lenderLoan.decreaseBalance(agreement.getLoanAmount());

        Loan borrowerLoan = new Loan(agreement);
        borrowerLoan.increaseBalance(agreement.getLoanAmount());

        agreement.getLender().addLoan(lenderLoan);
        agreement.getBorrower().addLoan(borrowerLoan);

    }

    public double getBalance() {
        return balance;
    }
}
