package hellofx;

import java.io.*;
import java.util.*;

public class DataManager {
    private static final String FILE_NAME = "accounts.txt";

    // Save login details and account info
    public static void saveAccount(String username, String password, String accountType,
                                   String branch, String customerId, double balance) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(username + "," + password + "," + accountType + "," + branch + "," + customerId + "," + balance);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load all accounts (optional, for later use)
    public static List<String[]> loadAccounts() {
        List<String[]> accounts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                accounts.add(line.split(","));
            }
        } catch (IOException e) {
            // file might not exist yet
        }
        return accounts;
    }
}
