package boundary;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class AccountView {

    @FXML
    private Label nameLabel;
    @FXML
    private Label accountNumberLabel;
    @FXML
    private Label accountTypeLabel;
    @FXML
    private Label balanceLabel;

    public void displayAccount(Account account) {
        nameLabel.setText("Name: " + account.getOwnerName());
        accountNumberLabel.setText("Account Number: " + account.getAccountNumber());
        accountTypeLabel.setText("Account Type: " + account.getAccountType());
        balanceLabel.setText("Balance: $" + String.format("%.2f", account.getBalance()));
    }

    @FXML
    private void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) nameLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Banking System - Login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
