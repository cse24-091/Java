import java.time.LocalDateTime;

public class Transaction {
    private String transactionType;
    private LocalDateTime transactionDate;
    private double amount;

    public Transaction(String transactionType, double amount) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return transactionType + " of BWP " + amount + " on " + transactionDate;
    }
}
