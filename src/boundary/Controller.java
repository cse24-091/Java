package boundary;

public class Controller {

    public boolean authenticate(String username, String password) {
        return username.equals("admin") && password.equals("1234");
    }

    public Account getAccountDetails(String username) {
        // Normally this would come from a database
        return new Account("ACC001", "Savings", 2500.75, "Admin User");
    }
}
