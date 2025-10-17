package hellofx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AccountController {

    @FXML
    private Label balanceLabel;

    private double balance = 1000.00; // Temporary placeholder

    @FXML
    private void handleDeposit() {
        balance += 100;
        updateBalance();
        showAlert("Deposit Successful", "Your new balance is $" + balance);
    }

    @FXML
    private void handleWithdraw() {
        if (balance >= 100) {
            balance -= 100;
            updateBalance();
            showAlert("Withdrawal Successful", "Your new balance is $" + balance);
        } else {
            showAlert("Insufficient Funds", "You don't have enough balance.");
        }
    }

    private void updateBalance() {
        balanceLabel.setText("Balance: $" + String.format("%.2f", balance));
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
