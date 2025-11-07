package hellofx.view;

import hellofx.view.AccountView;
import hellofx.model.DataManager;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerLoginView {
    public void start(Stage stage) {
        Label title = new Label("Customer Login");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Login");
        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red;");

        VBox layout = new VBox(10, title, usernameField, passwordField, loginButton, messageLabel);
        layout.setStyle("-fx-padding: 20;");
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        Scene scene = new Scene(layout, 400, 250);
        stage.setScene(scene);
        stage.setTitle("Customer Login");
        stage.show();

        loginButton.setOnAction(e -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            String[] accountData = DataManager.getCustomerAccountByCredentials(username, password);
            if (accountData != null) {
                new AccountView(accountData).start(stage);
            } else {
                messageLabel.setText("Invalid login credentials.");
            }
        });
    }
}
