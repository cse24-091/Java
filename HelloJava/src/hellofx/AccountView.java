package hellofx;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AccountView {

    public void start(Stage stage) {
        Label title = new Label("Account Details");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label accountNumberLabel = new Label("Account Number:");
        TextField accountNumberField = new TextField("ACC12345");

        Label balanceLabel = new Label("Balance:");
        TextField balanceField = new TextField("1000.00");

        Label accountTypeLabel = new Label("Account Type:");
        ComboBox<String> accountTypeBox = new ComboBox<>();
        accountTypeBox.getItems().addAll("Investment", "Savings", "Cheque");
        accountTypeBox.setValue("Savings");

        Label branchLabel = new Label("Branch:");
        ComboBox<String> branchBox = new ComboBox<>();
        branchBox.getItems().addAll("Gaborone", "Maun", "Francistown", "Molepolole");
        branchBox.setValue("Gaborone");

        Label customerLabel = new Label("Customer ID:");
        ComboBox<String> customerBox = new ComboBox<>();
        customerBox.getItems().addAll("C001", "C002", "C003");
        customerBox.setValue("C001");

        Button insertButton = new Button("Insert");
        Button deleteButton = new Button("Delete");
        Button updateButton = new Button("Update");
        Button searchButton = new Button("Search");
        Button listButton = new Button("List");

        // Layout grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(title, 0, 0, 2, 1);
        grid.add(accountNumberLabel, 0, 1);
        grid.add(accountNumberField, 1, 1);
        grid.add(balanceLabel, 0, 2);
        grid.add(balanceField, 1, 2);
        grid.add(accountTypeLabel, 0, 3);
        grid.add(accountTypeBox, 1, 3);
        grid.add(branchLabel, 0, 4);
        grid.add(branchBox, 1, 4);
        grid.add(customerLabel, 0, 5);
        grid.add(customerBox, 1, 5);

        grid.add(insertButton, 0, 6);
        grid.add(deleteButton, 1, 6);
        grid.add(updateButton, 0, 7);
        grid.add(searchButton, 1, 7);
        grid.add(listButton, 0, 8);

        Scene scene = new Scene(grid, 400, 400);
        stage.setTitle("Account View");
        stage.setScene(scene);
        stage.show();

        // Insert button saves the data
        insertButton.setOnAction(e -> {
            String username = "User"; // temporary
            String password = "pass";
            String accountType = accountTypeBox.getValue();
            String branch = branchBox.getValue();
            String customerId = customerBox.getValue();
            double balance = Double.parseDouble(balanceField.getText());

            DataManager.saveAccount(username, password, accountType, branch, customerId, balance);
            new Alert(Alert.AlertType.INFORMATION, "Account saved successfully!").showAndWait();
        });
    }
}
