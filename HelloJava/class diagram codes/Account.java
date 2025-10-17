import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    protected String accountId;
    protected double balance;
    protected String accountType;
    protected List<Transaction> transactions = new ArrayList<>();

    public Account(String accountId, double balance, String accountType) {
        this.accountId = accountId;
        this.balance = balance;
        this.accountType = accountType;
    }

    public boolean depositAmount(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add(new Transaction("Deposit", amount));
            return true;
        }
        return false;
    }

    public boolean withdrawAmount(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactions.add(new Transaction("Withdrawal", amount));
            return true;
        } else {
            System.out.println("Insufficient balance.");
            return false;
        }
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public String getAccountType() {
        return accountType;
    }
}
