package boundary;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class LoginView {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label messageLabel;

    private Controller controller = new Controller();

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (controller.authenticate(username, password)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("account.fxml"));
                Scene scene = new Scene(loader.load());

                // Get AccountView controller
                AccountView accountView = loader.getController();
                accountView.displayAccount(controller.getAccountDetails(username));

                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Bank Account");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            messageLabel.setText("Invalid login. Try again.");
        }
    }
}
