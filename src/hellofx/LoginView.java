package hellofx;

import hellofx.model.DataManager;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {

    private TextField usernameField;
    private PasswordField passwordField;
    private Label messageLabel;

    public void start(Stage stage) {
        Label title = new Label("Banking System Login");
        title.setStyle("-fx-font-size: 18px;");

        usernameField = new TextField();
        usernameField.setPromptText("Username");

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Login");
        messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red;");

        VBox layout = new VBox(10, title, usernameField, passwordField, loginButton, messageLabel);
        layout.setStyle("-fx-padding: 20;");
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        Scene scene = new Scene(layout, 400, 250);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (!username.isEmpty() && !password.isEmpty()) {
                String[] accountData = DataManager.getAccountByCredentials(username, password);
                if (accountData != null) {
                    new AccountView(accountData).start(stage);
                } else {
                    messageLabel.setText("Invalid username or password.");
                }
            } else {
                messageLabel.setText("Please enter username and password.");
            }
        });
    }
}