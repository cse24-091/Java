package hellofx.view;

import hellofx.controller.AccountController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TellerDashboardView {

    private final AccountController accountController = new AccountController();

    public void start(Stage stage) {
        Label title = new Label("Teller Account Creation");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField customerNameField = new TextField();
        customerNameField.setPromptText("Customer Name");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        ComboBox<String> customerTypeBox = new ComboBox<>();
        customerTypeBox.setItems(FXCollections.observableArrayList("Person", "Company"));
        customerTypeBox.setPromptText("Customer Type");

        ComboBox<String> accountTypeBox = new ComboBox<>();
        accountTypeBox.setItems(FXCollections.observableArrayList("Savings", "Investment", "Cheque"));
        accountTypeBox.setPromptText("Account Type");

        ComboBox<String> branchBox = new ComboBox<>();
        branchBox.setItems(FXCollections.observableArrayList("Gaborone", "Francistown", "Maun", "Orapa"));
        branchBox.setPromptText("Branch");

        TextField depositField = new TextField();
        depositField.setPromptText("Initial Deposit");

        TextField sourceOfIncomeField = new TextField();
        sourceOfIncomeField.setPromptText("Source of Income");

        TextField signatoryField = new TextField();
        signatoryField.setPromptText("Signatory");

        TextField taxIdField = new TextField();
        taxIdField.setPromptText("Tax ID");

        TextField idNumberField = new TextField();
        idNumberField.setPromptText("ID Number (8 digits)");

        VBox dynamicFields = new VBox(5);

        customerTypeBox.setOnAction(e -> {
            dynamicFields.getChildren().clear();
            String type = customerTypeBox.getValue();
            if ("Person".equals(type)) {
                dynamicFields.getChildren().addAll(sourceOfIncomeField, taxIdField, idNumberField);
            } else if ("Company".equals(type)) {
                dynamicFields.getChildren().addAll(signatoryField, taxIdField);
            }
        });

        Button createButton = new Button("Create Account");
        Label messageLabel = new Label();

        createButton.setOnAction(e -> {
            String customerName = customerNameField.getText().trim();
            String password = passwordField.getText().trim();
            String customerType = customerTypeBox.getValue();
            String accountType = accountTypeBox.getValue();
            String branch = branchBox.getValue();
            String depositText = depositField.getText().trim();
            double deposit = depositText.isEmpty() ? 0 : Double.parseDouble(depositText);
            String taxId = taxIdField.getText().trim();
            String sourceOfIncome = sourceOfIncomeField.getText().trim();
            String signatory = signatoryField.getText().trim();
            String idNumber = idNumberField.getText().trim();

            if (customerName.isEmpty() || password.isEmpty() || accountType == null || branch == null) {
                messageLabel.setText("All fields are required.");
                return;
            }

            boolean validDeposit = switch (accountType) {
                case "Savings" -> deposit >= 50;
                case "Investment" -> deposit >= 500;
                case "Cheque" -> true;
                default -> false;
            };

            boolean validId = !"Company".equals(customerType) && idNumber.matches("\\d{8}");

            if (!validDeposit) {
                messageLabel.setText("Deposit does not meet minimum requirement.");
            } else if ("Person".equals(customerType) && !validId) {
                messageLabel.setText("ID Number must be exactly 8 digits.");
            } else {
                boolean success = accountController.createAccount(
                        customerName, password, customerType, accountType,
                        branch, deposit, taxId, sourceOfIncome, signatory, idNumber
                );
                messageLabel.setText(success ? "mAccount created successfully!" : "Account creation failed.");
            }
        });

        VBox layout = new VBox(10, title, customerNameField, passwordField,
                customerTypeBox, dynamicFields, accountTypeBox, branchBox,
                depositField, createButton, messageLabel);

        layout.setStyle("-fx-padding: 20;");
        Scene scene = new Scene(layout, 500, 650);
        stage.setScene(scene);
        stage.show();
    }
}
