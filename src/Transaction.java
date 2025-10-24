package hellofx.src;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private String type;
    private double amount;
    private Date date;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.date = new Date(); // current date
    }

    @Override
    public String toString() {
        return sdf.format(date) + " - " + type + ": BWP " + amount;
    }
}
