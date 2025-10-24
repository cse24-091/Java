package hellofx.model;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataManager {
    private static final String ACCOUNT_FILE = "accounts.txt";
    private static final String CUSTOMER_FILE = "customers.txt";
    private static final String TRANSACTION_FILE = "transactions.txt";

    // 🔹 Save a new customer and account
    public static void saveAccount(String customerName, String password, String customerType, String accountType,
                                   String branch, double balance, String taxId,
                                   String sourceOfIncome, String signatory, String idNumber) {

        String accountNumber = "AC" + (1000 + new Random().nextInt(9000));
        String customerID = "CU" + (1000 + new Random().nextInt(9000));
        String createdDate = getFormattedDateTime();

        // 🧾 Save to customers.txt
        try (PrintWriter out = new PrintWriter(new FileWriter(CUSTOMER_FILE, true))) {
            String line = String.join(",", Arrays.asList(
                    customerID,              // 0
                    customerName,            // 1
                    password,                // 2
                    customerType,            // 3
                    taxId != null ? taxId : "",         // 4
                    sourceOfIncome != null ? sourceOfIncome : "", // 5
                    signatory != null ? signatory : "", // 6
                    idNumber != null ? idNumber : "",   // 7
                    createdDate,             // 8
                    accountNumber            // 9 (link to account)
            ));
            out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 🏦 Save to accounts.txt
        try (PrintWriter out = new PrintWriter(new FileWriter(ACCOUNT_FILE, true))) {
            String line = String.join(",", Arrays.asList(
                    accountNumber,           // 0
                    String.valueOf(balance), // 1
                    accountType,             // 2
                    branch,                  // 3
                    customerID               // 4
            ));
            out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 🔹 Load all customers
    public static List<String[]> loadCustomers() {
        List<String[]> customers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CUSTOMER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                customers.add(line.split(",", -1));
            }
        } catch (IOException e) {
            // ignore if file missing
        }
        return customers;
    }

    // 🔹 Load all accounts
    public static List<String[]> loadAccounts() {
        List<String[]> accounts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ACCOUNT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                accounts.add(line.split(",", -1));
            }
        } catch (IOException e) {
            // ignore if file missing
        }
        return accounts;
    }

    // 🔹 Find customer by username/password and get their linked account
    public static String[] getCustomerAccountByCredentials(String username, String password) {
        String[] customer = null;
        try (BufferedReader br = new BufferedReader(new FileReader(CUSTOMER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length >= 3 && parts[1].equals(username) && parts[2].equals(password)) {
                    customer = parts;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (customer == null) return null;

        String linkedAccountNumber = customer.length > 9 ? customer[9] : "";
        for (String[] acc : loadAccounts()) {
            if (acc[0].equals(linkedAccountNumber)) {
                return acc; // return the account details
            }
        }
        return null;
    }

    // 🔹 Save transaction
    public static void saveTransaction(String accountNumber, String type, double amount, double balance) {
        try (PrintWriter out = new PrintWriter(new FileWriter(TRANSACTION_FILE, true))) {
            String date = getFormattedDateTime();
            String line = accountNumber + "," + date + "," + type + "," + amount + "," + balance;
            out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 🔹 Load all transactions for an account
    public static List<String[]> loadTransactions(String accountNumber) {
        List<String[]> transactions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(TRANSACTION_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts[0].equals(accountNumber)) transactions.add(parts);
            }
        } catch (IOException e) {
            // ignore if file missing
        }
        return transactions;
    }

    // 🔹 Update account balance
    public static boolean updateBalance(String accountNumber, double newBalance) {
        List<String[]> accounts = loadAccounts();
        boolean updated = false;

        try (PrintWriter out = new PrintWriter(new FileWriter(ACCOUNT_FILE))) {
            for (String[] acc : accounts) {
                if (acc[0].equals(accountNumber)) {
                    acc[1] = String.valueOf(newBalance);
                    updated = true;
                }
                out.println(String.join(",", acc));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return updated;
    }

    // 🔹 Utility: Format current date and time
    public static String getFormattedDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
