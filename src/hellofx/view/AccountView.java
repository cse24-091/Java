package hellofx.view;

import hellofx.model.DataManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AccountView {
    private final String[] accountData;
    private ObservableList<String[]> transactionData;

    public AccountView(String[] accountData) {
        this.accountData = accountData;
    }

    public void start(Stage stage) {
        String accountNumber = accountData[0];
        String balance = accountData[1];
        String accountType = accountData[2];
        String branch = accountData[3];
        String customerID = accountData[4];

        String customerName = "Customer";
        for (String[] c : DataManager.loadCustomers()) {
            if (c[0].equals(customerID)) {
                customerName = c[1];
                break;
            }
        }

        Label title = new Label("Welcome, " + customerName + "!");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: green;");

        TextField accountNumberField = new TextField(accountNumber);
        TextField balanceField = new TextField(balance);
        TextField accountTypeField = new TextField(accountType);
        TextField branchField = new TextField(branch);
        TextField customerIdField = new TextField(customerID);

        for (TextField tf : new TextField[]{accountNumberField, balanceField, accountTypeField, branchField, customerIdField}) {
            tf.setEditable(false);
            tf.setStyle("-fx-background-color: #f5f5f5; -fx-border-color: #ddd;");
        }

        TextField amountField = new TextField();
        amountField.setPromptText("Enter amount");

        Button depositBtn = new Button("Deposit");
        Button withdrawBtn = new Button("Withdraw");
        depositBtn.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-weight: bold;");
        withdrawBtn.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;");

        depositBtn.setOnAction(e -> handleTransaction("Deposit", balanceField, amountField, accountNumber));
        withdrawBtn.setOnAction(e -> handleTransaction("Withdraw", balanceField, amountField, accountNumber));

        transactionData = FXCollections.observableArrayList(DataManager.loadTransactions(accountNumber));
        TableView<String[]> transactionTable = createTransactionTable(transactionData);

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.addRow(0, new Label("Account Number:"), accountNumberField);
        grid.addRow(1, new Label("Balance:"), balanceField);
        grid.addRow(2, new Label("Account Type:"), accountTypeField);
        grid.addRow(3, new Label("Branch:"), branchField);
        grid.addRow(4, new Label("Customer ID:"), customerIdField);
        grid.addRow(5, new Label("Amount:"), amountField);

        HBox buttons = new HBox(15, depositBtn, withdrawBtn);
        buttons.setAlignment(Pos.CENTER_RIGHT);
        grid.add(buttons, 1, 6);

        VBox layout = new VBox(20, title, grid, new Label("Transaction History:"), transactionTable);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: white;");
        layout.setAlignment(Pos.TOP_CENTER);

        stage.setScene(new Scene(layout, 700, 600));
        stage.setTitle("Account Dashboard");
        stage.show();
    }

    private TableView<String[]> createTransactionTable(ObservableList<String[]> data) {
        TableView<String[]> table = new TableView<>(data);
        String[] headers = {"Date", "Type", "Amount", "Balance"};

        for (int i = 1; i <= 4; i++) {
            final int idx = i;
            TableColumn<String[], String> col = new TableColumn<>(headers[i - 1]);
            col.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue()[idx]));
            table.getColumns().add(col);
        }
        table.setPrefHeight(200);
        return table;
    }

    private void handleTransaction(String type, TextField balanceField, TextField amountField, String accountNumber) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            double balance = Double.parseDouble(balanceField.getText());
            double newBalance = type.equals("Deposit") ? balance + amount : balance - amount;

            if (type.equals("Withdraw") && amount > balance) {
                new Alert(Alert.AlertType.ERROR, "Insufficient funds!").showAndWait();
                return;
            }

            DataManager.updateBalance(accountNumber, newBalance);
            DataManager.saveTransaction(accountNumber, type, amount, newBalance);
            balanceField.setText(String.valueOf(newBalance));
            transactionData.add(new String[]{accountNumber, DataManager.getFormattedDateTime(), type, String.valueOf(amount), String.valueOf(newBalance)});
            amountField.clear();
            new Alert(Alert.AlertType.INFORMATION, type + " successful!").showAndWait();
        } catch (NumberFormatException ex) {
            new Alert(Alert.AlertType.ERROR, "Invalid amount.").showAndWait();
        }
    }
}
