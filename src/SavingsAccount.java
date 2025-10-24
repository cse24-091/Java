package hellofx.src;
public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accountId, double balance, double interestRate) {
        super(accountId, balance, "Savings");
        this.interestRate = interestRate;
    }

    public void applyMonthlyInterest() {
        double interest = balance * (interestRate / 100);
        balance += interest;
        transactions.add(new Transaction("Interest", interest));
        System.out.println("Monthly interest added to Savings Account.");
    }
}
