package hellofx.src;
import java.util.ArrayList;
import java.util.List;

public class Branch {
    private String branchId;
    private String branchName;
    private String branchLocation;
    private List<Customer> customers;

    public Branch(String branchId, String branchName, String branchLocation) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.branchLocation = branchLocation;
        this.customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public String getBranchId() {
        return branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getBranchLocation() {
        return branchLocation;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    @Override
    public String toString() {
        return "Branch ID: " + branchId + ", Name: " + branchName + ", Location: " + branchLocation;
    }
}
