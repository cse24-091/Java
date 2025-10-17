package hellofx;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AccountView {

    private Label balanceLabel;

    public void start(Stage stage) {
        Label title = new Label("Welcome to Your Account");
        title.setStyle("-fx-font-size: 18px;");

        balanceLabel = new Label("Balance: $");
        balanceLabel.setStyle("-fx-font-size: 14px;");

        Button depositButton = new Button("Deposit");
        Button withdrawButton = new Button("Withdraw");

        VBox layout = new VBox(15, title, balanceLabel, depositButton, withdrawButton);
        layout.setStyle("-fx-padding: 20;");
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        Scene scene = new Scene(layout, 600, 400);
        stage.setTitle("Account Overview");
        stage.setScene(scene);
        stage.show();

        depositButton.setOnAction(e -> balanceLabel.setText("Deposit clicked"));
        withdrawButton.setOnAction(e -> balanceLabel.setText("Withdraw clicked"));
    }
}
