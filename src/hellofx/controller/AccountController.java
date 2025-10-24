package hellofx.controller;

import hellofx.model.DataManager;
import java.util.List;

public class AccountController {

    public boolean createAccount(String customerName, String password, String customerType, String accountType,
                                 String branch, double deposit, String taxId,
                                 String sourceOfIncome, String signatory, String idNumber) {

        // Validate password
        if (password == null || password.isBlank()) return false;

        // Validate deposit based on account type
        switch (accountType) {
            case "Savings":
                if (deposit < 50) return false;
                break;
            case "Investment":
                if (deposit < 500) return false;
                break;
            case "Cheque":
                // No minimum deposit
                break;
            default:
                return false; // Unknown account type
        }

        // Validate required fields based on customer type
        if ("Person".equalsIgnoreCase(customerType)) {
            if (sourceOfIncome == null || sourceOfIncome.isBlank()) return false;
            if (taxId == null || taxId.isBlank()) return false;
            if (idNumber == null || !idNumber.matches("\\d{8}")) return false;
        } else if ("Company".equalsIgnoreCase(customerType)) {
            if (signatory == null || signatory.isBlank()) return false;
            if (taxId == null || taxId.isBlank()) return false;
        } else {
            return false; // Invalid customer type
        }

        // Save account and customer info using DataManager
        DataManager.saveAccount(customerName, password, customerType, accountType, branch, deposit,
                                taxId, sourceOfIncome, signatory, idNumber);

        return true;
    }

    public List<String[]> getAllAccounts() {
        return DataManager.loadAccounts();
    }

    public List<String[]> getAllCustomers() {
        return DataManager.loadCustomers();
    }

    public void deleteAccount(String accountNumber) {
        DataManager.deleteAccount(accountNumber);
    }

    public List<String[]> getTransactions(String accountNumber) {
        return DataManager.loadTransactions(accountNumber);
    }
}