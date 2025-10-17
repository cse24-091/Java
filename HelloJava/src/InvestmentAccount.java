public class InvestmentAccount extends Account {
    private double interestRate;
    private static final double MINIMUM_DEPOSIT = 500.0;

    public InvestmentAccount(String accountId, double initialDeposit, double interestRate) {
        super(accountId, initialDeposit, "Investment");

        if (initialDeposit < MINIMUM_DEPOSIT) {
            throw new IllegalArgumentException("Minimum deposit of BWP 500 not met. Account cannot be created.");
        }

        this.interestRate = interestRate;
    }

    public void investMonthlyInterest() {
        double interest = balance * (interestRate / 100);
        balance += interest;
        transactions.add(new Transaction("Interest", interest));
        System.out.println("Monthly interest added to Investment Account.");
    }
}
