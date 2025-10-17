import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private int customerId;
    private List<Account> accounts;

    public Customer(String name, String address, String phoneNumber, String email, int customerId) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.customerId = customerId;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void viewTransactionHistory() {
        for (Account account : accounts) {
            System.out.println("\nTransaction history for " + account.getAccountType() + " Account:");
            for (Transaction t : account.getTransactions()) {
                System.out.println(t);
            }
        }
    }

    public String getName() {
        return name;
    }
}
