import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    protected String accountId;
    protected double balance;
    protected String accountType;
    protected List<Transaction> transactions;

    public Account(String accountId, double balance, String accountType) {
        this.accountId = accountId;
        this.balance = balance;
        this.accountType = accountType;
        this.transactions = new ArrayList<>();
    }

    // Standard deposit method
    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("Deposit", amount));
    }

    // Standard withdraw method
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactions.add(new Transaction("Withdrawal", amount));
        } else {
            System.out.println("Insufficient funds for withdrawal in " + accountType + " account!");
        }
    }

    public String getAccountType() {
        return accountType;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return accountType + " Account ID: " + accountId + ", Balance: BWP " + balance;
    }
}
