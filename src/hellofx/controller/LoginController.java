package hellofx.controller;

import hellofx.model.DataManager;

public class LoginController {

    public boolean validateTeller(String username, String password) {
        return username.trim().equalsIgnoreCase("Kopo Mokote") && password.trim().equals("987654321");
    }

    public String[] validateCustomer(String username, String password) {
        return DataManager.getAccountByCredentials(username.trim(), password.trim());
    }
}