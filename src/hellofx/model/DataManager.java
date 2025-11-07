package hellofx.model;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataManager {
    private static final String ACCOUNT_FILE = "accounts.txt";
    private static final String CUSTOMER_FILE = "customers.txt";
    private static final String TRANSACTION_FILE = "transactions.txt";

    public static void saveAccount(String customerName, String password, String customerType, String accountType, String branch, double balance, String taxId, String sourceOfIncome, String signatory, String idNumber) {
        Random rand = new Random();
        String accountNumber = "AC" + (1000 + rand.nextInt(9000));
        String customerID = "CU" + (1000 + rand.nextInt(9000));
        String createdDate = getFormattedDateTime();

        try (PrintWriter out = new PrintWriter(new FileWriter(CUSTOMER_FILE, true))) {
            String line = String.join(",", Arrays.asList(customerID, customerName, password, customerType, taxId, sourceOfIncome, signatory, idNumber, createdDate, accountNumber));
            out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new FileWriter(ACCOUNT_FILE, true))) {
            String line = String.join(",", Arrays.asList(accountNumber, String.valueOf(balance), accountType, branch, customerID));
            out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String[]> loadCustomers() {
        List<String[]> customers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CUSTOMER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                customers.add(line.split(",", -1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public static List<String[]> loadAccounts() {
        List<String[]> accounts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ACCOUNT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                accounts.add(line.split(",", -1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accounts;
    }

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

        if (customer == null || customer.length < 10) return null;

        String accountNumber = customer[9];
        for (String[] acc : loadAccounts()) {
            if (acc[0].equals(accountNumber)) {
                return acc;
            }
        }
        return null;
    }

    public static void saveTransaction(String accountNumber, String type, double amount, double balance) {
        try (PrintWriter out = new PrintWriter(new FileWriter(TRANSACTION_FILE, true))) {
            String date = getFormattedDateTime();
            String line = accountNumber + "," + date + "," + type + "," + amount + "," + balance;
            out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String[]> loadTransactions(String accountNumber) {
        List<String[]> transactions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(TRANSACTION_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts[0].equals(accountNumber)) {
                    transactions.add(parts);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }

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

    public static String getFormattedDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    // ✅ NEW METHOD: getAccountByCredentials
    public static String[] getAccountByCredentials(String username, String password) {
        return getCustomerAccountByCredentials(username, password);
    }

    // ✅ NEW METHOD: deleteAccount
    public static void deleteAccount(String accountNumber) {
        List<String[]> accounts = loadAccounts();
        try (PrintWriter out = new PrintWriter(new FileWriter(ACCOUNT_FILE))) {
            for (String[] acc : accounts) {
                if (!acc[0].equals(accountNumber)) {
                    out.println(String.join(",", acc));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
