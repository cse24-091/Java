package hellofx.src;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private int customerId;
    private Branch branch; // branch association
    private List<Account> accounts;

    // Constructor requires a Branch object
    public Customer(String name, String address, String phoneNumber, String email, int customerId, Branch branch) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.customerId = customerId;
        this.branch = branch;
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

    public int getCustomerId() {
        return customerId;
    }

    public Branch getBranch() {
        return branch;
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerId + ", Name: " + name + ", Branch: " + branch.getBranchName();
    }
}
