package hellofx.view;

import hellofx.controller.AccountController;
import hellofx.controller.LoginController;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TellerLoginView {

    private final LoginController loginController = new LoginController();

    public void start(Stage stage) {
        Label title = new Label("Bank Teller Login");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Login");
        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red;");

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (loginController.validateTeller(username, password)) {
                new TellerDashboardView().start(stage);
            } else {
                messageLabel.setText("Invalid teller credentials.");
            }
        });

        VBox layout = new VBox(10, title, usernameField, passwordField, loginButton, messageLabel);
        layout.setStyle("-fx-padding: 20;");
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        Scene scene = new Scene(layout, 400, 250);
        stage.setTitle("Teller Login");
        stage.setScene(scene);
        stage.show();
    }
}